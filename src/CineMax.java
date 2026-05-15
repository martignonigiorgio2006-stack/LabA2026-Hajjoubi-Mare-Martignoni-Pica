public static void main(String [] args){
    Scanner tastiera = new Scanner(System.in);
    int x = tastiera.nextInt();
    System.out.println("Hai scritto: " + x);
    Prova p = new Prova();
    p.stampa();
}