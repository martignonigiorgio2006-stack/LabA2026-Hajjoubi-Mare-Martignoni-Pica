import java.io.Serializable;

public abstract class Utente implements Serializable {
    private static int contaId = 0;
    private int id;
    private String nome, cognome, username, psw;
    private Luogo domicilio;
    private Data dataNascita;
    private Ruolo ruolo;


    //COSTRUTTORI
    public Utente(String nome, String cognome, String username, String psw, Luogo domicilio, Data dataNascita, Ruolo ruolo) throws IllegalValueException{
        this.id = contaId++;
        setNome(nome);
        setCognome(cognome);
        setUsername(username);
        setPsw(psw);
        setDomicilio(domicilio);
        setDataNascita(dataNascita);
        setRuolo(ruolo);
    }

    public Utente(String nome, String cognome, String username, String psw, Luogo domicilio, Ruolo ruolo) throws IllegalValueException{
        this(nome, cognome, username, psw, domicilio, null, ruolo);
    }


    //GETTER
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getUsername() {
        return username;
    }

    public String getPsw() {
        return psw;
    }

    public Luogo getDomicilio() {
        return domicilio;
    }

    public Data getDataNascita() {
        return dataNascita;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }


    //SETTER
    private void setNome(String nome) throws IllegalValueException {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalValueException("Errore: Nome non valido!");
        this.nome = nome.trim();
    }

    private void setCognome(String cognome) throws IllegalValueException {
        if (cognome == null || cognome.trim().isEmpty()) throw new IllegalValueException("Errore: Cognome non valido!");
        this.cognome = cognome.trim();
    }

    private void setUsername(String username) throws IllegalValueException {
        if (username == null || username.trim().isEmpty()) throw new IllegalValueException("Errore: Username non valido!");
        this.username = username.trim();
    }

    private void setPsw(String psw) throws IllegalValueException {
        if (psw == null || psw.trim().length() < 8) throw new IllegalValueException("Errore: Password non valida (almeno 8 caratteri)!");
        this.psw = psw;
    }

    private void setDomicilio(Luogo domicilio) throws IllegalValueException {
        if (domicilio == null) {
            throw new IllegalValueException("Errore: Domicilio obbligatorio!");
        }
        this.domicilio = domicilio;
    }

    private void setDataNascita(Data dataNascita) throws IllegalValueException {
        this.dataNascita = dataNascita;
    }

    private void setRuolo(Ruolo ruolo) throws IllegalValueException {
        if (ruolo == null) throw new IllegalValueException("Errore: Ruolo obbligatorio!");
        this.ruolo = ruolo;
    }


    //ToString
    @Override
    public String toString() {
        return "UTENTE #" + id + "\n" +
                "Nome completo: " + nome + " " + cognome + "\n" +
                "Username: " + username + "\n" +
                "Domicilio: " + domicilio + "\n" +
                "Nascita: " + (dataNascita != null ? dataNascita : "Non inserita") + "\n" +
                "Ruolo: " + ruolo;
    }
}
