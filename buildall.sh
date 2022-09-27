#!/bin/sh

## gnome-terminal --title=Basicrobot -- sh -c 'cd BasicRobot/ ;gradle run'


## gnome-terminal --title=Wenv -- sh -c 'cd BasicRobot ; docker-compose -f virtualRobotOnly4.0.yaml up'
gnome-terminal --title=Basicrobot -- sh -c 'cd BasicRobot ; ./gradlew build; read ONE' 
#gnome-terminal --title=Sonar -- sh -c 'cd unibo.sonarqak22 ; ./gradlew build ; read ONE'
#gnome-terminal --title=Led -- sh -c 'cd unibo.ledqak22 ; ./gradlew build ; read ONE'
gnome-terminal --title=Coreservice -- sh -c 'cd CoreWasteService ; ./gradlew build -x test; read ONE'
gnome-terminal --title=Pathplanner -- sh -c 'cd PathPlanner ; ./gradlew build; read ONE'
gnome-terminal --title=StatusGui -- sh -c 'cd statusgui; ./gradlew build ; read ONE'
gnome-terminal --title=TruckSimulator -- sh -c 'cd TruckSimulator ; ./gradlew build ; read ONE'

gnome-terminal --title=PathExec -- sh -c 'cd PathExecutorInterrupt ; ./gradlew build; read ONE'
