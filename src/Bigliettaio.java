/**
 * Rappresenta un bigliettaio composto da id, nome, cognome, username,
 * psw, domicilio, ruolo.
 *
 * Questa è una sottoclasse di {@code Utente}.
 *
 */
public class Bigliettaio extends Utente{

    /**
     * Costruisce un nuovo oggetto {@code Bigliettaio} tramite la chiamata a un costruttore della superclasse.
     * Ruolo viene impostato in automatico.
     *
     * @param nome il nome del utente
     * @param cognome il cognome del utente
     * @param username lo username del utente
     * @param psw la password del utente
     * @param domicilio il domicilio del utente
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta i vincoli di integrità
     */
    public Bigliettaio(String nome, String cognome, String username, String psw, Luogo domicilio) throws IllegalValueException {
        super(nome, cognome, username, psw, domicilio, Ruolo.BIGLIETTAIO);
    }

}
