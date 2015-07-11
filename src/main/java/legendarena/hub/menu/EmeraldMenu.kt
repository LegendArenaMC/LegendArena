package legendarena.hub.menu

import legendapi.emeralds.EmeraldsCore
import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin

class EmeraldMenu : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        try {
            if(!ev.getInventory().getName().equals(ChatUtils.getCustomMsg("Menus") + "Emeralds Menu")) return
            if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Emeralds") || ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Booster")) {
                ev.getWhoClicked().closeInventory()
                ev.getWhoClicked().sendMessage("...you really expected that would do something. wow. I'm amazed. no, really, I'm amazed.")
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("" + ChatColor.GRAY + "⇐ Back")) {
                ev.getWhoClicked().closeInventory()
                MainMenu().show(ev.getWhoClicked() as Player)
            }
            ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

    private var inv: Inventory? = null
    private var init = false

    private fun init(p: Plugin) {
        if (init) return  //if we've already initialized the emeralds menu, don't do anything
        inv = Bukkit.createInventory(null, 45, ChatUtils.getCustomMsg("Menus") + "Emeralds Menu")

        inv!!.setItem(13, MenuUtils.createItem(Material.BED, "" + ChatColor.GRAY + "⇐ Back", ""))
        inv!!.setItem(32, MenuUtils.createItem(Material.NETHER_BRICK_ITEM, "" + ChatColor.GREEN + "Booster", "" + ChatColor.RED + "Soon™"))

        Bukkit.getPluginManager().registerEvents(EmeraldMenu(), p)
        init = true
    }

    public fun show(p: Player) {
        val pInv = Bukkit.createInventory(null, 45, ChatUtils.getCustomMsg("Menus") + "Emeralds Menu")
        pInv.setContents(inv!!.getContents())
        pInv.setItem(30, MenuUtils.createItem(Material.EMERALD, "" + ChatColor.GREEN + "Emeralds", "" + ChatColor.YELLOW + "You have " + ChatColor.RED + EmeraldsCore.getEmeralds(p) + ChatColor.YELLOW + " emeralds!"))
        p.openInventory(pInv)
    }
}