
public static void main(String [] args){
        //IO.output("Hai inserito il numero " + IO.readInt("Inserisci un numero: "));

    GestoreCinema gc = new GestoreCinema();

    try {
        gc.registraCliente("Mario", "Rossi", "MarioRossi", "MarioRossi", new Luogo("Via le mani dal culo", 4, "Samarate", "21017"));
        gc.registraCliente("Luigi", "Verdi", "LuigiVerdi", "LuigiVerdi", new Luogo("Via le mani dai piedi", 5, "Gallarate", "21013"));
        gc.aggiungiFilm("Spiderman", 220, 2026, 1, "Bello", new Regista("Yo", "Yo"));
        gc.aggiungiFilm("Iron-Man", 200, 2008, 14, "Azione", new Regista ("Chen", "Chong"));
        gc.aggiungiPrenotazione(1, 200);
        gc.aggiungiPrenotazione(2, 150);
        gc.aggiungiProiezione(new Film("SpiderPork", 200, 2020, 2, "Brutto", new Regista("te", "te")), new Data(2, 12, 2000), new Ora(21, 0, 0), 150);
        gc.aggiungiProiezione(new Film("SpiderChicken", 180, 2023, 2, "Bruttissimo", new Regista("cu", "lo")), new Data(2, 2, 2000), new Ora(22, 0, 0), 50);
    } catch (Exception e) {
        e.printStackTrace();
        IO.outputErr("ERRORE");
    }
   
    gc.scritturaFile();

    gc.riempiListaUtenti();

    LinkedList<Utente> appoggio = gc.getListaUtenti();
    for(Utente u : appoggio)
        IO.output(u.toString(), true);

    gc.riempiListaFilm();

    LinkedList<Film> ghei = gc.getListaFilm();
    for(Film f : ghei)
        IO.output(f.toString(), true);

     gc.riempiListaPrenotazioni();
    
     try{
    LinkedList<Prenotazione> gay = gc.getListaPrenotazioni();
    for(Prenotazione p : gay)
        IO.output(p.toString(), true);
     } catch(Exception e){
        e.printStackTrace();
        IO.outputErr(e.getMessage());
     }
     gc.riempiListaProiezioni();

    LinkedList<Proiezione> gays = gc.getListaProiezioni();
    for(Proiezione p : gays)
        IO.output(p.toString(), true);
}

