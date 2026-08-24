/*
AUTORI:
- Hajjoubi, Omar, 766954, VA
- Mare, Filippo, 766773, VA
- Martignoni, Giorgio, 766932, VA
- Pica, Simone, 765155, VA
*/
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Rappresenta una data composta da giorno, mese e anno
 *
 * Implementa {@link Serializable} per consentire la serializzazione degli
 * oggetti.
 */
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * giorno.
     */
    private int giorno;

    /**
     * mese.
     */
    private int mese;

    /**
     * anno.
     */
    private int anno;

    /**
     * Carattere utilizzato per settare il formato gg:mm:aaaa.
     *
     */
    private static final char TOKEN = '/';

    /**
     * Mesi dell'anno.
     */
    private final int MESI = 12;
    /**
     * Anno corrente (limite date di nascita).
     */
    private static final int ANNO_CORRENTE = LocalDate.now().getYear();

    /**
     * Costruisce un nuovo oggetto {@code Data}. Tramite i set effettua
     * verifiche ai parametri.
     *
     * @param giorno giorno
     * @param mese mese
     * @param anno anno
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta
     * i vincoli di integrità
     */
    public Data(int giorno, int mese, int anno) throws IllegalValueException {
        setAnno(anno);
        setMese(mese);
        setGiorno(giorno);
    }

    /**
     * Restituisce quanti giorni ha il mese riciesto.
     *
     * @param mese mese
     * @return quanti giorni ha il mese riciesto
     */
    private int getMaxGiorniMese(int mese) {
        switch (mese) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isBisestile() ? 29 : 28;
            default:
                return 31;
        }
    }

    /**
     * Restituisce il giorno.
     *
     * @return il giorno
     */
    public int getGiorno() {
        return this.giorno;
    }

    /**
     * Imposta e valida il giorno.
     *
     * @param giorno il giorno da settare
     * @throws IllegalValueException se il giorno è minore di 1 oppure il giorno
     * è maggiore dei giorni di quel mese
     */
    private void setGiorno(int giorno) throws IllegalValueException {
        if (giorno < 1 || giorno > getMaxGiorniMese(this.mese)) {
            throw new IllegalValueException("Errore: Giorno inserito non valido!");
        }
        this.giorno = giorno;
    }

    /**
     * Restituisce il mese.
     *
     * @return il mese
     */
    public int getMese() {
        return this.mese;
    }

    /**
     * Imposta e valida il mese.
     *
     * @param mese il mese da settare
     * @throws IllegalValueException se il mese è minore di 1 oppure meggiore di
     * 12
     */
    private void setMese(int mese) throws IllegalValueException {
        if (mese < 1 || mese > 12) {
            throw new IllegalValueException("Errore: Mese inserito non valido!");
        }
        this.mese = mese;
    }

    /**
     * Restituisce l'anno.
     *
     * @return l'anno
     */
    public int getAnno() {
        return this.anno;
    }

    /**
     * Imposta e valida l'anno.
     *
     * @param anno l'anno da settare
     * @throws IllegalValueException se l'anno è minore di 1900
     */
    private void setAnno(int anno) throws IllegalValueException {
        if (anno < 1900 || anno > ANNO_CORRENTE) {
            throw new IllegalValueException("Errore: Anno inserito non valido!");
        }
        this.anno = anno;
    }

    /**
     * Restituisce se l'anno è bisestile o no.
     *
     * @return true se l'anno è bisestile, false altrimenti
     */
    private boolean isBisestile() {
        return (((anno % 4 == 0) && (anno % 100 != 0)) || (anno % 400 == 0));
    }

    /**
     * Confronta due date.
     *
     * @param altra la seconda data
     * @return un valore negativo se la prima data è precedente alla seconda, 0
     * se le due date sono uguali, un valore positivo se la prima data è
     * successiva alla seconda,
     */
    public int compareTo(Data altra) {
        if (this.anno != altra.getAnno()) {
            return this.anno - altra.getAnno();
        }
        if (this.mese != altra.getMese()) {
            return this.mese - altra.getMese();
        }
        return this.giorno - altra.getGiorno();
    }

    /**
     * Restituisce se i due oggetti sono uguali oppure no.
     *
     * @param obj il secondo oggetto
     * @return true se le due date sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data altra = (Data) obj;
        return giorno == altra.giorno && mese == altra.mese && anno == altra.anno;
    }

    /**
     * Restituisce la stringa corrispondente alla data. Il formato è:
     * {@code giorno, mese, anno}
     *
     * @return la stringa corrispondente alla data
     */
    @Override
    public String toString() {
        return "" + giorno + TOKEN + mese + TOKEN + anno;
    }

}
