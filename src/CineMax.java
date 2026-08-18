

      public static void main(String[] args) {

        GestoreCinema gc = new GestoreCinema();

        //Prima istruzione del programma sempre (lettura del file e riempimento delle liste su cui girerà il programma)
        gc.letturaFile(); //Non serve all'utente ma a noi, se l'utente chiede di stampare quali film usiamo le liste perchè il file si aggiorna alla fine
        gc.riempiListe();

        boolean scelta;
        do {
            scelta = menuPrincipale();
        } while (!scelta);

        IO.output("Arrivederci!");

        //Ultima istruzione del programma sempre scrittura
        gc.scritturaFile();

    }

    public static boolean menuPrincipale() {
        String[] opzioni = {"loggarti", "registrati come cliente", "cerca proiezioni per Film", "cerca proiezioni per Data", "visualizza Catalogo Film", "uscire"};
        IO.output("Digita:", true);
        for (int i = 0; i < opzioni.length; i++) {
            IO.output("- " + (i + 1) + "  per " + opzioni[i]);
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
                cercaProiezioniFilm();
                return false;
            case 4:
                cercaProiezioniData();
                return false;
            case 5:
                visualizzaCatalogoFilm();
                return false;
            case 6:
                return true;
            default:
                IO.outputErr("Errore: valore inserito non valido.\nRiprova!");
                return false;
        }
    }
