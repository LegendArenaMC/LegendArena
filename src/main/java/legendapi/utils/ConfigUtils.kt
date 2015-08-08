/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.utils

import com.sun.istack.internal.Nullable
import legendapi.exceptions.AreYouDrunkException
import legendapi.log.BukLog
import legendapi.log.Level
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException
import java.util.HashMap

/**
 * Base is stolen from the LegendArena plugin's first ConfigUtils class.
 */
public class ConfigUtils {

    internal var config: FileConfiguration? = null
    internal var configFile: File? = null
    internal var defaults = HashMap<String, Any>()
    internal var p: Plugin? = null
    internal var log: BukLog? = null
    internal var confVersion = 1
    internal var didExist = true

    public constructor(p: Plugin) {
        this.log = BukLog(p)

        configFile = File(p.getDataFolder().getAbsolutePath(), "Config.yml")

        this.p = p
        config = YamlConfiguration.loadConfiguration(configFile)
    }

    public fun saveConfig() {
        try {
            config!!.save(configFile)
        } catch(ex: IOException) {
            Bukkit.getLogger().info(" ")
            BukLog(p!!).log(Level.INTERNALERROR, "[[[ BEGIN ERROR SPAM ]]]")
            Bukkit.getLogger().info(" ")
            ex.printStackTrace()
            BukLog(p!!).log(Level.INTERNALERROR, "Failed to save config for plugin \"" + p!!.getDescription().getName() + "\"! (see above for error spam)")
        }
    }

    public fun setConfigVersion(ver: Int) {
        addDefault("configVersion", ver)
        this.confVersion = ver
    }

    public fun upgradeIfConfVersionIsNot(check: Int) {
        if(!configFile!!.exists()) {
            try {
                //Thanks Java[tm]
                p!!.getDataFolder().mkdirs()
                didExist = !configFile!!.createNewFile()
                log!!.log(Level.DEBUG, "Config file generated.")
            } catch(ex: IOException) {
                log!!.dumpError(ex, "generating config file")
                log!!.log(Level.INTERNALERROR, "Error while creating configuration file for plugin \"" + p!!.getDescription().getName() + "\"! (see above for reason)")
            }
            genDefaults()
            return
        }
        if(confVersion != check)
            throw AreYouDrunkException()
        if(!contains("configVersion")) {
            //we've probably never generated the config this run, generate it and exit
            genDefaults()
            return
        }
        if(get("configVersion") !is Int) {
            //something is wrong, throw a warning in the server log, reset the config to default and exit
            BukLog(p!!).log(Level.WARNING, "WARNING! CONFIG VERSION VALUE TAMPERING HAS OCCURED (or a very horrible internal error) - resetting config to default...")
            resetConfig()
            return
        }
        if((get("configVersion") as Int) < check)
            upgradeConfig()
    }

    public fun upgradeConfig() {
        BukLog(p!!).log(Level.INFO, "Upgrading configuration for plugin \"" + p!!.getDescription().getName() + "\"...")
        for(i in defaults.keySet()) {
            if(!contains(i))
                set(i, defaults.get(i))
            else {
                if(get(i) == defaults.get(i))
                    continue
                //sorry server owners! too lazy to make a new config system just to identify manually changed values! (blame bukkit[tm])
                set(i, defaults.get(i))
            }
        }
        saveConfig()
    }

    public fun contains(key: String): Boolean {
        return config!!.contains(key)
    }

    public fun set(key: String, set: Any) {
        config!!.set(key, set)
    }

    public fun addDefault(key: String, set: Any) {
        defaults.put(key, set)
    }

    public fun get(key: String): Any {
        return config!!.get(key)
    }

    public fun genDefaults() {
        log!!.log(Level.DEBUG, "Generating config from defaults...")
        for(i in defaults.keySet())
            set(i, defaults.get(i))
        log!!.log(Level.DEBUG, "Config generated.")
    }

    public fun resetConfig() {
        configFile!!.delete()
        config = null
        try {
            //thanks java[tm]
            p!!.getDataFolder().mkdirs()
            configFile!!.createNewFile()
        } catch(ex: IOException) {
            BukLog(p!!).dumpError(ex, "resetting configuration for plugin " + p!!.getDescription().getName())
            throw Exception("Could not reset configuration!")
        }
        config = YamlConfiguration.loadConfiguration(configFile)
    }

}