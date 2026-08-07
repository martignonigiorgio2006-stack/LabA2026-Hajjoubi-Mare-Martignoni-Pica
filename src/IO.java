import java.util.Scanner;

public class IO {

    private static Scanner tastiera = new Scanner(System.in);

    public static void output(String s, boolean b){
        if(b)
            System.out.println(s);
        else
            output(s);
    }
    public static void output(String s){
        System.out.print(s);
    }

    public static void output(int x, boolean b){
        if(b)
            System.out.println(x);
        else
            output(x);
    }
    public static void output(int x){
        System.out.print(x);
    }

    public static void output(double x, boolean b){
        if(b)
            System.out.println(x);
        else
            output(x);
    }
    public static void output(double x){
        System.out.print(x);
    }

    public static void output(char c, boolean b){
        if(b)
            System.out.println(c);
        else
            output(c);
    }
    public static void output(char c){
        System.out.print(c);
    }

    public static void outputErr(String s){
        System.err.println(s);
    }

    public static int readInt(String s){
        output(s, false);
        int valore = tastiera.nextInt();
        tastiera.nextLine(); //così da eliminare eventuali tasti invio in possibili successivi read consumando il resto della riga
        return valore;
    }

    public static double readDouble(String s){
        output(s, false);
        double valore = tastiera.nextDouble();
        tastiera.nextLine(); // Consuma il resto della riga
        return valore;
    }

    public static char readChar(String s){
        output(s, false);
        String temp = tastiera.nextLine();
        return temp.charAt(0);
    }
    public static char readChar(){
        String temp = tastiera.nextLine();
        return temp.charAt(0);
    }

    public static String readString(String s){
        output(s, false);
        return tastiera.nextLine();
    }
    public static String readString(){
        return tastiera.nextLine();
    }


}
