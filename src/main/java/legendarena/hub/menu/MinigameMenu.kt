package legendarena.hub.menu

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
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import java.util.*

class MinigameMenu : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        if (!ev.getInventory().getName().equals(inv!!.getName())) return
        try {
            if (ev.getCurrentItem().getItemMeta() == null) return
            if (ev.getCurrentItem().getItemMeta().getDisplayName().equals("Hub")) {
                ev.setCancelled(true)
                ev.getWhoClicked().closeInventory()
                val p = ev.getWhoClicked() as Player
                p.teleport(Bukkit.getWorld("world").getSpawnLocation())
            } else if (ev.getCurrentItem().getItemMeta().getDisplayName().equals("Build My Thing")) {
                ev.setCancelled(true)
                ev.getWhoClicked().closeInventory()
                ev.getWhoClicked().sendMessage(ChatColor.GREEN + "Totally not a hint towards an actual minigame that works, nope, no hints here </sarcasm>")
            } else if (ev.getCurrentItem().getItemMeta().getDisplayName().equals("⇐ Back")) {
                ev.setCancelled(true)
                ev.getWhoClicked().closeInventory()
                MainMenu.show(ev.getWhoClicked() as Player)
            } else {
                //failsafe
                ev.setCancelled(true)
            }
        } catch (ignore: Exception) {
            // Ignore the error
        }

    }

    companion object {

        private var inv: Inventory? = null
        private var init = false

        private fun init(p: Plugin) {
            if(init) return

            val items = HashMap<Int, ItemStack>()

            //TODO: Clean this damn thing up

            items.put(4, MenuUtils.createItem(Material.BED, ChatColor.GRAY + "⇐ Back", ""))
            items.put(21, MenuUtils.createItem(Material.DISPENSER, ChatColor.GREEN + "Hub"))
            items.put(23, MenuUtils.createItem(Material.STAINED_CLAY, ChatColor.GREEN + "Build My Thing"))

            inv = Bukkit.createInventory(null, 27, ChatUtils.getCustomMsg("Menus") + "Warper")

            for(a in items.keySet())
                inv!!.setItem(a, items.get(a))

            Bukkit.getPluginManager().registerEvents(MinigameMenu(), p)

            init = true
        }

        public fun show(p: Player) {
            init(Bukkit.getPluginManager().getPlugin("LegendArena"))
            p.closeInventory()
            p.openInventory(inv)
        }
    }

}