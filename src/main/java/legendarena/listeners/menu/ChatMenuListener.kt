package legendarena.listeners.menu

import legendarena.hub.menu.staff.StaffMenu
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class ChatMenuListener : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        try {
            if(!ev.getInventory().getName().equals("" + ChatColor.BLUE + "Chat Selector")) return
            if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Global")) {
                ev.getWhoClicked().closeInventory()
                (ev.getWhoClicked() as Player).performCommand("chat off")
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Alert")) {
                ev.getWhoClicked().closeInventory()
                (ev.getWhoClicked() as Player).performCommand("chat alert")
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Admin")) {
                ev.getWhoClicked().closeInventory()
                (ev.getWhoClicked() as Player).performCommand("chat admin")
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Staff")) {
                ev.getWhoClicked().closeInventory()
                (ev.getWhoClicked() as Player).performCommand("chat staff")
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Global")) {
                ev.getWhoClicked().closeInventory()
                StaffMenu().show(ev.getWhoClicked() as Player)
            }
            ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

}