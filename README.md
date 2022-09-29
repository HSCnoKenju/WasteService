# WasteService
University of Bologna, software engineering final theme project.


preview analisi e progettazione

https://htmlpreview.github.io/?https://github.com/HSCnoKenju/WasteService/blob/main/presentation/WasteServiceAnalysis_v_6.html



Last presentation:

presentation/WasteServiceAnalysis_6.html


# ESECUZIONE CENTRALIZZATO

> CoreWasteService/src/it/unibo/thresholdchecker/Thresholdchecker.kt

* riga 28, verificare che sia il seguente
    * CoapObserverSupport(myself, "127.0.0.1","8061","ctxsonarqak22","sonarqak22")

> statusgui/src/main/resources/application.properties

* riga 9, verificare che sia il seguente
    * waste.resources.ledstate    = 127.0.0.1:8065:ctxledqak22/ledqak22



> CoreWasteService/waste_service.pl

* riga 6
    * context(ctxledqak22, "127.0.0.1",  "TCP", "8065").
* riga 7
    * context(ctxsonarqak22, "127.0.0.1",  "TCP", "8061").


> building 

Eseguire lo script
* buildall.sh

> esecuzione

Eseguire lo script
* launchall_sprint3.sh
* ! ATTENZIONE, apre 7 terminali per poter monitorare i singoli componenti

## UTILIZZO

Una volta attivato, aprire un browser
* localhost:8090, visualizzare il robot virtuale
* localhost:8084, visualizzare WasteServiceStatusGui

Tra i terminali aperti dallo script, individuare quello nominato ***TruckSimulator***
* una Textual User Interface per poter inviare richieste, simula l'interazione da parte di un furgone di rifiuti


# ESECUZIONE DISTRIBUITA


> CoreWasteService/src/it/unibo/thresholdchecker/Thresholdchecker.kt

* riga 28, verificare che sia il seguente
    * CoapObserverSupport(myself, "\[ip_raspberry\]","8061","ctxsonarqak22","sonarqak22")

> statusgui/src/main/resources/application.properties

* riga 9, verificare che sia il seguente
    * waste.resources.ledstate    = \[ip_raspberry\]:8065:ctxledqak22/ledqak22



> CoreWasteService/waste_service.pl

* riga 6
    * context(ctxledqak22, "\[ip_raspberry\]",  "TCP", "8065").
* riga 7
    * context(ctxsonarqak22, "\[ip_raspberry\]",  "TCP", "8061").


> building 

Eseguire lo script
* buildall.sh




> distribuzione Led sul raspberry

    cd unibo.ledqak22

    # creazione zip
    ./gradlew distZip 
    
    # verificare che sia stato creato correttamente lo zip
    ls build/distributions 

    # spostare lo zip sul raspberry
    scp unibo.ledqak22/build/distributions/unibo.ledqak22-1.0.zip pi@\[ip_raspberry\]:/home/pi

    # login sul raspiberry
    ssh pi@\[ip_raspberry\]

    ----- da qui in poi sono sul raspberry

    # dearchivio lo zip
    unzip unibo.ledqak22-1.0.zip

    cd unibo.ledqak22-1.0

    # sposto i file interessanti nella directory root del programma
    mv *.pl *.sh bin/

    cd bin/

    # rendo eseguibile gli script
    chmod +x *.sh

    # esecuzione led
    ./unibo.ledqak22


> distribuzione Sonar sul raspberry

    cd unibo.sonarqak22

    # creazione zip
    ./gradlew distZip 
    
    # verificare che sia stato creato correttamente lo zip
    ls build/distributions 

    # spostare lo zip sul raspberry
    scp unibo.ledsonar22/build/distributions/unibo.sonarqak22-1.0.zip pi@\[ip_raspberry\]:/home/pi

    # login sul raspiberry
    ssh pi@\[ip_raspberry\]

    ----- da qui in poi sono sul raspberry

    # dearchivio lo zip
    unzip unibo.sonarqak22-1.0.zip

    cd unibo.sonarqak22-1.0

    # compilo SonarAlone.c
     * in the directory: of SonarAlone.c:
    1)  [ sudo ../../pi-blaster/pi-blaster ] if servo
    2)  g++  SonarAlone.c -l wiringPi -o  SonarAlone
 

    # sposto i file interessanti nella directory root del programma
    mv *.pl SonarAlone bin/

    cd bin/

    # esecuzione sonar
    ./unibo.sonarqak22


> sul PC host, attivare il resto del sistema

Eseguire lo script
* distribuited_test.sh
* ! ATTENZIONE, apre 7 terminali per poter monitorare i singoli componenti

## UTILIZZO

Una volta attivato, aprire un browser
* localhost:8090, visualizzare il robot virtuale
* localhost:8084, visualizzare WasteServiceStatusGui

Tra i terminali aperti dallo script, individuare quello nominato ***TruckSimulator***
* una Textual User Interface per poter inviare richieste, simula l'interazione da parte di un furgone di rifiuti
