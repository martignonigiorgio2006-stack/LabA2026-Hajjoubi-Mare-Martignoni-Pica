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

        String username = IO.readString("Username: ");
        String password = IO.readString("Password: ");

        try{
            gc.login(username, password);
            Utente utente = gc.getUtenteLoggato();

            IO.output("Login effettuato!\nBenvenuto " + utente.getNome() , true);

            boolean scelta;
            do {
                scelta = menuCliente();
            } while (!scelta);
                

        }catch(IllegalValueException e){
            IO.outuptErr(e.getMessage()+"\nRiprova!");
            return;
        }
    }

    public static void registraCliente(){
        
        try{

            String nome = IO.readString("Nome: ");
            String cognome = IO.readString("Cognome: ");
            String username = IO.readString("Username: ");
            String password = IO.readString("Password: ");

            IO.output("Inserisci il tuo domicilio:", true);
            String via = IO.readString("Via: ");
            int numeroCivico = IO.readInt("Numero civico: ");
            String citta = IO.readString("Citta: ");
            String cap = IO.readString("CAP: ");

            Luogo domicilio = new Luogo(via, numeroCivico, città, cap)

            gc.registraCliente(nome, cognome, username, password, domicilio);

            IO.output("Registrazione effettuata correttamente!", true);

        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova!");
        
        }
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

        if(gc.getCataLogoFilm().isEmpty()){
            IO.output("Catalogo vuoto.", true);
        }
        
        IO.output("Catalogo film:", true);
        for(Film f : gc.getCataLogoFilm()){
            IO.output(f.toString(), true);
        }

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
        try{
            IO.output("effettuare prenotazione", true);

             int idProiezione = IO.readInt("Inserisci l'ID della proiezione: ");
             int quantita = IO.readInt("Inserisci il numero di biglietti: ");

             gc.aggiungiPrenotazione(idProiezione, quantita)
             IO.output("Prenotazione effettuata con successo!", true);
        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+ true);

        }
        }

    }

    public static void modificaPrenotazione(){

        try{
             IO.output("Modifica Prenotazione", true);

              int idPrenotazione = IO.readInt("Inserisci l'ID della prenotazione: ");
              int nuovaQuantita = IO.readInt("Inserisci il nuovo numero di biglietti: ");

             gc.modificaPrenotazione(idPrenotazione, nuovaQuantita);

             IO.output("Prenotazione modificata con successo!", true);
        }catch(IllegalValueException e){

            IO.outputErr(e.getMessage()+ true);
        }
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



