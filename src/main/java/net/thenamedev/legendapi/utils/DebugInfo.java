package net.thenamedev.legendapi.utils;

import net.thenamedev.legendapi.exceptions.DoYouEvenKnowWhatYourDoingException;
import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;

/**
 * Created on 3/27/15
 *
 * @author ThePixelDev
 */
public class DebugInfo {

    boolean extraDebug = false;
    boolean debug = true;

    public boolean isExtraDebugOn() {
        return extraDebug;
    }

    public boolean isDebugOn() {
        return debug;
    }

}
