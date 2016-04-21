/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners

import legendarena.api.utils.Rank
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

class BlockPlaceListener : Listener {

    @EventHandler fun listenForPlace(ev: BlockPlaceEvent) {
        try {
            if(Rank.ADMIN.isRanked(ev.player)) return
            if(ev.itemInHand.itemMeta.displayName.equals("" + ChatColor.GREEN + "JumpPad"))
                ev.block.location.subtract(0.0, 1.0, 0.0).block.type = Material.REDSTONE_BLOCK
        } catch(ignore: Exception) {}
    }

}