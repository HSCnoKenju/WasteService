#!/bin/sh

## gnome-terminal --title=Basicrobot -- sh -c 'cd BasicRobot/ ;gradle run'


gnome-terminal --title=Wenv -- sh -c 'cd BasicRobot ; docker-compose -f virtualRobotOnly4.0.yaml up'
gnome-terminal --title=Basicrobot -- sh -c 'cd BasicRobot ; ./gradlew run; read ONE' 
gnome-terminal --title=SonarLed -- sh -c 'cd AlarmRPI ; ./gradlew run ; read ONE'
gnome-terminal --title=PathExec -- sh -c 'cd PathExecutorInterrupt ; ./gradlew run; read ONE'
gnome-terminal --title=Pathplanner -- sh -c 'cd PathPlanner ; ./gradlew run; read ONE'
gnome-terminal --title=Coreservice -- sh -c 'cd CoreWasteService ; ./gradlew run ; read ONE'

