/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub.menu

import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class MainMenu {

    public constructor() {}

    public fun show(p: Player) {
        var pInv = Bukkit.createInventory(null, 9, ChatUtils.getCustomMsg("Menus") + "Main Menu")
        pInv.setItem(3, MenuUtils.createItem(Material.COMPASS, "" + ChatColor.GREEN + "Warper"))
        pInv.setItem(5, MenuUtils.createItem(Material.NETHER_STAR, "" + ChatColor.GREEN + "Particle Selector"))
        p.openInventory(pInv)
    }

}