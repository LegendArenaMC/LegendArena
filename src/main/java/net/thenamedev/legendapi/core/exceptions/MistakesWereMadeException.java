package net.thenamedev.legendapi.core.exceptions;

/**
 * Mistakes were made.
 *
 * @author ThePixelDev
 */
public class MistakesWereMadeException extends RuntimeException {

    public MistakesWereMadeException() {
        super("mistakes.");
    }

    public MistakesWereMadeException(String msg) {
        super(msg);
    }

}
