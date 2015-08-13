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
    FOUNDER(),
    /**
     * Developer rank.
     *
     * When the Rank class is asked if the member is a Developer, it polls the [SpecialStaffListUtils] class.
     */
    DEV(),
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
    MEMBER();

    private var permission = ""

    private constructor() {}

    private constructor(permission: String) {
        this.permission = permission
    }

    public fun isRanked(p: CommandSender): Boolean {
        if(p is ConsoleCommandSender) return true
        when(this) {
            Rank.MEMBER -> return true
            Rank.DEV -> return SpecialStaffListUtils.isDeveloper(p as Player)
            Rank.FOUNDER -> return SpecialStaffListUtils.isFounder(p as Player) //yes, I know, it calls DeveloperListUtils. no, I don't care.

            else -> return p.hasPermission(permission)
        }
    }

    /**
     * Yes, I realize it's Color, not Colour. Fuck it, too lazy to go through more code to fix it.
     */
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