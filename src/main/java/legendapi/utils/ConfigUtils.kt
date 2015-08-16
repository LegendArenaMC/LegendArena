/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.utils

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
 *
 * Also: WHY THE FUCK DO YOU NOT WORK. WHAT THE FUCK IS WRONG WITH YOU, YOU IDIOTIC PIECE OF CRAP, CONFIGUTILS. FUCK YOU. /innerrage
 */
public class ConfigUtils {

    internal var config: FileConfiguration? = null
    internal var configFile: File? = null
    internal var defaults = HashMap<String, Any>()
    internal var p: Plugin? = null
    internal var log: BukLog? = null
    internal var confVersion = 1

    public constructor(p: Plugin) {
        this.log = BukLog(p)

        configFile = File(p.getDataFolder().getAbsolutePath(), "Config.yml")

        this.p = p
        try {
            config = YamlConfiguration.loadConfiguration(configFile)
        } catch(ex: Exception) {
            resetConfig()
        }
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

    public fun genIfDoesNotExist(key: String) {
        if(!contains(key))
            genDefaults()
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

    public fun get(key: String): Any? {
        return config!!.get(key)
    }

    public fun genDefaults() {
        log!!.log(Level.DEBUG, "Generating config from defaults...")
        for(i in defaults.keySet())
            set(i, defaults.get(i))
        log!!.log(Level.DEBUG, "Config generated.")
        saveConfig()
    }

    public fun resetConfig() {
        try {
            configFile!!.delete()
        } catch(ex: IOException) {
            //do nothing
        }

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
        genDefaults()
    }

}