package net.thenamedev.legendapi.utils;

import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;

/**
 * Created on 3/27/15
 *
 * @author ThePixelDev
 */
public class DebugInfo {

    boolean locked = false;

    boolean extraDebug = false;
    boolean debug = true;

    public boolean isExtraDebugOn() {
        return extraDebug;
    }

    public boolean isDebugOn() {
        return debug;
    }

    public void lock() {
        if(locked)
            throw new MistakesWereMadeException("Debug info is already locked!");
        locked = true;
    }

    public void toggleExtraDebug() {
        if(locked)
            throw new MistakesWereMadeException("Debug modes are locked!");
        extraDebug = !extraDebug;
    }

    public void toggleDebug() {
        if(locked)
            throw new MistakesWereMadeException("Debug modes are locked!");
        debug = !debug;
    }

}
