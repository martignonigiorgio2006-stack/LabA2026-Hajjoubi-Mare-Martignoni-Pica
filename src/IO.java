import java.util.Scanner;

public class IO {

    private static Scanner tastiera = new Scanner(System.in);

    public static int readInt(String s){
        output(s, false);
        return tastiera.nextInt();
    }
    public static int readInt(){
        return tastiera.nextInt();
    }

    public static double readDouble(String s){
        output(s, false);
        return tastiera.nextDouble();
    }
    public static double readDouble(){
        return tastiera.nextDouble();
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

}
