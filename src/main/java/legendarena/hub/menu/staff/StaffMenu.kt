package legendarena.hub.menu.staff

import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendarena.chat.ChatSystem
import legendarena.hub.menu.MainMenu
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin

class StaffMenu : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        try {
            if (!ev.getInventory().getName().equals(ChatUtils.getCustomMsg("Menus") + "Staff Menu")) return
            if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Chat Selector")) {
                ev.getWhoClicked().closeInventory()
                (ev.getWhoClicked() as Player).performCommand("chat menu")
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GRAY + "⇐ Back")) {
                ev.getWhoClicked().closeInventory()
                MainMenu.show(ev.getWhoClicked() as Player)
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Global Mute")) {
                (ev.getWhoClicked() as Player).performCommand("staff chat globalmute")
                ev.getInventory().setItem(22, MenuUtils.createItem(Material.BARRIER, "" + ChatColor.GREEN + "Global Mute", "" + ChatColor.BLUE + "Current status: " + ChatColor.RED + (if (ChatSystem.isChatMuted()) "ON" else "OFF") + ChatColor.GRAY + " (click to toggle)"))
            }
            ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }
    }

    companion object {

        private var inv: Inventory? = null
        private var init = false

        private fun init(p: Plugin) {
            if(init) return  //if we've already initialized the staff menu, don't do anything
            inv = Bukkit.createInventory(null, 27, ChatUtils.getCustomMsg("Menus") + "Staff Menu")

            inv!!.setItem(4, MenuUtils.createItem(Material.BED, "" + ChatColor.GRAY + "⇐ Back", ""))

            Bukkit.getPluginManager().registerEvents(StaffMenu(), p)
            init = true
        }

        public fun show(p: Player) {
            init(Bukkit.getPluginManager().getPlugin("LegendArena"))
            val pInv = Bukkit.createInventory(null, 27, ChatUtils.getCustomMsg("Menus") + "Staff Menu")
            pInv.setContents(inv!!.getContents())
            pInv.setItem(19, MenuUtils.createItem(Material.GLASS, "" + ChatColor.GREEN + "Chat Selector", "" + ChatColor.BLUE + "Current channel: " + ChatColor.RED + (if (ChatSystem.getChannel(p) == null) "PUBLIC" else ChatSystem.getChannel(p))))
            pInv.setItem(22, MenuUtils.createItem(Material.BARRIER, "" + ChatColor.GREEN + "Global Mute", "" + ChatColor.BLUE + "Current status: " + ChatColor.RED + (if (ChatSystem.isChatMuted()) "ON" else "OFF") + ChatColor.GRAY + " (click to toggle)"))
            p.openInventory(pInv)
        }
    }
}