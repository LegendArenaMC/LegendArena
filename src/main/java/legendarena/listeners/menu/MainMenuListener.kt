/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners.menu

import legendarena.api.message.Message
import legendarena.api.utils.ChatUtils
import legendarena.hub.menu.Tag
import legendarena.hub.menu.MinigameMenu
import legendarena.hub.menu.ParticleMenu
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class MainMenuListener : Listener {

    @EventHandler fun onInventoryClick(ev: InventoryClickEvent) {
        if(!ev.inventory.name.equals(ChatUtils.getCustomMsg("Menus") + "Main Menu")) return
        try {
            if(ev.currentItem.itemMeta == null) return
            if(ev.currentItem.itemMeta.displayName.equals("" + ChatColor.GREEN + "Warper")) {
                ev.whoClicked.closeInventory()
                MinigameMenu().show(ev.whoClicked as Player)
            } else if(ev.currentItem.itemMeta.displayName.equals("" + ChatColor.GREEN + "Particle Selector")) {
                ev.whoClicked.closeInventory()
                ParticleMenu().show(ev.whoClicked as Player)
            } else if(ev.currentItem.itemMeta.displayName.equals("" + ChatColor.GREEN + "Tag Selector")) {
                ev.whoClicked.closeInventory()
                Tag().tagGUI(ev.whoClicked as Player)
            }
            ev.isCancelled = true
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

}