# LabA2026-Hajjoubi-Mare-Martignoni-Pica

1. AUTORI
   Progetto di gruppo di Hajjoubi, Mare, Martignoni e Pica.

2. DESCRIZIONE DEL PROGETTO

- CineMax è un applicazione in java che permette la gestione di un cinema monosala con capienza massima di 200 posti. Il sistema gestisce il funzionamento generale del cinema: dal catalogo dei film alla programmazione delle proiezioni, fino al sistema di prenotazione dei biglietti, garantendo un controllo degli accessi suddiviso per ruoli.

Funzionalità principali:

- Gestione degli utenti: ci si può collegare come Cliente, Proiezionista o Bigliettaio (oppure utilizzarlo come ospite senza accedere)
- Per il Cliente: permette di cercare i film (filtrando per titolo, genere, data o prezzo), registrarsi , prenotare i biglietti e modificare e cancellare le proprie prenotazioni
- Per il Proiezionista: permette di aggiungere, modificare o cancellare le proiezioni dei film
- Per il Bigliettaio: permette di cercare e controllare le prenotazioni dei clienti usando il codice, il nome, il film o le date
- Salvataggio dati: tutti i dati di film, utenti e prenotazioni vengono salvati su file in maniera tale da non perderli quando si chiude il programma

3. REQUISITI DI SISTEMA

Per utilizzare CineMax è necessario avere installato sul computer la versione di java 26.0.1 e scaricare il repository gitHub: https://github.com/martignonigiorgio2006-stack/LabA2026-Hajjoubi-Mare-Martignoni-Pica.git 

4. ISTRUZIONI DI ESECUZIONE
   È possibile avviare l'applicazione utilizzando gli script preconfigurati nella cartella `bin`:

- Su Windows:
  Utilizzare il file  `avvio.bat`.

- Su macOS / Linux:
  Utilizzare il file `avvio.command`

5. STRUTTURA DIRECTORY DEL PROGETTO

- bin --> contiene gli eseguibili che sono: avvio.bat (per windows), avvio.command (per macOS)
- data --> contiene il file (archivio.dat) che fa da database
- doc --> contiene i due manuali (tecnico e utente) in formato pdf
- doc/javadoc --> contiene gli html della nostra javadoc
- src --> contiene tutti i file .java
- autori.txt --> Informazioni degli autori
