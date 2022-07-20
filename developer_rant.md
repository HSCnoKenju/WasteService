> COAP_reseurce observe assieme a Junit test

Junit test con una fonte osservabile posso solo fare get sincrone, altrimenti mi chiama assert prima di ottenere il valore interessante


> qak ed import

Essendo autogenerato, ogni volta che genera mi cancella gli "import custom"

Workaround: creare kotlin object nelle dir resources che funzionano da wrapper, chiamabili all'interno dei qak senza import


> qak ed ordine dei contesti

L'ultimo contesto deve essere quello del localhost, altrimenti non avvia il contesto locale per qualche motivo oscuro

>
    Context ctxa    ip [host="127.0.0.1" port=8049]
    Context ctxb    ip [host="localhost" port=8078]

E NON

>

    Context ctxb    ip [host="localhost" port=8078]
    Context ctxa    ip [host="127.0.0.1" port=8049]

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


> qak e nomi di qactor

Se un attore ha bisogno di usare WhenTime, non usare caratteri speciali nel nome del QActor (Es. NON a_b, corretto -> ab ) perchè non genera l'attore di supporto che gli dà il messaggio di terminazione


> tuprolog e caratteri speciali

Mi è capitato che dovevo mettere una stringa speciale nel payload (Formattazione di default di java.sql.TIMESTAMP), penso per la natura di Prolog non legge bene lo spazio oppure il punto o altri caratteri speciali


> wenv e delay

gli step di wenv (tramite il robotsupport) funzionano che al comando gli invio un cmd(w) e dopo stepTime gli invio un halt, se tra un cmd il successivo non passa abbastanza tempo, "si mangia" il tratto che deve ancora fare

Es. step(300) ; step(300); ma in mezzo non c'è delay, magari il primo step fa solo 100 e poi arriva subito quello dopo, quindi un totale di step(400)

Aggiungere un CommUtil.delay adeguatamente lungo 