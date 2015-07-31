package legendarena.listeners.menu

import legendapi.utils.ChatUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class MinigameMenuListener : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        if(!ev.getInventory().getName().equals(ChatUtils.getCustomMsg("Menus") + "Warper")) return
        try {
            if(ev.getCurrentItem().getItemMeta() == null) return
            if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Hub")) {
                ev.setCancelled(true)
                ev.getWhoClicked().closeInventory()
                val p = ev.getWhoClicked() as Player
                p.teleport(Bukkit.getWorld("world").getSpawnLocation())
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Never Have I Ever")) {
                ev.setCancelled(true)
                ev.getWhoClicked().closeInventory()
                ev.getWhoClicked().sendMessage("" + ChatColor.GREEN + "Totally not a hint towards an actual minigame that works, nope, no hints here </sarcasm>")
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Close Menu")) {
                ev.setCancelled(true)
                ev.getWhoClicked().closeInventory()
            } else
                ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

}