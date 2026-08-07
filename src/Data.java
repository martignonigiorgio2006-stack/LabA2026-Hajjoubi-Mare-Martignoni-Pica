public class Data {

    //ATTRIBUTI
    private int giorno, mese, anno;

    //COSTANTI
    private static final char TOKEN =  '/';
    private final int MESI = 12;

    //COSTRUTTORE
    public Data(int giorno, int mese, int anno) throws IllegalValueException{
        setAnno(anno);
        setMese(mese);
        setGiorno(giorno);
    }

    private int getMaxGiorniMese(int mese, int anno){
        switch(mese){
            case 4: case 6: case 9: case 11: return 30;
            case 2: return isBisestile() ? 29:28;
            default: return 31;
        }
    }

    public int getGiorno(){
        return this.giorno;
    }

    public void setGiorno(int giorno) throws IllegalValueException{
        if(giorno<1 || giorno>getMaxGiorniMese(this.mese, this.anno))
            throw new IllegalValueException("Errore: Giorno inserito non valido!");
        this.giorno = giorno;
    }

    public int getMese(){
        return this.mese;
    }

    private void setMese(int mese) throws IllegalValueException{
        if(mese < 1 || mese > 12)
            throw new IllegalValueException("Errore: Mese inserito non valido!");
        this.mese = mese;
    }

    public int getAnno(){
        return this.anno;
    }

    private void setAnno(int anno) throws IllegalValueException{
        if(anno < 1900)
            throw new IllegalValueException("Errore: Anno inserito non valido!");
        this.anno = anno;
    }

    //ToString
    public String toString(){
        return getGiorno() + TOKEN + getMese() + TOKEN + getAnno() + "\n";
    }



    private boolean isBisestile(){
        return (((anno%4 == 0)&&(anno%100 != 0)) || (anno%400 == 0));
    }

}
