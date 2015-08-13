package legendarena.hub.menu

import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import java.util.*

class MinigameMenu {

    private var inv: Inventory? = null

    public constructor() {
        inv = Bukkit.createInventory(null, 9, ChatUtils.getCustomMsg("Menus") + "Warper")

        inv!!.setItem(0, MenuUtils.createItem(Material.DISPENSER, "" + ChatColor.GREEN + "Hub"))
        inv!!.setItem(1, MenuUtils.createItem(Material.STAINED_CLAY, "" + ChatColor.GREEN + "Never Have I Ever"))

        inv!!.setItem(8, MenuUtils.createItem(Material.BED, "" + ChatColor.GREEN + "⇐ Back"))
    }

    public fun show(p: Player) {
        //...who thought this was a good idea to call a closeInventory function
        //p.closeInventory()
        p.openInventory(inv)
    }

}