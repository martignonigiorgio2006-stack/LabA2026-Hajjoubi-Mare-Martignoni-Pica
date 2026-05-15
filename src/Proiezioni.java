

public class Proiezioni {
    private int id;
    private static int contaId;
    private Film film;
    private Data data;
    private Ora ora;
    private double costoBiglietto;
    private static final char valuta = '€';
    private static final int maxPosti = 200;
    private int postiLiberi;

    //COSTRUTTORE
    public Proiezioni(int id, Film film, Data data, Ora ora, double costoBiglietto, int postiLiberi) {
        setId(id);
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

    public static int getContaId() {
        return contaId;
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
    private void setId(int id) {
        this.id = getContaId();
    }

    private static void setContaId(int contaId) {
        Proiezioni.contaId = contaId;
    }

    private void setFilm(Film film) {
        this.film = film;
    }

    private void setData(Data data) {
        this.data = data;
    }

    private void setOra(Ora ora) {
        this.ora = ora;
    }

    private void setCostoBiglietto(double costoBiglietto) throws IllegalArgumentException{
        if(costoBiglietto <0){
            throw new IllegalArgumentException("Errore: il costo del biglietto non è valido!");
        }
        this.costoBiglietto = costoBiglietto;
    }

    private void setPostiLiberi(int postiLiberi) throws IllegalArgumentException{
        if(postiLiberi >200 || postiLiberi<0){
            throw new IllegalArgumentException("Errori: posti liberi non validi!");
        }
        this.postiLiberi = postiLiberi;
    }

    //ToString
    public String toString(){
        return getId() + "\n" + film.toString() + "\n" + data.toString() + "\n" + ora.toString() + "\n" + getCostoBiglietto() + "\n" + getPostiLiberi() + "\n";

    }
}
