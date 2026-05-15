public class Data {

    private int giorno, mese, anno;




    public int getGiorno(){
        return this.giorno;
    }

    public void setGiorno(int giorno){

    }

    public int getMese(){
        return this.mese;
    }

    public void setMese(int mese) throws IllegalMonthValue{
        if(mese < 1 || mese > 12)
            throw new IllegalMonthValue("Mese inserito non valido");

    }

    public int getAnno(){
        return this.anno;
    }
}
