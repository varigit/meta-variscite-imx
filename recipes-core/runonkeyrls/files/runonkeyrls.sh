#!/bin/sh

devfiles=`ls -1 /dev/input/by-path/*:snvs-powerkey-event 2> /dev/null | wc -l`
if [ $devfiles -eq 1 ]; then
  KEY_POWER=116
  /usr/bin/runonkeyrls /dev/input/by-path/*:snvs-powerkey-event $KEY_POWER 'shutdown -h -P now' &
fi

