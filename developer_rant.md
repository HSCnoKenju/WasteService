> COAP_reseurce observe assieme a Junit test

Junit test con una fonte osservabile posso solo fare get sincrone, altrimenti mi chiama assert prima di ottenere il valore interessante


> qak ed import

Essendo autogenerato, ogni volta che genera mi cancella gli "import custom"

Workaround: creare kotlin object nelle dir resources che funzionano da wrapper, chiamabili all'interno dei qak senza import

> step e steptime

Se si guarda dentro a BasicRobot/resources/robot.support, si fa uno step lungo (1000), si aspetta steptime e poi si ferma
* ha detto a lezione che serve per uniformare con il robot fisico

> qak e tuprolog

Non posso fare Termini prolog a mo' di lista ma solo predefiniti, quindi per estensioni tipo altri container oltre Glass e Plastic devo superare il modello

> basicrobot aggiornamenti

Non funziona con il gradle build nuovo, sia per dipendenze che per conflitto versione java e kotlin

> log4j 

Perchè sempre warning lol

log4j:WARN No appenders could be found for logger (org.eclipse.californium.elements.config.Configuration).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
