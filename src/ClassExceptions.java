/**
 * Rappresenta le eccezioni del progetto.
 *
 * Questa è una sottoclasse di {@code Exceptions}.
 */
public class ClassExceptions extends Exception {
    public ClassExceptions(String message) {
        super(message);
    }
}

/**
 * Rappresenta l'eccezione IllegalValueException che rappresenta
 * dei parametri che non rispettno le integrità del dato specifico.
 *
 * Questa è una sottoclasse di {@code ClassExceptions}.
 */
class IllegalValueException extends ClassExceptions{
    public IllegalValueException(String message) {
        super(message);
    }
}