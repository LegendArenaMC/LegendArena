package legendarena.listeners.menu

import legendapi.emeralds.EmeraldsCore
import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendarena.hub.menu.MinigameMenu
import legendarena.hub.menu.staff.StaffMenu
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class MainMenuListener : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        try {
            if(!ev.getInventory().getName().equals(ChatUtils.getCustomMsg("Menus") + "Main Menu")) return
            var itemName = ev.getCurrentItem().getItemMeta().getDisplayName()
            if(itemName == "" + ChatColor.GREEN + "Warper") {
                ev.getWhoClicked().closeInventory()
                MinigameMenu().show(ev.getWhoClicked() as Player)
            } else if(itemName == "" + ChatColor.GREEN + "Particles") {
                if(!Rank.MEMBERPLUS.isRanked(ev.getWhoClicked() as Player)) {
                    if(EmeraldsCore().getEmeralds(ev.getWhoClicked() as Player) < 15) {
                        ev.getWhoClicked().closeInventory()
                        Message(MessageType.SUBTITLE).append(ChatUtils.Messages.errorMsg + "You are not a Member+, nor have more than 15 emeralds!").send(ev.getWhoClicked() as Player)
                        return
                    } else {
                        EmeraldsCore().removeEmeralds(ev.getWhoClicked() as Player, 15, false)
                    }
                }
                ev.getWhoClicked().sendMessage("" + ChatColor.GREEN + "Particle selector will be fixed soon[tm]; sorry :( -Pixel")
                ev.getWhoClicked().closeInventory()
                //ParticleMenu.show(ev.getWhoClicked() as Player)
            } else if(itemName == "" + ChatColor.GREEN + "Emeralds") {
                ev.getWhoClicked().closeInventory()
            }
            ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

}