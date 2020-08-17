package wang.bannong.gk5.offer.jdk.chapter14.exception;

public class BufferEmptyException extends RuntimeException {
    private static final long serialVersionUID = 290736586947559658L;

    public BufferEmptyException() {
        super();
    }

    public BufferEmptyException(String message) {
        super(message);
    }

    public BufferEmptyException(Throwable cause) {
        super(cause);
    }

    public BufferEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
