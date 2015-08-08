/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub

import legendapi.utils.Rank
import org.bukkit.entity.Player

interface Gadget {

    /**
     * Can be null to signify anyone can use this.
     */
    fun rankRequired(): Rank?;

    /**
     * Run the gadget for a player.
     */
    fun activate(p: Player);

}