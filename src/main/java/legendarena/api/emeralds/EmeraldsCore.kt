/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.api.emeralds

import legendarena.api.economy.LegendEconomy
import legendarena.api.exceptions.AreYouDrunkException
import legendarena.api.log.BukLog
import legendarena.api.log.Level
import legendarena.api.utils.ChatUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

/**
 * This is broken to hell and back and should be avoided at all costs.
 */
@Deprecated(message = "This is broken to hell and back - avoid at all costs.")
class EmeraldsCore {

    private var econ = LegendEconomy()

    constructor() {}

    fun getEcon(): LegendEconomy {
        return econ
    }

    fun getEmeralds(p: String): Int {
        return econ.getEmeralds(p)
    }

    fun removeEmeralds(p: String, amount: Int) {
        if(amount < 1)
            throw NullPointerException()
        else if(amount >= Integer.MAX_VALUE)
            throw AreYouDrunkException("Amount cannot be over max value of an Integer") //hopefully, no one is actually an idiot enough to hit this (...I'll give it a year)
        getEcon().takeEmeralds(p, amount)
    }

    fun addEmeralds(p: String, amount: Int) {
        if(amount < 1)
            throw NullPointerException("You may want to actually give the player an emerald with the addEmeralds() function.")
        else if(amount >= Integer.MAX_VALUE)
            throw AreYouDrunkException("Amount cannot be over max value of an Integer") //hopefully, no one is actually an idiot enough to hit this (...I'll give it a year)
        econ.addEmeralds(p, amount)
    }

    fun resetEmeralds(p: String) {
        removeEmeralds(p, getEmeralds(p))
    }

}