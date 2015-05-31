package net.thenamedev.legendapi.core.regex;

/**
 * Regex utilities.
 *
 * @author ThePixelDev
 */
public class RegexUtils {

    public static boolean endsWith(String check, Object endsWith) {
        if(endsWith == null)
            throw new NullPointerException("Input cannot be null (endsWith Object)");
        if(check == null || check.equals(""))
            throw new NullPointerException("Input cannot be null (check String)");
        return check.matches("*" + endsWith.toString());
    }

    public static boolean startsWith(String check, Object startsWith) {
        if(startsWith == null)
            throw new NullPointerException("Input cannot be null (startsWith Object)");
        if(check == null || check.equals(""))
            throw new NullPointerException("Input cannot be null (check String)");
        return check.matches(startsWith.toString() + "*");
    }

    /**
     * Yo dawg, I heard you like Strings, so I put some Strings inside your Strings so you can String while you String.<br><br>
     *
     * <em>i am so sorry, that joke was fucking atrocious, i promise to never do that again</em>
     * @param target The target to replace
     * @param replace The string to replace
     * @param replaceWith The replacement string
     * @return The string with the replaced text
     */
    public static String replace(String target, String replace, String replaceWith) {
        return target.replaceAll(replace, replaceWith);
    }

    public static boolean contains(String check, String contains) {
        return check.matches("*" + contains + "*");
    }

}
