/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners.menu

import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendarena.hub.HubWarper
import legendarena.hub.JumpPad
import legendarena.hub.menu.MainMenu
import legendarena.hub.menu.ParticleMenu
import legendarena.hub.menu.staff.StaffMenu
import legendarena.hub.particles.ParticleCore
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

public class ParticleMenuListener : Listener {

    EventHandler(ignoreCancelled = false, priority = EventPriority.LOW)
    public fun onInventoryClick(ev: InventoryClickEvent) {
        try {
            if(HubWarper.isExempt(ev.getWhoClicked().getUniqueId())) return
            ev.setCancelled(true) //do NOT [re]move this. if you do, literally every submenu in the main menu will just die. don't believe me? try it,
            var itemName = ev.getCurrentItem().getItemMeta().getDisplayName()
            if(itemName == "" + ChatColor.GREEN + "Fire Particles") {
                sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.FIRE)
                ParticleCore.setParticles(ParticleCore.ParticleType.FIRE, ev.getWhoClicked() as Player)
                ev.getWhoClicked().closeInventory()
            } else if(itemName == "" + ChatColor.GREEN + "End Particles") {
                sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.ENDER)
                ParticleCore.setParticles(ParticleCore.ParticleType.ENDER, ev.getWhoClicked() as Player)
                ev.getWhoClicked().closeInventory()
            } else if(itemName == "" + ChatColor.GREEN + "Enchant Particles") {
                sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.ENCHANT)
                ParticleCore.setParticles(ParticleCore.ParticleType.ENCHANT, ev.getWhoClicked() as Player)
                ev.getWhoClicked().closeInventory()
            } else if(itemName == "" + ChatColor.GREEN + "Redstone Particles") {
                sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.REDSTONE)
                ParticleCore.setParticles(ParticleCore.ParticleType.REDSTONE, ev.getWhoClicked() as Player)
                ev.getWhoClicked().closeInventory()
            } else if(itemName == "" + ChatColor.GREEN + "Critical Hit Particles") {
                sendSelectMsg(ev.getWhoClicked() as Player, ParticleCore.ParticleType.CRIT)
                ParticleCore.setParticles(ParticleCore.ParticleType.CRIT, ev.getWhoClicked() as Player)
                ev.getWhoClicked().closeInventory()
            }

            else if(itemName == "" + ChatColor.GREEN + "No Particles") {
                sendSelectMsg(ev.getWhoClicked() as Player, null)
                ParticleCore.clearParticles(ev.getWhoClicked().getUniqueId())
                ev.getWhoClicked().closeInventory()
            }

            else if(itemName == "" + ChatColor.GREEN + "Back") {
                ev.getWhoClicked().closeInventory()
                MainMenu().show(ev.getWhoClicked() as Player)
            }
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

    private fun sendSelectMsg(p: Player, type: ParticleCore.ParticleType?) {
        if(type == null)
            Message().append(ChatUtils.getCustomMsg("Particles") + "Cleared particles").send(p)
        else
            Message().append(ChatUtils.getCustomMsg("Particles") + "Selected " + ChatColor.YELLOW + type + ChatColor.BLUE + " particles").send(p)
    }

}