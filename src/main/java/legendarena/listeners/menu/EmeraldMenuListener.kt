package legendarena.listeners.menu

import legendapi.utils.ChatUtils
import legendarena.hub.menu.MainMenu
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class EmeraldMenuListener : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        try {
            if(!ev.getInventory().getName().equals(ChatUtils.getCustomMsg("Menus") + "Emeralds Menu")) return
            if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Emeralds") || ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Booster")) {
                ev.getWhoClicked().closeInventory()
                ev.getWhoClicked().sendMessage("...you really expected that would do something. wow. I'm amazed. no, really, I'm amazed.")
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GRAY + "⇐ Back")) {
                ev.getWhoClicked().closeInventory()
                MainMenu().show(ev.getWhoClicked() as Player)
            }
            ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

}