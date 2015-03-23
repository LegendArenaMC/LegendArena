package net.thenamedev.legendapi.exceptions;

/**
 * Never ask why this was made.. I was most likely drunk at the time of making this.
 *
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

    /**
     * USE AT YOUR OWN DAMN RISK.
     */
    @Deprecated
    public MistakesWereMadeException(boolean loop) {
        super("MISTAKES WERE MADE TODAY.");
        if(loop)
            throw new MistakesWereMadeException(true); //DEAR GOD I REGRET NOTHING OF MAKING THIS
        else
            throw new MistakesWereMadeException("MISTAKES WERE MADE TODAY.");
    }

    /**
     * USE AT YOUR OWN DAMN RISK.
     */
    @Deprecated
    public MistakesWereMadeException(boolean b, String s) {
        super(s);
        if(b)
            throw new MistakesWereMadeException(true, s); //MISTAKES WERE DAMN WELL MADE TODAY.
        else
            throw new MistakesWereMadeException(s);
    }
}
