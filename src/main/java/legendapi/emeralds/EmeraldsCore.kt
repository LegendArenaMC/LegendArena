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

    public fun getEmeralds(p: String): Int {
        return econ.getEmeralds(p)
    }

    public fun removeEmeralds(p: String, amount: Int) {
        if(amount < 1)
            throw NullPointerException()
        else if(amount >= Integer.MAX_VALUE)
            throw AreYouDrunkException("Amount cannot be over max value of an Integer") //hopefully, no one is actually an idiot enough to hit this (...I'll give it a year)
        getEcon().takeEmeralds(p, amount)
    }

    public fun addEmeralds(p: String, amount: Int) {
        if(amount < 1)
            throw NullPointerException("You may want to actually give the player an emerald with the addEmeralds() function.")
        else if(amount >= Integer.MAX_VALUE)
            throw AreYouDrunkException("Amount cannot be over max value of an Integer") //hopefully, no one is actually an idiot enough to hit this (...I'll give it a year)
        econ.addEmeralds(p, amount)
    }

    public fun resetEmeralds(p: String) {
        removeEmeralds(p, getEmeralds(p))
    }

}