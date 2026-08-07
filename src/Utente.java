public abstract class Utente {
    protected static int contaId = 0;
    private int id;
    private String nome, cognome, username, psw;
    private Luogo domicilio;
    private Data dataNascita;
    private Ruolo ruolo;
}
