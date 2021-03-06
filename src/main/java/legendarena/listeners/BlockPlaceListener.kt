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

public class BlockPlaceListener : Listener {

    EventHandler
    public fun listenForPlace(ev: BlockPlaceEvent) {
        try {
            if(!Rank.ADMIN.isRanked(ev.getPlayer())) return
            if(ev.getItemInHand().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "JumpPad"))
                ev.getBlock().getLocation().subtract(0.0, 1.0, 0.0).getBlock().setType(Material.REDSTONE_BLOCK)
        } catch(ignore: Exception) {}
    }

}