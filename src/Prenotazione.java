
import java.io.Serializable;

/**
 * Rappresenta una prenotazione composta da id, utente, quantità, proiezione
 *
 * Implementa {@link Serializable} per consentire la serializzazione degli
 * oggetti.
 */
public class Prenotazione implements Serializable {

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
     * Costruisce un nuovo oggetto {@code Prenotazione}.
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
     * chiamate ai metodi di {@code Proiezione}
     */
    public void annullaPrenotazione() throws IllegalValueException {
        proiezione.ripristinaPosti(this.quantita);
    }

    //L'UTENTE NON PUO' MODIFICARE I POSTI CHE HA COMPRATO!!
    public void aggiornaQuantita(int nuovaQuantita) throws IllegalValueException {
        if (nuovaQuantita <= 0) {
            throw new IllegalValueException("Errore: Impossibile prenotare meno di 1 posto!");
        }
        int differenza = nuovaQuantita - this.quantita;
        if (differenza > 0) {
            // signifca che l'utente aggiunge posti e verifico quindi la disponibilità che è rimasta
            if (differenza > proiezione.getPostiLiberi()) {
                throw new IllegalValueException("Errore: Non ci sono abbastanza posti disponibili per la modifica!");
            }
            proiezione.scalaPosti(differenza);
        } else if (differenza < 0) {
            //significa che l'utente ha tolto dei posti quindi semplicemente aggiungo"
            proiezione.ripristinaPosti(-differenza);
        }
        this.quantita = nuovaQuantita;
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
     * Restituisce la quantità.
     *
     * @return quantita
     *
     */
    public int getQuantita() {
        return quantita;
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
     * Restituisce la stringa corrispondente alla prenotazione. Il formato è:
     * {@code id, nome, cognome, username, proiezione, quantita, costoTotale}
     *
     * @return la stringa corrispondente all'utente formattata
     */
    @Override
    public String toString() {
        return "PRENOTAZIONE #" + id + "\n"
                + "Cliente: " + utente.getNome() + " " + utente.getCognome() + " (" + utente.getUsername() + ")\n"
                + "Dettagli Proiezione:\n" + proiezione + "\n"
                + "Posti prenotati: " + quantita + "\n"
                + "Costo Totale: " + getCostoTotale() + "€";
    }
}
