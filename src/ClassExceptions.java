public class ClassExceptions extends Exception {
    public ClassExceptions(String message) {
        super(message);
    }
}
class IllegalMonthValue extends ClassExceptions{
    public IllegalMonthValue(String message) {
        super(message);
    }
}


