import java.util.LinkedList;

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

    }//Chiusura main


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
    }//chiusura menuPrincipale

    public static void login(){//da controllora il login mostrare il menu in base al ruolo di chi fa il login
        IO.output("## Login ##", true); 


        //Controlli del null nel metodo della classe GestioneCinema
        String username = IO.readString("Username: ");
        String password = IO.readString("Password: ");

        try{
            gc.login(username, password);
            
            Utente utente = gc.getUtenteLoggato();

            IO.output("Login effettuato!\nBenvenuto " + utente.getNome() , true);

            boolean scelta;
            do {
                scelta = menuClienteRegistrato();
            } while (!scelta);
                

        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova!");
            return;
        }
    }//chiusura login

    public static void registraCliente(){
        IO.output("## Registrazione clienti ##", true); 

        try{

            String nome = IO.readString("Nome: ");
            String cognome = IO.readString("Cognome: ");
            String username = IO.readString("Username: ");
            String password = IO.readString("Password: ");

            Luogo domicilio = null;
            try{
                domicilio = IO.readDomicilio("Inserisci il tuo domicilio");
            }catch(IllegalValueException e){
                IO.outputErr(e.getMessage() + "\nRiprova!");
            }

            Data dataDiNascita = null;
            boolean errore = false;
            do{
                try {
                    dataDiNascita = IO.readData("Inserisci la tua data di nascita in formato gg/mm/aaaa (-1 per saltare): ");
                } catch (IllegalValueException e) {
                    IO.outputErr(e.getMessage()+"\nRiprova!");
                    errore = true;
                }    
            }while(errore);

            gc.registraCliente(nome, cognome, username, password, domicilio, dataDiNascita);

            IO.output("Registrazione effettuata correttamente!", true);

        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova!");
            return;
        }
    }//chiusura registraCliente

    public static void cercaProiezione(){
        IO.output("## Ricerca proiezioni ##", true); 

        String[] opzioni = {"Titolo: ", "Da (formato data gg/mm/aaaa): ", "A (formato data gg/mm/aaaa): ", "Genere: ", "Costo minimo: ", "Costo massimo: "};
        IO.output("Inserisci i filtri della ricerca (-1 per saltare):", true);

        String titolo;
        titolo = IO.readString(opzioni[0]);
        if(titolo.trim().equals("-1"))
            titolo = null;

        Data inizio = null, fine = null;
        boolean errore;
        do{
            errore = false;
            try {
                inizio = IO.readData(opzioni[1]);
                fine = IO.readData(opzioni[2]);
            } catch (IllegalValueException e) {
                IO.outputErr(e.getMessage()+"\nRiprova!");
                errore = true;
            }    
        }while(errore);

        Genere genere = null;
        errore = false;
         do{
            try {
                genere = IO.readGenere(opzioni[3]);
            } catch (IllegalValueException e) {
                IO.outputErr(e.getMessage()+"\nRiprova!");
                errore = true;
            }    
        }while(errore);

        Double costoMin; 
        errore = false;
        do{
            costoMin = IO.readDouble(opzioni[4]);
            if(costoMin == -1)
                costoMin = null;
            else if(costoMin < 0 ){
                IO.outputErr("Errore: Costo negativo non consentito\nRiprova!");
                errore = true;
            }
        }while(errore);

        Double costoMax; 
        errore = false;
        do{
            costoMax = IO.readDouble(opzioni[4]);
            if(costoMax == -1)
                costoMax = null;
            else if(costoMax < 0 ){
                IO.outputErr("Errore: Costo negativo non consentito\nRiprova!");
                errore = true;
            }
        }while(errore);

        LinkedList<Proiezione> risultato = gc.cercaProiezioni(titolo, inizio, fine, genere, costoMin, costoMax);
        if(risultato.isEmpty()){
            IO.output("Nessuna proiezione trovata con questi filtri.", true);
        }else{
            IO.output("Proiezioni trovate: ");
            for(Proiezione p: risultato){
                IO.output(p.toString(), true);
            }
        }
    }//Chiusura cercaProiezione
    
    public static void visualizzaCatalogoFilm(){
        IO.output("## Catalogo film ##", true); 

        if(gc.getListaFilm().isEmpty() || gc.getListaFilm() == null){
            IO.output("Catalogo vuoto.", true);
        }
        
        IO.output("Catalogo film:", true);
        for(Film f : gc.getListaFilm()){
            IO.output(f.toString(), true);
        }

    }//Chiusura visualizzaCatalogoFilm

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
                logout();
                return true;
            default:
                IO.outputErr("Errore: valore inserito non valido.\nRiprova!");
                return false;
        }
    }//Chiusura menuClienteRegistrato()


    public static void effettuaPrenotazione(){
        try{
            IO.output("## Effettuare prenotazione ##", true); 

            String idProiezioneString;
            boolean errore = false;

            do{
                idProiezioneString = IO.readIntFormatoStringa("Inserisci l'ID della proiezione: ");
                if(idProiezioneString == null || idProiezioneString.trim().isEmpty()){
                    errore = true;
                    IO.outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                }
            }while(errore);
    
            int idProiezione = Integer.parseInt(idProiezioneString);

            String quantitaString;
            errore = false;
            do{
                quantitaString = IO.readIntFormatoStringa("Inserisci il numero di biglietti: ");
                if(quantitaString == null ||  quantitaString.trim().isEmpty()){
                    errore = true;
                    IO.outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                } else if(Integer.parseInt(quantitaString) <= 0){
                    errore = true;
                    IO.outputErr("Errore: Quantità non ammessa!\nRiprova!");
                } 
            }while(errore);

            int quantita = Integer.parseInt(quantitaString);

            gc.aggiungiPrenotazione(idProiezione, quantita);
            
            IO.output("Prenotazione effettuata con successo!", true);

        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova!");

        }
    }//Chiusura menuClienteRegistrato()
    
    public static void modificaPrenotazione(){
        try{
            IO.output("## Modifica prenotazione ##", true); 
            
            String idProiezioneString;
            boolean errore = false;
            do{
                idProiezioneString = IO.readIntFormatoStringa("Inserisci l'ID della proiezione: ");
                if(idProiezioneString == null || idProiezioneString.trim().isEmpty()){
                    errore = true;
                    IO.outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                }
            }while(errore);
    
            int idProiezione = Integer.parseInt(idProiezioneString);

            String nuovaQuantitaString;
            errore = false;
            do{
                nuovaQuantitaString = IO.readIntFormatoStringa("Inserisci il numero di biglietti: ");
                if(nuovaQuantitaString == null ||  nuovaQuantitaString.trim().isEmpty()){
                    errore = true;
                    IO.outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                } else if(Integer.parseInt(nuovaQuantitaString) <= 0){
                    errore = true;
                    IO.outputErr("Errore: Quantità non ammessa!\nRiprova!");
                } 
            }while(errore);

            int nuovaQuantita = Integer.parseInt(nuovaQuantitaString);

            gc.aggiungiPrenotazione(idProiezione, nuovaQuantita);
            
            IO.output("Prenotazione effettuata con successo!", true);

        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova!");

        }
    }//Chiusura modificaPrenotazione()

    public static void cancellarePrenotazione(){

    }

    public static void visualizzaPrenotazioni(){
        
    }

    public static void logout(){
        
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
                logout();
                return true;
            default:
                IO.outputErr("Errore: valore inserito non valido.\nRiprova!");
                return false;
        }
    }//Chiusura menuProiezionistaRegistrato

    public static void aggiungiFilm(){
        try{
            IO.output("## Aggiungi film ##",true); 

            //Non effettuo controlli sui parametri siccome lo fa già il costruttore (tecnicamente tutti i metodi aggiungi non devno avere controlli)
            String titolo = IO.readString("Titolo: ");
            int durata = IO.readInt("Durata: ");
            int anno = IO.readInt("Anno: ");
            int etaMin = IO.readInt("Età minima: ");
            Genere genere = IO.readGenere("Genere:");
            Regista regista = IO.readRegista("Regista: ");

            gc.aggiungiFilm(titolo, durata,anno, etaMin, genere, regista);
            IO.output("Film aggiunto con successo", true);

        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova");
        }
    }//Chiusura aggiungiFilm
    
    public static void rimuoviFilm(){
        try{  
            IO.output("## Rimuovi film ##", true);

            String titolo = IO.readString();
            gc.rimuoviFilm(titolo);
            IO.output("Film rimosso con successo");

        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova");
        }
    }//Chiusura rimuoviFilm

    public static void aggiungiRegista(){
         try{
            IO.output("## Aggiungi regista ##", true);

            gc.aggiungiRegista(IO.readRegista("Inserisci dati regista"));

            IO.output("Regista aggiunto con successo ", true);
         }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova");
         }

    }//Chiusura aggiungiRegista

    public static void rimuoviRegista(){
        try{
            IO.output("## Rimuovi regista ##", true);

            gc.rimuoviRegista(IO.readRegista("Inserisci dati regista"));

            IO.output("Regista rimosso con successo", true);

        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova");
        }
    }//Chiusura rimuoviRegista

    public static void aggiungiProiezione(){
        try{
            IO.output("## Aggiungi proiezione ##", true);

            for(Film f : gc.getListaFilm()){
                IO.output(f.toString(), true);
            }

            Film filmscelto = null;
            int idFilm = IO.readInt("Inserisci l'ID del film: ");
            
            for(Film f : gc.getListaFilm()){
                if(f.getId() == idFilm){
                    filmscelto = f;
                    break;
                }
            }

            if(filmscelto == null){
                IO.outputErr("Errore: Film non trovato ");
                return;
            }

            Data data = IO.readData("Data proiezione: ");

            Ora orario = IO.readOra("Inserisci orrario proiezione");
            
            double costo = IO.readDouble("Costo: ");

            gc.aggiungiProiezione(filmscelto, data, orario, costo);
            
            IO.output("Proiezione aggiunta con successo", true);

        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova");
        }

    }

    public static void modificaProiezione(){
        try{
            IO.output("Modifica proiezione", true);
            
            int idProiezione = IO.readInt("Inserisci l'ID della proiezione da modificare: ");
            
            Data nuovaData = IO.readData("Nuova data: ");
            
            int nuovaOra = IO.readInt("Nuova ora: ");
            int nuovoMinuto = IO.readInt("Nuovo minuto: ");
            int nuovoSecondo = IO.readInt("Nuovo secondo: ");
            Ora nuovoOrario = new Ora(nuovaOra, nuovoMinuto, nuovoSecondo);

            double nuovoCosto = IO.readDouble("Nuovo costo: ");

            gc.modificaProiezione(idProiezione, nuovaData, nuovoOrario, nuovoCosto);   
           
        }catch(IllegalValueException e){
          IO.outputErr(e.getMessage()+"\nRiprova");
        }
    }
    public static void cancellaProiezione(){
        try{
            IO.output("Cancella proiezione", true);

            int idProiezione = IO.readInt("Inserisci l'ID della proiezione da cancellare:");

            gc.rimuoviProiezione(idProiezione);
            IO.output("Proiezione cancellata con successo", true);
            

        }catch(IllegalValuException e){
            IO.outputErr(e.getMessage()+"\nRiprova");
        }
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
        try{
            IO.output("Prenotazioni odierne", true);
        }catch(IllegalValueException e){
            IO.outputErr(e.getMessage()+"\nRiprova");
        }

    }
    
    public static void cercaPrenotazione(){
    String[] opzioni = {"Codice: ", "Nome: ", "Cognome: ", "Titolo: ", "Da: ", "A: "};
    IO.output("Inserisci i filtri della ricerca (-1 per saltare):", true);

    Integer codice;
    codice = IO.readInt(opzioni[0]);
    if(codice == -1)
        codice = null;

    String nome;
    nome = IO.readString(opzioni[1]);
    if(nome.trim().equals("-1"))
        nome = null;

    String cognome;
    cognome = IO.readString(opzioni[1]);
    if(cognome.trim().equals("-1"))
        cognome = null;

    String titolo;
    titolo = IO.readString(opzioni[3]);
    if(titolo.trim().equals("-1"))
        titolo = null;

    Data inizio;
    Data fine;
    boolean errore;
    do{
        errore = false;
        try {
            inizio = IO.readData(opzioni[4]);
            fine = IO.readData(opzioni[5]);
        } catch (IllegalValueException e) {
            IO.outputErr(e.getMessage()+"\nRiprova!");
            errore = true;
        }    
    }while(errore);
    try{
        LinkedList<Prenotazione> risultato = gc.cercaPrenotazione(codice, nome, cognome, titolo, inizio, fine);
        if(risultato.isEmpty()){
            IO.output("Nessuna prenotazione trovata.", true);
        } else {
            IO.output("Prenotazioni trovate: ");
            for (Prenotazione p : risultato) {
                IO.output(p.toString(), true);
            }
        }
    }catch(IllegalValueException e){
        IO.outputErr(e.getMessage());
    }
    
    }//chiusura metodo cercaPrenotazione()
    
    public static void aggiungiBigliettaio(){

    }

    public static void aggiungiProiezionista(){

    }



}