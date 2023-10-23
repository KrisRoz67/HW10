package exceptions;

public class BlackListedException extends RuntimeException {
    public BlackListedException(String message) {
        super(message);
    }
}
