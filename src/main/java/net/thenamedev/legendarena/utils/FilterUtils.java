package net.thenamedev.legendarena.utils;

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

    public static boolean containsSwear(String m) {
        boolean swear = false;
        String msg = m.toLowerCase();
        for(String sw : swears) {
            if(msg.contains(sw)) {
                swear = true;
            }
        }
        for(String sw : dupeSwears) {
            if(msg.contains(sw)) {
                swear = true;
            }
        }
        return swear;
    }

}
