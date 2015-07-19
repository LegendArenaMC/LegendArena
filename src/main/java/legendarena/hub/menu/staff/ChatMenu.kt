package legendarena.hub.menu.staff

import legendapi.utils.MenuUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin

class ChatMenu {

    private var inv: Inventory? = null
    private var init = false

    public constructor() {
        init()
    }

    private fun init() {
        if(init) return  //if we've already initialized the chat menu, don't do anything
        inv = Bukkit.createInventory(null, 27, "" + ChatColor.BLUE + "Chat Selector")

        inv!!.setItem(4, MenuUtils.createItem(Material.BED, "" + ChatColor.GRAY + "⇐ Back", ""))

        inv!!.setItem(19, MenuUtils.createItem(Material.BEDROCK, "" + ChatColor.GREEN + "Global", ""))
        inv!!.setItem(23, MenuUtils.createItem(Material.APPLE, "" + ChatColor.GREEN + "Alert", ""))
        inv!!.setItem(24, MenuUtils.createItem(Material.APPLE, "" + ChatColor.GREEN + "Admin", ""))
        inv!!.setItem(25, MenuUtils.createItem(Material.APPLE, "" + ChatColor.GREEN + "Staff", ""))

        init = true
    }

    public fun show(p: Player) {
        val pInv = Bukkit.createInventory(null, 27, "" + ChatColor.BLUE + "Chat Selector")
        pInv.setContents(inv!!.getContents())
        p.openInventory(pInv)
    }
}