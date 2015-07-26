/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.utils;

import legendapi.log.BukLog;
import legendapi.log.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration utility stuff.
 */
public class ConfigUtils {

    private static final File configFile = new File(Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().getAbsolutePath(), "Config.yml");
    private static FileConfiguration config;
    private static boolean init = false;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void init() {
        if(init) return;

        if(!configFile.exists())
            try {
                //thanks java[tm]
                Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().mkdirs();
                configFile.createNewFile();
            } catch(IOException ex) {
                Bukkit.getLogger().info(" ");
                new BukLog().log(Level.INTERNALERROR, "[[[ BEGIN ERROR SPAM ]]]");
                Bukkit.getLogger().info(" ");
                ex.printStackTrace();
                new BukLog().log(Level.INTERNALERROR, "Error while creating configuration file! (see above for error spam)");
                init = true;
            }

        config = YamlConfiguration.loadConfiguration(configFile);

        if(!contains("configVersion"))
            //assume we need to generate a default config
            genConfig();
        else
            if(get("configVersion") != 1)
                //gen a default config
                try {
                    //noinspection deprecation
                    upgradeConfig();
                } catch(UnsupportedOperationException ignore) {}

        init = true;
    }

    public static void set(String key, Object set) {
        config.set(key, set);
    }

    public static Object get(String key) {
        return config.get(key);
    }

    public static void saveConfig() {
        try {
            config.save(configFile);
        } catch(IOException ex) {
            Bukkit.getLogger().info(" ");
            new BukLog().log(Level.INTERNALERROR, "[[[ BEGIN ERROR SPAM ]]]");
            Bukkit.getLogger().info(" ");
            ex.printStackTrace();
            new BukLog().log(Level.INTERNALERROR, "Failed to save config! (see above for error spam)");
        }
    }

    public static boolean contains(String key) {
        return config.contains(key);
    }

    private static void genConfig() {
        set("configVersion", 1);
        set("enable.lobbyServer", true);
        set("enable.warp", true);
        List<String> founders = new ArrayList<>();
        founders.add("ThePixelDev");
        founders.add("ZRaptor22");
        set("founders", founders);
        saveConfig();
    }

    @Deprecated
    /**
     * Not used yet.
     */
    private static void upgradeConfig() {
        throw new UnsupportedOperationException();
    }

}
