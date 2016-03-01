/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub.menu

import legendarena.api.message.Message
import legendarena.api.utils.ChatUtils
import legendarena.api.utils.MenuUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.RankUtils
import legendarena.hub.particles.ParticleCore
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent

class ParticleMenu {

    var invName: String = ChatUtils.getCustomMsg("Menus") + "Particles"

    constructor() {}

    fun show(p: Player) {
        if(!Rank.VIP.isRanked(p)) {
            RankUtils.fancyNoPermissions(Rank.VIP, p)
            return
        }

        var pInv = Bukkit.createInventory(null, 36, invName)
        pInv.setItem(10, MenuUtils.createItem(Material.BLAZE_POWDER, "" + ChatColor.GREEN + "Fire Particles"))
        pInv.setItem(11, MenuUtils.createItem(Material.ENDER_CHEST, "" + ChatColor.GREEN + "End Particles"))
        pInv.setItem(12, MenuUtils.createItem(Material.ENCHANTMENT_TABLE, "" + ChatColor.GREEN + "Enchant Particles"))
        pInv.setItem(13, MenuUtils.createItem(Material.REDSTONE, "" + ChatColor.GREEN + "Redstone Particles"))
        pInv.setItem(14, MenuUtils.createItem(Material.DIAMOND_SWORD, "" + ChatColor.GREEN + "Critical Hit Particles"))
        pInv.setItem(15, MenuUtils.createItem(Material.WOOL, "" + ChatColor.GREEN + "Heart Particles"))
        pInv.setItem(16, MenuUtils.createItem(Material.WATER_BUCKET, "" + ChatColor.GREEN + "Colorful Effect Particles"))

        pInv.setItem(30, MenuUtils.createItem(Material.ARROW, "" + ChatColor.GREEN + "Back"))
        pInv.setItem(31, MenuUtils.createItem(Material.EMERALD, "" + ChatColor.GREEN + "Current Particles", "" + ChatColor.BLUE + if(ParticleCore.getParticles(p.uniqueId) == null) "NONE" else ParticleCore.getParticles(p.uniqueId).toString()))
        pInv.setItem(32, MenuUtils.createItem(Material.BARRIER, "" + ChatColor.GREEN + "No Particles"))
        p.openInventory(pInv)
    }

    inner class Listener : org.bukkit.event.Listener {

        @EventHandler fun onInventoryClick(ev: InventoryClickEvent) {

            try {

                if(ev.inventory.name != invName)
                    return

                var itemName = ev.currentItem.itemMeta.displayName

                if(itemName == "" + ChatColor.GREEN + "Fire Particles") {
                    select(ParticleCore.ParticleType.FIRE, ev.whoClicked as Player)
                    ev.whoClicked.closeInventory()
                    ev.isCancelled = true
                } else if(itemName == "" + ChatColor.GREEN + "End Particles") {
                    select(ParticleCore.ParticleType.ENDER, ev.whoClicked as Player)
                    ev.whoClicked.closeInventory()
                    ev.isCancelled = true
                } else if(itemName == "" + ChatColor.GREEN + "Enchant Particles") {
                    select(ParticleCore.ParticleType.ENCHANT, ev.whoClicked as Player)
                    ev.whoClicked.closeInventory()
                    ev.isCancelled = true
                } else if(itemName == "" + ChatColor.GREEN + "Redstone Particles") {
                    select(ParticleCore.ParticleType.REDSTONE, ev.whoClicked as Player)
                    ev.whoClicked.closeInventory()
                    ev.isCancelled = true
                } else if(itemName == "" + ChatColor.GREEN + "Critical Hit Particles") {
                    select(ParticleCore.ParticleType.CRIT, ev.whoClicked as Player)
                    ev.whoClicked.closeInventory()
                    ev.isCancelled = true
                } else if(itemName == "" + ChatColor.GREEN + "Heart Particles") {
                    select(ParticleCore.ParticleType.HEART, ev.whoClicked as Player)
                    ev.whoClicked.closeInventory()
                    ev.isCancelled = true
                } else if(itemName == "" + ChatColor.GREEN + "Colorful Effect Particles") {
                    select(ParticleCore.ParticleType.COLOURFUL, ev.whoClicked as Player)
                    ev.whoClicked.closeInventory()
                    ev.isCancelled = true
                }

                else if(itemName == "" + ChatColor.GREEN + "No Particles") {
                    sendSelectMsg(ev.whoClicked as Player, null)
                    ParticleCore.clearParticles(ev.whoClicked.uniqueId)
                    ev.whoClicked.closeInventory()
                    ev.isCancelled = true
                }

                else if(itemName == "" + ChatColor.GREEN + "Back") {
                    ev.whoClicked.closeInventory()
                    ev.isCancelled = true
                    MainMenu().show(ev.whoClicked as Player)
                }

            } catch(ex: Exception) {
                //do nothing
            }

        }

        private fun select(type: ParticleCore.ParticleType, p: Player) {
            sendSelectMsg(p, type)
            ParticleCore.setParticles(type, p)
        }

        private fun sendSelectMsg(p: Player, type: ParticleCore.ParticleType?) {
            if(type == null)
                Message().append(ChatUtils.getCustomMsg("Particles") + "Cleared particles").send(p)
            else
                Message().append(ChatUtils.getCustomMsg("Particles") + "Selected " + ChatColor.YELLOW + type + ChatColor.GREEN + " particles").send(p)
        }

    }

}