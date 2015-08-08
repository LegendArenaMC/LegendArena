/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.utils

import org.bukkit.entity.Player

public class StaffPlayer {

    var p: Player? = null
    var r: Rank? = null //protip: don't name this "rank" unless you want IntelliJ to bitch about it constantly.

    public constructor(p: Player) {
        this.p = p
        this.r = RankUtils.getRank(p)
    }

    public fun getPlayer(): Player {
        return p!!
    }

    public fun getRank(): Rank {
        return r!!
    }

}