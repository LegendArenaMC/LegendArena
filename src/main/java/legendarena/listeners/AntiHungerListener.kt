/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners

import legendarena.hub.HubWarper
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.FoodLevelChangeEvent

class AntiHungerListener : Listener {

    @EventHandler fun listenForHunger(ev: FoodLevelChangeEvent) {
        if(HubWarper.isExempt(ev.entity.uniqueId))
            return
        ev.isCancelled = true
    }

}