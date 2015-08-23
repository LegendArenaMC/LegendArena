package legendapi.utils;

import java.util.HashMap;

public class VersionUtils {

    private static HashMap<String, String> versionList = new HashMap<>();

    public static String getAPIVersion() {
        return "1.2";
    }

    public static int getAPIVersionInt() {
        return 3;
    }

    /**
     * Codenames are usually generated here: http://www.michaelfogleman.com/phrases/
     */
    public static String getAPIVersionCodename() {
        return "Control Guide";
    }

    public static String getVersion(String plugin) {
        return (versionList.containsKey(plugin) ? versionList.get(plugin) : "Version list key \"" + plugin + "\" not found");
    }

    public static void setVersion(String plugin, String version) {
        if(versionList.containsKey(plugin)) throw new UnsupportedOperationException("Version for plugin \"" + plugin + "\" is already set!");
        versionList.put(plugin, version);
    }

}
