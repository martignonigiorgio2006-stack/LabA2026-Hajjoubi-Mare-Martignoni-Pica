/*
AUTORI:
- Hajjoubi, Omar, 766954, VA
- Mare, Filippo, 766773, VA
- Martignoni, Giorgio, 766932, VA
- Pica, Simone, 765155, VA
*/
import java.io.Serializable;
/**
 * Rappresenta una prenotazione composta da id, utente, quantità, proiezione
 *
 * Implementa {@link Serializable} per consentire la serializzazione degli
 * oggetti.
 */
public class Prenotazione implements Serializable {

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
     * utente.
     */
    private Utente utente;

    /**
     * quantità.
     */
    private int quantita;

    /**
     * proiezione.
     */
    private Proiezione proiezione;

    /**
     * Costruisce un nuovo oggetto {@link Prenotazione}.
     *
     * Tramite i set effettua verifiche ai parametri.
     *
     * L'id è auto-incrementato.
     *
     * @param utente l'utente
     * @param quantita la quantità
     * @param proiezione la proiezione
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta
     * i vincoli di integrità
     */
    public Prenotazione(Utente utente, int quantita, Proiezione proiezione) throws IllegalValueException {
        this.id = ++contaId;
        setUtente(utente);
        setProiezione(proiezione);
        setQuantita(quantita);
    }

    /**
     * Imposta il conta id.
     *
     * @param contaId il contatore degli ID
     */
    public static void setContaId(int contaId) {
        Prenotazione.contaId = contaId;
    }

    /**
     * Imposta e valida l'utente.
     *
     * @param utente l'utente
     * @throws IllegalValueException se l'utente non viene inserito
     */
    private void setUtente(Utente utente) throws IllegalValueException {
        if (utente == null) {
            throw new IllegalValueException("Errore: Utente non inserito");
        }
        this.utente = utente;
    }

    /**
     * Imposta e valida la proiezione.
     *
     * @param proiezione la proiezione
     * @throws IllegalValueException se la proiezione non viene inserita
     */
    public void setProiezione(Proiezione proiezione) throws IllegalValueException {
        if (proiezione == null) {
            throw new IllegalValueException("Errore: proiezione non inserita!");
        }
        this.proiezione = proiezione;
    }

    /**
     * Imposta e valida la quantità di biglietti.
     *
     * Se i biglietti vengono impostati verranno aggiornati i poati disponibili
     * alla proiezione in questione.
     *
     * @param quantita la quantità
     * @throws IllegalValueException se i posti richiesti sono meno di 1 oppure
     * più dei posti disponibili
     */
    private void setQuantita(int quantita) throws IllegalValueException {
        if (quantita <= 0) {
            throw new IllegalValueException("Errore: Impossibile prenotare meno di 1 posto!");
        } else if (quantita > proiezione.getPostiLiberi()) {
            throw new IllegalValueException("Errore: Non ci sono abbastanza posti a sedere disponibili!");
        }
        this.quantita = quantita;
        proiezione.scalaPosti(quantita);
    }

    /**
     * Annulla la prenotazione.
     *
     * Se ila cancellazione va a buon fine verranno aggiornati i poati
     * disponibili alla proiezione in questione.
     *
     * @throws IllegalValueException se vengono sollevate eccezioni nelle
     * chiamate ai metodi di {@link Proiezione}
     */
    public void annullaPrenotazione() throws IllegalValueException {
        proiezione.ripristinaPosti(this.quantita);
    }

    /**
     * Modifica la proiezione associata alla prenotazione.
     *
     * @param p nuova {@link Proiezione} da associare
     * @throws IllegalValueException se vengono sollevate eccezioni nelle
     * chiamate ai metodi
     */
    public void modificaPrenotazione(Proiezione p) throws IllegalValueException {
        setProiezione(p);
    }

    /**
     * Restituisce il costo totale.
     *
     * @return il costo totale della prenotazione
     *
     */
    public double getCostoTotale() {
        return quantita * proiezione.getCostoBiglietto();
    }

    /**
     * Restituisce l'id.
     *
     * @return id
     *
     */
    public int getId() {
        return id;
    }

    /**
     * Restituisce l'utente.
     *
     * @return utente
     *
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * Restituisce la proiezione.
     *
     * @return proiezione
     *
     */
    public Proiezione getProiezione() {
        return proiezione;
    }

    /**
     * Restituisce la stringa corrispondente alla prenotazione. 
     *
     * @return la stringa corrispondente all'utente formattata
     */
    @Override
    public String toString() {
        return "- PRENOTAZIONE #" + id + "\n"
                + "\tCliente: " + utente.getNome() + " " + utente.getCognome() + " (" + utente.getUsername() + ")\n"
                + "\t" + proiezione + "\n"
                + "\tPosti prenotati: " + quantita + "\n"
                + "\tCosto Totale: " + getCostoTotale() + "€";
    }
}
