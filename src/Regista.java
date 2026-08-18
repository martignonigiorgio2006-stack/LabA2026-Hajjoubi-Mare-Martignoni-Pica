
import java.io.Serializable;

public class Regista implements Serializable {

    private static int contaId = -1;
    private int id;
    private String nome;
    private String cognome;

    //COSTRUTTORE
    public Regista(String nome, String cognome) throws IllegalValueException {
        this.id = ++contaId;
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
    public static void setContaId(int contaId) {
        Regista.contaId = contaId;
    }

    public void setNome(String nome) throws IllegalValueException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalValueException("Errore: nome non valido");
        }
        this.nome = nome.trim();
    }

    public void setCognome(String cognome) throws IllegalValueException {
        if (cognome == null || cognome.trim().isEmpty()) {
            throw new IllegalValueException("Errore: cognome non valido");
        }
        this.cognome = cognome.trim();
    }

    //ToString
    @Override
    public String toString() {
        return "Regista #" + id + " " + nome + " " + cognome;
    }
}
