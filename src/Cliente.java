public class Cliente extends Utente{

    public Cliente(String nome, String cognome, String username, String psw, Luogo domicilio, Data dataNascita) throws IllegalValueException {
        super(nome, cognome, username, psw, domicilio, dataNascita, Ruolo.CLIENTE);
    }

    public Cliente(String nome, String cognome, String username, String psw, Luogo domicilio) throws IllegalValueException {
        super(nome, cognome, username, psw, domicilio, Ruolo.CLIENTE);
    }
}
