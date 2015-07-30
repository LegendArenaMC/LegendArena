package legendarena.hub.menu

import legendapi.emeralds.EmeraldsCore
import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendarena.hub.menu.staff.StaffMenu
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin

Deprecated class MainMenu {

    private var inv: Inventory? = null

    public constructor() {
        inv = Bukkit.createInventory(null, 9, ChatUtils.getCustomMsg("Menus") + "Main Menu")

        inv!!.setItem(0, MenuUtils.createItem(Material.WATCH, "" + ChatColor.GREEN + "Warper"))
        //inv!!.setItem(22, MenuUtils.createItem(Material.JUKEBOX, "" + ChatColor.GREEN + "Music"))
    }

    public fun show(p: Player) {
        val pInv = Bukkit.createInventory(null, 9, ChatUtils.getCustomMsg("Menus") + "Main Menu")
        pInv.setContents(inv!!.getContents())
        pInv.setItem(8, MenuUtils.createItem(Material.EMERALD, "" + ChatColor.GREEN + "Emeralds", "" + ChatColor.YELLOW + "You have " + ChatColor.RED + EmeraldsCore().getEmeralds(p) + ChatColor.YELLOW + " emeralds!"))
        p.openInventory(pInv)
    }
}