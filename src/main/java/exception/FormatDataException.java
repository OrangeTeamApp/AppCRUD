package exception;

public class FormatDataException extends Exception {
    public FormatDataException(String message) {
        super("Incorrect input data for user. " + message);
    }
}
