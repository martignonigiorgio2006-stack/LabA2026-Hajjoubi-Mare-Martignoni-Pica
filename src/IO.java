/*
AUTORI:
- Hajjoubi, Omar, 766954, VA
- Mare, Filippo, 766773, VA
- Martignoni, Giorgio, 766932, VA
- Pica, Simone, 765155, VA
*/
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Racchiude tutti i metodi di lettura da tastiera
 * e scrittura sul terminale utilizzati dal programma.
 */
public class IO {

    /**
     * tastiera (Stream di lettura dati da tastiera).
     */
    public static Scanner tastiera = new Scanner(System.in);

    /**
     * Permette di stampare la stringa passata come parametro
     * scegliendo se andare a capo oppure no tramite la variabile booleana
     * b passata come parametro.
     *
     * @param s la stringa da stampare
     * @param b variabile che permette di andare a capo o meno
     */
    public static void output(String s, boolean b) {
        if (b) {
            System.out.println(s);
        } else {
            output(s);
        }
    }

    /**
     * Permette di stampare la stringa passata come parametro
     *
     * @param s la stringa da stampare
     */
    public static void output(String s) {
        System.out.print(s);
    }

    /**
     * Permette di stampare l'intero passato come parametro
     * scegliendo se andare a capo oppure no tramite la variabile booleana
     * b passata come parametro.
     *
     * @param x l'intero da stampare
     * @param b variabile che permette di andare a capo o meno
     */
    public static void output(int x, boolean b) {
        if (b) {
            System.out.println(x);
        } else {
            output(x);
        }
    }

    /**
     * Permette di stampare l'intero passato come parametro
     *
     * @param x l'intero da stampare
     */
    public static void output(int x) {
        System.out.print(x);
    }

    /**
     * Permette di stampare il double passato come parametro
     * scegliendo se andare a capo oppure no tramite la variabile booleana
     * b passata come parametro.
     *
     * @param x il double da stampare
     * @param b variabile che permette di andare a capo o meno
     */
    public static void output(double x, boolean b) {
        if (b) {
            System.out.println(x);
        } else {
            output(x);
        }
    }

    /**
     * Permette di stampare il double passato come parametro
     *
     * @param x il double da stampare
     */
    public static void output(double x) {
        System.out.print(x);
    }

    /**
     * Permette di stampare il char passato come parametro
     * scegliendo se andare a capo oppure no tramite la variabile booleana
     * b passata come parametro.
     *
     * @param c il char da stampare
     * @param b variabile che permette di andare a capo o meno
     */
    public static void output(char c, boolean b) {
        if (b) {
            System.out.println(c);
        } else {
            output(c);
        }
    }

    /**
     * Permette di stampare il char passato come parametro
     *
     * @param c il char da stampare
     */
    public static void output(char c) {
        System.out.print(c);
    }

    /**
     * Permette di stampare una stringa passata come
     * parametro che indica un'eccezione o un errore
     * da segnalare all'utente (la stringa verrà visualizzata di colore rosso).
     *
     * @param s la stringa da stampare
     */
    public static void outputErr(String s) {
        System.out.println("\u001B[31m" + s + "\u001B[0m");//Scrive rosso
    }

    /**
     * Permette di stampare la stringa passata come parametro e di
     * retituire un valore intero al metodo chiamante.
     *
     * La variabile x consente l'overloading del metodo.
     *
     * @param s la stringa da stampare
     * @param x variabile utilizzata per effettuare l'overloading del metodo
     * @return int i'intero inserito dall'utente
     * @throws InputMismatchException se il valore inserito dall'utente non è un intero
     */
    public static int readInt(String s, int x) throws InputMismatchException {
        int risposta;
        boolean errore;
        do {
            errore = false;
            output(s, false);
            try {
                risposta = tastiera.nextInt();
                tastiera.nextLine();
                return risposta;
            } catch (InputMismatchException e) {
                outputErr("\t" + "Errore: Il campo richiesto contiene valori non ammessi!\n\tRiprova!");
                tastiera.nextLine();
                errore = true;
            }
        } while (errore);
        return 0;
    }

    /**
     * Permette di stampare la stringa passata come parametro e di
     * retituire un valore intero al metodo chiamante.
     *
     * @param s la stringa da stampare
     * @return int i'intero inserito dall'utente
     * @throws InputMismatchException se il valore inserito dall'utente non è un intero
     */
    public static int readInt(String s) throws InputMismatchException {
        int risposta;
        boolean errore;
        do {
            errore = false;
            output(s, false);
            try {
                risposta = tastiera.nextInt();
                tastiera.nextLine();
                return risposta;
            } catch (InputMismatchException e) {
                outputErr("Errore: Il campo richiesto contiene valori non ammessi!\nRiprova!");
                tastiera.nextLine();
                errore = true;
            }
        } while (errore);
        return 0;
    }

    /**
     * Permettec retituire un valore intero al metodo chiamante.
     *
     * @return int i'intero inserito dall'utente
     * @throws InputMismatchException se il valore inserito dall'utente non è un intero
     */
    public static int readInt() {
        int risposta;
        boolean errore;
        do {
            errore = false;
            try {
                risposta = tastiera.nextInt();
                tastiera.nextLine();
                return risposta;
            } catch (InputMismatchException e) {
                outputErr("Errore: Il campo richiesto contiene valori non ammessi!\nRiprova!");
                tastiera.nextLine();
                errore = true;
            }
        } while (errore);
        return 0;
    }

    public static String readIntFormatoStringa(String s) {
        return readString(s);
    }

    /**
     * Permette di stampare la stringa passata come parametro e di
     * retituire un valore double al metodo chiamante.
     *
     * @param s la stringa da stampare
     * @return double il double inserito dall'utente
     * @throws InputMismatchException se il valore inserito dall'utente non è un double
     */
    public static double readDouble(String s) throws InputMismatchException {
        double risposta;
        boolean errore;
        do {
            errore = false;
            output(s, false);
            try {
                risposta = tastiera.nextDouble();
                tastiera.nextLine();
                return risposta;
            } catch (InputMismatchException e) {
                outputErr("Errore: Il campo richiesto contiene valori non ammessi!\nRiprova!");
                tastiera.nextLine();
                errore = true;
            }
        } while (errore);
        return 0;
    }

    public static double readDouble() {
        double risposta;
        boolean errore;
        do {
            errore = false;
            try {
                risposta = tastiera.nextDouble();
                tastiera.nextLine();
                return risposta;
            } catch (InputMismatchException e) {
                outputErr("Errore: Il campo richiesto contiene valori non ammessi!\nRiprova!");
                tastiera.nextLine();
                errore = true;
            }
        } while (errore);
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

    /**
     * Permette di stampare la stringa passata come parametro e di
     * retituire un valore String al metodo chiamante.
     *
     * @param s la stringa da stampare
     * @return String la stringa inserita dall'utente
     */
    public static String readString(String s) {
        boolean errore;
        String risposta;
        do {
            errore = false;
            output(s, false);
            risposta = tastiera.nextLine();
            if (risposta == null || risposta.trim().isEmpty()) {
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            }
        } while (errore);
        return risposta;
    }

    /**
     * Permette di stampare la stringa passata come parametro e di
     * retituire un valore String al metodo chiamante.
     *
     * La variabile x consente l'overloading del metodo.
     *
     * @param s la stringa da stampare
     * @param x variabile utilizzata per effettuare l'overloading del metodo
     * @return String la stringa inserita dall'utente
     */
    public static String readString(String s, int x) {
        boolean errore;
        String risposta;
        do {
            errore = false;
            output(s, false);
            risposta = tastiera.nextLine();
            if (risposta == null || risposta.trim().isEmpty()) {
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            } else {
                for (int i = 0; i < risposta.length(); i++) {
                    char c = risposta.charAt(i);
                    if (Character.isDigit(c)) {
                        outputErr("Errore: Il campo richiesto contiene campi non ammessi!\nRiprova!");
                        errore = true;
                        break;
                    }
                }

            }
        } while (errore);
        return risposta;
    }

    public static String readString() {
        boolean errore;
        String risposta;
        do {
            errore = false;
            risposta = tastiera.nextLine();
            if (risposta == null || risposta.trim().isEmpty()) {
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            }
        } while (errore);
        return risposta;
    }

    /**
     * Permette di stampare la stringa passata come parametro e di
     * retituire un valore Data al metodo chiamante.
     *
     * @param s la stringa da stampare
     * @return Data la data inserita dall'utente
     */
    public static Data readData(String s) {
        boolean errore;
        do {
            errore = false;
            String dataFormatoStringa;
            dataFormatoStringa = readString(s);
            if (dataFormatoStringa == null || dataFormatoStringa.trim().isEmpty()) {
                errore = true;
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
            } else if (dataFormatoStringa.trim().equals("-1")) {
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
                        outputErr(e.getMessage() + "\nRiprova!");
                        errore = true;
                    }
                }
            }
        } while (errore);
        return null;
    }

    /**
     * Permette di stampare la stringa passata come parametro e di
     * retituire un valore Genere al metodo chiamante.
     *
     * @param s la stringa da stampare
     * @return Genere il genere inserito dall'utente
     */
    public static Genere readGenere(String s) {
        boolean errore;
        do {
            errore = false;
            String genereFormatoStringa = readString(s);
            if (genereFormatoStringa == null || genereFormatoStringa.trim().isEmpty()) {
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            } else if (genereFormatoStringa.trim().equals("-1")) {
                return null;
            } else {
                Genere[] listaGeneri = Genere.values();
                for (Genere g : listaGeneri) {
                    if (genereFormatoStringa.trim().toUpperCase().equals(g.toString().toUpperCase())) {
                        return g;
                    }
                }
                outputErr("Errore: Il genere non esiste!\nRiprova!");
                errore = true;
            }
        } while (errore);
        return null;
    }

    /**
     * Permette di stampare la stringa passata come parametro e di
     * retituire un valore Domicilio al metodo chiamante.
     *
     * @param s la stringa da stampare
     * @return Domicilio il domicilio inserito dall'utente
     */
    public static Luogo readDomicilio(String s) {
        boolean errore;
        Luogo luogo = null;
        output(s, true);
        do {
            errore = false;
            String via, citta, cap, numeroCivicoFormatoStringa;
            int numeroCivico = 0;
            via = IO.readString("\tVia (non inserire qui il numero civico e non riscrivere via o piazza): ", 1);
            if (via == null || via.trim().isEmpty()) {
                outputErr("\t" + "Errore: Compilare i campi richiesti!\n\tRiprova!");
                errore = true;
                continue;
            }
            numeroCivico = readInt("\tNumero civico: ", 1);
            citta = IO.readString("\tCittà: ", 1);
            if (citta == null || citta.trim().isEmpty()) {
                outputErr("\t" + "Errore: Compilare i campi richiesti!\n\tRiprova!");
                errore = true;
                continue;
            }
            cap = IO.readString("\tCap: ");
            if (cap == null || cap.trim().isEmpty()) {
                outputErr("\t" + "Errore: Compilare i campi richiesti!\n\tRiprova!");
                errore = true;
                continue;
            }
            try {
                luogo = new Luogo(via, numeroCivico, citta, cap);
            } catch (IllegalValueException e) {
                outputErr("\t" + e.getMessage() + "\n\tRiprova!");
                errore = true;
            }
        } while (errore);
        return luogo;
    }

    /**
     * Permette di stampare la stringa passata come parametro e di
     * retituire un valore Regista al metodo chiamante.
     *
     * @param s la stringa da stampare
     * @return Regista il regista inserito dall'utente
     * @throws IllegalValueException se il costruttore di Regista genera eccezioni
     */
    public static Regista readRegista(String s) throws IllegalValueException {
        output(s, true);

        String nome, cognome;
        boolean errore = false;
        do {
            errore = false;
            nome = readString("\tNome: ", 1);
            if (nome == null || nome.trim().isEmpty()) {
                outputErr("\t" + "Errore: Compilare i campi richiesti!\n\tRiprova!");
                errore = true;
            }
        } while (errore);

        do {
            errore = false;
            cognome = readString("\tCognome: ", 1);
            if (cognome == null || cognome.trim().isEmpty()) {
                outputErr("\t" + "Errore: Compilare i campi richiesti!\n\tRiprova!");
                errore = true;
            }
        } while (errore);

        return new Regista(nome, cognome);
    }

    /**
     * Permette di stampare la stringa passata come parametro e di
     * retituire un valore Ora al metodo chiamante.
     *
     * @param s la stringa da stampare
     * @return Ora l'ora inserita dall'utente
     */
    public static Ora readOra(String s) {
        boolean errore;
        do {
            errore = false;
            String oraFormatoStringa = readString(s);
            if (oraFormatoStringa == null || oraFormatoStringa.trim().isEmpty()) {
                outputErr("Errore: Compilare i campi richiesti!\nRiprova!");
                errore = true;
            } else if (oraFormatoStringa.trim().equals("-1")) {
                return null;
            } else {
                String[] oraArray = oraFormatoStringa.split(":");
                if (oraArray.length != 3) {
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
                        outputErr(e.getMessage() + "\nRiprova!");
                        errore = true;
                    }
                }
            }
        } while (errore);
        return null;
    }

}
