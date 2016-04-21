/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.api.utils

import legendarena.api.log.BukLog
import legendarena.api.message.Message
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import java.util.ArrayList
import java.util.Collections

class SetupUtils {

    internal var p: Plugin? = null
    internal var log: BukLog

    constructor(p: Plugin) {
        //someone remind me to fix this monstrocity of a plugin loading header message.
        //Message().append(java.lang.String.format("%s-•- [%s%s%s] -•-", ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE, "Plugin: " + p.getDescription().getName(), ChatColor.LIGHT_PURPLE)).broadcast(Rank.DEV)
        Message().append(ChatUtils.getCustomMsg("SetupUtils") + "Loading " + ChatColor.YELLOW + p.description.name)
        this.p = p
        this.log = BukLog(p)
    }

    constructor(p: Plugin, quiet: Boolean) {
        //someone remind me to fix this monstrocity of a plugin loading header message.
        //Message().append(java.lang.String.format("%s-•- [%s%s%s] -•-", ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE, "Plugin: " + p.getDescription().getName(), ChatColor.LIGHT_PURPLE)).broadcast(Rank.DEV)
        if(!quiet)
            Message().append(ChatUtils.getCustomMsg("SetupUtils") + "Loading " + ChatColor.YELLOW + p.description.name)
        this.p = p
        this.log = BukLog(p)
    }

    /**
     * Announce the init status
     * @param msg The message to announce
     */
    fun announceStatus(msg: String) {
        Message().append(ChatUtils.getCustomMsg("Startup Log [" + p!!.description.name + "]") + msg).broadcast(Rank.DEV)
        log.debug("Startup Log [" + p!!.description.name + "] " + msg)
    }

    /**
     * Register a listener
     * @param listener The listener to register
     */
    fun registerListener(listener: Listener) {
        Bukkit.getPluginManager().registerEvents(listener, p)
    }

    /**
     * Register a command
     * @param cmd The CommandExecutor to register
     * @param name The command to register the executor as
     */
    fun registerCommand(cmd: CommandExecutor, name: String) {
        Bukkit.getPluginCommand(name).executor = cmd
    }

    /**
     * Register an async timer (use registerNonAsyncTimer if you plan on doing things such as adding potion effects)
     * @param run The runnable to register
     * @param time The amount of ticks between each run
     */
    fun registerTimer(run: Runnable, time: Long) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(p, run, time, time)
    }

    /**
     * Register a non-async timer
     * @param run The runnable to register
     * @param time The amount of ticks between each run
     */
    fun registerNonAsyncTimer(run: Runnable, time: Long) {
        Bukkit.getScheduler().runTaskTimer(p, run, time, time)
    }
    /**
     * Register a non-async timer
     * @param run The runnable to register
     * @param time The amount of ticks between each run
     */
    fun registerNonAsyncTimer(run: Runnable, time1: Long, time2: Long) {
        Bukkit.getScheduler().runTaskTimer(p, run, time1, time2)
    }

    /**
     * Set a command's aliases
     * @param target The target command
     * @param list The aliases to register
     */
    fun setAliases(target: String, vararg list: String) {
        val alias = ArrayList<String>()
        Collections.addAll(alias, *list)
        Bukkit.getPluginCommand(target).aliases = alias
    }

}