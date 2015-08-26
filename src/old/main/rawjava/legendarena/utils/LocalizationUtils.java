/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.utils;

import org.bukkit.Bukkit;

/**
 * I18N utils class for the main plugin.
 */
@SuppressWarnings("unused")
public class LocalizationUtils {

    //TODO: Setup English strings

    private static legendapi.utils.ConfigUtils i18n = new legendapi.utils.ConfigUtils(Bukkit.getPluginManager().getPlugin("LegendArena"), "I18n.yml");

    public static String getLocalizedString(String language, String key) {
        return (String) i18n.get(language + "." + key);
    }

    public static void setLocalizedString(String language, String key, String localized) {
        i18n.set(language + "." + key, localized);
    }

}
