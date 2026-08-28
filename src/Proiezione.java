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
 * Rappresenta una proiezione composta da id, film, data, ora, costoBiglietto.
 *
 * Implementa {@link Serializable} per consentire
 * la serializzazione degli oggetti e {@link LocalDate} per consentire
 * di recuperare la data odierna.
 */

public class Proiezione implements Serializable {

    /**
     * Identificatore della versione della classe utilizzato durante la serializzazione.
     */
    private static final long serialVersionUID = 1L;

    /**
     * contaId.
     */
    private static int contaId = -1;

    /**
     * id.
     */
    private final int id;

    /**
     * film.
     */
    private Film film;

    /**
     * data.
     */
    private Data data;

    /**
     * ora.
     */
    private Ora ora;

    /**
     * costoBiglietto.
     */
    private double costoBiglietto;

    /**
     * Carattere utilizzato per settare il formato di valuta in €.
     */
    private static final char valuta = '€';

    /**
     * maxPosti.
     */
    private static final int maxPosti = 200;

    /**
     * postiLiberi.
     */
    private int postiLiberi;

    /**
     * Costruisce un nuovo oggetto {@link Proiezione}.
     *
     * Tramite i set effettua verifiche ai parametri.
     *
     * L'id è auto-incrementato.
     *
     * @param film il film
     * @param data la data
     * @param ora l'ora
     * @param costoBiglietto il costo del biglietto
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta i vincoli di integrità
     */
    public Proiezione(Film film, Data data, Ora ora, double costoBiglietto) throws IllegalValueException {
        this.id = ++contaId;
        setFilm(film);
        setData(data);
        setOra(ora);
        setCostoBiglietto(costoBiglietto);
        this.postiLiberi = maxPosti;
    }

    /**
     * Restituisce l'id.
     *
     * @return id
     * */
    public int getId() {
        return id;
    }

    /**
     * Restituisce il film.
     *
     * @return film
     * */
    public Film getFilm() {
        return film;
    }

    /**
     * Restituisce la data.
     *
     * @return la data
     * */
    public Data getData() {
        return data;
    }

    /**
     * Restituisce l'ora.
     *
     * @return ora
     * */
    public Ora getOra() {
        return ora;
    }

    /**
     * Restituisce il costo del biglietto.
     *
     * @return  costoBiglietto
     * */
    public double getCostoBiglietto() {
        return costoBiglietto;
    }

    /**
     * Restituisce i posti liberi.
     *
     * @return postiLiberi
     * */
    public int getPostiLiberi() {
        return postiLiberi;
    }

    /**
     * Imposta e valida il contatore degli id.
     *
     * @param contaId il conta id
     */
    public static void setContaId(int contaId){
        Proiezione.contaId = contaId;
    }

    /**
     * Imposta e valida il film
     *
     * @param film il film
     * @throws IllegalValueException se il film non viene inserito
     */
    private void setFilm(Film film) throws IllegalValueException{
        if(film == null)
            throw new IllegalValueException("Errore: film non inserito!");
        this.film = film;
    }

    /**
     * Imposta e valida la data
     *
     * @param data la data
     * @throws IllegalValueException se la data non viene inserita o è passata
     */
    private void setData(Data data) throws IllegalValueException{
        if(data==null)
            throw new IllegalValueException("Errore: data non inserita!");

        LocalDate oggi = LocalDate.now();
        LocalDate dataProiezione = LocalDate.of(data.getAnno(), data.getMese(), data.getGiorno());

        if (dataProiezione.isBefore(oggi))
            throw new IllegalArgumentException("Errore: La data non è valida!");
        
        this.data = data;
    }

    /**
     * Imposta e valida l'ora
     *
     * @param ora l'ora
     * @throws IllegalValueException se l'ora non viene inserita
     */
    private void setOra(Ora ora) throws IllegalValueException {
        if(ora == null)
            throw new IllegalValueException("Errore: ora non inserita!");
        this.ora = ora;
    }

    /**
     * Imposta e valida il costo del biglietto.
     *
     * @param costoBiglietto il costo del biglietto
     * @throws IllegalValueException se il prezzo è negativo (0€ = gratis)
     */
    private void setCostoBiglietto(double costoBiglietto) throws IllegalValueException {
        if (costoBiglietto < 0) {
            throw new IllegalValueException("Errore: il costo del biglietto non è valido!");
        }
        this.costoBiglietto = costoBiglietto;
    }

    /**
     * Imposta e valida i posti liberi.
     *
     * @param postiLiberi i posti liberi
     * @throws IllegalValueException se i posti liberi sono maggiorni dei posti massimi della sala
     * oppure se negativi
     */
    private void setPostiLiberi(int postiLiberi) throws IllegalValueException {
        if (postiLiberi > maxPosti || postiLiberi < 0) {
            throw new IllegalValueException("Errori: posti liberi non validi!");
        }
        this.postiLiberi = postiLiberi;
    }

    /**
     * Scala i posti liberi se viene effettuata una prenotazione.
     *
     * @param quantita posti richiesti
     * @throws IllegalValueException se vengono sollevate eccezioni nelle chiamate ai metodi di {@link Proiezione}
     */
    public void scalaPosti(int quantita) throws IllegalValueException {
        setPostiLiberi(this.postiLiberi - quantita);
    }

    /**
     * Ripristina i posti liberi se viene cancellata una prenotazione.
     *
     * @param quantita posti da ripristinare
     * @throws IllegalValueException se vengono sollevate eccezioni nelle chiamate ai metodi di {@link Proiezione}
     */
    public void ripristinaPosti(int quantita) throws IllegalValueException {
        setPostiLiberi(postiLiberi + quantita);
    }

    public void aggiornaProiezione(Data nuovaData, Ora nuovaOra, double nuovoCosto) throws IllegalValueException {
        if (nuovaData == null || nuovaOra == null) {
            throw new IllegalValueException("Errore: Compilare i campi di data e ora!");
        }
        setData(nuovaData);
        setOra(nuovaOra);
        setCostoBiglietto(nuovoCosto);
    }

    /**
     * Restituisce la stringa corrispondente alla proiezione.
     *
     * @return la stringa corrispondente alla proiezione
     */
    @Override
    public String toString() {
        return "- PROIEZIONE #" + id + "\n"
                + "\tFilm: " + film.getTitolo() + "\n"
                + "\tIl " + data + " alle " + ora
                + "\tPrezzo: " + costoBiglietto + valuta + " | Posti liberi: " + postiLiberi;
    }
}
