/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.api.utils

import legendarena.api.user.User
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
    MEMBER("", 0),
    /**
     * Removes ALL permissions. See the MEMBER rank for the actual default rank.
     */
    NERD("", -1);

    private var permission = ""
    private var internalId = 0

    private constructor() {}

    private constructor(permission: String, internalId: Int) {
        this.permission = permission
        this.internalId = internalId
    }

    public fun isRanked(p: CommandSender): Boolean {
        if(p !is Player) {
            if(p is ConsoleCommandSender)
                return true
            return false
        }

        when(this) {
            Rank.NERD -> {
                var u = User(p)
                if(u.isNoob())
                    return true
                return false
            }
            Rank.MEMBER -> return true
            Rank.DEV -> return SpecialStaffListUtils.isDeveloper(p)
            Rank.FOUNDER -> return SpecialStaffListUtils.isFounder(p)

            else -> return p.hasPermission(permission)
        }
    }

    /**
     * Yes, I realize the function name is Color, not Colour. Fuck it, too lazy to go through more code to fix it.
     */
    public fun getNameColor(): ChatColor {
        when(this) {
            Rank.FOUNDER -> return ChatColor.DARK_AQUA
            Rank.DEV -> return ChatColor.DARK_PURPLE
            Rank.ADMIN -> return ChatColor.DARK_RED
            Rank.MOD -> return ChatColor.RED
            Rank.HELPER -> return ChatColor.GREEN
            Rank.VIP -> return ChatColor.GOLD
            Rank.MEMBERPLUS -> return ChatColor.AQUA

            else -> return ChatColor.GRAY
        }
    }

    public fun getInternalId(): Int {
        return internalId
    }

    public fun isStaff(): Boolean {
        when(this) {
            FOUNDER -> return true
            DEV -> return true
            ADMIN -> return true
            MOD -> return true
            HELPER -> return true

            else -> return false
        }
    }

}