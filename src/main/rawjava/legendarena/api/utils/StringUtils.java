package legendarena.api.utils;

/**
 * This only exists because Kotlin doesn't have a lot of String.[x] utils, such as toLowerCase(), etc.
 *
 * @author ThePixelDev
 */
public class StringUtils {

    public static String valueOf(Object o) {
        return String.valueOf(o);
    }

    public static String toUpper(String upper) {
        return upper.toUpperCase();
    }

    public static String toLower(String lower) {
        return lower.toLowerCase();
    }

    public static boolean contains(String check, String doesContain) {
        return check.contains(doesContain);
    }

    public static boolean startsWith(String check, String doesStartWith) {
        return check.startsWith(doesStartWith);
    }

    public static char[] toChars(String from) {
        return from.toCharArray();
    }

    public static String replace(String target, String replace, String with) {
        return target.replace(replace, with);
    }
}
