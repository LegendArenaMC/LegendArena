/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub.menu

import legendapi.message.Message
import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendarena.hub.HubWarper
import legendarena.hub.particles.ParticleCore
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class ParticleMenu {

    public var invName: String = ChatUtils.getCustomMsg("Menus") + "Particles"

    public constructor() {}

    public fun show(p: Player) {
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

        pInv.setItem(30, MenuUtils.createItem(Material.ARROW, "" + ChatColor.GREEN + "Back"))
        pInv.setItem(31, MenuUtils.createItem(Material.EMERALD, "" + ChatColor.GREEN + "Current Particles", "" + ChatColor.BLUE + if(ParticleCore.getParticles(p.getUniqueId()) == null) "NONE" else ParticleCore.getParticles(p.getUniqueId()).toString()))
        pInv.setItem(32, MenuUtils.createItem(Material.BARRIER, "" + ChatColor.GREEN + "No Particles"))
        p.openInventory(pInv)
    }

    public inner class Listener : org.bukkit.event.Listener {

        EventHandler(ignoreCancelled = false, priority = EventPriority.LOW)
        public fun onInventoryClick(ev: InventoryClickEvent) {

            try {
                var itemName = ev.getCurrentItem().getItemMeta().getDisplayName()

                when(itemName) {
                    "" + ChatColor.GREEN + "Fire Particles" -> {
                        sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.FIRE)
                        ParticleCore.setParticles(ParticleCore.ParticleType.FIRE, ev.getWhoClicked() as Player)
                        ev.getWhoClicked().closeInventory()
                        ev.setCancelled(true)
                    }
                    "" + ChatColor.GREEN + "End Particles" -> {
                        sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.ENDER)
                        ParticleCore.setParticles(ParticleCore.ParticleType.ENDER, ev.getWhoClicked() as Player)
                        ev.getWhoClicked().closeInventory()
                        ev.setCancelled(true)
                    }
                    "" + ChatColor.GREEN + "Enchant Particles" -> {
                        sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.ENCHANT)
                        ParticleCore.setParticles(ParticleCore.ParticleType.ENCHANT, ev.getWhoClicked() as Player)
                        ev.getWhoClicked().closeInventory()
                        ev.setCancelled(true)
                    }
                    "" + ChatColor.GREEN + "Redstone Particles" -> {
                        sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.REDSTONE)
                        ParticleCore.setParticles(ParticleCore.ParticleType.REDSTONE, ev.getWhoClicked() as Player)
                        ev.getWhoClicked().closeInventory()
                        ev.setCancelled(true)
                    }
                    "" + ChatColor.GREEN + "Critical Hit Particles" -> {
                        sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.CRIT)
                        ParticleCore.setParticles(ParticleCore.ParticleType.CRIT, ev.getWhoClicked() as Player)
                        ev.getWhoClicked().closeInventory()
                        ev.setCancelled(true)
                    }
                    "" + ChatColor.GREEN + "Heart Particles" -> {
                        sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.HEART)
                        ParticleCore.setParticles(ParticleCore.ParticleType.HEART, ev.getWhoClicked() as Player)
                        ev.getWhoClicked().closeInventory()
                        ev.setCancelled(true)
                    }

                    "" + ChatColor.GREEN + "No Particles" -> {
                        sendSelectMsg(ev.getWhoClicked() as Player, null)
                        ParticleCore.clearParticles(ev.getWhoClicked().getUniqueId())
                        ev.getWhoClicked().closeInventory()
                        ev.setCancelled(true)
                    }

                    "" + ChatColor.GREEN + "Back" -> {
                        ev.getWhoClicked().closeInventory()
                        MainMenu().show(ev.getWhoClicked() as Player)
                        ev.setCancelled(true)
                    }
                }

            } catch(ex: Exception) {
                //do nothing
            }

        }

        private fun sendSelectMsg(p: Player, type: ParticleCore.ParticleType?) {
            if(type == null)
                Message().append(ChatUtils.getCustomMsg("Particles") + "Cleared particles").send(p)
            else
                Message().append(ChatUtils.getCustomMsg("Particles") + "Selected " + ChatColor.YELLOW + type + ChatColor.BLUE + " particles").send(p)
        }

    }

}