/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners

import legendapi.utils.Rank
import legendarena.hub.HubWarper
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryInteractEvent

public class InventoryInteractListener : Listener {

    EventHandler
    public fun listenForInteract(ev: InventoryInteractEvent) {
        if(HubWarper.isExempt((ev.getWhoClicked() as Player).getUniqueId())) return

        ev.setResult(Event.Result.DENY)
    }

}