public class Proiezionista extends Utente{

    public Proiezionista(String nome, String cognome, String username, String psw, Luogo domicilio) throws IllegalValueException {
        super(nome, cognome, username, psw, domicilio, Ruolo.PROIEZIONISTA);
    }
    //solo un costruttore perché ho deciso che i bigliettai e i proiezionisti che sono interni del cinema non registrano la loro data di nascita per comodità visto che poco importante, mentre agli utenti do' la scelta
}
