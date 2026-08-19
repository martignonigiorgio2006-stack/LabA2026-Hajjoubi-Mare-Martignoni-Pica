
import java.util.Scanner;

public class IO {

    private static Scanner tastiera = new Scanner(System.in);

    public static void output(String s, boolean b) {
        if (b) {
            System.out.println(s); 
        }else {
            output(s);
        }
    }

    public static void output(String s) {
        System.out.print(s);
    }

    public static void output(int x, boolean b) {
        if (b) {
            System.out.println(x); 
        }else {
            output(x);
        }
    }

    public static void output(int x) {
        System.out.print(x);
    }

    public static void output(double x, boolean b) {
        if (b) {
            System.out.println(x); 
        }else {
            output(x);
        }
    }

    public static void output(double x) {
        System.out.print(x);
    }

    public static void output(char c, boolean b) {
        if (b) {
            System.out.println(c); 
        }else {
            output(c);
        }
    }

    public static void output(char c) {
        System.out.print(c);
    }

    public static void outputErr(String s) {
        System.err.println(s);
    }

    public static int readInt(String s) {
        output(s, false);
        int valore = tastiera.nextInt();
        tastiera.nextLine(); //così da eliminare eventuali tasti invio in possibili successivi read consumando il resto della riga
        return valore;
    }

    public static int readInt() {
        int valore = tastiera.nextInt();
        tastiera.nextLine(); //così da eliminare eventuali tasti invio in possibili successivi read consumando il resto della riga
        return valore;
    }

    public static String readIntFormatoStringa(String s) {
       return readString(s);
    }

    public static double readDouble(String s) {
        output(s, false);
        double valore = tastiera.nextDouble();
        tastiera.nextLine(); // Consuma il resto della riga
        return valore;
    }

    public static double readDouble() {
        double valore = tastiera.nextDouble();
        tastiera.nextLine(); // Consuma il resto della riga
        return valore;
    }

    public static char readChar(String s) {
        output(s, false);
        String temp = tastiera.nextLine();
        return temp.charAt(0);
    }

    public static char readChar() {
        String temp = tastiera.nextLine();
        return temp.charAt(0);
    }

    public static String readString(String s) {
        output(s, false);
        return tastiera.nextLine();
    }

    public static String readString() {
        return tastiera.nextLine();
    }

    public static Data readData(String s) throws IllegalValueException{
        String dataFormatoStringa = readString(s);

        if(dataFormatoStringa.trim().equals("-1"))
            return null;

        String[] dataArray = dataFormatoStringa.split("/");
        if(dataArray.length != 3)
            throw new IllegalValueException("Errore: Formato data non valido");
        
        try {
            int giorno = Integer.parseInt(dataArray[0].trim());
            int mese = Integer.parseInt(dataArray[1].trim()); 
            int anno = Integer.parseInt(dataArray[2].trim());
            return new Data(giorno, mese, anno);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Errore: La data contiene valori non ammessi!");
        }    
    }

    public static Genere readGenere(String s) throws IllegalValueException{
        String genereFormatoStringa = readString(s);
        if(genereFormatoStringa.trim().equals("-1"))
            return null;
        Genere[] listaGeneri = Genere.values();
        for(Genere g : listaGeneri)
            if(genereFormatoStringa.trim().toUpperCase().equals(g.toString().toUpperCase()))
                return g;
        throw new IllegalValueException("Errore: il genere non esiste!");
    }

}


/*
public static Data readData(String s) throws IllegalValueException {
    String dataFormatoStringa = readString(s);
    
    // 1. Ritorna null se è -1
    if (dataFormatoStringa.trim().equals("-1")) {
        return null;
    }
    
    // 2. Controllo del separatore e del numero di elementi
    String[] dataArray = dataFormatoStringa.split("/");
    if (dataArray.length != 3) {
        throw new IllegalValueException("Errore: Formato data non valido (usare GG/MM/AAAA)");
    }
    
    // 3. Conversione in interi e gestione di altri errori
    try {
        int giorno = Integer.parseInt(dataArray[0].trim());
        int mese = Integer.parseInt(dataArray[1].trim()); // Corretto l'indice [1]
        int anno = Integer.parseInt(dataArray[2].trim());
        
        return new Data(giorno, mese, anno);
    } catch (NumberFormatException e) {
        // Cattura testo o caratteri non validi nei numeri
        throw new IllegalValueException("Errore: La data contiene valori non numerici");
    }
}
*/
