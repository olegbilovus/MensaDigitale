# IL SEGUENTE PROGETTO NON E' STATO COMMISSIONATO O FATTO PER ADISUR. IL PROGETTO E' UNA SIMULAZIONE, I LOGHI E IL NOME DELL'AZIENDA SONO STATI USATI AI SOLO A SCOPI EDUCATIVI IN AMBITO UNIVERSITARIO.
# THE FOLLOWING PROJECT HAS NOT BEEN COMMISSIONED OR MADE FOR ADISUR. THE PROJECT IS A SIMULATION, THE LOGOS AND COMPANY NAME HAVE BEEN USED FOR UNIVERSITY EDUCATIONAL PURPOSES ONLY.

# MensaDigitale

![TravisBuildStatus](https://www.travis-ci.com/OB-UNISA/MensaDigitale.svg?token=DRwbyQ7m4WitXGz6KtsW&branch=main)

![Logo](https://github.com/OB-UNISA/MensaDigitale/blob/main/src/main/webapp/assets/img/Logo.png
)

## Intro

MensaDigitale è un sistema di gestione pensato e realizzato per la mensa dell'Università degli Studi di Salerno.

Lo scopo di Mensa Digitale è quello di apportare un’utenza sempre maggiore nel servizio di ristorazione universitaria
fornito dall’ADISURC per UNISA rispettando le nuove norme vigenti in materia di COVID-19 e garantendo una velocizzazione
delle attività quotidiane di quest’ultima. Il team si impegna a creare un sistema che possa automatizzare e ottimizzare
i servizi offerti dalla mensa inserendo soprattutto delle caratteristiche che possano, nello specifico, garantire il
distanziamento e le altre materie di messa in sicurezza per i consumatori.

## Installazione

L'applicazione MensaDigitale non ha bisogno di una vera e propria installazione. Nonostante ciò, per come attualmente è
distribuita (tramite codice sorgente disponibile in questa repo), essa ha bisogno di vari software dai quali dipende.

### Lato client

Su macchina client gli unici requisiti sono una connessione a internet e un browser web.

### Lato server

Per il corretto funzionamento dell'applicazione lato server sono necessari:

* Java (v.14)
* Tomcat (v.9)
* MySQL Server (v.8)

E' consigliato ma non necessario l'utilizzo di Maven per effettuare il packaging del codice sorgente.

Le versioni tra parentesi si riferiscono a quelle utilizzate per lo sviluppo ed il testing di MensaDigitale. Di
conseguenza il corretto funzionamento è assolutamente garantito solo utilizzando le versioni specificate, ma è comunque
possibile che utilizzando altre versioni (soprattutto successive) tutto funzioni perfettamente.

Il database ha bisogno di poter essere acceduto in locale su porta 3306 tramite username "esame" e password "esame".

Per la creazione dello schema necessario (denominato "mensadigitale") nel database è possibile eseguire lo script DB.sql
da scaricare [qui](https://github.com/OB-UNISA/MensaDigitale/blob/main/scriptDatabase/DB.sql).

Ulteriori informazioni sui passaggi da seguire per la completa installazione di MensaDigitale e di tutte le sue
dipendenze sono riportate
nell'[Installation Manual](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_IM_V_1.pdf).

## Documentazione

Link alla documentazione relativa al progetto:

* [Class Diagram](https://ob-unisa.github.io/MensaDigitale/ClassDiagram.svg)
* [RAD](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_RAD_V_3.pdf)
* [SDD](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_SDD_V_2.pdf)
* [ODD](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_ODD_V_2.pdf)
* [Test Plan](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_TP_V_1.4.pdf)
* [Test Case Suite](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_TCS_V_1.pdf)
* [Test Execution Report](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_TER_V_1.pdf)
* [Test Incident Report](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_TIR_V_1.pdf)
* [Test Summary Report](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_TSR_V_1.pdf)
* [User Manual](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_UM_V_1.pdf)
* [Installation Manual](https://github.com/OB-UNISA/MensaDigitale/blob/main/documentazione/MD_IM_V_1.pdf)

## Coverage

Tabella riassuntiva della coverage, generata con Jacoco, disponibile [qui](https://ob-unisa.github.io/MensaDigitale/).
