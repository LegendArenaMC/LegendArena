/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.utils

import org.bukkit.entity.Player

/**
 * Horrible wrapper for a Player that is a staff member.
 *
 * Does it work? Yes. Does it use memory like nobody's business? Depends.
 */
public class StaffPlayer {

    var p: Player? = null
    var r: Rank? = null

    public constructor(p: Player) {
        this.p = p
        this.r = RankUtils.getRank(p)
    }

    public fun getPlayer(): Player {
        return p!!
    }

    public fun getFormattedName(): String {
        return "" + r!!.getNameColor() + p!!.getName()
    }

    public fun getRank(): Rank {
        return r!!
    }

    fun getName(): String {
        return getPlayer().getName()
    }

}