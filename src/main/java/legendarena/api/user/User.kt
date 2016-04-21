/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.api.user

import legendarena.api.utils.ConfigUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.RankUtils
import legendarena.api.utils.StringUtils
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
class User {

    private var data: ConfigUtils? = null
    private var p: Player? = null

    constructor(p: Player) {
        this.p = p
        var folder = File(Bukkit.getPluginManager().getPlugin("LegendArena").dataFolder.absolutePath, "data")
        folder.mkdirs()
        var file = File(Bukkit.getPluginManager().getPlugin("LegendArena").dataFolder.absolutePath, "data" + File.separator + p.uniqueId.toString() + ".yml")
        file.createNewFile()
        this.data = ConfigUtils(Bukkit.getPluginManager().getPlugin("LegendArena"), file, true)

        //BEGIN DEFAULTS

        data!!.addDefault("uuid", p.uniqueId.toString())
        var usernameList = ArrayList<String>()
        usernameList.add(p.name)
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
        data!!.addDefault("isNerd", false)

        data!!.genIfDoesNotExist("uuid")
    }

    fun save() {
        data!!.saveConfig()
    }

    fun setIsNerd(set: Boolean) {
        data!!.set("isNerd", set)
    }

    fun isNerd(): Boolean {
        return data!!.get("isNerd") as Boolean
    }

    private fun getArrayList(): ArrayList<String> {
        var list = ArrayList<String>()
        list.add("None")
        return list
    }

    fun report(reason: String) {
        var reports = getReports()

        if(!hasBeenReported())
            reports.clear()

        reports.add(reason)
        data!!.set("reports.list", reports)
        data!!.set("reports.count", StringUtils.getSize(reports))
        Notification.alert("" + ChatColor.YELLOW + p!!.name + ChatColor.RED + " has been reported for reason " + ChatColor.YELLOW + reason + ChatColor.RED + "!")

        save()
    }

    fun warn(reason: String) {
        var warnings = getReports()

        if(!hasBeenWarned())
            warnings.clear()

        warnings.add(reason)
        data!!.set("punish.warnings.list", warnings)
        data!!.set("reports.count", StringUtils.getSize(warnings))
        Notification.alert("" + ChatColor.YELLOW + p!!.name + ChatColor.RED + " has been warned for reason " + ChatColor.YELLOW + reason)

        save()
    }

    fun clearReports() {
        var reports = ArrayList<String>()

        reports.add("None")
        data!!.set("reports.list", reports)
        data!!.set("reports.count", 0)

        save()
    }

    fun hasBeenWarned(): Boolean {
        var r = getWarnings()
        if(StringUtils.getSize(r) == 1)
            if(r[0].equals("None"))
                return false

        return true
    }

    fun hasBeenReported(): Boolean {
        var r = getReports()
        if(StringUtils.getSize(r) == 1)
            if(r[0].equals("None"))
                return false

        return true
    }

    fun getReports(): ArrayList<String> {
        return data!!.get("reports.list") as ArrayList<String>
    }

    fun getWarnings(): ArrayList<String> {
        return data!!.get("punish.warnings.list") as ArrayList<String>
    }

    fun getReportAmount(): Int {
        if(!hasBeenReported())
            return 0
        else
            return data!!.get("reports.count") as Int
    }

    fun getName(): String {
        return ChatSystemUtils().getFormattedName(p!!)
    }

    fun getPlayer(): Player {
        return p!!
    }

    fun getDisplayRank(): Rank {
        return RankUtils.getDisplayRank(p!!)
    }

    fun getRank(): Rank {
        return RankUtils.getRank(p!!)
    }

    fun isBanned(): Boolean {
        return java.lang.Boolean.valueOf(data!!.get("punish.banned") as String)
    }

    fun ban(reason: String, banner: String) {
        if(isBanned()) return
        data!!.set("", true)
        data!!.saveConfig()
    }

}