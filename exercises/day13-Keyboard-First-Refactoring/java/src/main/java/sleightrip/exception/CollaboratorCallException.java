package sleightrip.exception;

/**
 * Thrown when a static collaborator (like UserSession or TripDAO) is called directly.
 */
public class CollaboratorCallException extends RuntimeException {

    public CollaboratorCallException(String message) {
        super(message);
    }
}
