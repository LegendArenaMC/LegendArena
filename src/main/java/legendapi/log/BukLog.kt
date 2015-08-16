/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.log

import legendapi.utils.StringUtils
import legendarena.LegendArena
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.util.logging.Logger

/**
 * The name is a stupid combination of Bukkit and Log.
 *
 * Don't ask how the fuck I came up with it. Because even I don't know how.
 */
class BukLog {

    internal var p: Plugin? = null
    internal var l: Logger? = null

    public constructor(p: Plugin) {
        this.p = p
        l = Bukkit.getLogger()
    }

    internal fun log(level: String, log: String) {
        Bukkit.getLogger().info("BukLog [Level: " + StringUtils.toUpper(level) + " | From: " + p!!.getDescription().getName() + "] " + log)
    }

    public fun log(l: Level, s: String): Unit = when(l) {
        Level.ITSALLBROKEN -> log("IT'S ALL BROKEN", s) //WE'RE ALL GONNA DIE
        Level.PIXELBROKEIT -> log("PIXEL BROKE IT", s)
        Level.SEVERE -> log("SEVERE", s)
        Level.WARNING -> log("WARNING", s)
        Level.INFO -> log("INFO", s)
        Level.DEBUG -> log("DEBUG", s)
        Level.ERROR -> log("ERROR", s)
        Level.INTERNALERROR -> log("INTERNAL ERROR", s)
    }

    fun debug(m: String) {
        log(Level.DEBUG, m)
    }

    /**
     * Dump an error into the console with a reason string.
     *
     * Example log: "An error occured while giving emeralds to all players! (Player "ZRaptor22" is a nub)" (sorry Jaden!)
     */
    fun dumpError(ex: Throwable, reason: String) {
        if(LegendArena().devMode) { //are we in dev mode? if so, print the stacktrace instead of the plain message
            ex.printStackTrace()
            log(Level.PIXELBROKEIT, "An error was encountered while " + reason + "! (see above for stacktrace)")
        } else {
            log(Level.INTERNALERROR, "An error was encountered while " + reason + "! (\"" + ex.getMessage() + "\")")
        }
    }

    /**
     * Dump an error into the console, with no "An error was occured while {x}" addition.
     *
     * Example log: "Exception occured while trying to give diamonds to ThePixelDev! (NullPointerException {...})"
     */
    fun dumpRawError(ex: Throwable, reason: String) {
        if(LegendArena().devMode) { //are we in dev mode? if so, print the stacktrace instead of the plain message
            ex.printStackTrace()
            log(Level.PIXELBROKEIT, reason + " (see above for stacktrace)")
        } else {
            log(Level.INTERNALERROR, reason + " (\"" + ex.getMessage() + "\")")
        }
    }

}