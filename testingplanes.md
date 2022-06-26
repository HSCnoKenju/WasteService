
## PROBLEMA 1 

ad *Waste_Service* viene comunicato il carico arrivato, a sua discrezione accetta o meno il carico ed incarica il TROLLEY. Il TROLLEY comunica al WASTE_TRUCK e al *Waste_Service* dopo che ha prelevato il carico; Dopo che ha scaricato il carico, chiede al *Waste_Service* se c'è un altro incarico
* risposta positiva : si sposta direttamente ad INDOOR 
* risposta negativa : si sposta ad HOME 

> TESTING PLAN 1

6 iterazioni di WASTE_TRUCK, MAXPB = 10, MAXGB = 10

Iterazioni

|       |       |       |
| ---      |   ---    |   ---    |
|1. VETRO 2                           |           --->  | ACCETTATO
|2. VETRO,3  subito dopo carico       |           --->  |  ACCETTATO, TROLLEY NON PASSA DA HOME
|3. VETRO,2  aspetta un po'           |           --->  |  ACCETTATO, TROLLEY RIMANE PER UN PO' DA HOME
|4. VETRO, 5 ininfluente              |           --->  |  NON ACCETTATO
|5. PLASTICA, 3 ininfluente           |           --->  |  ACCETTATO
|6. VETRO, 2  ininfluente             |           --->  |  ACCETTATO

stato finale, MAXPB = 1, MAXGB = 7


## PROBLEMA 2

Il TROLLEY si ferma quando il SONAR rileva una distanza minore di DLIMIT, e riprende l'esecuzione quando viene rilevata una distanza maggiore.

Il LED codifica lo stato del TROLLEY
* acceso, bloccato dal sonar
* spento, home
* lampeggiante, in operazione

> TESTING PLAN 2

SONAR simula i seguenti valori quando avviato, DLIMIT = 100

200,200,200,200,200,200,200,200,200,200,200,1,1,1,1,56,400,400,400,400,44,4,4,4,4,4,4,4,100,100,100,100,100....


TROLLEY fermo in home, LED spento

2 iterazioni di WASTE_TRUCK

1.  VETRO, 1          
2.  PLASTICA, 1

durante la prima iterazione il LED lampeggia per tutto il tragitto, quando è a home si spegne

durante la seconda iterazione avvio il SONAR al momento di arrivo del TRUCK, il LED deve essere ACCESO per la durata per cui SONAR è sotto DLIMIT, e il TROLLEY fermo, una volta riavviato, il LED torna a lampeggiare e quando ARRIVA AD HOME il trolley, il led si spegne



## PROBLEMA 3 


disporre di una GUI che mostra
* stato del trolley
* peso corrente nei containers
* stato del led


> TESTING PLAN 3

RIESEGUIRE IL TESTPLAN_2, facendo 3* 2 iterazioni, la gui deve mostrare le stesse informazioni dei componenti




------

# Risposta a domande tramite mail 21-03-2022





>  L'analisi induce a definire una lista di priorità per affrontare i requisiti?

L'analisi induce ad affrontare i requisiti nel seguente ordine : 
1. base functionality
2. stop-resume-notify
3. gui tracker

Per una relazione di ***dipendenza*** : il requisito di *base functionality* ha semantica completica stand-alone, *stop-resume-notify* è una funzionalità aggiuntiva al primo requisito, infine *gui tracker* è come se fosse una funzionalità 'parassita', non modifica in nessun modo lo stato di esecuzione dei altri requisiti, si "aggancia" ai eventi di suo interesse emessi dal sistema.

>  L'analisi (e il punto precedente) suggerisce un obiettivo per il primo Sprint?

Si possono seguire due strategie di avanzamento

FACILITÀ
1. gui-tracker, message-driven e non message-based, non essendo proattivo è anche molto semplice la progettazione
2. stop-resume-notify, data la disponibilità della mia software house dei componenti *Led* e *Sonar* molto simili a quelli richiesti da questo progetto, posso estendere il comportamento di essi, la difficoltà sta nella separazione delle responsabilità dell'attore waste_service
3. base functionality, non solo bisogna identificare le altre responsabilità di waste_service, ma è necessario anche ingegnare sui trolley

UTILITÀ DEL PROTOTIPO
1. base functionality, racchiude il funzionamento a regime del sistema
2. stop-resume-notify, interazione con componenti proattivi che possono modificare il flusso di esecuzione del sistema
3. gui-tracker, componente passivo


STRAVOLGIMENTI FUTURI

dando a ==> la semantica di "dipende da" , abbiamo la catena di dipendenze


gui-tracker ==> stop-resume-notify ==> base functionality

ci conviene cominicare da base functionality visto 
1. può stravolgere analisi e progetto degli altri due requisiti, in più il prototipo è più utile dal punto di vista del committente

>  L'obiettivo da raggiungere nel primo Sprint può essere associato a un piano di testing che ne verifichi il raggiungimento?

Sì, già riportato nel documento di progetto



> 

    RICEVIMENTO 23 GIUGNO 2022


Motivare perchè transport trolley è logicamente un attore
* componente reattivo o proattivo?


perchè goal è un dispaccio?

i requisiti implicano problematiche
* quali requisiti, quali problematiche


L'analista non fa scelte, ma considerazione


considerazione, load-accepted dopo il pickup

considerazione pickup se evento
* va giù la connessione


pickup è evento, dispaccio oppure una reply


Analisi, mettere in luce le problematiche 


Che cosa si intende nella posizione del trolley
* posizione è indicazione della zona in cui sono
    * INDOOR 
    * WORKING
    * CONTAINER
    * HOME


> Webrobot22

Sprint 1


