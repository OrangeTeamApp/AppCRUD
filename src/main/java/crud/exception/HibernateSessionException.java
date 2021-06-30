package crud.exception;

public class HibernateSessionException extends RuntimeException {

    public HibernateSessionException(String message) {
        super("Unreachable data source: " + message);
    }
}