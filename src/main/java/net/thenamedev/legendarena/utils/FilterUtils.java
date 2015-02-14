package net.thenamedev.legendarena.utils;

import org.jetbrains.annotations.*;

/**
 * I really did not want to add this, but for whatever reason people make me do this. /sigh
 * @author TheNameMan
 */
public class FilterUtils {

    // I feel so dirty making these String[] lines ;-;
    private static final String[] swears =
    {
            "bitch",
            "fuck",
            "shit",
            "crap",
            "ass",
            "penis" /*dear god I regret adding this class already*/,
            "cunt",
            ""
    };
    private static final String[] dupeSwears =
    {
            "fak",
            "fek",
            "biotch"
    };

    public static boolean containsSwear(@NotNull String m) {
        boolean swear = false;
        @NotNull String msg = m.toLowerCase();
        for(@NotNull String sw : swears) {
            if(msg.contains(sw)) {
                swear = true;
            }
        }
        for(@NotNull String sw : dupeSwears) {
            if(msg.contains(sw)) {
                swear = true;
            }
        }
        return swear;
    }

}
