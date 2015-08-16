/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub.menu

import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendarena.hub.particles.ParticleCore
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player

class ParticleMenu {

    public var invName: String = ChatUtils.getCustomMsg("Menus") + "Particles"

    public constructor() {}

    public fun show(p: Player) {
        if(!Rank.VIP.isRanked(p)) {
            RankUtils.fancyNoPermissions(Rank.VIP, p)
            return
        }

        var pInv = Bukkit.createInventory(null, 9, invName)
        pInv.setItem(0, MenuUtils.createItem(Material.BLAZE_POWDER, "" + ChatColor.GREEN + "Fire Particles"))
        pInv.setItem(1, MenuUtils.createItem(Material.ENDER_CHEST, "" + ChatColor.GREEN + "End Particles"))
        pInv.setItem(2, MenuUtils.createItem(Material.ENCHANTMENT_TABLE, "" + ChatColor.GREEN + "Enchant Particles"))
        pInv.setItem(3, MenuUtils.createItem(Material.REDSTONE, "" + ChatColor.GREEN + "Redstone Particles"))
        pInv.setItem(4, MenuUtils.createItem(Material.DIAMOND_SWORD, "" + ChatColor.GREEN + "Critical Hit Particles"))

        pInv.setItem(8, MenuUtils.createItem(Material.BARRIER, "" + ChatColor.GREEN + "No Particles"))
        p.openInventory(pInv)
    }

}