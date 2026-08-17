
import java.io.Serializable;

public class Prenotazione implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private static int contaId = -1;
    private int id;
    private Utente utente;
    private int quantita;
    private Proiezione proiezione;

    //COSTRUTTORE
    public Prenotazione(Utente utente, int quantita, Proiezione proiezione) throws IllegalValueException {
        this.id = ++contaId;
        setUtente(utente);
        setProiezione(proiezione);
        setQuantita(quantita);
    }

    //SETTER
    public static void setContaId(int contaId){
        Prenotazione.contaId = contaId;
    }

    private void setUtente(Utente utente) throws IllegalValueException {
        if (utente == null) {
            throw new IllegalValueException("Errore: Utente non inserito");
        }
        this.utente = utente;
    }

    private void setProiezione(Proiezione proiezione) throws IllegalValueException {
        if (proiezione == null) {
            throw new IllegalValueException("Errore: proiezione non inserita");
        }
        this.proiezione = proiezione;
    }

    private void setQuantita(int quantita) throws IllegalValueException {
        if (quantita <= 0) {
            throw new IllegalValueException("Errore: Impossibile prenotare meno di 1 posto!"); 
        }else if (quantita > proiezione.getPostiLiberi()) {
            throw new IllegalValueException("Errore: Non ci sono abbastanza posti a sedere disponibili!");
        }
        this.quantita = quantita;
        proiezione.scalaPosti(quantita);
    }

    public void annullaPrenotazione() throws IllegalValueException {
        proiezione.ripristinaPosti(this.quantita);
    }

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

    //GETTER
    public double getCostoTotale() {
        return quantita * proiezione.getCostoBiglietto();
    }

    public int getId() {
        return id;
    }

    public Utente getUtente() {
        return utente;
    }

    public int getQuantita() {
        return quantita;
    }

    public Proiezione getProiezione() {
        return proiezione;
    }

    //ToString
    @Override
    public String toString() {
        return "PRENOTAZIONE #" + id + "\n"
                + "Cliente: " + utente.getNome() + " " + utente.getCognome() + " (" + utente.getUsername() + ")\n"
                + "Dettagli Proiezione:\n" + proiezione + "\n"
                + "Posti prenotati: " + quantita + "\n"
                + "Costo Totale: " + getCostoTotale() + "€";
    }
}
