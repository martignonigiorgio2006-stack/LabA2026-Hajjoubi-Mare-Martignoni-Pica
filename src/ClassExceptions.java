public class ClassExceptions extends Exception {
    public ClassExceptions(String message) {
        super(message);
    }
}

class IllegalValueException extends ClassExceptions{
    public IllegalValueException(String message) {
        super(message);
    }
}