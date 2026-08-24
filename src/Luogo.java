/*
AUTORI:
- Hajjoubi, Omar, 766954, VA
- Mare, Filippo, 766773, VA
- Martignoni, Giorgio, 766932, VA
- Pica, Simone, 765155, VA
*/
import java.io.Serializable;

/**
 * Rappresenta un indirizzo composto da via, numero civico, città e cap.
 * Implementa {@link Serializable} per consentire la serializzazione degli oggetti.
 */
public class Luogo implements Serializable {

    /** Nome della via.*/
    private String via;

    /** Nome della città.*/
    private String citta;

    /** Codice cap. */
    private String cap;

    /** Numero civico dell'indirizzo. */
    private int numC;

    /**
     * Costruisce un nuovo oggetto {@code Luogo}.
     * Tramite i set effettua verifiche ai parametri.
     * 
     * @param via   il nome della via
     * @param numC  il numero civico 
     * @param citta il nome della città
     * @param cap   il codice cap
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta i vincoli di integrità 
     */
    public Luogo(String via, int numC, String citta, String cap) throws IllegalValueException {
        setVia(via);
        setNumC(numC);
        setCitta(citta);
        setCAP(cap);
    }

    /**
     * Restituisce il nome della via.
     *
     * @return il nome della via
     */
    public String getVia() {
        return via;
    }

    /**
     * Restituisce il numero civico.
     *
     * @return il numero civico
     */
    public int getNumC() {
        return numC;
    }

    /**
     * Restituisce il nome della città.
     *
     * @return il nome della città
     */
    public String getCitta() {
        return citta;
    }

    /**
     * Restituisce il cap.
     *
     * @return il codice cap
     */
    public String getCAP() {
        return cap;
    }

    /**
     * Imposta e valida il nome della via.
     *
     * @param via il nome della via da settare
     * @throws IllegalValueException se la via è {@code null} o vuota
     */
    private void setVia(String via) throws IllegalValueException {
        if (via == null || via.trim().isEmpty()) {
            throw new IllegalValueException("Errore: Formato via non valida!");
        }
        this.via = via.trim();
    }

    /**
     * Imposta e valida il numero civico.
     *
     * @param numC il numero civico da settare
     * @throws IllegalValueException se il numero civico è inferiore o uguale a zero
     */
    private void setNumC(int numC) throws IllegalValueException {
        if (numC <= 0) {
            throw new IllegalValueException("Errore: Numero civico non valido!");
        }
        this.numC = numC;
    }

    /**
     * Imposta e valida il nome della città.
     *
     * @param citta il nome della città da settare
     * @throws IllegalValueException se la città è {@code null} o vuota
     */
    private void setCitta(String citta) throws IllegalValueException {
        if (citta == null || citta.trim().isEmpty()) {
            throw new IllegalValueException("Errore: Formato città non valida!");
        }
        this.citta = citta.trim();
    }

    /**
     * Imposta e valida il cap.
     *
     * @param cap il cap da impostare (deve contenere esattamente 5 caratteri numerici)
     * @throws IllegalValueException se il cap è {@code null}, vuoto, di lunghezza diversa da 5 o contiene caratteri non numerici
     */
    private void setCAP(String cap) throws IllegalValueException {
        if (cap == null || cap.trim().isEmpty() || cap.length() != 5) {
            throw new IllegalValueException("Errore: Formato CAP non valido!");
        }
        for (int i = 0; i < cap.length(); i++) {
            char c = cap.charAt(i);
            if (!Character.isDigit(c)) {
                throw new IllegalValueException("Errore: Formato CAP non valido, i valori inseriti non sono tutti numeri!");
            }
        }
        this.cap = cap.trim();
    }

    /**
     * Restituisce la stringa corrispondente all'indirizzo completo.
     * Il formato è: {@code via numC, citta, cap} 
     *
     * @return la stringa contenente all'indirizzo formattato
     */
    @Override
    public String toString() {
        return via + " " + numC + ", " + citta + ", " + cap;
    }
}
