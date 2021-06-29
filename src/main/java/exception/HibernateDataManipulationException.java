package exception;

public class HibernateDataManipulationException extends RuntimeException {
    public HibernateDataManipulationException(String message) {
        super("Impossible action with date base: " + message);
    }
}