public class Regista {
    private static int contaId = 0;
    private int id;
    private String nome;
    private String cognome;

    //COSTRUTTORE
    public Regista(int id, String nome, String cognome) {
        setId(id);
        setNome(nome);
        setCognome(cognome);
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

    //SETTER
    public void setId(int id) {
        this.id = contaId++;
    }

    public void setNome(String nome) {
        if(nome == null || nome.trim().isEmpty()) throw new IllegalValue("Errore: nome non valido");
        this.nome = nome.trim().toLowerCase();
    }

    public void setCognome(String cognome) {
        if(cognome == null || cognome.trim().isEmpty()) throw new IllegalValue("Errore: cognome non valido");
        this.cognome = cognome.trim().toLowerCase();
    }
}
