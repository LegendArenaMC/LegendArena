package net.thenamedev.legendapi.exceptions;

/**
 * Created on 3/10/2015
 *
 * @author ThePixelDev
 */
public class BoosterAlreadyActive extends RuntimeException {

    public BoosterAlreadyActive() {
        super();
    }

    public BoosterAlreadyActive(String s) {
        super(s);
    }

    public BoosterAlreadyActive(boolean a) {
        throw new MistakesWereMadeException("Really? You actually expected this to do something useful?");
    }

}
