package legendarena.hub.menu

import legendarena.api.message.Message
import legendarena.api.utils.ChatUtils
import legendarena.api.utils.MenuUtils
import legendarena.utils.ConfigUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import java.util.*

class MinigameMenu {

    public var invName: String = ChatUtils.getCustomMsg("Menus") + "Warper"
    private var inv: Inventory? = null

    public constructor() {
        inv = Bukkit.createInventory(null, 36, invName)

        inv!!.setItem(12, MenuUtils.createItem(Material.DISPENSER, "" + ChatColor.GREEN + "Hub"))
        inv!!.setItem(14, MenuUtils.createItem(Material.STAINED_CLAY, "" + ChatColor.GREEN + "Never Have I Ever"))

        inv!!.setItem(31, MenuUtils.createItem(Material.ARROW, "" + ChatColor.GREEN + "Back"))
    }

    public fun show(p: Player) {
        p.openInventory(inv)
    }

    public inner class Listener : org.bukkit.event.Listener {

        @EventHandler
        public fun onInventoryClick(ev: InventoryClickEvent) {

            try {
                if(!ev.getInventory().getName().equals(invName)) return
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
                    Message().setSound(Sound.WOOD_CLICK).setPitch(1.0f, 1.1f).send(ev.getWhoClicked() as Player)
                }
                ev.setCancelled(true)
            } catch(ignore: Exception) {
                // Ignore the error
            }

        }

    }

}