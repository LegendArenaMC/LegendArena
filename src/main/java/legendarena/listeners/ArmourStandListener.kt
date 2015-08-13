/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners

import legendarena.hub.HubWarper
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerArmorStandManipulateEvent

public class ArmourStandListener : Listener {

    EventHandler public fun listenForArmourStand(ev: PlayerArmorStandManipulateEvent) {
        if(HubWarper.isExempt(ev.getPlayer().getUniqueId()))
            return
        ev.setCancelled(true)
    }

}