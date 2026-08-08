public class Proiezione {
    private int id;
    private static int contaId = 0;
    private Film film;
    private Data data;
    private Ora ora;
    private double costoBiglietto;
    private static final char valuta = '€';
    private static final int maxPosti = 200;
    private int postiLiberi;

    //COSTRUTTORE
    public Proiezione(Film film, Data data, Ora ora, double costoBiglietto, int postiLiberi) throws IllegalValueException{
        this.id = contaId++;
        setFilm(film);
        setData(data);
        setOra(ora);
        setCostoBiglietto(costoBiglietto);
        setPostiLiberi(postiLiberi);
    }

    //GETTER
    public int getId() {
        return id;
    }

    public Film getFilm() {
        return film;
    }

    public Data getData() {
        return data;
    }

    public Ora getOra() {
        return ora;
    }

    public double getCostoBiglietto() {
        return costoBiglietto;
    }

    public int getPostiLiberi() {
        return postiLiberi;
    }


    //SETTER
    private void setFilm(Film film) {
        this.film = film;
    }

    private void setData(Data data) {
        this.data = data;
    }

    private void setOra(Ora ora) {
        this.ora = ora;
    }

    private void setCostoBiglietto(double costoBiglietto) throws IllegalValueException{
        if(costoBiglietto<0) throw new IllegalValueException("Errore: il costo del biglietto non è valido!");
        this.costoBiglietto = costoBiglietto;
    }

    private void setPostiLiberi(int postiLiberi) throws IllegalValueException{
        if(postiLiberi>maxPosti || postiLiberi<0) throw new IllegalValueException("Errori: posti liberi non validi!");
        this.postiLiberi = postiLiberi;
    }

    //METODO PUBBLICO PER AGGIORNARE PSOTI LIBERI
    public void scalaPosti(int quantita) throws IllegalValueException{
        setPostiLiberi(this.postiLiberi - quantita);
    }

    //ToString
    @Override
    public String toString() {
        return "PROIEZIONE #" + id + "\n" +
                " Film: " + film.getTitolo() + "\n" +
                " Data/Ora: " + data + " alle " + ora + "\n" +
                " Prezzo: " + costoBiglietto + "€ | Posti liberi: " + postiLiberi;
    }
}
