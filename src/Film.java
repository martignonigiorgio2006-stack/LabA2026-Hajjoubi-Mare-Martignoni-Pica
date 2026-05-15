public class Film {
    private static int contaId = 0;
    private int id;
    private String titolo;
    private int durata;
    private int anno;
    private int etaMin;
    private String genere;
    private Regista regista;

    //COSTRUTTORE
    public Film(int id, String titolo, int durata, int anno, int etaMin, String genere, Regista regista) throws IllegalValueException{
        setId(id);
        setTitolo(titolo);
        setDurata(durata);
        setAnno(anno);
        setEtaMin(etaMin);
        setGenere(genere);
        setRegista(regista);
    }

    //GETTER
    private int getId() {
        return id;
    }

    private String getTitolo() {
        return titolo;
    }

    private int getDurata() {
        return durata;
    }

    private int getAnno() {
        return anno;
    }

    private int getEtaMin() {
        return etaMin;
    }

    private String getGenere() {
        return genere;
    }

    private Regista getRegista() {
        return regista;
    }

    //SETTER
    private void setId(int id) {
        this.id = contaId++;
    }

    private void setTitolo(String titolo) throws IllegalValueException{
        if(titolo==null || titolo.trim().isEmpty()) throw new IllegalValueException("Errore: Titolo non valido!");
        this.titolo = titolo.trim().toLowerCase();
    }

    private void setDurata(int durata) throws IllegalValueException{
        if(durata <= 0) throw new IllegalValueException("Errore: Durata non valida!");
        this.durata = durata;
    }

    private void setAnno(int anno) throws IllegalValueException{
        if(anno < 1985) throw new IllegalValueException("Errore: Anno inserito <1985!");
        this.anno = anno;
    }

    private void setEtaMin(int etaMin) throws IllegalValueException{
        if(etaMin < 0) throw new IllegalValueException("Errore: Età non valida!");
        this.etaMin = etaMin;
    }

    private void setGenere(String genere) {
        this.genere = genere.trim().toLowerCase();
    }

    private void setRegista(Regista regista) {
        this.regista = regista;
    }
}
