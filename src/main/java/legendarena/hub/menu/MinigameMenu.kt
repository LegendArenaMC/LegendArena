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
    private var init = false

    public constructor() {
        init()
    }

    private fun init() {
        if(init) return

        val items = HashMap<Int, ItemStack>()

        //TODO: Clean this damn thing up

        items.put(4, MenuUtils.createItem(Material.BED, "" + ChatColor.GRAY + "⇐ Back", ""))
        items.put(21, MenuUtils.createItem(Material.DISPENSER, "" + ChatColor.GREEN + "Hub"))
        items.put(23, MenuUtils.createItem(Material.STAINED_CLAY, "" + ChatColor.GREEN + "Never Have I Ever"))

        inv = Bukkit.createInventory(null, 27, ChatUtils.getCustomMsg("Menus") + "Warper")

        for(a in items.keySet())
            inv!!.setItem(a, items.get(a))

        init = true
    }

    public fun show(p: Player) {
        p.closeInventory()
        p.openInventory(inv)
    }

}