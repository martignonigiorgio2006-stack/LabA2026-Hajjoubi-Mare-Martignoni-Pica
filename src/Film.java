
import java.io.Serializable;

public class Film implements Serializable {


    private static final long serialVersionUID = 1L;

    private static int contaId = -1;
    private int id, durata, anno, etaMin;
    private String titolo;
    private Genere genere;
    private Regista regista;

    //COSTRUTTORE
    public Film(String titolo, int durata, int anno, int etaMin, Genere genere, Regista regista) throws IllegalValueException {
        this.id = ++contaId;
        setTitolo(titolo);
        setDurata(durata);
        setAnno(anno);
        setEtaMin(etaMin);
        setGenere(genere);
        setRegista(regista);
    }

    //GETTER
    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getDurata() {
        return durata;
    }

    public int getAnno() {
        return anno;
    }

    public int getEtaMin() {
        return etaMin;
    }

    public Genere getGenere() {
        return genere;
    }

    public Regista getRegista() {
        return regista;
    }

    //SETTER

    public static void setContaId(int contaId){
        Film.contaId = contaId;
    }


    private void setTitolo(String titolo) throws IllegalValueException {
        if (titolo == null || titolo.trim().isEmpty()) {
            throw new IllegalValueException("Errore: Titolo non valido!");
        }
        this.titolo = titolo.trim();
    }

    private void setDurata(int durata) throws IllegalValueException {
        if (durata <= 0) {
            throw new IllegalValueException("Errore: Durata non valida!");
        }
        this.durata = durata;
    }

    private void setAnno(int anno) throws IllegalValueException {
        if (anno < 1985) {
            throw new IllegalValueException("Errore: Anno inserito <1985!");
        }
        this.anno = anno;
    }

    private void setEtaMin(int etaMin) throws IllegalValueException {
        if (etaMin < 0) {
            throw new IllegalValueException("Errore: Età non valida!");
        }
        this.etaMin = etaMin;
    }

    private void setGenere(Genere genere) {
        this.genere = genere;
    }

    private void setRegista(Regista regista) {
        this.regista = regista;
    }

    //ToString
    @Override
    public String toString() {
        return "FILM #" + id + "\n"
                + "Titolo: " + titolo + "\n"
                + "Durata: " + durata + " min | Anno: " + anno + " | VM" + etaMin + "\n"
                + "Genere: " + genere + "\n"
                + regista;
    }
}
