/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.api.user

import legendarena.api.utils.ConfigUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.RankUtils
import legendarena.chat.ChatSystemUtils
import legendarena.chat.Notification
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.io.File
import java.util.*

/**
 * Is this a horrible temporary workaround for storing info about a player? Absolutely. Does it work? Yes.
 */
public class User {

    private var data: ConfigUtils? = null
    private var p: Player? = null

    public constructor(p: Player) {
        this.p = p
        var folder = File(Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().getAbsolutePath(), "data")
        folder.mkdirs()
        var file = File(Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().getAbsolutePath(), "data" + File.separator + p.getUniqueId().toString() + ".yml")
        file.createNewFile()
        this.data = ConfigUtils(Bukkit.getPluginManager().getPlugin("LegendArena"), file, true)

        //BEGIN DEFAULTS

        data!!.addDefault("uuid", p.getUniqueId().toString())
        var usernameList = ArrayList<String>()
        usernameList.add(p.getName())
        data!!.addDefault("namehistory", usernameList)
        data!!.addDefault("reports.count", 0)
        var reports = getArrayList()
        data!!.addDefault("reports.list", reports)
        data!!.addDefault("punish.banned", false)
        data!!.addDefault("punish.banreason", "Not banned")
        data!!.addDefault("punish.bannedby", "Not banned")
        data!!.addDefault("punish.warnings.count", 0)
        var warnings = getArrayList()
        data!!.addDefault("punish.warnings.list", warnings)
        data!!.addDefault("emeralds", 0)
        data!!.addDefault("isNoob", false)

        data!!.genIfDoesNotExist("uuid")
    }

    public fun setIsNoob(set: Boolean) {
        data!!.set("isNoob", set)
    }

    public fun isNoob(): Boolean {
        return data!!.get("isNoob") as Boolean
    }

    private fun getArrayList(): ArrayList<String> {
        var list = ArrayList<String>()
        list.add("None")
        return list
    }

    public fun report(reason: String) {
        var reports = getReports()

        if(!hasBeenReported())
            reports.clear()

        reports.add(reason)
        data!!.set("reports.list", reports)
        data!!.set("reports.count", reports.size())
        Notification.alert("" + ChatColor.YELLOW + p!!.getName() + ChatColor.RED + " has been reported for reason " + ChatColor.YELLOW + reason + ChatColor.RED + "!")

        data!!.saveConfig()
    }

    public fun warn(reason: String) {
        var warnings = getReports()

        if(!hasBeenWarned())
            warnings.clear()

        warnings.add(reason)
        data!!.set("punish.warnings.list", warnings)
        data!!.set("reports.count", warnings.size())
        Notification.alert("" + ChatColor.YELLOW + p!!.getName() + ChatColor.RED + " has been warned for reason " + ChatColor.YELLOW + reason)

        data!!.saveConfig()
    }

    public fun clearReports() {
        var reports = ArrayList<String>()

        reports.add("None")
        data!!.set("reports.list", reports)
        data!!.set("reports.count", 0)

        data!!.saveConfig()
    }

    public fun hasBeenWarned(): Boolean {
        var r = getWarnings()
        if(r.size() == 1)
            if(r.get(0).equals("None"))
                return false

        return true
    }

    public fun hasBeenReported(): Boolean {
        var r = getReports()
        if(r.size() == 1)
            if(r.get(0).equals("None"))
                return false

        return true
    }

    public fun getReports(): ArrayList<String> {
        return data!!.get("reports.list") as ArrayList<String>
    }

    public fun getWarnings(): ArrayList<String> {
        return data!!.get("punish.warnings.list") as ArrayList<String>
    }

    public fun getReportAmount(): Int {
        if(!hasBeenReported())
            return 0
        else
            return data!!.get("reports.count") as Int
    }

    public fun getName(): String {
        return ChatSystemUtils().getFormattedName(p!!)
    }

    public fun getPlayer(): Player {
        return p!!
    }

    public fun getDisplayRank(): Rank {
        return RankUtils.getDisplayRank(p!!)
    }

    public fun getRank(): Rank {
        return RankUtils.getRank(p!!)
    }

    public fun isBanned(): Boolean {
        return java.lang.Boolean.valueOf(data!!.get("punish.banned") as String)
    }

    public fun ban(reason: String, banner: String) {
        if(isBanned()) return
        data!!.saveConfig()
    }

}