import java.io.Serializable;

/**
 * Rappresenta un orario composto da ore, minuti e secondi.
 * Implementa {@link Serializable} per consentire la serializzazione degli oggetti.
*/
public class Ora implements Serializable {

    /** Ora.*/
    private int ora;
    
    /** Minuto.*/
    private int minuto;

    /** Secondo.*/
    private int secondo;

    /**
     * Carattere utilizzato per settare il formato hh:mm:ss.
     * */
    private static final char TOKEN = ':';

    /**
     * Costruisce un nuovo oggetto {@code Ora}.
     * Tramite i set effettua verifiche ai parametri.
     * 
     * @param ora   ora
     * @param minuto minuto  
     * @throws IllegalValueException se uno qualsiasi dei parametri non rispetta i vincoli di integrità 
    */
    public Ora(int ora, int minuto, int secondo) throws IllegalValueException{
        setOra(ora);
        setMinuto(minuto);
        setSecondo(secondo);
    }

    /**
     * Restituisce l'ora.
     *
     * @return l'ora
    */
    public int getOra() {
        return ora;
    }

    /**
     * Restituisce il minuto.
     *
     * @return il minuto
    */
    public int getMinuto() {
        return minuto;
    }

    /**
     * Restituisce il secondo.
     *
     * @return secondo
    */
    public int getSecondo(){
        return secondo;
    }

    /**
     * Imposta e valida l'ora.
     *
     * @param ora l'ora da settare
     * @throws IllegalValueException se l'ora è minore di 0 oppure maggiore di 23 (formato ore da 00:00:00 a 23:59:59)
    */
    private void setOra(int ora) throws IllegalValueException{
        if(ora<0 || ora>23)
            throw new IllegalValueException("Errore: Ora non valida!");
        this.ora = ora;
    }

    /**
     * Imposta e valida il minuto.
     *
     * @param minuto il minuto da settare
     * @throws IllegalValueException se il minuto è minore di 0 e maggiore di 59 
    */
    private void setMinuto(int minuto) throws IllegalValueException{
        if(minuto<0 || minuto>59)
            throw new IllegalValueException("Errore: Minutaggio non valido!");
        this.minuto = minuto;
    }

    /**
     * Imposta e valida il secondo.
     *
     * @param secondo il secondo da settare
     * @throws IllegalValueException se il secondo è minore di 0 e maggiore di 59
    */
    private void setSecondo(int secondo) throws IllegalValueException{
        if(secondo<0 || secondo>59)
            throw new IllegalValueException("Errore: Secondi non validi!");
        this.secondo = secondo;
    }

    /**
     * Restituisce la stringa corrispondente all'orario.
     * Il formato è: {@code ora, minuto, secondo} 
     *
     * @return la stringa corrispondente all'orario formattato
    */
    @Override
    public String toString() { 

        // %02d va a stampare un intero (d) occupando almeno 2 spazi (02), riempiendo di zeri a sinistra se serve (per avere 05:12:02 e non 5:12:2)
        return String.format("%02d%c%02d%c%02d\n", ora, TOKEN, minuto, TOKEN, secondo);
    
    }

}
