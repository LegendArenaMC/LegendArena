package legendapi.utils;

import java.util.HashMap;

public class VersionUtils {

    private static HashMap<String, String> versionList = new HashMap<>();

    public static String getAPIVersion() {
        return "1.0";
    }

    /**
     * Codenames are generated here: http://www.michaelfogleman.com/phrases/<br>
     * Exceptions can be made for special releases, however.
     */
    public static String getAPIVersionCodename() {
        return "Initial Hope";
    }

    public static String getVersion(String plugin) {
        return (versionList.containsKey(plugin) ? versionList.get(plugin) : "Version list key \"" + plugin + "\" not found");
    }

    public static void setVersion(String plugin, String version) {
        if(versionList.containsKey(plugin)) throw new UnsupportedOperationException("Version for plugin \"" + plugin + "\" is already set!");
        versionList.put(plugin, version);
    }

}
