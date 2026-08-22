
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Rappresenta un film composto da id, durata, anno. etaMin, titolo, genere,
 * regista
 *
 * Implementa {@link Serializable} per consentire la serializzazione degli
 * oggetti.
 */
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * contaId.
     */
    private static int contaId = -1;
    /**
     * id.
     */
    private int id;
    /**
     * durata del film.
     */
    private int durata;
    /**
     * anno di uscita film.
     */
    private int anno;
    /**
     * età minima per vedere il film.
     */
    private int etaMin;
    /**
     * titolo del film.
     */
    private String titolo;
    /**
     * genere del film.
     */
    private Genere genere;
    /**
     * regista del film.
     */
    private Regista regista;
    /**
     * anno limite per l'inserimento dei film (può inserire al massimo fino
     * all'anno successivo al corrente).
     */
    private static final int ANNO_LIMITE = LocalDate.now().getYear() + 1;

    /**
     * Costruisce un nuovo oggetto {@code Film}. Tramite i set effettua
     * verifiche ai parametri.
     *
     * @param titolo il titolo del film
     * @param durata la durata del film
     * @param anno l'anno di uscita del film
     * @param etaMin l'etaMin per vedere il film film
     * @param genere il genere del film
     * @param regista il regista del film
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta
     * i vincoli di integrità
     */
    public Film(String titolo, int durata, int anno, int etaMin, Genere genere, Regista regista) throws IllegalValueException {
        this.id = ++contaId;
        setTitolo(titolo);
        setDurata(durata);
        setAnno(anno);
        setEtaMin(etaMin);
        setGenere(genere);
        setRegista(regista);
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
     * Restituisce il titolo.
     *
     * @return titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Restituisce la durata.
     *
     * @return durata
     */
    public int getDurata() {
        return durata;
    }

    /**
     * Restituisce l'anno.
     *
     * @return anno
     */
    public int getAnno() {
        return anno;
    }

    /**
     * Restituisce l'eta minima.
     *
     * @return etaMin
     */
    public int getEtaMin() {
        return etaMin;
    }

    /**
     * Restituisce il genere.
     *
     * @return genere
     */
    public Genere getGenere() {
        return genere;
    }

    /**
     * Restituisce il regista.
     *
     * @return regista
     */
    public Regista getRegista() {
        return regista;
    }

    /**
     * Imposta e valida il contaId.
     *
     * @param contaId il contaId da settare
     */
    public static void setContaId(int contaId) {
        Film.contaId = contaId;
    }

    /**
     * Imposta e valida il titolo.
     *
     * @param titolo il titolo da settare
     */
    private void setTitolo(String titolo) throws IllegalValueException {
        if (titolo == null || titolo.trim().isEmpty()) {
            throw new IllegalValueException("Errore: Titolo non valido!");
        }
        this.titolo = titolo.trim();
    }

    /**
     * Imposta e valida la durata.
     *
     * @param durata la durata da settare
     */
    private void setDurata(int durata) throws IllegalValueException {
        if (durata <= 0) {
            throw new IllegalValueException("Errore: Durata non valida!");
        }
        this.durata = durata;
    }

    /**
     * Imposta e valida l'anno.
     *
     * @param anno l'anno da settare
     */
    private void setAnno(int anno) throws IllegalValueException {
        if (anno < 1985 || anno > ANNO_LIMITE) {
            throw new IllegalValueException("Errore: Anno inserito minore di 1985 o maggiore di " + ANNO_LIMITE + "!");
        }
        this.anno = anno;
    }

    /**
     * Imposta e valida l'eta minima.
     *
     * @param etaMin l'etaMin da settare
     */
    private void setEtaMin(int etaMin) throws IllegalValueException {
        if (etaMin < 0) {
            throw new IllegalValueException("Errore: Età non valida!");
        }
        this.etaMin = etaMin;
    }

    /**
     * Imposta e valida il genere.
     *
     * @param genere il genere da settare
     */
    private void setGenere(Genere genere) {
        this.genere = genere;
    }

    /**
     * Imposta e valida il regista.
     *
     * @param regista il regista da settare
     */
    private void setRegista(Regista regista) {
        this.regista = regista;
    }

    /**
     * Restituisce la stringa corrispondente al titolo, durata, anno, età
     * minima, genere, regista del film.
     *
     * Il formato è: {@code titolo, durata, anno, età minima, genere, regista}
     *
     * @return la stringa corrispondente al film
     */
    @Override
    public String toString() {
        return "- FILM #" + id + "\n"
                + "\tTitolo: " + titolo + "\n"
                + "\tDurata: " + durata + " min | Anno: " + anno + " | VM" + etaMin + "\n"
                + "\tGenere: " + genere + "\n"
                + "\t" + regista;
    }
}
