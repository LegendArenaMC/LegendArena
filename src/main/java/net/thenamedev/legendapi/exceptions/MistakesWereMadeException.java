package net.thenamedev.legendapi.exceptions;

/**
 * Created on 3/3/2015
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
