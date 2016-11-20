#!/bin/sh

som=var-dart-6ul
tuple_num=0x80
firmware=/lib/firmware/bcm/bcm43430a1.hcd

function gen_bluez_conf {
cat > /etc/bluetooth/main.conf << EOF
[General]
Name = $1
EOF
	sync
}

function max() {
  printf "%s\n" "$@" | sort -g -r | head -n1
}

[ -d /etc/bluetooth/main.conf ] || gen_bluez_conf $som


if [ "$som" = "var-dart-6ul" ]; then
  gpio_num=132
  tty_dev=ttymxc1
elif [ "$som" = "var-som-mx7" ]; then
  gpio_num=14
  tty_dev=ttymxc2
else
  exit 1
fi

# Check if BT/WIFI is up
if dmesg | grep -q "tuple $tuple_num"; then
  echo "BT found"
fi

echo $gpio_num >/sys/class/gpio/export
echo "out" > /sys/class/gpio/gpio${gpio_num}/direction
echo 0 > /sys/class/gpio/gpio${gpio_num}/value
sleep 1
echo 1 > /sys/class/gpio/gpio${gpio_num}/value


eth0_addr=$(cat /sys/class/net/eth0/address | sed 's/\://g')
eth1_addr=$(cat /sys/class/net/eth1/address | sed 's/\://g')
bd_addr_temp=$(max $eth0_addr $eth1_addr)
bd_addr_temp=$((0x$bd_addr_temp+1))
bd_addr_temp=$(printf '%012X' $bd_addr_temp)
bd_addr=$(echo $bd_addr_temp | sed 's/\(..\)/\1:/g;s/:$//')

brcm_patchram_plus \
   --patchram ${firmware} \
   --enable_hci \
   --bd_addr ${bd_addr} \
   --no2bytes \
   --tosleep 1000 \
   /dev/${tty_dev} > /dev/null &

echo $! > /run/brcm_patchram_plus.pid
