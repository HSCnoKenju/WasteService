#!/bin/bash

MYPID=$$

## kill other blinks except myself
kill $(ps aux | grep '[l]ed25GpioBlink.sh' | awk '{print $2}'| grep -v $MYPID)

gpio mode 6 out #

while [ 1 ]; do
  gpio write 6 1 #
  sleep 1
  gpio write 6 0 #
  sleep 1
done