package legendarena.hub.menu.staff

import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendarena.chat.ChatSystem
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

    public constructor() {}

    public fun show(p: Player) {
        val inv = Bukkit.createInventory(null, 9, ChatUtils.getCustomMsg("Menus") + "Staff")
        inv.setItem(0, MenuUtils.createItem(Material.STAINED_GLASS, "" + ChatColor.GREEN + "Chat Selector"))
        inv.setItem(1, MenuUtils.createItem(Material.IRON_PLATE, "" + ChatColor.GREEN + "JumpPads", "" + ChatColor.BLUE + "Whee!"))
        //inv.setItem(2, MenuUtils.createItem(Material.BARRIER, "" + ChatColor.GREEN + "Global Mute", "" + ChatColor.BLUE + "Current status: " + ChatColor.RED + (if (ChatSystem.isChatMuted()) "ON" else "OFF") + ChatColor.GRAY + " (click to toggle)"))

        inv.setItem(8, MenuUtils.createItem(Material.BED, "" + ChatColor.GREEN + "Close Menu"))
        p.openInventory(inv)
    }
}