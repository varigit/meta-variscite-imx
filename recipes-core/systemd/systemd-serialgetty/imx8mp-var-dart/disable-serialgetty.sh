#!/bin/sh

UART0_SERVICE=serial-getty@ttymxc0.service
UART1_SERVICE=serial-getty@ttymxc1.service

tty_service_enabled()
{
	test -f /etc/systemd/system/getty.target.wants/$1
}

som_is_var_som_mx8mp()
{
	grep -q VAR-SOM-MX8M-PLUS /sys/devices/soc0/machine
}

som_is_dart_mx8mp()
{
        grep -q DART-MX8M-PLUS /sys/devices/soc0/machine
}

if som_is_var_som_mx8mp && tty_service_enabled ${UART0_SERVICE}; then
	systemctl stop ${UART0_SERVICE}
	systemctl disable ${UART0_SERVICE}
	systemctl enable ${UART1_SERVICE}
elif som_is_dart_mx8mp && tty_service_enabled ${UART1_SERVICE}; then
	systemctl stop ${UART1_SERVICE}
	systemctl disable ${UART1_SERVICE}
	systemctl enable ${UART0_SERVICE}
fi
