package by.verishko.kefir.dao.connect.exception;

public class ConnectionError extends RuntimeException {

    public ConnectionError() {
        super();
    }

    public ConnectionError(final String message) {
        super(message);
    }

    public ConnectionError(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ConnectionError(final Throwable cause) {
        super(cause);
    }
}