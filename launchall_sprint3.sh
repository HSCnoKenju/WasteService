#!/bin/sh

## gnome-terminal --title=Basicrobot -- sh -c 'cd BasicRobot/ ;gradle run'


gnome-terminal --title=Wenv -- sh -c 'cd BasicRobot ; docker-compose -f virtualRobotOnly4.0.yaml up'
gnome-terminal --title=Basicrobot -- sh -c 'cd BasicRobot ; ./gradlew run; read ONE' 
##gnome-terminal --title=Sonar -- sh -c 'cd unibo.sonarqak22 ; ./gradlew run ; read ONE'
gnome-terminal --title=Led -- sh -c 'cd unibo.ledqak22 ; ./gradlew run ; read ONE'
gnome-terminal --title=Coreservice -- sh -c 'cd CoreWasteService ; ./gradlew run ; read ONE'
gnome-terminal --title=Pathplanner -- sh -c 'cd PathPlanner ; ./gradlew run; read ONE'
gnome-terminal --title=StatusGui -- sh -c 'cd statusgui; ./gradlew bootRun ; read ONE'
gnome-terminal --title=TruckSimulator -- sh -c 'cd TruckSimulator ; ./gradlew -q tui ; read ONE'

gnome-terminal --title=PathExec -- sh -c 'cd PathExecutorInterrupt ; ./gradlew run; read ONE'
