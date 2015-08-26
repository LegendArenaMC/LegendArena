/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.utils;

import legendapi.log.BukLog;
import legendapi.log.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ConfigUtils {

    private FileConfiguration config = null;
    private File configFile = null;
    private HashMap<String, Object> defaults = new HashMap<>();
    private Plugin p;
    private BukLog log;
    //TODO: Use the following method somewhere
    private int confVersion = 1;

    public ConfigUtils(Plugin p) {
        this.p = p;
        this.log = new BukLog(p);

        configFile = new File(p.getDataFolder().getAbsolutePath(), "Config.yml");

        try {
            config = YamlConfiguration.loadConfiguration(configFile);
        } catch(Exception ex) {
            resetConfig();
        }
    }

    public ConfigUtils(Plugin p, String name) {
        this.p = p;
        this.log = new BukLog(p);

        configFile = new File(p.getDataFolder().getAbsolutePath(), name + ".yml");

        try {
            config = YamlConfiguration.loadConfiguration(configFile);
        } catch(Exception ex) {
            resetConfig();
        }
    }

    public ConfigUtils(Plugin p, File f) {
        this.p = p;
        this.log = new BukLog(p);

        configFile = f;

        try {
            config = YamlConfiguration.loadConfiguration(configFile);
        } catch(Exception ex) {
            resetConfig();
        }
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch(IOException ex) {
            Bukkit.getLogger().info(" ");
            log.log(Level.ERROR, "[[[ BEGIN ERROR SPAM ]]]");
            Bukkit.getLogger().info(" ");
            ex.printStackTrace();
            log.log(Level.ERROR, "Failed to save config for plugin \"" + p.getDescription().getName() + "\"! (see above for error spam)");
        }
    }

    public void setConfigVersion(int ver) {
        addDefault("configVersion", ver);
        this.confVersion = ver;
    }

    public void genIfDoesNotExist(String key) {
        if(!contains(key))
            genDefaults();
    }

    public boolean contains(String key) {
        return config.contains(key);
    }

    public void set(String key, Object set) {
        config.set(key, set);
    }

    public void addDefault(String key, Object set) {
        defaults.put(key, set);
    }

    public Object get(String key) {
        return config.get(key);
    }

    public void genDefaults() {
        log.log(Level.DEBUG, "Generating config from defaults...");
        for(String i : defaults.keySet())
            set(i, defaults.get(i));
        log.log(Level.DEBUG, "Config generated.");
        saveConfig();
    }

    public void resetConfig() {
        configFile.delete();

        config = null;

        try {
            //thanks java[tm]
            p.getDataFolder().mkdirs();
            configFile.createNewFile();
        } catch(IOException ex) {
            log.dumpError(ex, "resetting configuration for plugin " + p.getDescription().getName());
            return;
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        genDefaults();
    }

}
