

      public static void main(String[] args) {
        //IO.output("Hai inserito il numero " + IO.readInt("Inserisci un numero: "));

        GestoreCinema gc = new GestoreCinema();

        gc.letturaFile();

        gc.riempiListe();

        LinkedList<Utente> appoggio = gc.getListaUtenti();
        for (Utente u : appoggio) {
            IO.output(u.toString(), true);
        }

        gc.riempiListe();

        LinkedList<Film> ghei = gc.getListaFilm();
        for (Film f : ghei) {
            IO.output(f.toString(), true);
        }

        gc.riempiListe();

        try {
            LinkedList<Prenotazione> gay = gc.getListaPrenotazioni();
            for (Prenotazione p : gay) {
                IO.output(p.toString(), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            IO.outputErr(e.getMessage());
        }
        gc.riempiListe();

        LinkedList<Proiezione> gays = gc.getListaProiezioni();
        for (Proiezione p : gays) {
            IO.output(p.toString(), true);
        }

        //Ultima istruzione del programma
        gc.scritturaFile();

    }
