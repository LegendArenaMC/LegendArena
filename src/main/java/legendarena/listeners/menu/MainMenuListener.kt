/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners.menu

import legendarena.api.message.Message
import legendarena.api.utils.ChatUtils
import legendarena.hub.menu.Tag
import legendarena.hub.menu.MinigameMenu
import legendarena.hub.menu.ParticleMenu
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

public class MainMenuListener : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        if(!ev.getInventory().getName().equals(ChatUtils.getCustomMsg("Menus") + "Main Menu")) return
        try {
            if(ev.getCurrentItem().getItemMeta() == null) return
            if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Warper")) {
                ev.getWhoClicked().closeInventory()
                MinigameMenu().show(ev.getWhoClicked() as Player)
                Message().setSound(Sound.WOOD_CLICK).setPitch(1.0f, 1.1f).send(ev.getWhoClicked() as Player)
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Particle Selector")) {
                ev.getWhoClicked().closeInventory()
                ParticleMenu().show(ev.getWhoClicked() as Player)
                Message().setSound(Sound.WOOD_CLICK).setPitch(1.0f, 1.1f).send(ev.getWhoClicked() as Player)
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Tag Selector")) {
                ev.getWhoClicked().closeInventory()
                Tag().tagGUI(ev.getWhoClicked() as Player)
                Message().setSound(Sound.WOOD_CLICK).setPitch(1.0f, 1.1f).send(ev.getWhoClicked() as Player)
            }
            ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

}