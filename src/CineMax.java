public static void main(String [] args){
        //IO.output("Hai inserito il numero " + IO.readInt("Inserisci un numero: "));

    try {
        Data data = new Data(29,2,2026);
    } catch (IllegalValueException e) {
        IO.outputErr(e.getMessage());
    }


}