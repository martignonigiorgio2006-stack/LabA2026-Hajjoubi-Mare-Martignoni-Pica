/*
AUTORI:
- Hajjoubi, Omar, 766954, VA
- Mare, Filippo, 766773, VA
- Martignoni, Giorgio, 766932, VA
- Pica, Simone, 765155, VA
 */
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * Rappresenta il main
 *
 */
public class CineMax {

    /**
     * Gestore del cinema.
     */
    public static GestoreCinema gc;

    /**
     * Punto di ingresso principale del programma. Inizializza il gestore del
     * cinema, carica i dati dai file nelle liste di memoria, gestisce il ciclo
     * del menu principale e, alla chiusura, salva lo stato aggiornato sui file.
     *
     * @param args argomenti passati da riga di comando (non usati in questa
     * applicazione)
     */
    public static void main(String[] args) {

        gc = new GestoreCinema();

        //gc.letturaFile(); //Non serve all'utente ma a noi, se l'utente chiede di stampare quali film usiamo le liste perchè il file si aggiorna alla fine
        //Prima istruzione del programma sempre (lettura del file per il riempimento delle liste su cui girerà il programma)
        gc.riempiListe();

        boolean scelta;
        do {
            scelta = menuPrincipale();
        } while (!scelta);

        IO.output("Arrivederci!");
        //Ultima istruzione del programma sempre (scrittura sul file delle liste che contengono i dati del programma)
        gc.scritturaFile();

        IO.tastiera.close();
    }//Chiusura main

    /**
     * Gestisce il menù principale
     *
     * @return un boolean per capire se uscire dal programma
     */
    public static boolean menuPrincipale() {
        IO.output("\n## Menù principale ##", true);

        String[] opzioni = {"loggarti", "registrati come cliente", "cercare proiezioni", "visualizza catalogo film", "uscire"};
        IO.output("Digita:", true);
        for (int i = 0; i < opzioni.length; i++) {
            IO.output("- " + (i + 1) + "  per " + opzioni[i], true);
        }

        int scelta = IO.readInt();
        switch (scelta) {
            case 1:
                login();
                return false;
            case 2:
                registraCliente();
                return false;
            case 3:
                cercaProiezione();
                return false;
            case 4:
                visualizzaCatalogoFilm();
                return false;
            case 5:
                return true;
            default:
                IO.outputErr("Errore: valore inserito non valido!\nRiprova!");
                return false;
        }
    }//Chiusura menuPrincipale

    /**
     * Gestisce il login di un utente
     *
     */
    public static void login() {
        IO.output("\n## Login ##", true);

        String username = IO.readString("Username: ");
        String password = IO.readString("Password: ");

        try {
            gc.login(username, password);

            Utente utente = gc.getUtenteLoggato();

            IO.output("Login effettuato!\nBenvenuto/a " + utente.getNome(), true);

            if (utente.getRuolo() == Ruolo.CLIENTE) {
                boolean scelta;
                do {
                    scelta = menuClienteRegistrato();
                } while (!scelta);
            } else if (utente.getRuolo() == Ruolo.BIGLIETTAIO) {
                boolean scelta;
                do {
                    scelta = menuBigliettaioRegistrato();
                } while (!scelta);
            } else if (utente.getRuolo() == Ruolo.PROIEZIONISTA) {
                boolean scelta;
                do {
                    scelta = menuProiezionistaRegistrato();
                } while (!scelta);
            }

        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");
            return;
        }
    }//Chiusura login

    /**
     * Registra un cliente
     *
     */
    public static void registraCliente() {
        IO.output("\n## Registrazione clienti ##", true);
        try {

            String nome = IO.readString("Nome: ", 1);
            String cognome = IO.readString("Cognome: ", 1);
            String username = IO.readString("Username: ");
            String password = IO.readString("Password: ");
            Luogo domicilio = IO.readDomicilio("Inserisci il tuo domicilio");
            Data dataDiNascita = IO.readData("Inserisci la tua data di nascita in formato gg/mm/aaaa (-1 per saltare): ");

            gc.registraCliente(nome, cognome, username, password, domicilio, dataDiNascita);

            IO.output("Registrazione effettuata correttamente!", true);

        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");
        }
    }//chiusura registraCliente

    /**
     * Cerca una proiezione
     *
     */
    public static void cercaProiezione() {
        IO.output("\n## Ricerca proiezioni ##", true);

        String[] opzioni = {"Titolo: ", "Da (formato data gg/mm/aaaa): ", "A (formato data gg/mm/aaaa): ", "Genere: ", "Costo minimo ", "Costo massimo "};
        IO.output("Inserisci i filtri della ricerca (-1 per saltare):", true);

        String titolo;
        titolo = IO.readString(opzioni[0]);
        if (titolo.trim().equals("-1")) {
            titolo = null;
        }

        Data inizio = IO.readData(opzioni[1]);
        Data fine = IO.readData((opzioni[2]));
        Genere genere = IO.readGenere(opzioni[3]);

        Double costoMin;
        boolean errore;
        do {
            errore = false;
            costoMin = IO.readDouble(opzioni[4] + " (scrivere solo l'importo senza valuta): ");
            if (costoMin == -1) {
                costoMin = null;
            } else if (costoMin < 0) {
                IO.outputErr("Errore: Importo negativo non consentito\nRiprova!");
                errore = true;
            }
        } while (errore);

        Double costoMax;
        do {
            errore = false;
            costoMax = IO.readDouble(opzioni[5] + " (scrivere solo l'importo senza valuta): ");
            if (costoMax == -1) {
                costoMax = null;
            } else if (costoMax < 0) {
                IO.outputErr("Errore: Importo negativo non consentito\nRiprova!");
                errore = true;
            }
        } while (errore);

        LinkedList<Proiezione> risultato = gc.cercaProiezioni(titolo, inizio, fine, genere, costoMin, costoMax);
        if (risultato.isEmpty()) {
            IO.output("Nessuna proiezione trovata con questi filtri.", true);
        } else {
            IO.output("Proiezioni trovate: ", true);
            for (Proiezione p : risultato) {
                IO.output(p.toString(), true);
            }
        }
    }//Chiusura cercaProiezione

    /**
     * Visualizza il catalogo dei film
     *
     */
    public static void visualizzaCatalogoFilm() {
        IO.output("\n## Catalogo film ##", true);

        if (gc.getListaFilm().isEmpty() || gc.getListaFilm() == null) {
            IO.output("Catalogo vuoto.", true);
        }

        IO.output("Catalogo film:", true);
        for (Film f : gc.getListaFilm()) {
            IO.output(f.toString(), true);
        }

    }//Chiusura visualizzaCatalogoFilm

    /**
     * Il menù del cliente
     *
     * @return un boolean
     */
    public static boolean menuClienteRegistrato() {
        IO.output("\n## Menù cliente ##", true);

        String[] opzioni = {"effettuare una prenotazione", "modificare una prenotazione", "cancellare una prenotazione", "visualizzare le prenotazioni", "logout"};
        IO.output("Digita:", true);
        for (int i = 0; i < opzioni.length; i++) {
            IO.output("- " + (i + 1) + "  per " + opzioni[i], true);
        }
        int scelta = IO.readInt();
        switch (scelta) {
            case 1:
                effettuaPrenotazione();
                return false;
            case 2:
                modificaPrenotazione();
                return false;
            case 3:
                cancellarePrenotazione();
                return false;
            case 4:
                visualizzaPrenotazioni();
                return false;
            case 5:
                logout();
                return true;
            default:
                IO.outputErr("Errore: valore inserito non valido.\nRiprova!");
                return false;
        }
    }//Chiusura menuClienteRegistrato()

    /**
     * Per effettuare nuove prentoazioni
     *
     */
    public static void effettuaPrenotazione() {
        try {
            IO.output("\n## Effettuare prenotazione ##", true);

            IO.output("Catalogo proiezioni:", true);
            for (Proiezione p : gc.getListaProiezioni()) {
                IO.output(p.toString(), true);
            }

            int idProiezione = IO.readInt("Inserisci l'ID della proiezione: ");

            int quantita;
            boolean errore;
            do {
                errore = false;
                quantita = IO.readInt("Inserisci il numero di biglietti: ");
                if (quantita <= 0) {
                    errore = true;
                    IO.outputErr("Errore: Quantità non ammessa!\nRiprova!");
                }
            } while (errore);

            gc.aggiungiPrenotazione(idProiezione, quantita);

            IO.output("Prenotazione effettuata con successo!", true);
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");

        }
    }//Chiusura menuClienteRegistrato()

    /**
     * Per modificare le prentoazioni
     *
     */
    public static void modificaPrenotazione() {
        try {
            IO.output("\n## Modifica prenotazione ##", true);

            int idPrenotazione = IO.readInt("Inserisci l'ID della prenotazione: ");

            Data nuovaData = IO.readData("Inserisci la nuova data (formato data gg/mm/aaaa): ");

            gc.modificaPrenotazione(idPrenotazione, nuovaData);

            IO.output("\nModifica effettuata con successo!", true);

        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");

        }
    }//Chiusura modificaPrenotazione()

    /**
     * Per cancellare le prentoazioni
     *
     */
    public static void cancellarePrenotazione() {
        IO.output("## Cancellare prenotazione ##", true);
        try {

            int idPrenotazione = IO.readInt("Inserisci l'ID della prenotazione da cancellare: ");;

            gc.rimuoviPrenotazione(idPrenotazione);

            IO.output("Prenotazione cancellata con successo!", true);
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");
        }

    }//Chiusura cancellarePrenotazione

    /**
     * Per visualizzare le prentoazioni
     *
     */
    public static void visualizzaPrenotazioni() {
        IO.output("\n## Visualizza prenotazioni ##", true);
        try {
            LinkedList<Prenotazione> prenotazioniUtente = gc.getPrenotazioneUtente();
            if (prenotazioniUtente == null || prenotazioniUtente.isEmpty()) {
                IO.output("Nessuna prenotazione trovata.", true);
                return;
            }
            for (Prenotazione p : prenotazioniUtente) {
                IO.output(p.toString(), true);
            }

        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");
        }

    }//Chiusura visualizzaPrenotazioni

    /**
     * Per effettuare il logout
     *
     */
    public static void logout() {
        IO.output("\n## Logout ##", true);
        gc.logout();
    }//Chiusura logout

    public static boolean menuProiezionistaRegistrato() {
        IO.output("\n## Menù proiezionista ##", true);

        String[] opzioni = {"aggiungere un nuovo film al catalogo", "rimuovere un film dal catalogo", "aggiungere una nuova proiezione", "modificare una proiezione", "cancellare una proiezione", "logout"};
        IO.output("Digita:", true);
        for (int i = 0; i < opzioni.length; i++) {
            IO.output("- " + (i + 1) + "  per " + opzioni[i], true);
        }
        int scelta = IO.readInt();
        switch (scelta) {
            case 1:
                aggiungiFilm();
                return false;
            case 2:
                rimuoviFilm();
                return false;
            case 3:
                aggiungiProiezione();
                return false;
            case 4:
                modificaProiezione();
                return false;
            case 5:
                cancellaProiezione();
                return false;
            case 6:
                logout();
                return true;
            default:
                IO.outputErr("Errore: valore inserito non valido.\nRiprova!");
                return false;
        }
    }//Chiusura menuProiezionistaRegistrato

    /**
     * Per aggiungere un nuovo film
     *
     */
    public static void aggiungiFilm() {
        try {
            IO.output("\n## Aggiungi film ##", true);

            //Non effettuo controlli sui parametri siccome lo fa già il costruttore (tecnicamente tutti i metodi aggiungi non devno avere controlli)
            String titolo = IO.readString("Titolo: ");
            int durata = IO.readInt("Durata: ");
            int anno = IO.readInt("Anno: ");
            int etaMin = IO.readInt("Età minima: ");
            Genere genere = IO.readGenere("Genere: ");
            Regista regista = aggiungiRegista();

            gc.aggiungiFilm(titolo, durata, anno, etaMin, genere, regista);
            IO.output("Film aggiunto con successo", true);

        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova");
        }
    }//Chiusura aggiungiFilm

    /**
     * Per rimuovere dei film
     *
     */
    public static void rimuoviFilm() {
        try {
            IO.output("\n## Rimuovi film ##", true);

            String titolo = IO.readString("Inserisci il titolo: ");
            gc.rimuoviFilm(titolo);
            IO.output("Film rimosso con successo!");

        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova");
        }
    }//Chiusura rimuoviFilm

    /**
     * Per aggiungere regista prentoazioni
     *
     * @return Il regista aggiunto
     */
    public static Regista aggiungiRegista() {
        try {
            Regista regista = gc.aggiungiRegista(IO.readRegista("Inserisci dati regista"));

            IO.output("Regista aggiunto con successo ", true);

            return regista;
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova");
        }
        return null;
    }//Chiusura aggiungiRegista

    /**
     * Per aggiungere una nuova proiezione
     *
     */
    public static void aggiungiProiezione() {
        try {
            IO.output("\n## Aggiungi proiezione ##", true);

            IO.output("Catalogo film:", true);
            for (Film f : gc.getListaFilm()) {
                IO.output(f.toString(), true);
            }

            Film filmscelto = null;
            int idFilm = IO.readInt("Inserisci l'ID del film: ");

            for (Film f : gc.getListaFilm()) {
                if (f.getId() == idFilm) {
                    filmscelto = f;
                    break;
                }
            }

            if (filmscelto == null) {
                IO.outputErr("Errore: Film non trovato ");
                return;
            }

            Data data = IO.readData("Data proiezione (formato gg/mm/aaaa): ");

            Ora orario = IO.readOra("Inserisci orrario proiezione (formato hh:mm:ss):");

            Double costo = null;
            boolean errore;
            do {
                errore = false;
                try {
                    costo = IO.readDouble("Costo (scrivere solo l'importo senza valuta): ");
                } catch (InputMismatchException e) {
                    IO.outputErr(e.getMessage() + "\nRiprova!");
                    errore = true;
                }
            } while (errore);

            gc.aggiungiProiezione(filmscelto, data, orario, costo);

            IO.output("Proiezione aggiunta con successo", true);

        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova");
        }

    }//Chiusura aggiungiProiezione

    /**
     * Per modificare una proiezione
     *
     */
    public static void modificaProiezione() {
        try {
            IO.output("\n## Modifica proiezione ##", true);

            IO.output("Catalogo proiezioni:", true);
            for (Proiezione p : gc.getListaProiezioni()) {
                IO.output(p.toString(), true);
            }

            int idProiezione = IO.readInt("Inserisci l'ID della proiezione da modificare: ");

            Data nuovaData = IO.readData("Inserisci la nuova data (formato gg/mm/aaaa): ");

            Ora nuovoOrario = IO.readOra("Inserisci orario proiezione (formato hh:mm:ss):");

            Double costoBiglietto = IO.readDouble("Inserisci il costo del biglietto (scrivere solo l'importo senza valuta): ");

            gc.modificaProiezione(idProiezione, nuovaData, nuovoOrario, costoBiglietto);
            IO.output("Modifica effettuata con successo!", true);

        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova");
        }
    }//Chiusura modificaProiezione

    /**
     * Per cancellare una proiezione
     *
     */
    public static void cancellaProiezione() {
        try {
            IO.output("\n## Cancella proiezione ##", true);

            IO.output("Catalogo proiezioni:", true);
            for (Proiezione p : gc.getListaProiezioni()) {
                IO.output(p.toString(), true);
            }

            int idProiezione = IO.readInt("Inserisci l'ID della proiezione da cancellare: ");

            gc.rimuoviProiezione(idProiezione);

            IO.output("Proiezione cancellata con successo!", true);
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova");
        }
    }//Chiusura cancellaProiezione

    /**
     * Menù bigliettaio registrato
     *
     * @return un booleano
     */
    public static boolean menuBigliettaioRegistrato() {
        IO.output("\n## Menù bigliettaio ##", true);

        String[] opzioni = {"consultare le prenotazioni odierne", "cercare prenotazione", "aggiungere un nuovo bigliettaio", "aggiungere un nuovo proiezionista", "logout"};
        IO.output("Digita:", true);
        for (int i = 0; i < opzioni.length; i++) {
            IO.output("- " + (i + 1) + "  per " + opzioni[i], true);
        }
        int scelta = IO.readInt();
        switch (scelta) {
            case 1:
                cercaPrenotazioniOdierne();
                return false;
            case 2:
                cercaPrenotazione();
                return false;
            case 3:
                aggiungiBigliettaio();
                return false;
            case 4:
                aggiungiProiezionista();
                return false;
            case 5:
                logout();
                return true;
            default:
                IO.outputErr("Errore: valore inserito non valido.\nRiprova!");
                return false;
        }
    }//Chiusura menuBigliettaioRegistrato

    /**
     * Per cercare prenotazioni odierna
     *
     */
    public static void cercaPrenotazioniOdierne() {
        IO.output("\n## Prenotazioni odierne ##", true);

        try {
            LocalDate oggi = LocalDate.now();

            Data dataOdierna = new Data(oggi.getDayOfMonth(), oggi.getMonthValue(), oggi.getYear());

            LinkedList<Prenotazione> prenotazioniOdierne = gc.getPrenotazionePerData(dataOdierna);

            if (prenotazioniOdierne.isEmpty() || prenotazioniOdierne == null) {
                IO.output("Nessuna prenotazione trovata per oggi.", true);
                return;
            }

            for (Prenotazione p : prenotazioniOdierne) {
                IO.output(p.toString(), true);
            }
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova");
        }

    }//Chiusura cercaPrenotazioniOdierne

    /**
     * Per cercare una prenotazione
     *
     */
    public static void cercaPrenotazione() {
        IO.output("\n## Cerca prenotazione ##", true);

        String[] opzioni = {"Codice: ", "Nome: ", "Cognome: ", "Titolo: ", "Da: ", "A: "};
        IO.output("Inserisci i filtri della ricerca (-1 per saltare):", true);

        Integer codice;
        codice = IO.readInt(opzioni[0]);
        if (codice == -1) {
            codice = null;
        }

        String nome;
        nome = IO.readString(opzioni[1]);
        if (nome.trim().equals("-1")) {
            nome = null;
        }

        String cognome;
        cognome = IO.readString(opzioni[2]);
        if (cognome.trim().equals("-1")) {
            cognome = null;
        }

        String titolo;
        titolo = IO.readString(opzioni[3]);
        if (titolo.trim().equals("-1")) {
            titolo = null;
        }

        Data inizio = IO.readData(opzioni[4]);
        Data fine = IO.readData(opzioni[5]);

        try {
            LinkedList<Prenotazione> risultato = gc.cercaPrenotazione(codice, nome, cognome, titolo, inizio, fine);
            if (risultato.isEmpty()) {
                IO.output("Nessuna prenotazione trovata.", true);
            } else {
                IO.output("Prenotazioni trovate: ", true);
                for (Prenotazione p : risultato) {
                    IO.output(p.toString(), true);
                }
            }
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");
        }
    }//Chiusura metodo cercaPrenotazione()

    /**
     * Per aggiungere un bigliettaio
     *
     */
    public static void aggiungiBigliettaio() {
        IO.output("\n## Aggiungi bigliettaio ##", true);

        try {
            String nome = IO.readString("Nome: ");
            String cognome = IO.readString("Cognome: ");
            String username = IO.readString("Username: ");
            String password = IO.readString("Password: ");
            Luogo domicilio = IO.readDomicilio("Inserisci il tuo domicilio");

            gc.registraBigliettaio(nome, cognome, username, password, domicilio);
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");
        }

    }//Chiusura aggiungiBigliettaio

    /**
     * Per aggiungere un proiezionista
     *
     */
    public static void aggiungiProiezionista() {
        IO.output("\n## Aggiungi proiezionista ##", true);

        try {
            String nome = IO.readString("Nome: ");
            String cognome = IO.readString("Cognome: ");
            String username = IO.readString("Username: ");
            String password = IO.readString("Password: ");
            Luogo domicilio = IO.readDomicilio("Inserisci il tuo domicilio");

            gc.registraProiezionista(nome, cognome, username, password, domicilio);
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");
        }

    }//Chiusura aggiungiProiezionista

    //////  METODI NON RICHIESTI LI USIAMO?
    public static void rimuoviCliente() {
        IO.output("\n## Rimuovi cliente ##", true);

        int idCliente = IO.readInt("Inserisci l'ID del cliente da cancellare: ");

        try {
            gc.rimuoviCliente(idCliente);
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");
        }

    }//Chiusura rimuoviBigliettaio

    public static void rimuoviBigliettaio() {
        IO.output("\n## Rimuovi bigliettaio ##", true);

        int idBigliettaio = IO.readInt("Inserisci l'ID del bigliettaio da cancellare: ");

        try {
            gc.rimuoviBigliettaio(idBigliettaio);
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");
        }

    }//Chiusura rimuoviBigliettaio

    public static void rimuoviProiezionista() {
        IO.output("\n## Rimuovi proiezionista ##", true);

        int idProiezionista = IO.readInt("Inserisci l'ID del proiezionista da cancellare: ");

        try {
            gc.rimuoviProiezione(idProiezionista);
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage() + "\nRiprova!");
        }

    }//Chiusura rimuoviProiezionista

}//Fine Classe 
