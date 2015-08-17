/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub.gadget

import legendapi.utils.Rank
import legendarena.hub.Gadget
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player

class CupidArrow : Gadget {

    override fun activate(p: Player) {
        //
    }

    override fun rankRequired(): Rank? {
        return null
    }

    override fun cooldownTime(): Int {
        return 30
    }

}