public class CineMax{
    
    public static GestoreCinema gc;

      public static void main(String[] args) {

        gc = new GestoreCinema();

        //Prima istruzione del programma sempre (lettura del file e riempimento delle liste su cui girerà il programma)
        //gc.letturaFile(); //Non serve all'utente ma a noi, se l'utente chiede di stampare quali film usiamo le liste perchè il file si aggiorna alla fine
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
        String[] opzioni = {"loggarti", "registrati come cliente", "cercare proiezioni", "visualizza catalogo film", "uscire"};
        IO.output("Digita:", true);
        for (int i = 0; i < opzioni.length; i++) 
            IO.output("- " + (i + 1) + "  per " + opzioni[i]);
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
                IO.outputErr("Errore: valore inserito non valido.\nRiprova!");
                return false;
        }
    }

    public static void login(){

    }

    public static void registraCliente(){
        
    }

    public static void cercaProiezione(){
        String[] opzioni = {"Titolo: ", "Da: ", "A: ", "Genere: ", "Costo del biglietto: "};
        IO.output("Inserisci i filtri della ricerca (-1 per saltare):", true);

        String titolo;
        titolo = IO.readString(opzioni[0]);
        if(titolo.trim().equals("-1"))
            titolo = null;

        Data inizio, fine;
        boolean errore = false;
        do{
            try {
                inizio = IO.readData(opzioni[1]);
                fine = IO.readData(opzioni[2]);
            } catch (IllegalValueException e) {
                IO.outputErr(e.getMessage()+"\nRiprova!");
                errore = true;
            }    
        }while(errore);
       
        Genere genere;
        errore = false;
         do{
            try {
                genere = IO.readGenere(opzioni[3]);
            } catch (IllegalValueException e) {
                IO.outputErr(e.getMessage()+"\nRiprova!");
                errore = true;
            }    
        }while(errore);
        
        Double costo; //classe Double così accetta il null
        costo = IO.readDouble(opzioni[4]);
        if(costo == -1)
            costo = null;

        gc.cercaProiezioni(titolo, inizio, fine, genere, costo);
        
    }
    
    public static void visualizzaCatalogoFilm(){

    }

    public static boolean menuClienteRegistrato() {
        String[] opzioni = {"effettuare una prenotazione", "modificare una prenotazione", "cancellare una prenotazione", "visualizzare le prenotazioni", "logout"};
        IO.output("Digita:", true);
        for (int i = 0; i < opzioni.length; i++) 
            IO.output("- " + (i + 1) + "  per " + opzioni[i]);
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
                //Logout = torno al menu principale
                return true;
            default:
                IO.outputErr("Errore: valore inserito non valido.\nRiprova!");
                return false;
        }
    }

    public static void effettuaPrenotazione(){

    }

    public static void modificaPrenotazione(){

    }

    public static void cancellarePrenotazione(){

    }

    public static void visualizzaPrenotazioni(){
        
    }

    public static boolean menuProiezionistaRegistrato() {
        String[] opzioni = {"aggiungere un nuovo film al catalogo", "rimuovere un film dal catalogo", "aggiungingere un nuovo regista al catalogo", "rimuovere un regista dal catalogo", "aggiungiere una nuova proiezione", "modificare una proiezione", "cancellare una proiezione", "logout"};
        IO.output("Digita:", true);
        for (int i = 0; i < opzioni.length; i++) 
            IO.output("- " + (i + 1) + "  per " + opzioni[i]);
        int scelta = IO.readInt();
        switch (scelta) {
            case 1:
                aggiungiFilm();
                return false;
            case 2:
                rimuoviFilm();
                return false;
            case 3:
                aggiungiRegista();
                return false;
            case 4:
                rimuoviRegista();
                return false;
            case 5:
                aggiungiProiezione();
                return false;
            case 6:
                modificaProiezione();
                return false;
            case 7: 
                cancellaProiezione();
                return false;
            case 8: 
                //Logout = torno al menu principale
                return true;
            default:
                IO.outputErr("Errore: valore inserito non valido.\nRiprova!");
                return false;
        }
    }

    public static void aggiungiFilm(){

    }
    
    public static void rimuoviFilm(){

    }
    
    public static void aggiungiRegista(){

    }

    public static void rimuoviRegista(){

    }

    public static void aggiungiProiezione(){

    }

    public static void modificaProiezione(){
        
    }
    public static void cancellaProiezione(){
        
    }
    
    public static boolean menuBigliettaioRegistrato() {
        String[] opzioni = {"consultare le prenotazioni odierne", "cercare prenotazione", "aggiungere un nuovo bigliettaio", "aggiungere un nuovo proiezionista", "logout"};
        IO.output("Digita:", true);
        for (int i = 0; i < opzioni.length; i++) 
            IO.output("- " + (i + 1) + "  per " + opzioni[i]);
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
                //Logout = torno al menu principale
                return true;
            default:
                IO.outputErr("Errore: valore inserito non valido.\nRiprova!");
                return false;
        }
    }

    public static void cercaPrenotazioniOdierne(){

    }
    
    public static void cercaPrenotazione(){
    String[] opzioni = {"Titolo: ", "Da: ", "A: "};
    IO.output("Inserisci i filtri della ricerca (-1 per saltare):", true);

    String titolo;
    titolo = IO.readString(opzioni[0]);
    if(titolo.trim().equals("-1"))
        titolo = null;

    Data inizio, fine;
    boolean errore = false;
    do{
        try {
            inizio = IO.readData(opzioni[1]);
            fine = IO.readData(opzioni[2]);
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage()+"\nRiprova!");
            errore = true;
        }    
    }while(errore);

    gc.cercaPrenotazione(titolo, inizio, fine);
    
}
    
    public static void aggiungiBigliettaio(){

    }

    public static void aggiungiProiezionista(){

    }

}

