public class Ora {
    private int ora;
    private int minuto;
    private int secondo;

    //COSTANTI
    private static final char TOKEN = ':';

    //COSTRUTTORE
    public Ora(int ora, int minuto, int secondo) throws IllegalValueException{
        setOra(ora);
        setMinuto(minuto);
        setSecondo(secondo);
    }

    //GETTER
    public int getOra() {
        return ora;
    }

    public int getMinuto() {
        return minuto;
    }

    public int getSecondo(){
        return secondo;
    }

    //SETTER
    public void setOra(int ora) throws IllegalValueException{
        if(ora<0 || ora>23)
            throw new IllegalValueException("Errore: Ora non valida!");
        this.ora = ora;
    }

    public void setMinuto(int minuto) throws IllegalValueException{
        if(minuto<0 || minuto>59)
            throw new IllegalValueException("Errore: Minutaggio non valido!");
        this.minuto = minuto;
    }

    public void setSecondo(int secondo) throws IllegalValueException{
        if(secondo<0 || secondo>59)
            throw new IllegalValueException("Errore: Secondi non validi!");
        this.secondo = secondo;
    }


    //ToString
    public String toString() { return String.format("%02d%c%02d%c%02d\n", getOra(), TOKEN, getMinuto(), TOKEN, getSecondo()); }
        // %02d va a stampare un intero (d) occupando almeno 2 spazi, riempiendo di zeri a sinistra se serve (per avere 05:12:02 e non 5:12:2)
}
