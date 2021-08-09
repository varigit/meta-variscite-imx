#!/bin/sh

[ -x /etc/wifi/variscite-wifi ] || exit 0

case $1 in

"suspend")
        /etc/wifi/variscite-wifi stop
        ;;
"resume")
        /etc/wifi/variscite-wifi start
        ;;
esac
