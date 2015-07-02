package legendarena.hub.menu

import legendapi.emeralds.EmeraldsCore
import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendarena.hub.menu.staff.StaffMenu
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.Plugin

class MainMenu : Listener {

    EventHandler
    public fun onInventoryClick(ev: InventoryClickEvent) {
        try {
            if(!ev.getInventory().getName().equals(ChatUtils.getCustomMsg("Menus") + "Main Menu")) return
            when(ev.getCurrentItem().getItemMeta().getDisplayName()) {
                "" + ChatColor.GREEN + "Warper" -> {
                    ev.getWhoClicked().closeInventory()
                    MinigameMenu().show(ev.getWhoClicked() as Player)
                }
                "" + ChatColor.GREEN + "Particles" -> {
                    /*if(Rank.isRanked(ev.getWhoClicked(), Rank.MEMBERPLUS)) {
                        if(!(EmeraldsCore.getEmeralds((Player) ev.getWhoClicked()) > 15)) {
                            ev.getWhoClicked().closeInventory();
                            new Message(MessageType.SUBTITLE).append(ChatUtils.Messages.errorMsg + "You are not a Member+, nor have more than 15 emeralds!").send((Player) ev.getWhoClicked());
                        }
                    }*/
                    Message().append("" + ChatColor.GREEN + "Particles will be fixed soon[tm]; sorry :( -Pixel").send(Sound.ANVIL_BREAK, ev.getWhoClicked() as Player)
                    ev.getWhoClicked().closeInventory()
                    //ParticleMenu.show((Player) ev.getWhoClicked());
                }
                "" + ChatColor.GREEN + "Staff Tools" -> {
                    ev.getWhoClicked().closeInventory()
                    StaffMenu().show(ev.getWhoClicked() as Player)
                }
                "" + ChatColor.GREEN + "Music" -> {
                    ev.getWhoClicked().closeInventory()
                    ev.getWhoClicked().sendMessage("" + ChatColor.RED + "Music selector coming soon, to a Legend Arena server near you.")
                }
                "" + ChatColor.GREEN + "Emeralds" -> {
                    ev.getWhoClicked().closeInventory()
                    EmeraldMenu().show(ev.getWhoClicked() as Player)
                }
            }
            ev.setCancelled(true)
        } catch(ignore: Exception) {
            // Ignore the error
        }

    }

    private var inv: Inventory? = null

    public constructor() {
        init(Bukkit.getPluginManager().getPlugin("LegendArena"))
    }

    private fun init(p: Plugin) {
        inv = Bukkit.createInventory(null, 45, ChatUtils.getCustomMsg("Menus") + "Main Menu")

        inv!!.setItem(4, MenuUtils.createItem(Material.WATCH, "" + ChatColor.GREEN + "Warper", ""))
        inv!!.setItem(40, MenuUtils.createItem(Material.JUKEBOX, "" + ChatColor.GREEN + "Music", ""))
        inv!!.setItem(25, MenuUtils.createItem(Material.YELLOW_FLOWER, "" + ChatColor.GREEN + "Particles", ""))

        Bukkit.getPluginManager().registerEvents(this, p)
    }

    public fun show(p: Player) {
        val pInv = Bukkit.createInventory(null, 45, ChatUtils.getCustomMsg("Menus") + "Main Menu")
        pInv.setContents(inv!!.getContents())
        pInv.setItem(19, MenuUtils.createItem(Material.EMERALD, "" + ChatColor.GREEN + "Emeralds", "" + ChatColor.YELLOW + "You have " + ChatColor.RED + EmeraldsCore.getEmeralds(p) + ChatColor.YELLOW + " emeralds!"))
        if(Rank.isRanked(p, Rank.HELPER))
            pInv.setItem(22, MenuUtils.createItem(Material.PAPER, "" + ChatColor.GREEN + "Staff Tools", ""))
        p.openInventory(pInv)
    }
}