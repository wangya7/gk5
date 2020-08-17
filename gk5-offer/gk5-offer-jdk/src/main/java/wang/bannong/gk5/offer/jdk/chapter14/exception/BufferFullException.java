package wang.bannong.gk5.offer.jdk.chapter14.exception;

public class BufferFullException extends RuntimeException {
    private static final long serialVersionUID = -2154202599421355229L;

    public BufferFullException() {
        super();
    }

    public BufferFullException(String message) {
        super(message);
    }

    public BufferFullException(Throwable cause) {
        super(cause);
    }

    public BufferFullException(String message, Throwable cause) {
        super(message, cause);
    }

}
