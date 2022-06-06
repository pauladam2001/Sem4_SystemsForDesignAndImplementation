package core.domain.validators;

public class RailwayException extends RuntimeException {
    public RailwayException(String message) {
        super(message);
    }

    public RailwayException(String message, Throwable cause) {
        super(message, cause);
    }

    public RailwayException(Throwable cause) {
        super(cause);
    }
}
