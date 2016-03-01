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

    var invName: String = ChatUtils.getCustomMsg("Menus") + "Warper"
    private var inv: Inventory? = null

    constructor() {
        inv = Bukkit.createInventory(null, 36, invName)

        inv!!.setItem(12, MenuUtils.createItem(Material.DISPENSER, "" + ChatColor.GREEN + "Hub"))
        inv!!.setItem(14, MenuUtils.createItem(Material.STAINED_CLAY, "" + ChatColor.GREEN + "Never Have I Ever"))

        inv!!.setItem(31, MenuUtils.createItem(Material.ARROW, "" + ChatColor.GREEN + "Back"))
    }

    fun show(p: Player) {
        p.openInventory(inv)
    }

    inner class Listener : org.bukkit.event.Listener {

        @EventHandler fun onInventoryClick(ev: InventoryClickEvent) {

            try {
                if(!ev.inventory.name.equals(invName)) return
                if(ev.currentItem.itemMeta.displayName.equals("" + ChatColor.GREEN + "Hub")) {
                    ev.isCancelled = true
                    ev.whoClicked.closeInventory()
                    val p = ev.whoClicked as Player
                    p.teleport(Bukkit.getWorld(ConfigUtils.config.get("hub.hubworld") as String).spawnLocation)
                } else if(ev.currentItem.itemMeta.displayName.equals("" + ChatColor.GREEN + "Never Have I Ever")) {
                    ev.isCancelled = true
                    ev.whoClicked.closeInventory()
                    ev.whoClicked.sendMessage("" + ChatColor.GREEN + "Totally not a hint towards an actual minigame that works, nope, no hints here </sarcasm>")
                } else if(ev.currentItem.itemMeta.displayName.equals("" + ChatColor.GREEN + "Back")) {
                    ev.isCancelled = true
                    ev.whoClicked.closeInventory()
                    MainMenu().show(ev.whoClicked as Player)
                }
                ev.isCancelled = true
            } catch(ignore: Exception) {
                // Ignore the error
            }

        }

    }

}