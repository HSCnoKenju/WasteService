
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