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
     *
     * Polls [SpecialStaffListUtils] like the Dev rank.
     */
    FOUNDER("", 7),
    /**
     * Developer rank.
     *
     * When the Rank class is asked if the member is a Developer, it polls the [SpecialStaffListUtils] class.
     */
    DEV("", 6),
    /**
     * Administrator rank.
     */
    ADMIN("legendarena.rank.admin", 5),
    /**
     * Moderator rank.
     */
    MOD("legendarena.rank.mod", 4),
    /**
     * Helper rank.
     */
    HELPER("legendarena.rank.helper", 3),
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
    VIP("legendarena.rank.vip", 2),
    /**
     * Donor rank. Only gives cosmetic options.
     *
     * Planned to be 10 dollars in the Legend Arena store.
     */
    MEMBERPLUS("legendarena.rank.memberplus", 1),
    /**
     * Default user rank. This is only here as a catch-all, really. Not much else use.
     */
    MEMBER("", 0);

    private var permission = ""
    private var internalId = 0

    private constructor() {}

    private constructor(permission: String, internalId: Int) {
        this.permission = permission
        this.internalId = internalId
    }

    public fun isRanked(p: CommandSender): Boolean {
        if(p is ConsoleCommandSender) return true

        when(this) {
            Rank.MEMBER -> return true
            Rank.DEV -> return SpecialStaffListUtils.isDeveloper(p as Player)
            Rank.FOUNDER -> return SpecialStaffListUtils.isFounder(p as Player)

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
            Rank.ADMIN -> return ChatColor.DARK_RED
            Rank.MOD -> return ChatColor.RED
            Rank.HELPER -> return ChatColor.GREEN
            Rank.VIP -> return ChatColor.GOLD
            Rank.MEMBERPLUS -> return ChatColor.BLUE

            else -> return ChatColor.GRAY
        }
    }

    public fun getInternalId(): Int {
        return internalId
    }

}