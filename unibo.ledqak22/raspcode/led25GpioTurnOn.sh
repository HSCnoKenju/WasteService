#!/bin/bash
# -------------------------------------------------------------
# led25GpioTurnOn.sh
# Key-point: we can manage a device connected on a GPIO pin by
# using the GPIO shell library. 
# The pin 25 is physical 22 and Wpi 6.
#	sudo bash led25GpioTurnOn.sh
# -------------------------------------------------------------

## kill running blinks if any
kill $(ps aux | grep '[l]ed25GpioBlink.sh' | awk '{print $2}')
gpio mode 6 out #
gpio write 6 1 #