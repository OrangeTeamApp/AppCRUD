package exception;

public class DsException extends RuntimeException {

    public DsException(String message) {
        super("Unreachable data source: "  + message);
    }
}