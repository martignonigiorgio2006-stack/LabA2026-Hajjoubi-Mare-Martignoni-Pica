/*
AUTORI:
- Hajjoubi, Omar, 766954, VA
- Mare, Filippo, 766773, VA
- Martignoni, Giorgio, 766932, VA
- Pica, Simone, 765155, VA
*/
import java.io.Serializable;
/**
 * Rappresenta un regista composto da id, nome, cognome. Implementa
 * {@link Serializable} per consentire la serializzazione degli oggetti.
 */
public class Regista implements Serializable {

    /**
     * Identificatore della versione della classe utilizzato durante la serializzazione.
     */
    private static final long serialVersionUID = 1L;

    /**
     * contaId.
     */
    private static int contaId = -1;
    /**
     * id del regista.
     */
    private final int id;
    /**
     * nome del regista.
     */
    private String nome;
    /**
     * cognome regista.
     */
    private String cognome;

    /**
     * Costruisce un nuovo oggetto {@link Regista}. Tramite i set effettua
     * verifiche ai parametri.
     *
     * @param nome il nome del regista
     * @param cognome il cognome del regista
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta
     * i vincoli di integrità
     */
    public Regista(String nome, String cognome) throws IllegalValueException {
        this.id = ++contaId;
        setNome(nome);
        setCognome(cognome);
    }

    /**
     * Restituisce l'id.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Restituisce il nome.
     *
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il cognome.
     *
     * @return cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta e valida il contaId.
     *
     * @param contaId il contaId da settare
     */
    public static void setContaId(int contaId) {
        Regista.contaId = contaId;
    }

    /**
     * Imposta e valida il nome.
     *
     * @param nome il nome da settare
     * @throws IllegalValueException se il nome è {@link null} o vuota
     */
    public void setNome(String nome) throws IllegalValueException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalValueException("Errore: nome non valido");
        }
        this.nome = nome.trim();
    }

    /**
     * Imposta e valida il cognome.
     *
     * @param cognome il cognime da settare
     * @throws IllegalValueException se il cognome è {@link null} o vuota
     */
    public void setCognome(String cognome) throws IllegalValueException {
        if (cognome == null || cognome.trim().isEmpty()) {
            throw new IllegalValueException("Errore: cognome non valido");
        }
        this.cognome = cognome.trim();
    }

    /**
     * Restituisce la stringa corrispondente al nome e il cognome del regista.
     *
     * @return la stringa corrispondente al nome e cognome
     */
    @Override
    public String toString() {
        return "Regista #" + id + ": " + nome + " " + cognome;
    }
}
