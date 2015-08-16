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

        var pInv = Bukkit.createInventory(null, 36, invName)
        pInv.setItem(11, MenuUtils.createItem(Material.BLAZE_POWDER, "" + ChatColor.GREEN + "Fire Particles"))
        pInv.setItem(12, MenuUtils.createItem(Material.ENDER_CHEST, "" + ChatColor.GREEN + "End Particles"))
        pInv.setItem(13, MenuUtils.createItem(Material.ENCHANTMENT_TABLE, "" + ChatColor.GREEN + "Enchant Particles"))
        pInv.setItem(14, MenuUtils.createItem(Material.REDSTONE, "" + ChatColor.GREEN + "Redstone Particles"))
        pInv.setItem(15, MenuUtils.createItem(Material.DIAMOND_SWORD, "" + ChatColor.GREEN + "Critical Hit Particles"))

        pInv.setItem(30, MenuUtils.createItem(Material.ARROW, "" + ChatColor.GREEN + "Back"))
        pInv.setItem(31, MenuUtils.createItem(Material.EMERALD, "" + ChatColor.GREEN + "Current Particles", "" + ChatColor.BLUE + if(ParticleCore.getParticles(p.getUniqueId()) == null) "NONE" else ParticleCore.getParticles(p.getUniqueId()).toString()))
        pInv.setItem(32, MenuUtils.createItem(Material.BARRIER, "" + ChatColor.GREEN + "No Particles"))
        p.openInventory(pInv)
    }

}