package legendarena.listeners.menu

import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendarena.chat.ChatSystem
import legendarena.hub.JumpPad
import legendarena.hub.menu.staff.ChatMenu
import legendarena.hub.menu.staff.JumpPadMenu
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

public class StaffMenuListener : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        try {
            if (!ev.getInventory().getName().equals(ChatUtils.getCustomMsg("Menus") + "Staff Menu")) return
            if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Chat Selector")) {
                ev.getWhoClicked().closeInventory()
                ChatMenu().show(ev.getWhoClicked() as Player)
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Global Mute")) {
                (ev.getWhoClicked() as Player).performCommand("chat manage globalmute")
                ev.getInventory().setItem(4, MenuUtils.createItem(Material.BARRIER, "" + ChatColor.GREEN + "Global Mute", "" + ChatColor.BLUE + "Current status: " + ChatColor.RED + (if (ChatSystem.isChatMuted()) "ON" else "OFF") + ChatColor.GRAY + " (click to toggle)"))
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "JumpPads")) {
                ev.getWhoClicked().closeInventory()
                JumpPadMenu().show(ev.getWhoClicked() as Player)
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Close Menu"))
                ev.getWhoClicked().closeInventory()
            ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }
    }

}