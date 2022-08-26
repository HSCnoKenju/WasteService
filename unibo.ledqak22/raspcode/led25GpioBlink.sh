#!/bin/bash


UNIQUE=1

echo "mypid" $$

## run the blinking only if the link is NOT ALREADY blinking
for pid in $(pidof -x $0); do
        echo "pid" $pid
  if [ $pid != $$ ]; then
          UNIQUE=0
  fi
 done

echo "unique" $UNIQUE

if [ $UNIQUE = 1 ] ; then

        echo "haha"
        gpio mode 6 out #

        while [ 1 ]; do

                gpio write 6 1 #
                sleep 1
                gpio write 6 0 #
                sleep 1
        done

else
        echo "already blinking"
fi
