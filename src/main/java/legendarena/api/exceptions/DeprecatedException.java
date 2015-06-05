package legendarena.api.exceptions;

/**
 * Thrown when a function is deprecated, and shouldn't be used at all anymore.
 *
 * @author ThePixelDev
 */
public class DeprecatedException extends RuntimeException {

    public DeprecatedException() {}

    public DeprecatedException(String s) {
        super(s);
    }
}
