public class Luogo {
    private String via;
    private int numC;
    private String citta;
    private String CAP;


    //COSTRUTTORE
    public Luogo(String via, int numC, String citta, String CAP) throws IllegalValueException {
        setVia(via);
        setNumC(numC);
        setCitta(citta);
        setCAP(CAP);
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
        return CAP;
    }


    //SETTER

    public void setVia(String via) throws IllegalValueException{
        if(via == null || via.trim().isEmpty()) throw new IllegalValueException("Errore: Formato via non valida!");
        this.via=via;
    }

    public void setNumC(int numC) throws IllegalValueException{
        if(numC<=0) throw new IllegalValueException("Errore: Numero civico non valido!");
        this.numC=numC;
    }
    public void setCitta(String citta) throws IllegalValueException{
        if(citta == null || citta.trim().isEmpty()) throw new IllegalValueException("Errore: Formato città non valida!");
        this.citta = citta;
    }

    public void setCAP(String CAP) throws IllegalValueException{
        if(CAP == null || CAP.trim().isEmpty() || CAP.length()!=5) throw new IllegalValueException(("Errore: Formato CAP non valido!"));
        for(int i=0; i<CAP.length(); i++) {
            char c = CAP.charAt(i);
            if (!Character.isDigit(c)) throw new IllegalValueException("Errore: Non sono tutti numeri!");
        }
        this.CAP = CAP;
    }

    //ToString
    public String toString() {
        return "Via " + getVia() + " " + getNumC() + ", " + getCitta() + ", " + getCAP();
    }
}
