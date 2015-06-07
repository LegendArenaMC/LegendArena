package legendarena.api.exceptions;

/**
 * Thrown when an event called by a function is cancelled by a plugin, for whatever reason.
 *
 * @author ThePixelDev
 */
public class CancelledEventException extends RuntimeException {

    public CancelledEventException() {
        super();
    }

}
