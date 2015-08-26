/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners

import legendarena.hub.HubWarper
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

public class PlayerDamageListener : Listener {

    EventHandler
    public fun listenForDamage(ev: EntityDamageEvent) {
        if(ev.getEntity() !is Player) return
        var p = ev.getEntity() as Player
        if(HubWarper.isExempt(p.getUniqueId())) return
        ev.setCancelled(true)
    }

}