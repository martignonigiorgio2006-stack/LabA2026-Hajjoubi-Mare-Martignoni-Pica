
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Rappresenta un utente composto da id, nome, cognome, username, psw,
 * domicilio, dataNascita, ruolo
 *
 * Implementa {@link Serializable} per consentire la serializzazione degli
 * oggetti.
 */
public abstract class Utente implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * contaId.
     */
    private static int contaId = -1;
    /**
     * Id.
     */
    private int id;
    /**
     * nome.
     */
    private String nome;
    /**
     * cognome.
     */
    private String cognome;
    /**
     * username.
     */
    private String username;
    /**
     * password.
     */
    //private String psw;
    private transient String psw;

    /**
     * domiclio.
     */
    private Luogo domicilio;
    /**
     * data di nascita.
     */
    private Data dataNascita;
    /**
     * ruolo.
     */
    private Ruolo ruolo;

    /**
     * Costruisce un nuovo oggetto {@code Utente}.
     *
     * Tramite i set effettua verifiche ai parametri.
     *
     * L'id è auto-incrementato.
     *
     * @param nome il nome del utente
     * @param cognome il cognome del utente
     * @param username lo username del utente
     * @param psw la password del utente
     * @param domicilio il domicilio del utente
     * @param dataNascita la data di nascita del utente
     * @param ruolo il ruolo del utente
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta
     * i vincoli di integrità
     */
    public Utente(String nome, String cognome, String username, String psw, Luogo domicilio, Data dataNascita, Ruolo ruolo) throws IllegalValueException {
        this.id = ++contaId;
        setNome(nome);
        setCognome(cognome);
        setUsername(username);
        setPsw(psw);
        setDomicilio(domicilio);
        setDataNascita(dataNascita);
        setRuolo(ruolo);
    }

    /**
     * Costruisce un nuovo oggetto {@code Utente}.
     *
     * Tramite i set effettua verifiche ai parametri.
     *
     * @param nome il nome del utente
     * @param cognome il cognome del utente
     * @param username il username del utente
     * @param psw il password del utente
     * @param domicilio il domicilio del utente
     * @param ruolo il ruolo del utente
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta
     * i vincoli di integrità
     *
     */
    public Utente(String nome, String cognome, String username, String psw, Luogo domicilio, Ruolo ruolo) throws IllegalValueException {
        this(nome, cognome, username, psw, domicilio, null, ruolo);
    }

    /**
     * Restituisce l'id.
     *
     * @return id
     *
     */
    public int getId() {
        return id;
    }

    /**
     * Restituisce il nome.
     *
     * @return nome
     *
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il cognome.
     *
     * @return cognome
     *
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Restituisce lo username.
     *
     * @return username
     *
     */
    public String getUsername() {
        return username;
    }

    /**
     * Restituisce la password.
     *
     * @return psw
     *
     */
    public String getPsw() {
        return psw;
    }

    /**
     * Restituisce il domicilio.
     *
     * @return domicilio
     *
     */
    public Luogo getDomicilio() {
        return domicilio;
    }

    /**
     * Restituisce la data di nascita.
     *
     * @return dataNascita
     *
     */
    public Data getDataNascita() {
        return dataNascita;
    }

    /**
     * Restituisce il ruolo.
     *
     * @return ruolo
     *
     */
    public Ruolo getRuolo() {
        return ruolo;
    }

    /**
     * Imposta il conta id.
     *
     * @param contaId il contatore degli ID
     */
    public static void setContaId(int contaId) {
        Utente.contaId = contaId;
    }

    /**
     * Imposta e valida il nome.
     *
     * @param nome il nome utente
     * @throws IllegalValueException se il nome non viene inserito
     */
    private void setNome(String nome) throws IllegalValueException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalValueException("Errore: Nome non valido!");
        }
        this.nome = nome.trim();
    }

    /**
     * Imposta e valida il cognome.
     *
     * @param cognome il cognome utente
     * @throws IllegalValueException se il cognome non viene inserito
     */
    private void setCognome(String cognome) throws IllegalValueException {
        if (cognome == null || cognome.trim().isEmpty()) {
            throw new IllegalValueException("Errore: Cognome non valido!");
        }
        this.cognome = cognome.trim();
    }

    /**
     * Imposta e valida lo username.
     *
     * @param username lo username
     * @throws IllegalValueException se lo username non viene inserito
     */
    private void setUsername(String username) throws IllegalValueException {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalValueException("Errore: Username non valido!");
        }
        this.username = username.trim();
    }

    /**
     * Imposta e valida la password.
     *
     * @param psw pa password
     * @throws IllegalValueException se la password non viene inserita oppure ha
     * meno di 8 caratteri
     */
    private void setPsw(String psw) throws IllegalValueException {
        if (psw == null || psw.trim().length() < 8) {
            throw new IllegalValueException("Errore: Password non valida (almeno 8 caratteri)!");
        }
        this.psw = psw.trim();
    }

    /**
     * Imposta e valida il domicilio.
     *
     * @param domicilio il domicilio
     * @throws IllegalValueException se il domicilio non viene inserito
     */
    private void setDomicilio(Luogo domicilio) throws IllegalValueException {
        if (domicilio == null) {
            throw new IllegalValueException("Errore: Domicilio obbligatorio!");
        }
        this.domicilio = domicilio;
    }

    /**
     * Imposta la data di nascita.
     *
     * @param dataNascita la data di nascita
     */
    private void setDataNascita(Data dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * Imposta e valida il ruolo.
     *
     * @param ruolo il ruolo
     * @throws IllegalValueException se il ruolo non viene inserito
     *
     */
    private void setRuolo(Ruolo ruolo) throws IllegalValueException {
        if (ruolo == null) {
            throw new IllegalValueException("Errore: Ruolo obbligatorio!");
        }
        this.ruolo = ruolo;
    }

    /**
     * Restituisce la stringa corrispondente all'utente. Il formato è:
     * {@code nome, cognome, username, domicilio, nascita, ruolo}
     *
     * @return la stringa corrispondente all'utente formattata
     */
    @Override
    public String toString() {
        return "- UTENTE #" + id + "\n"
                + "\tNome completo: " + nome + " " + cognome + "\n"
                + "\tUsername: " + username + "\n"
                + "\tDomicilio: " + domicilio + "\n"
                + "\tNascita: " + (dataNascita != null ? dataNascita : "Non inserita") + "\n"
                + "\tRuolo: " + ruolo;
    }




    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(cifra(psw));
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        psw = decifra((String) in.readObject());
    }

    private String cifra(String testo) {
        String chiave = "CineMax";
        StringBuilder risultato = new StringBuilder();

        for (int i = 0; i < testo.length(); i++) {
            risultato.append((char) (testo.charAt(i) ^ chiave.charAt(i % chiave.length())));
        }

        return java.util.Base64.getEncoder().encodeToString(
                risultato.toString().getBytes()
        );
    }

    private String decifra(String testo) {
        String chiave = "CineMax";
        byte[] dati = java.util.Base64.getDecoder().decode(testo);
        String cifrato = new String(dati);

        StringBuilder risultato = new StringBuilder();

        for (int i = 0; i < cifrato.length(); i++) {
            risultato.append((char) (cifrato.charAt(i) ^ chiave.charAt(i % chiave.length())));
        }

        return risultato.toString();
    }

}
