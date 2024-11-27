package wang.bannong.gk5.json.exception;

public class IllegalJsonException extends RuntimeException {

    public IllegalJsonException() {
        super();
    }

    public IllegalJsonException(String message) {
        super(message);
    }

    public IllegalJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalJsonException(Throwable cause) {
        super(cause);
    }

    protected IllegalJsonException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
