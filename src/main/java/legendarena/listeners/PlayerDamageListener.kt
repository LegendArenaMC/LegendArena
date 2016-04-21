/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners

import legendarena.hub.HubWarper
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class PlayerDamageListener : Listener {

    @EventHandler fun listenForDamage(ev: EntityDamageEvent) {
        if(ev.entity !is Player) return
        var p = ev.entity as Player
        if(HubWarper.isExempt(p.uniqueId)) return
        ev.isCancelled = true
    }

}