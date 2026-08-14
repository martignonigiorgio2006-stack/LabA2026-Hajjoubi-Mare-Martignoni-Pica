
import java.io.Serializable;

public class Luogo implements Serializable{

    //ATTRIBUTI
    private String via, citta, cap;
    private int numC;

    //COSTRUTTORE
    public Luogo(String via, int numC, String citta, String cap) throws IllegalValueException {
        setVia(via);
        setNumC(numC);
        setCitta(citta);
        setCAP(cap);
    }


    //GETTER
    public String getVia() {
        return via;
    }

    public int getNumC(){
        return numC;
    }

    public String getCitta() {
        return citta;
    }

    public String getCAP() {
        return cap;
    }


    //SETTER
    private void setVia(String via) throws IllegalValueException{
        if(via == null || via.trim().isEmpty()) throw new IllegalValueException("Errore: Formato via non valida!");
        this.via = via.trim();
    }

    private void setNumC(int numC) throws IllegalValueException{
        if(numC<=0) throw new IllegalValueException("Errore: Numero civico non valido!");
        this.numC = numC;
    }
    private void setCitta(String citta) throws IllegalValueException{
        if(citta == null || citta.trim().isEmpty()) throw new IllegalValueException("Errore: Formato città non valida!");
        this.citta = citta.trim();
    }

    private void setCAP(String cap) throws IllegalValueException{
        if(cap == null || cap.trim().isEmpty() || cap.length() != 5) throw new IllegalValueException(("Errore: Formato CAP non valido!"));
        for(int i = 0; i < cap.length(); i++) {
            char c = cap.charAt(i);
            if (!Character.isDigit(c)) throw new IllegalValueException("Errore: Non sono tutti numeri!");
        }
        this.cap = cap.trim();
    }

    //ToString
    @Override
    public String toString() {
        return  via + " " + numC + ", " + citta + ", " + cap;
    }
}
