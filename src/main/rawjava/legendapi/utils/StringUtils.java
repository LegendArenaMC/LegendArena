package legendapi.utils;

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

}
