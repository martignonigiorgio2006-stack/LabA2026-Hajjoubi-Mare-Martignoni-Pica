import java.util.LinkedList;

public class GestoreCinema {

    private LinkedList<Utente> listaUtenti;
    private LinkedList<Film> listaFilm;
    private LinkedList<Proiezione> listaProiezioni;
    private LinkedList<Prenotazione> listaPrenotazioni;
    private Utente utenteLoggato;

    public GestoreCinema() {
        this.listaUtenti = new LinkedList<Utente>();
        this.listaFilm = new LinkedList<Film>();
        this.listaProiezioni = new LinkedList<Proiezione>();
        this.listaPrenotazioni = new LinkedList<Prenotazione>();
        this.utenteLoggato = null;
    }

    //SETTER
    public void setUtenteLoggato(Utente utenteLoggato) {
        this.utenteLoggato = utenteLoggato;
    }

    //GETTER
    public Utente getUtenteLoggato() {
        return utenteLoggato;
    }


    //METODI PER LOGIN E LOGOUT
    public void login(String username, String psw) throws IllegalValueException{
        for(Utente u: listaUtenti){
            if(username.equals(u.getUsername()) && psw.equals(u.getPsw())) {
                this.utenteLoggato = u;
                return;
            }
        }
        throw new IllegalValueException("Credenziali errate");
    }

    public void logout(){
        this.utenteLoggato = null;
    }


    //METODO PER REGISTRARSI
    private void verificaUsernameDisponibile(String username) throws IllegalValueException {
        for (Utente u : listaUtenti) {
            if (username.equals(u.getUsername())) {
                throw new IllegalValueException("Username già in uso!");
            }
        }
    }

    public void registraCliente(String nome, String cognome, String username, String psw, Luogo domicilio, Data dataNascita) throws IllegalValueException{
        verificaUsernameDisponibile(username);
        Cliente cliente = new Cliente(nome, cognome, username, psw, domicilio, dataNascita);
        listaUtenti.add(cliente);
        this.utenteLoggato = cliente;
    }

    public void registraCliente(String nome, String cognome, String username, String psw, Luogo domicilio) throws IllegalValueException{
        verificaUsernameDisponibile(username);
        Cliente cliente = new Cliente(nome, cognome, username, psw, domicilio);
        listaUtenti.add(cliente);
        this.utenteLoggato = cliente;
    }

    public void registraBigliettaio(String nome, String cognome, String username, String psw, Luogo domicilio) throws IllegalValueException {
        verificaUsernameDisponibile(username);
        Bigliettaio b = new Bigliettaio(nome, cognome, username, psw, domicilio);
        listaUtenti.add(b);
    }

    public void registraProiezionista(String nome, String cognome, String username, String psw, Luogo domicilio) throws IllegalValueException {
        verificaUsernameDisponibile(username);
        Proiezionista p = new Proiezionista(nome, cognome, username, psw, domicilio);
        listaUtenti.add(p);
    }


    //METODI FILM
    public void aggiungiFilm(String titolo, int durata, int anno, int etaMin, String genere, Regista regista) throws IllegalValueException{
        for (Film f : listaFilm) {
            if (titolo.equalsIgnoreCase(f.getTitolo())) throw new IllegalValueException("Film già presente nel catalogo!");
        }
        Film film = new Film(titolo, durata, anno, etaMin, genere, regista);
        listaFilm.add(film);
    }

    public void rimuoviFilm(String titolo) throws IllegalValueException{
        for (Film f : listaFilm) {
            if (titolo.equalsIgnoreCase(f.getTitolo())) {
                listaFilm.remove(f);
                return;
            }
        }
        throw new IllegalValueException("Impossibile rimuovere: film non trovato!");
    }

    //METODI PROIEZIONI
    public void aggiungiProiezione(Film film, Data data, Ora ora, double costoBiglietto, int postiLiberi) throws IllegalValueException{
        if(listaFilm.contains(film)) throw new IllegalValueException("Impossibile creare proiezione: film non presente in catalogo!");
        for(Proiezione p: listaProiezioni){
            if(data.equals(p.getData()) && ora.equals(p.getOra())) throw new IllegalValueException("Impossibile aggiungere: sala già occupata!");
        }
        Proiezione proiezione = new Proiezione(film, data, ora, costoBiglietto, postiLiberi);
        listaProiezioni.add(proiezione);
    }

    public void rimuoviProiezione(int id) throws IllegalValueException{
        for(Proiezione p: listaProiezioni){
            if(id == p.getId()) {
                listaProiezioni.remove(p);
                return;
            }
        }
        throw new IllegalValueException("Impossibile rimuovere: proiezione non trovata!");
    }


    //METODI PRENOTAZIONI
    public void aggiungiPrenotazione(int idProiezione, int quantita) throws IllegalValueException{
        if (utenteLoggato == null) throw new IllegalValueException("Devi prima effettuare il login per effettuare una prenotazione!");
        //qua nel main farei che dopo questo messaggio gli va a chiedere se vuole registrarsi oppure lo manda direttamente sul metodo RegistraCliente
        for(Proiezione p: listaProiezioni){
            if(idProiezione == p.getId()){
                Prenotazione prenotazione = new Prenotazione(utenteLoggato, quantita, p);
                listaPrenotazioni.add(prenotazione);
                return;
            }
        }
        throw new IllegalValueException("Errore: l'id non corrisponde a nessuna proiezione nel catalogo!");
    }

    public void rimuoviPrenotazione(int idPrenotazione) throws IllegalValueException{
        if (utenteLoggato == null) throw new IllegalValueException("Devi prima effettuare il login per cancellare una prenotazione!");
        for(Prenotazione p: listaPrenotazioni){
            if(idPrenotazione == p.getId()){
                p.annullaPrenotazione();
                listaPrenotazioni.remove(p);
                return;
            }
        }
        throw new IllegalValueException("Impossibile rimuovere: l'id non corrisponde a nessuna prenotazione nel catalogo!");
    }

    //METODI DI RICERCA E VISUALIZZAZIONE

}
