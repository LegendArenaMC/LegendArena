/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.utils;

import java.util.HashMap;

public class HelpUtils {

    private static HashMap<String, HashMap<String, String>> helpEntries = new HashMap<>();

    public static HashMap<String, HashMap<String, String>> getHelpList() {
        return helpEntries;
    }

    public static HashMap<String, String> getHelpList(String key) {
        return helpEntries.get(key);
    }

    public static void addEntries(String key, HashMap<String, String> add) {
        helpEntries.put(key, add);
    }

    public static void removeEntries(String key) {
        helpEntries.remove(key);
    }

    public static boolean containsKey(String key) {
        return helpEntries.containsKey(key);
    }

    public static void appendOntoEntryList(String key, HashMap<String, String> append) {
        HashMap<String, String> appendOnto = getHelpList(key);
        for(String a : append.keySet())
            appendOnto.put(a, append.get(a));
        removeEntries(key);
        addEntries(key, appendOnto);
    }

}
