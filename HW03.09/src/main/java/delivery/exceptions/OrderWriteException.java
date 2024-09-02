package delivery.exceptions;

public class OrderWriteException extends Exception {
    public OrderWriteException(String message) {
        super(message);
    }

    public OrderWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}