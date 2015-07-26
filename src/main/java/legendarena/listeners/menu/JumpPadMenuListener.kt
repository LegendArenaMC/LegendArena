/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners.menu

import legendapi.emeralds.EmeraldsCore
import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendarena.hub.JumpPad
import legendarena.hub.menu.MinigameMenu
import legendarena.hub.menu.staff.StaffMenu
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class JumpPadMenuListener : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        try {
            if(!ev.getInventory().getName().equals(ChatUtils.getCustomMsg("Menus") + "Jump Pads Menu")) return
            var itemName = ev.getCurrentItem().getItemMeta().getDisplayName()
            if(ev.getCurrentItem().getType() == Material.GOLD_PLATE) {
                JumpPad.jump(ev.getWhoClicked() as Player)
                ev.getWhoClicked().closeInventory()
            } else if(ev.getCurrentItem().getType() == Material.IRON_PLATE) {
                (ev.getWhoClicked() as Player).getInventory().addItem(MenuUtils.createItem(Material.IRON_PLATE, "" + ChatColor.GREEN + "JumpPad", "" + ChatColor.GREEN + "Place me anywhere to create a " + ChatColor.YELLOW + "JumpPad" + ChatColor.GREEN + "!"))
            } else if(ev.getCurrentItem().getType() == Material.BED) {
                ev.getWhoClicked().closeInventory()
                StaffMenu().show(ev.getWhoClicked() as Player)
            }
            ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

}