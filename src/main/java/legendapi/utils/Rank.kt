/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.utils

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

public enum class Rank {

    /**
     * Founder rank. (really just a renamed Owner rank in a sense)
     */
    FOUNDER(true),
    /**
     * Developer rank.
     *
     * When the Rank class is asked if the member is a Developer, it polls the `legendapi.utils.DeveloperListUtils` class.
     */
    DEV(true),
    /**
     * Administrator rank.
     */
    ADMIN("legendarena.rank.admin"),
    /**
     * Moderator rank.
     */
    MOD("legendarena.rank.mod"),
    /**
     * Helper rank.
     */
    HELPER("legendarena.rank.helper"),
    /**
     * VIP rank.
     *
     * Requirements (any of the following):
     *
     * - 1,500+ subs (YT) / 2,000+ followers (Twitch) and 4+ videos on LA / stream at least three times on LA
     *
     * - Avg. 1k+ views per video / avg. 700+ viewers per stream
     *
     * - Founder/Owner (whichever one you prefer I guess) descretion (overrides the above two)
     */
    VIP("legendarena.rank.vip"),
    /**
     * Donor rank. Only gives cosmetic options.
     */
    MEMBERPLUS("legendarena.rank.memberplus"),
    /**
     * Default user rank. This is only here as a catch-all, really. Not much else use.
     */
    MEMBER(false);

    private var permission = ""

    private constructor(isSpecialRank: Boolean) {}

    private constructor(permission: String) {
        this.permission = permission
    }

    public fun isRanked(p: CommandSender): Boolean {
        if(p is ConsoleCommandSender) return true
        when(this) {
            Rank.MEMBER -> return true
            Rank.DEV -> return DeveloperListUtils.isDeveloper(p as Player)
            Rank.FOUNDER -> return p.getName().equals("ThePixelDev") || (p as Player).getUniqueId().toString().equals("2dec56e8-5548-4d89-8967-ee0da35f9874") //the UUID is Jaden - who apparently changes his IGN a lot.

            else -> return p.hasPermission(permission)
        }
    }

    public fun getNameColor(): ChatColor {
        when(this) {
            Rank.FOUNDER -> return ChatColor.LIGHT_PURPLE
            Rank.DEV -> return ChatColor.DARK_PURPLE
            Rank.ADMIN -> return ChatColor.RED
            Rank.MOD -> return ChatColor.RED
            Rank.HELPER -> return ChatColor.GREEN
            Rank.VIP -> return ChatColor.GOLD
            Rank.MEMBERPLUS -> return ChatColor.BLUE

            else -> return ChatColor.GRAY
        }
    }

}