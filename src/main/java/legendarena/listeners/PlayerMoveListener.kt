/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerTeleportEvent

public class PlayerMoveListener : Listener {

    @EventHandler public fun listenForMove(ev: PlayerMoveEvent) {
        if(ev.getTo().getY() < -5) {
            ev.setCancelled(true)
            ev.getPlayer().teleport(ev.getPlayer().getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN)
        }
    }

}