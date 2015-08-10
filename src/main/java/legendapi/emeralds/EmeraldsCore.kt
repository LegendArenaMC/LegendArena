/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.emeralds

import legendapi.economy.LegendEconomy
import legendapi.exceptions.AreYouDrunkException
import legendapi.log.BukLog
import legendapi.log.Level
import legendapi.utils.ChatUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class EmeraldsCore {

    private var econ = LegendEconomy()

    public constructor() {}

    public fun getEcon(): LegendEconomy {
        return econ
    }

    public fun getEmeralds(p: Player): Int {
        return econ.getEmeralds(p.getName())
    }

    public fun removeEmeralds(p: Player, amount: Int, showMsg: Boolean) {
        if(amount < 1)
            throw NullPointerException()
        else if(amount >= Integer.MAX_VALUE)
            throw AreYouDrunkException("Amount cannot be over max value of an Integer") //hopefully, no one is actually an idiot enough to hit this (...I'll give it a year)
        getEcon().takeEmeralds(p.getName(), amount)
        if(showMsg)
            p.sendMessage(ChatUtils.getCustomMsg("Emeralds") + "You lost " + ChatColor.DARK_PURPLE + amount + ChatColor.BLUE + " emerald(s). You now have " + ChatColor.DARK_PURPLE + getEmeralds(p) + ChatColor.BLUE + " emerald(s).")
    }

    public fun addEmeralds(p: Player, amount: Int, showMsg: Boolean) {
        if(amount < 1)
            throw NullPointerException()
        econ.addEmeralds(p.getName(), amount)
        if(showMsg)
            p.sendMessage(ChatUtils.getCustomMsg("Emeralds") + "You gained " + ChatColor.DARK_PURPLE + amount + ChatColor.BLUE + " emerald(s)! You now have " + ChatColor.DARK_PURPLE + getEmeralds(p) + ChatColor.BLUE + " emerald(s)!")
    }

    public fun resetEmeralds(p: Player, showMsg: Boolean, resetter: String) {
        removeEmeralds(p, getEmeralds(p), false)
        if(showMsg)
            p.sendMessage(ChatUtils.getCustomMsg("Emeralds") + "Aww. You lost all your emeralds, due to a reset of your account by " + ChatColor.DARK_PURPLE + resetter + ChatColor.BLUE + ".")
    }

}