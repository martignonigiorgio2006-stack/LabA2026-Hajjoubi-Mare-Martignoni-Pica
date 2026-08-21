import java.util.Scanner;
import java.util.InputMismatchException;

public class IO {

    //NON CHIUDIAMO MAI LO STREAM DI LETTURA PROBLEMA?
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
        //System.out.println(s);
        System.out.println("\u001B[31m" + s + "\u001B[0m");//Scrive rosso
    }

    public static int readInt(String s, int x) throws InputMismatchException{
        int risposta;
        boolean errore;
        do{
            errore = false;
            output(s, false);
            try{
                risposta = tastiera.nextInt();
                tastiera.nextLine();
                return risposta;
            }
            catch(InputMismatchException e) {
                outputErr("\t"+"Errore: Il campo richiesto contiene valori non ammessi!\n\tRiprova!");
                tastiera.nextLine();
                errore = true;
            }
        }while(errore);
        return 0;
    }

    public static int readInt(String s) throws InputMismatchException{
        int risposta;
        boolean errore;
        do{
            errore = false;
            output(s, false);
            try{
                risposta = tastiera.nextInt();
                tastiera.nextLine();
                return risposta;
            }
            catch(InputMismatchException e) {
                outputErr("Errore: Il campo richiesto contiene valori non ammessi!\nRiprova!");
                tastiera.nextLine();
                errore = true;
            }
        }while(errore);
        return 0;
    }

    public static int readInt() {
        int risposta;
        boolean errore;
        do{
            errore = false;
            try{
                risposta = tastiera.nextInt();
                tastiera.nextLine();
                return risposta;
            }
            catch(InputMismatchException e) {
               outputErr("Errore: Il campo richiesto contiene valori non ammessi!\nRiprova!");
                tastiera.nextLine();
                errore = true;
            }
        }while(errore);
        return 0;
    }

    public static String readIntFormatoStringa(String s) {
       return readString(s);
    }

    public static double readDouble(String s) throws InputMismatchException{
        double risposta;
        boolean errore;
        do{
            errore = false;
            output(s, false);
            try{
                risposta = tastiera.nextDouble();
                tastiera.nextLine();
                return risposta;
            }
            catch(InputMismatchException e) {
                outputErr("Errore: Il campo richiesto contiene valori non ammessi!\nRiprova!");
                tastiera.nextLine();
                errore = true;
            }
        }while(errore);
        return 0;
    }

    public static double readDouble() {
        double risposta;
        boolean errore;
        do{
            errore = false;
            try{
                risposta = tastiera.nextDouble();
                tastiera.nextLine();
                return risposta;
            }
            catch(InputMismatchException e) {
                outputErr("Errore: Il campo richiesto contiene valori non ammessi!\nRiprova!");
                tastiera.nextLine();
                errore = true;
            }
        }while(errore);
        return 0;
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
        boolean errore;
        String risrposta;
        do{
            errore = false;
            output(s,false);
            risrposta = tastiera.nextLine();
            if(risrposta == null || risrposta.trim().isEmpty()){
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            }
        }while(errore);
        return risrposta;
    }

    public static String readString() {
        boolean errore;
        String risrposta;
        do{
            errore = false;
            risrposta = tastiera.nextLine();
            if(risrposta == null || risrposta.trim().isEmpty()){
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            }
        }while(errore);
        return risrposta;
    }

    public static Data readData(String s) throws IllegalValueException{
        boolean errore;
        do{
            errore = false;
            String dataFormatoStringa;
            dataFormatoStringa = readString(s);
            if(dataFormatoStringa == null || dataFormatoStringa.trim().isEmpty()) {
                errore = true;
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
            } else if(dataFormatoStringa.trim().equals("-1")) {
                return null;
            } else {
                String[] dataArray = dataFormatoStringa.split("/");
                if (dataArray.length != 3) {
                    outputErr("Errore: Formato data non valido!\nRiprova!");
                    errore = true;
                } else {
                    try {
                        int giorno = Integer.parseInt(dataArray[0].trim());
                        int mese = Integer.parseInt(dataArray[1].trim());
                        int anno = Integer.parseInt(dataArray[2].trim());
                        return new Data(giorno, mese, anno);
                    } catch (NumberFormatException e) {
                        outputErr("Errore: La data contiene valori non ammessi!\nRiprova!");
                        errore = true;
                    } catch (IllegalValueException e) {
                        outputErr(e.getMessage()+"\nRiprova!");
                        errore = true;
                    }
                }
            }
        }while(errore);
        return null;
    }

    public static Genere readGenere(String s){
        boolean errore;
        do{
            errore = false;
            String genereFormatoStringa = readString(s);
            if(genereFormatoStringa == null || genereFormatoStringa.trim().isEmpty()){
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            } else if(genereFormatoStringa.trim().equals("-1")){
                return null;
            } else {
                Genere[] listaGeneri = Genere.values();
                for (Genere g : listaGeneri)
                    if (genereFormatoStringa.trim().toUpperCase().equals(g.toString().toUpperCase()))
                        return g;
                outputErr("Errore: Il genere non esiste!\nRiprova!");
                errore = true;
            }
        }while(errore);
        return null;
    }

    public static Luogo readDomicilio(String s) throws IllegalValueException{
        boolean errore;
        Luogo luogo = null;
        output(s, true);
        do{
            errore = false;
            String via, citta, cap, numeroCivicoFormatoStringa;
            int numeroCivico = 0;
            via = IO.readString("\tVia: ");
            if(via == null || via.trim().isEmpty()){
                outputErr("\t"+"Errore: Compilare i campi richiesti!\n\tRiprova!");
                errore = true;
                continue;
            }
            numeroCivico = readInt("\tNumero civico: ", 1);
            citta = IO.readString("\tCittà: ");
            if(citta == null || citta.trim().isEmpty()) {
                outputErr("\t"+"Errore: Compilare i campi richiesti!\n\tRiprova!");
                errore = true;
                continue;
            }
            cap = IO.readString("\tCap: ");
            if(cap == null || cap.trim().isEmpty()){
                outputErr("\t"+"Errore: Compilare i campi richiesti!\n\tRiprova!");
                errore = true;
                continue;
            }
            try{
                luogo = new Luogo(via, numeroCivico, citta, cap);
            }catch(IllegalValueException e){
                outputErr("\t"+e.getMessage()+"\n\tRiprova!");
                errore = true;
            }
        }while(errore);
        return luogo;
    }

    public static Regista readRegista(String s) throws IllegalValueException{
        output(s);
        
        String nome, cognome;
        boolean errore = false;
        do{
            errore = false;
            nome = readString("\tNome: ");
            if(nome == null || nome.trim().isEmpty()){
                outputErr("\tErrore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            }
        }while(errore);

        do{
            errore = false;
            cognome = readString("\tCognome: ");
            if(cognome == null || cognome.trim().isEmpty()){
                outputErr("\tErrore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            }
        }while(errore);

        return new Regista(nome, cognome);
    }

    public static Ora readOra(String s) throws IllegalValueException{ //se lo invio null?
        boolean errore;
        do{
            errore = false;
            String oraFormatoStringa = readString(s);
            if(oraFormatoStringa == null || oraFormatoStringa.trim().isEmpty()){
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            } else if(oraFormatoStringa.trim().equals("-1")) {
                return null;
            } else {
                String[] oraArray = oraFormatoStringa.split(":");
                if(oraArray.length != 3) {
                    outputErr("Errore: Formato ora non valido!\nRiprova!");
                    errore = true;
                } else {
                    try {
                        int ora = Integer.parseInt(oraArray[0].trim());
                        int minuto = Integer.parseInt(oraArray[1].trim());
                        int secondo = Integer.parseInt(oraArray[2].trim());
                        return new Ora(ora, minuto, secondo);
                    } catch (NumberFormatException e) {
                        outputErr("Errore: L'orario contiene valori non ammessi!\nRiprova!");
                        errore = true;
                    } catch (IllegalValueException e) {
                        outputErr(e.getMessage()+"\nRiprova!");
                        errore = true;
                    }
                }
            }
        }while(errore);
        return null;
    }

}



