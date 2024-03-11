# Return true if board is VAR-SOM-MX8M-PLUS
board_is_var_som_mx8m_plus()
{
	grep -q VAR-SOM-MX8M-PLUS /sys/devices/soc0/machine
}

# Configure VAR-SOM-MX8M-PLUS WIFI/BT pins
config_pins()
{
	if board_is_var_som_mx8m_plus; then
		WIFI_PWR_GPIO=${WIFI_PWR_GPIO_SOM}
		WIFI_EN_GPIO=${WIFI_EN_GPIO_SOM}
		BT_BUF_GPIO=${BT_BUF_GPIO_SOM}
		BT_EN_GPIO=${BT_EN_GPIO_SOM}
	fi
}

# Setup WIFI control GPIOs
wifi_pre_up()
{
	# Return if wifi is iw61x, which is managed by the kernel
	wifi_is_bcm43xx || return

	# Configure WIFI/BT pins
	config_pins

	if [ ! -d /sys/class/gpio/gpio${WIFI_PWR_GPIO} ]; then
		echo ${WIFI_PWR_GPIO} > /sys/class/gpio/export
		echo out > /sys/class/gpio/gpio${WIFI_PWR_GPIO}/direction
	fi

	if [ ! -d /sys/class/gpio/gpio${WIFI_EN_GPIO} ]; then
		echo ${WIFI_EN_GPIO} > /sys/class/gpio/export
		echo out > /sys/class/gpio/gpio${WIFI_EN_GPIO}/direction
	fi

	if [ ! -d /sys/class/gpio/gpio${BT_BUF_GPIO} ]; then
		echo ${BT_BUF_GPIO} > /sys/class/gpio/export
		echo out > /sys/class/gpio/gpio${BT_BUF_GPIO}/direction
	fi

	if [ ! -d /sys/class/gpio/gpio${BT_EN_GPIO} ]; then
		echo ${BT_EN_GPIO} > /sys/class/gpio/export
		echo out > /sys/class/gpio/gpio${BT_EN_GPIO}/direction
	fi
}

# Get the SOM revision
get_somrev() {
	# Get the raw output
	raw_output=$(i2cget -f -y 0x0 0x52 0x1e)

	# Convert the output to decimal
	decimal_output=$(( $raw_output ))

	# Extract major and minor versions
	major=$(( ($decimal_output & 0xE0) >> 5 ))
	minor=$(( $decimal_output & 0x1F ))

	# Adjust the major version as per the specification
	major=$(( $major + 1 ))

	echo "$major.$minor"
}

# Check if wifi is bcm43xx
wifi_is_bcm43xx() {
	somrev=$(get_somrev)

	if [ "$(echo "$somrev < 2.0" | bc)" -eq 1 ]; then
		return 0
	else
		return 1
	fi
}

# Function to disable a network interface
disable_network_interface() {
	local iface="$1"

	# Check if the interface exists
	if ip link show "$iface" &>/dev/null; then
		ip link set dev "$iface" down
	fi
}

wifi_up_iw61x()
{
	modprobe moal mod_para=nxp/var_wifi_mod_para.conf
}

wifi_up_bcm43xx()
{
	# Configure WIFI/BT pins
	config_pins

	# Unbind WIFI device from MMC controller
	if [ -e /sys/bus/platform/drivers/sdhci-esdhc-imx/${WIFI_MMC_HOST} ]; then
		echo ${WIFI_MMC_HOST} > /sys/bus/platform/drivers/sdhci-esdhc-imx/unbind
	fi

	# WIFI_PWR up
	echo 1 > /sys/class/gpio/gpio${WIFI_PWR_GPIO}/value
	usleep 10000

	# WLAN_EN up
	echo 1 > /sys/class/gpio/gpio${WIFI_EN_GPIO}/value

	# BT_EN up
	echo 1 > /sys/class/gpio/gpio${BT_EN_GPIO}/value

	# BT_BUF up
	echo 0 > /sys/class/gpio/gpio${BT_BUF_GPIO}/value

	# Wait at least 150ms
	usleep 200000

	# BT_BUF down
	echo 1 > /sys/class/gpio/gpio${BT_BUF_GPIO}/value

	# BT_EN down
	echo 0 > /sys/class/gpio/gpio${BT_EN_GPIO}/value

	# Bind WIFI device to MMC controller
	echo ${WIFI_MMC_HOST} > /sys/bus/platform/drivers/sdhci-esdhc-imx/bind

	# Load WIFI driver
	modprobe brcmfmac
}

# Power up WIFI chip
wifi_up()
{
	if wifi_is_bcm43xx; then
		wifi_up_bcm43xx
	else
		wifi_up_iw61x
	fi
}

wifi_down_iw61x()
{
	disable_network_interface wlan0
	disable_network_interface uap0
	disable_network_interface wfd0
	modprobe -r moal;
}

wifi_down_bcm43xx()
{
	# Configure WIFI/BT pins
	config_pins

	# Unload WIFI driver
	modprobe -r brcmfmac

	# Unbind WIFI device from MMC controller
	if [ -e /sys/bus/platform/drivers/sdhci-esdhc-imx/${WIFI_MMC_HOST} ]; then
		echo ${WIFI_MMC_HOST} > /sys/bus/platform/drivers/sdhci-esdhc-imx/unbind
	fi

	# WIFI_EN down
	echo 0 > /sys/class/gpio/gpio${WIFI_EN_GPIO}/value

	# BT_BUF down
	echo 1 > /sys/class/gpio/gpio${BT_BUF_GPIO}/value

	# BT_EN down
	echo 0 > /sys/class/gpio/gpio${BT_EN_GPIO}/value
	usleep 10000

	# WIFI_PWR down
	echo 0 > /sys/class/gpio/gpio${WIFI_PWR_GPIO}/value
}

# Power down WIFI chip
wifi_down()
{
	if wifi_is_bcm43xx; then
		wifi_down_bcm43xx
	else
		wifi_down_iw61x
	fi
}

# Return true if SOM has WIFI module assembled
wifi_is_available()
{
	# Read SOM options EEPROM field
	opt=$(i2cget -f -y 0x0 0x52 0x20)

	# Check WIFI bit in SOM options
	if [ $((opt & 0x1)) -eq 1 ]; then
		return 0
	else
		return 1
	fi
}

# Return true if WIFI should be started
wifi_should_not_be_started()
{
	# Do not start WIFI if it is not available
	if ! wifi_is_available; then
		return 0
	fi

	# Do not start WIFI if it is already started
	[ -d /sys/class/net/wlan0 ] && return 0

	return 1
}

# Return true if WIFI should not be stopped
wifi_should_not_be_stopped()
{
	# Do not stop WIFI if it is not available
	if ! wifi_is_available; then
		return 0
	fi

	return 1
}
