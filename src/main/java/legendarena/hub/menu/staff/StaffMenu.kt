package legendarena.hub.menu.staff

import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendarena.chat.ChatSystemOld
import legendarena.hub.menu.MainMenu
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin

class StaffMenu {

    public constructor() {
        init(Bukkit.getPluginManager().getPlugin("LegendArena"))
    }

    private var inv: Inventory? = null
    private var init = false

    private fun init(p: Plugin) {
        if(init) return  //if we've already initialized the staff menu, don't do anything
        inv = Bukkit.createInventory(null, 27, ChatUtils.getCustomMsg("Menus") + "Staff Menu")

        inv!!.setItem(4, MenuUtils.createItem(Material.BED, "" + ChatColor.GRAY + "⇐ Back", ""))

        init = true
    }

    public fun show(p: Player) {
        val pInv = Bukkit.createInventory(null, 27, ChatUtils.getCustomMsg("Menus") + "Staff Menu")
        pInv.setContents(inv!!.getContents())
        pInv.setItem(19, MenuUtils.createItem(Material.GLASS, "" + ChatColor.GREEN + "Chat Selector", "" + ChatColor.BLUE + "Current channel: " + ChatColor.RED + (if (ChatSystemOld.getChannel(p) == null) "PUBLIC" else ChatSystemOld.getChannel(p))))
        pInv.setItem(22, MenuUtils.createItem(Material.BARRIER, "" + ChatColor.GREEN + "Global Mute", "" + ChatColor.BLUE + "Current status: " + ChatColor.RED + (if (ChatSystemOld.isChatMuted()) "ON" else "OFF") + ChatColor.GRAY + " (click to toggle)"))
        p.openInventory(pInv)
    }
}