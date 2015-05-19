package net.thenamedev.legendapi.core.exceptions;

/**
 * @author ThePixelDev
 */
public class DeprecatedException extends RuntimeException {

    public DeprecatedException() {}

    public DeprecatedException(String s) {
        super(s);
    }
}
