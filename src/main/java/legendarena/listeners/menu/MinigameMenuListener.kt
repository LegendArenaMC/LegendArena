package legendarena.listeners.menu

import legendapi.utils.ChatUtils
import legendarena.hub.menu.MainMenu
import legendarena.hub.menu.MinigameMenu
import legendarena.utils.ConfigUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

public class MinigameMenuListener : Listener {

    EventHandler(ignoreCancelled = false, priority = EventPriority.LOW)
    public fun onInventoryClick(ev: InventoryClickEvent) {
        try {
            if(!ev.getInventory().getName().equals(MinigameMenu().invName)) return
            if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Hub")) {
                ev.setCancelled(true)
                ev.getWhoClicked().closeInventory()
                val p = ev.getWhoClicked() as Player
                p.teleport(Bukkit.getWorld(ConfigUtils.config.get("hub.hubworld") as String).getSpawnLocation())
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Never Have I Ever")) {
                ev.setCancelled(true)
                ev.getWhoClicked().closeInventory()
                ev.getWhoClicked().sendMessage("" + ChatColor.GREEN + "Totally not a hint towards an actual minigame that works, nope, no hints here </sarcasm>")
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Back")) {
                ev.setCancelled(true)
                ev.getWhoClicked().closeInventory()
                MainMenu().show(ev.getWhoClicked() as Player)
            }
            ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

}