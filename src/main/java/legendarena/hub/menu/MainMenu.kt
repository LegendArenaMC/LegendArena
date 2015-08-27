/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub.menu

import legendarena.api.utils.ChatUtils
import legendarena.api.utils.MenuUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class MainMenu {

    public constructor() {}

    public fun show(p: Player) {
        var pInv = Bukkit.createInventory(null, 36, ChatUtils.getCustomMsg("Menus") + "Main Menu")
        pInv.setItem(11, MenuUtils.createItem(Material.COMPASS, "" + ChatColor.GREEN + "Warper"))
        pInv.setItem(13, MenuUtils.createItem(Material.NETHER_STAR, "" + ChatColor.GREEN + "Particle Selector"))
        pInv.setItem(15, MenuUtils.createItem(Material.NAME_TAG, "" + ChatColor.GREEN + "Tag Selector"))
        p.openInventory(pInv)
    }

}