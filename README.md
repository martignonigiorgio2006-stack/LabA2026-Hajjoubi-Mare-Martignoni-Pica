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

- Java Development Kit (JDK)
- un ambiente di sviluppo compatibile con Java

4. ISTRUZIONE PER LA COMPILAZIONE DA RIGA DI COMANDO
   Per compilare il progetto da riga di comando è necessario aver installato JDK; aprire il terminale nella cartella principale del progetto e spostarsi nella cartella "scr" ed eseguire:

   > ```bash
   > cd src
   > javac -d ../bin *.java
   > ```

5. ISTRUZIONI DI ESECUZIONE
   È possibile avviare l'applicazione utilizzando gli script preconfigurati nella cartella `bin`:

- Su Windows:
  Aprire il terminale nella cartella `bin` e digitare:

  > avvio.bat
  > (oppure fare doppio click sul file)

- Su macOS / Linux:
  Fare doppio click su `avvia.command` oppure eseguirlo da terminale
  > chmod +x avvia.command
  > ./avvia.command

6. STRUTTURA DIRECTORY DEL PROGETTO

- bin --> contiene gli eseguibili che sono: avvio.bat (per windows), avvio.command (per macOS)
- data --> contiene il file (archivio.dat) che fa da database
- doc/javadoc --> contiene gli html della nostra javadoc
- src --> contiene tutti i file .java
- autori.txt --> Informazioni degli autori
