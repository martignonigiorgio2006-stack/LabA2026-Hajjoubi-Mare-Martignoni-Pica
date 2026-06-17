public class Data {

    //ATTRIBUTI
    private int giorno, mese, anno;
    private int [] maxGiorni = new int[12];

    //COSTANTI
    private static final char TOKEN =  '/';
    private final int MESI = 12;

    //COSTRUTTORE
    public Data(int giorno, int mese, int anno) throws IllegalValueException{
        setAnno(anno);
        setMaxGiorni();
        setMese(mese);
        setGiorno(giorno);
    }

    private void setMaxGiorni(){
        for(int i = 0; i < MESI; i++)
            switch(i){
                case 0: maxGiorni[0] = 31; break;
                case 1: if(isBisestile())
                            maxGiorni[1] = 29;
                        else
                            maxGiorni[1] = 28;
                        break;
                case 2: maxGiorni[2] = 31; break;
                case 3: maxGiorni[3] = 30; break;
                case 4: maxGiorni[4] = 31; break;
                case 5: maxGiorni[5] = 30; break;
                case 6: maxGiorni[6] = 31; break;
                case 7: maxGiorni[7] = 31; break;
                case 8: maxGiorni[8] = 30; break;
                case 9: maxGiorni[9] = 31; break;
                case 10: maxGiorni[10] = 30; break;
                case 11: maxGiorni[11] = 31;
            }
    }

    public int getGiorno(){
        return this.giorno;
    }

    private void setGiorno(int giorno) throws IllegalValueException{
        if(giorno < 1 || giorno > maxGiorni[this.mese - 1])
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
