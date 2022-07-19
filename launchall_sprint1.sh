#!/bin/sh

## gnome-terminal --title=Basicrobot -- sh -c 'cd BasicRobot/ ;gradle run'


gnome-terminal --title=Wenv -- sh -c 'cd BasicRobot ; docker-compose -f virtualRobotOnly4.0.yaml up'
gnome-terminal --title=Basicrobot -- sh -c 'cd BasicRobot ; ./gradlew run' 
gnome-terminal --title=Pathplanner -- sh -c 'cd PathPlanner ; ./gradlew run'
gnome-terminal --title=Coreservice -- sh -c 'cd CoreWasteService ; ./gradlew run'

