/*
AUTORI:
- Hajjoubi, Omar, 766954, VA
- Mare, Filippo, 766773, VA
- Martignoni, Giorgio, 766932, VA
- Pica, Simone, 765155, VA
*/

/**
 * Rappresenta l'eccezione IllegalValueException che rappresenta
 * dei parametri che non rispettno le integrità del dato specifico.
 *
 * Questa è una sottoclasse di {@link Exceptions}.
 */

public class IllegalValueException extends Exception {
    public IllegalValueException(String message) {
        super(message);
    }
}


