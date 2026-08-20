
import javax.naming.ldap.LdapReferralException;

import java.io.*;
import java.util.*;

public class GestoreCinema {

    private LinkedList<Utente> listaUtenti;
    private LinkedList<Film> listaFilm;
    private LinkedList<Proiezione> listaProiezioni;
    private LinkedList<Prenotazione> listaPrenotazioni;
    private LinkedList<Regista> listaRegisti;
    private Utente utenteLoggato;

    public GestoreCinema() {
        this.listaUtenti = new LinkedList<Utente>();
        this.listaFilm = new LinkedList<Film>();
        this.listaProiezioni = new LinkedList<Proiezione>();
        this.listaPrenotazioni = new LinkedList<Prenotazione>();
        this.listaRegisti = new LinkedList<Regista>();
        this.utenteLoggato = null;
    }

    // =========================================================================
    // 1. METODI DI CONTROLLO INTERNI E VERIFICA PERMESSI (PRIVATE)
    // =========================================================================
    private void verificaCliente() throws IllegalValueException {
        if (utenteLoggato == null || !(utenteLoggato instanceof Cliente)) {
            throw new IllegalValueException("Operazione consentita solo ai clienti!");
        }
    }

    private void verificaProiezionista() throws IllegalValueException {
        if (utenteLoggato == null || !(utenteLoggato instanceof Proiezionista)) {
            throw new IllegalValueException("Operazione consentita solo al proiezionista!");
        }
    }

    private void verificaBigliettaio() throws IllegalValueException {
        if (utenteLoggato == null || !(utenteLoggato instanceof Bigliettaio)) {
            throw new IllegalValueException("Operazione consentita solo al bigliettaio!");
        }
    }

    private void verificaProprietaPrenotazione(Prenotazione p) throws IllegalValueException {
        if (utenteLoggato instanceof Cliente && p.getUtente().getId() != utenteLoggato.getId()) {
            throw new IllegalValueException("Non hai i permessi per modificare o cancellare le prenotazioni di altri utenti!");
        }
    }

    private void verificaUsernameDisponibile(String username) throws IllegalValueException {
        for (Utente u : listaUtenti) {
            if (username.equals(u.getUsername())) {
                throw new IllegalValueException("Errore: Username già in uso!");
            }
        }
    }

    private boolean haPrenotazioniAttive(int idProiezione) {
        for (Prenotazione p : listaPrenotazioni) {
            if (p.getProiezione().getId() == idProiezione) {
                return true;
            }
        }
        return false;
    }

    // =========================================================================
    // 2. GESTIONE SESSIONE E GETTER GENERALI
    // =========================================================================
    public void setUtenteLoggato(Utente utenteLoggato) {
        this.utenteLoggato = utenteLoggato;
    }

    public Utente getUtenteLoggato() {
        return utenteLoggato;
    }

    public void login(String username, String psw) throws IllegalValueException {
        if(username == null || psw == null)
                throw new IllegalValueException("Errore: credenziali non inserite correttamente!");
        for (Utente u : listaUtenti) {
            if (username.equals(u.getUsername()) && psw.equals(u.getPsw())) {
                this.utenteLoggato = u;
                return;
            }
        }
        throw new IllegalValueException("Errore: credenziali errate!");
    }

    public void logout() {
        this.utenteLoggato = null;
    }

    public LinkedList<Film> getListaFilm() {
        return new LinkedList<>(listaFilm);
    }

    public LinkedList<Proiezione> getListaProiezioni() {
        return new LinkedList<>(listaProiezioni);
    }

    public LinkedList<Utente> getListaUtenti() {
        return new LinkedList<>(listaUtenti);
    }

    public LinkedList<Regista> getListaRegista() {
        return new LinkedList<>(listaRegisti);
    }

    // =========================================================================
    // 3. OPERAZIONI UTENTE NON AUTENTICATO (OSPITE)
    // =========================================================================
    public void registraCliente(String nome, String cognome, String username, String psw, Luogo domicilio, Data dataNascita) throws IllegalValueException {
        if(dataNascita == null)
            registraCliente(nome, cognome, username, psw, domicilio);
        verificaUsernameDisponibile(username);
        Cliente cliente = new Cliente(nome, cognome, username, psw, domicilio, dataNascita);
        listaUtenti.add(cliente);
    }

    public void registraCliente(String nome, String cognome, String username, String psw, Luogo domicilio) throws IllegalValueException {
        verificaUsernameDisponibile(username);
        Cliente cliente = new Cliente(nome, cognome, username, psw, domicilio);
        listaUtenti.add(cliente);
    }

    public Proiezione getProiezionePerId(int id) throws IllegalValueException {
        for (Proiezione p : listaProiezioni) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new IllegalValueException("Nessuna proiezione trovata con ID: " + id);
    }

    public LinkedList<Proiezione> getProiezioniPerTitolo(String titolo) {
        LinkedList<Proiezione> lista = new LinkedList<>();
        for (Proiezione p : listaProiezioni) {
            if (titolo.equalsIgnoreCase(p.getFilm().getTitolo())) {
                lista.add(p);
            }
        }
        return lista;
    }

    public LinkedList<Proiezione> getProiezionePerData(Data data) {
        LinkedList<Proiezione> lista = new LinkedList<>();
        for (Proiezione p : listaProiezioni) {
            if (data.equals(p.getData())) {
                lista.add(p);
            }
        }
        return lista;
    }

    public LinkedList<Proiezione> cercaProiezioni(String titolo, Data inizio, Data fine, Genere genere, Double costoMin, Double costoMax){
        LinkedList<Proiezione> risultato = new LinkedList<>();
        for(Proiezione p: listaProiezioni){
            boolean controllo = true;

            if(titolo != null && !p.getFilm().getTitolo().toLowerCase().contains(titolo.toLowerCase()))
                controllo = false;
            if(inizio != null && p.getData().compareTo(inizio) < 0)
                controllo = false;
            if(fine != null && p.getData().compareTo(fine) > 0)
                controllo = false;
            if(genere != null && p.getFilm().getGenere().equals(genere))
                controllo = false;
            if(costoMin != null && p.getCostoBiglietto() < costoMin)
                controllo = false;
            if(costoMax != null && p.getCostoBiglietto() > costoMax)
                controllo = false;

            if(controllo)
                risultato.add(p);
        }
        return risultato;
    }

    // =========================================================================
    // 4. OPERAZIONI ESCLUSIVE CLIENTE
    // =========================================================================
    public LinkedList<Prenotazione> getPrenotazioneUtente() throws IllegalValueException {
        verificaCliente();
        LinkedList<Prenotazione> lista = new LinkedList<>();
        for (Prenotazione p : listaPrenotazioni) {
            if (utenteLoggato.getId() == p.getUtente().getId()) {
                lista.add(p);
            }
        }
        return lista;
    }

    public void aggiungiPrenotazione(int idProiezione, int quantita) throws IllegalValueException {
        verificaCliente();
        for (Proiezione p : listaProiezioni) {
            if (idProiezione == p.getId()) {
                Prenotazione prenotazione = new Prenotazione(utenteLoggato, quantita, p);
                listaPrenotazioni.add(prenotazione);
                return;
            }
        }
        throw new IllegalValueException("Errore: L'ID non corrisponde a nessuna proiezione nel catalogo!");
    }

    public void rimuoviPrenotazione(int idPrenotazione) throws IllegalValueException {
        verificaCliente();
        for (Prenotazione p : listaPrenotazioni) {
            if (idPrenotazione == p.getId()) {
                //verificaProprietaPrenotazione(p);
                p.annullaPrenotazione();
                listaPrenotazioni.remove(p);
                return;
            }
        }
        throw new IllegalValueException("Impossibile rimuovere: l'id non corrisponde a nessuna prenotazione nel catalogo!");
    }

/*
    LA MODIFICA DI PRENOTAZIONE DEVE SOLO FAR CAMBIARE LA DATA SE LA NUOVA DATA NON E' PASSATA
    public void modificaPrenotazione(int idPrenotazione, int nuovaQuantita) throws IllegalValueException {
        verificaCliente();
        for (Prenotazione p : listaPrenotazioni) {
            if (idPrenotazione == p.getId()) {
                verificaProprietaPrenotazione(p);
                p.aggiornaQuantita(nuovaQuantita);
                return;
            }
        }
        throw new IllegalValueException("Impossibile modificare: prenotazione non trovata!");
    }

    public void modificaPrenotazione(int idPrenotazione, Proiezione nuovaProiezione, Data dataOdierna) throws IllegalValueException {
        verificaCliente();
        for(Prenotazione p: listaPrenotazioni){
            verificaProprietaPrenotazione(p);

            if(idPrenotazione == p.getId()){

                Data vecchiaData = p.getProiezione().getData();
                Data nuovaData = nuovaProiezione.getData();
                if(vecchiaData.compareTo(dataOdierna) <= 0 || nuovaData.compareTo(dataOdierna) <= 0) throw new IllegalValueException("Impossibile modificare: data già superata!");
                
                if(nuovaProiezione.getPostiLiberi() < p.getQuantita() ) throw new IllegalValueException("Impossibile eseguire: non ci sono abbastanza posti disponibili nella nuova proiezione!");

                p.getProiezione().ripristinaPosti(p.getQuantita());
                nuovaProiezione.scalaPosti(p.getQuantita());
                
                p.setProiezione(nuovaProiezione);
            }
        }
    }
    */

    // =========================================================================
    // 5. OPERAZIONI ESCLUSIVE BIGLIETTAIO
    // =========================================================================
    public void registraBigliettaio(String nome, String cognome, String username, String psw, Luogo domicilio) throws IllegalValueException {
        verificaBigliettaio();
        verificaUsernameDisponibile(username);
        Bigliettaio b = new Bigliettaio(nome, cognome, username, psw, domicilio);
        listaUtenti.add(b);
    }

    public void rimuoviBigliettaio(int idBigliettaio) throws IllegalValueException{
        verificaBigliettaio();
        for(Utente u : listaUtenti){
            if(u instanceof Bigliettaio && u.getId() == idBigliettaio)
                listaUtenti.remove(u);
        }
        throw new IllegalValueException("Errore: Bigliettaio non trovato!");
    }

    public void registraProiezionista(String nome, String cognome, String username, String psw, Luogo domicilio) throws IllegalValueException {
        verificaBigliettaio();
        verificaUsernameDisponibile(username);
        Proiezionista p = new Proiezionista(nome, cognome, username, psw, domicilio);
        listaUtenti.add(p);
    }

    public void rimuoviProiezionista(int idProiezionista) throws IllegalValueException{
        verificaBigliettaio();
        for(Utente u : listaUtenti){
            if(u instanceof Proiezionista && u.getId() == idProiezionista)
                listaUtenti.remove(u);
        }
        throw new IllegalValueException("Errore: Proiezionista non trovato!");
    }

    public LinkedList<Prenotazione> getPrenotazionePerData(Data data) throws IllegalValueException {
        verificaBigliettaio();
        LinkedList<Prenotazione> lista = new LinkedList<>();
        for (Prenotazione p : listaPrenotazioni) {
            if (data.equals(p.getProiezione().getData())) {
                lista.add(p);
            }
        }
        return lista;
    }

    public LinkedList<Prenotazione> cercaPrenotazione(Integer id, String nome, String cognome, String titolo, Data inizio, Data fine) throws IllegalValueException {
        LinkedList<Prenotazione> risultato = new LinkedList<>();
        for(Prenotazione p: listaPrenotazioni){
            boolean controllo = true;

            if(id != null && p.getId() != id.intValue())
                controllo = false;
            if(nome != null && !p.getUtente().getNome().toLowerCase().equals(nome.toLowerCase()))
                controllo = false;
            if(cognome != null && !p.getUtente().getCognome().toLowerCase().equals(cognome.toLowerCase()))
                controllo = false;
            if(titolo != null && !p.getProiezione().getFilm().getTitolo().toLowerCase().equals(titolo.toLowerCase()))
                controllo = false;
            if(inizio != null && p.getProiezione().getData().compareTo(inizio) < 0)
                controllo = false;
            if(fine != null && p.getProiezione().getData().compareTo(fine) > 0)
                controllo = false;

            if(controllo)
                risultato.add(p);
        }

        return risultato;
    }

    public LinkedList<Prenotazione> getListaPrenotazioni() throws IllegalValueException {
        verificaBigliettaio();
        return new LinkedList<>(listaPrenotazioni);
    }

    // =========================================================================
    // 6. OPERAZIONI ESCLUSIVE PROIEZIONISTA
    // =========================================================================
    public void aggiungiFilm(String titolo, int durata, int anno, int etaMin, Genere genere, Regista regista) throws IllegalValueException {
        verificaProiezionista();
        for (Film f : listaFilm) {
            if (titolo.equalsIgnoreCase(f.getTitolo())) {
                throw new IllegalValueException("Film già presente nel catalogo!");
            }
        }
        Film film = new Film(titolo, durata, anno, etaMin, genere, regista);
        listaFilm.add(film);
    }

    public void rimuoviFilm(String titolo) throws IllegalValueException {
        verificaProiezionista();
        for (Film f : listaFilm) {
            if (titolo.equalsIgnoreCase(f.getTitolo())) {
                listaFilm.remove(f);
                return;
            }
        }
        throw new IllegalValueException("Impossibile rimuovere: film non trovato!");
    }

    public void aggiungiRegista(Regista regista) throws IllegalValueException {
        verificaProiezionista();
        for (Regista r : listaRegisti) {
            if (regista.getNome().equalsIgnoreCase(r.getNome()) && regista.getCognome().equalsIgnoreCase(r.getCognome())) {
                throw new IllegalValueException("Errore: Regista già presente nel catalogo!");
            }
        }
        listaRegisti.add(regista);
    }

    public void rimuoviRegista(Regista regista) throws IllegalValueException {
        verificaProiezionista();
        for (Regista r : listaRegisti) {
            if (regista.getNome().equalsIgnoreCase(r.getNome()) && regista.getCognome().equalsIgnoreCase(r.getCognome())) {
                listaRegisti.remove(r);
            }
        }
        throw new IllegalValueException("Errore: Regista non trovato!");
    }

    public void aggiungiProiezione(Film film, Data data, Ora ora, double costoBiglietto) throws IllegalValueException {
        verificaProiezionista();
        if (!listaFilm.contains(film)) {
            throw new IllegalValueException("Errore: Il film richiesto non è presente in catalogo!");
        }
        for (Proiezione p : listaProiezioni) {
            if (data.equals(p.getData()) && ora.equals(p.getOra())) {
                throw new IllegalValueException("Impossibile aggiungere: sala già occupata!");
            }
        }
        Proiezione proiezione = new Proiezione(film, data, ora, costoBiglietto);
        listaProiezioni.add(proiezione);
    }

    public void rimuoviProiezione(int id) throws IllegalValueException {
        verificaProiezionista();
        if (haPrenotazioniAttive(id)) {
            throw new IllegalValueException("Errore: Non si può effettuare l'eliminazione della proiezione esistono prenotazioni attive!");
        }
        for (Proiezione p : listaProiezioni) {
            if (id == p.getId()) {
                listaProiezioni.remove(p);
                return;
            }
        }
        throw new IllegalValueException("Errore: Proiezione non trovata!");
    }

    public void modificaProiezione(int idProiezione, Data data, Ora ora, double costoBiglietto) throws IllegalValueException {
        verificaProiezionista();
        if (haPrenotazioniAttive(idProiezione)) {
            throw new IllegalValueException("Errore: Esistono già prenotazioni per questa proiezione non si può modificare!");
        }
        for (Proiezione p1 : listaProiezioni) {
            if (p1.getId() == idProiezione) {
                for (Proiezione p2 : listaProiezioni) {
                    if (p2.getId() != idProiezione && data.equals(p2.getData()) && ora.equals(p2.getOra())) {
                        throw new IllegalValueException("Impossibile modificare: sala già occupata in quella data e ora!");
                    }
                }
                p1.aggiornaProiezione(data, ora, costoBiglietto);
                return;
            }
        }
        throw new IllegalValueException("Impossibile modificare: proiezione non trovata nel catalogo!");
    }

    // =========================================================================
    // 7. SCRITTURA FILE
    // =========================================================================
    public void scritturaFile() {

        File file = new File("archivio.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ObjectOutputStream scrittore;

        try {

            //Stream di scrittura
            scrittore = new ObjectOutputStream(new FileOutputStream(file));

            //Scriviamo sul file una lista che contiene gli id istanziati dal quale ripartire a creare oggetti
            scrittore.writeObject(aggiornaID());

            //Dividiamo le liste con il token *
            scrittore.writeObject("*");

            //Scriviamo sul file tutta la lista di utenti registrati
            scrittore.writeObject(listaUtenti);

            //Cancelliamo tutti gli elementi dalla lista che abbiamo scritto 
            listaUtenti.clear();

            //Dividiamo le liste con il token *
            scrittore.writeObject("*");

            //Scriviamo sul file tutta la lista di utenti registrati
            scrittore.writeObject(listaFilm);

            //Cancelliamo tutti gli elementi dalla lista che abbiamo scritto 
            listaFilm.clear();

            //Dividiamo le liste con il token *
            scrittore.writeObject("*");

            //Scriviamo sul file tutta la lista delle prenotazioni registrate            
            scrittore.writeObject(listaPrenotazioni);

            //Cancelliamo tutti gli elementi dalla lista che abbiamo scritto 
            listaPrenotazioni.clear();

            //Dividiamo le liste con il token *
            scrittore.writeObject("*");

            //Scriviamo sul file tutta la lista delle proiezioni registrate            
            scrittore.writeObject(listaProiezioni);

            //Cancelliamo tutti gli elementi dalla lista che abbiamo scritto 
            listaProiezioni.clear();

            //Dividiamo le liste con il token *
            scrittore.writeObject("*");

            //Scriviamo sul file tutta la lista delle proiezioni registrate            
            scrittore.writeObject(listaRegisti);

            //Cancelliamo tutti gli elementi dalla lista che abbiamo scritto 
            listaRegisti.clear();

            //Chiusura dello stream
            scrittore.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =========================================================================
    // 8. METODO PER RIEMPIRE LE LINKEDLIST DEL PROGRAMMA ALL'AVVIO
    // =========================================================================
    //Riempiamo la lista dei film 
    public void riempiListe() {

        File file = new File("archivio.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ObjectInputStream lettore;

        try {

            lettore = new ObjectInputStream(new FileInputStream(file));

            Object oggetto;
            int contatoreSezioni = 0;

            while (true) {
                try {
                    oggetto = lettore.readObject();

                    // Quando incontri il token, incrementi il contatore e vai avanti
                    if (oggetto instanceof String && oggetto.equals("*")) {
                        contatoreSezioni++;
                        continue;
                    }

                    if (oggetto instanceof LinkedList) {
                        LinkedList<?> lista = (LinkedList<?>) oggetto;

                        switch (contatoreSezioni) {
                            case 0:
                                modificaContatoriID((LinkedList<Integer>) lista);
                                break;
                            case 1:
                                this.listaUtenti = (LinkedList<Utente>) lista;
                                break;
                            case 2:
                                this.listaFilm = (LinkedList<Film>) lista;
                                break;
                            case 3:
                                this.listaPrenotazioni = (LinkedList<Prenotazione>) lista;
                                break;
                            case 4:
                                this.listaProiezioni = (LinkedList<Proiezione>) lista;
                                break;
                            case 5:
                                this.listaRegisti = (LinkedList<Regista>) lista;
                                break;
                        }
                    }
                    if (contatoreSezioni == 5) {
                        return;
                    }

                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

// =========================================================================
// 0. LETTURA FILE (SCRUPOLO PER VERIFICARE CHE LA SCRITTURA SIA STATA EFFETTUATA)
// =========================================================================
    public void letturaFile() {

        File file = new File("archivio.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ObjectInputStream lettore;

        try {

            lettore = new ObjectInputStream(new FileInputStream(file));

            Object oggetto;
            int contatoreSezioni = 0; 

            while (true) {
                try {
                    oggetto = lettore.readObject();

                    // Quando incontri il token, incrementi il contatore e vai avanti
                    if (oggetto instanceof String && oggetto.equals("*")) {
                        contatoreSezioni++;
                        continue;
                    }

                    if (oggetto instanceof LinkedList) {
                        LinkedList<?> lista = (LinkedList<?>) oggetto;

                        switch (contatoreSezioni) {
                            case 0:
                                for (Integer i : (LinkedList<Integer>) lista) {
                                    IO.output(i.toString() + "\t");
                                }
                                IO.output("\n");
                                break;
                            case 1:
                                for (Utente u : (LinkedList<Utente>) lista) {
                                    IO.output(u.toString() + "\n", true);
                                }
                                break;
                            case 2:
                                for (Film f : (LinkedList<Film>) lista) {
                                    IO.output(f.toString() + "\n", true);
                                }
                                break;
                            case 3:
                                for (Prenotazione p : (LinkedList<Prenotazione>) lista) {
                                    IO.output(p.toString() + "\n", true);
                                }
                                break;
                            case 4:
                                for (Proiezione p : (LinkedList<Proiezione>) lista) {
                                    IO.output(p.toString() + "\n", true);
                                }
                                break;
                            case 5:
                                for (Regista r : (LinkedList<Regista>) lista) {
                                    IO.output(r.toString() + "\n", true);
                                }
                                break;
                        }
                    }
                    if (contatoreSezioni == 5) {
                        return;
                    }

                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public LinkedList<Integer> aggiornaID() {
        LinkedList<Integer> listaID = new LinkedList<Integer>();
        if (listaUtenti.isEmpty()) {
            listaID.add(0);
        } else {
            listaID.add(listaUtenti.getLast().getId());
        }

        if (listaFilm.isEmpty()) {
            listaID.add(0);
        } else {
            listaID.add(listaFilm.getLast().getId());
        }

        if (listaPrenotazioni.isEmpty()) {
            listaID.add(0);
        } else {
            listaID.add(listaPrenotazioni.getLast().getId());
        }

        if (listaProiezioni.isEmpty()) {
            listaID.add(0);
        } else {
            listaID.add(listaProiezioni.getLast().getId());
        }

        if (listaRegisti.isEmpty()) {
            listaID.add(0);
        } else {
            listaID.add(listaRegisti.getLast().getId());
        }

        return listaID;
    }

    public void modificaContatoriID(LinkedList<Integer> listaID) {
        Utente.setContaId((int) listaID.get(0));
        Film.setContaId((int) listaID.get(1));
        Prenotazione.setContaId((int) listaID.get(2));
        Proiezione.setContaId((int) listaID.get(3));
        Regista.setContaId((int) listaID.get(4));
    }


//////  METODI NON RICHIESTI LI USIAMO??

    public void rimuoviCliente (int idCliente) throws IllegalValueException{
        //verificaBigliettaio();    boh chi verificia
        for(Utente u : listaUtenti){
            if(u instanceof Cliente && u.getId() == idCliente)
                listaUtenti.remove(u);
        }
        throw new IllegalValueException("Errore: Cliente non trovato!");
    }

}