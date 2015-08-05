/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub.menu.staff

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
import org.bukkit.plugin.Plugin

class JumpPadMenu {

    private var inv: Inventory? = null

    public constructor() {
        inv = Bukkit.createInventory(null, 9, ChatUtils.getCustomMsg("Menus") + "Jump Pads Menu")

        inv!!.setItem(0, MenuUtils.createItem(Material.IRON_PLATE, "" + ChatColor.GREEN + "Get JumpPad"))
        inv!!.setItem(1, MenuUtils.createItem(Material.GOLD_PLATE, "" + ChatColor.GREEN + "Portable JumpPad"))

        inv!!.setItem(8, MenuUtils.createItem(Material.BED, "" + ChatColor.GREEN + "⇐ Back"))
    }

    public fun show(p: Player) {
        p.openInventory(inv)
    }

}