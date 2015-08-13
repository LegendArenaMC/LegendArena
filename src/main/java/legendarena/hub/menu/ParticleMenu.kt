/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub.menu

import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player

class ParticleMenu {

    public constructor() {}

    public fun show(p: Player) {
        if(!Rank.VIP.isRanked(p))
            RankUtils.fancyNoPermissions(Rank.VIP, p)

        var pInv = Bukkit.createInventory(null, 18, ChatUtils.getCustomMsg("Menus") + "Particles")
        //pInv.setItem(3, MenuUtils.createItem(Material.COMPASS, "" + ChatColor.GREEN + "Warper"))
        //pInv.setItem(5, MenuUtils.createItem(Material.NETHER_STAR, "" + ChatColor.GREEN + "Particle Selector"))
        pInv.setItem(0, MenuUtils.createItem(Material.BLAZE_POWDER, "" + ChatColor.GREEN + "Fire Particles"))
        p.openInventory(pInv)
    }

}