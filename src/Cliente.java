/*
AUTORI:
- Hajjoubi, Omar, 766954, VA
- Mare, Filippo, 766773, VA
- Martignoni, Giorgio, 766932, VA
- Pica, Simone, 765155, VA
*/
/**
 * Rappresenta un cliente composto da id, nome, cognome, username,
 * psw, domicilio, dataNascita (opzionale), ruolo.
 *
 * Questa è una sottoclasse di {@link Utente}.
 *
 */
public class Cliente extends Utente{

    /**
     * Costruisce un nuovo oggetto {@link Cliente} tramite la chiamata a un costruttore della superclasse.
     * Ruolo viene impostato in automatico.
     *
     * @param nome il nome del utente
     * @param cognome il cognome del utente
     * @param username lo username del utente
     * @param psw la password del utente
     * @param domicilio il domicilio del utente
     * @param dataNascita la data di nascita del utente
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta i vincoli di integrità
     */
    public Cliente(String nome, String cognome, String username, String psw, Luogo domicilio, Data dataNascita) throws IllegalValueException {
        if(dataNascita == null)
            new Cliente(nome, cognome, username, psw, domicilio);
        super(nome, cognome, username, psw, domicilio, dataNascita, Ruolo.CLIENTE);
    }

    /**
     * Costruisce un nuovo oggetto {@link Cliente} tramite la chiamata a un costruttore della superclasse.
     * Ruolo viene impostato in automatico.
     *
     * @param nome il nome del utente
     * @param cognome il cognome del utente
     * @param username lo username del utente
     * @param psw la password del utente
     * @param domicilio il domicilio del utente
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta i vincoli di integrità
     */
    public Cliente(String nome, String cognome, String username, String psw, Luogo domicilio) throws IllegalValueException {
        super(nome, cognome, username, psw, domicilio, Ruolo.CLIENTE);
    }
}
