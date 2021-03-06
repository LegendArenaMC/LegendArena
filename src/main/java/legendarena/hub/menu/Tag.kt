/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub.menu

import legendarena.api.exceptions.AreYouDrunkException
import legendarena.api.fanciful.FancyMessage
import legendarena.api.message.Message
import legendarena.api.utils.*
import legendarena.hub.HubWarper
import legendarena.hub.menu.MainMenu
import legendarena.hub.menu.MinigameMenu
import legendarena.hub.menu.ParticleMenu
import legendarena.scoreboard.ScoreboardSystem
import legendarena.staffutils.VanishUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import java.util.*

class Tag {

    private class TagGUI {

        public fun show(p: Player) {
            val inv = Bukkit.createInventory(null, 36, ChatUtils.getCustomMsg("Menus") + "Tag Selector")
            inv.setItem(2, createItem("FOUNDER", 11))
            inv.setItem(3, createItem("DEV", 10))
            inv.setItem(4, createItem("ADMIN", 14))
            inv.setItem(5, createItem("MOD", 14, true))
            inv.setItem(6, createItem("HELPER", 5))
            inv.setItem(12, createItem("VIP", 1))
            inv.setItem(13, createItem("MEMBER+", 3))
            inv.setItem(14, createItem("MEMBER", 8))
            inv.setItem(30, MenuUtils.createItem(Material.ARROW, "" + ChatColor.GREEN + "Back"))
            inv.setItem(31, MenuUtils.createItem(Material.EMERALD, "" + ChatColor.GREEN + "Current Tag", "" + RankUtils.getDisplayRank(p).getNameColor() + RankUtils.getDisplayRank(p).toString()))
            inv.setItem(32, MenuUtils.createItem(Material.BARRIER, "" + ChatColor.GREEN + "Clear Tag"))
            p.openInventory(inv)
        }

        internal fun createItem(name: String, color: Short): ItemStack {
            val i = ItemStack(Material.STAINED_GLASS, 1, color)
            val im = i.getItemMeta()
            im.setDisplayName("" + ChatColor.GREEN + name)
            i.setItemMeta(im)
            return i
        }

        internal fun createItem(name: String, color: Short, dummy: Boolean): ItemStack {
            val i = ItemStack(Material.STAINED_GLASS_PANE, 1, color)
            val im = i.getItemMeta()
            im.setDisplayName("" + ChatColor.GREEN + name)
            i.setItemMeta(im)
            return i
        }

    }

    public class TagListener : Listener {

        public fun setTag(p: Player, r: Rank?) {
            if(r == null) {
                RankUtils.clearTag(p)
                Message().append("" + ChatColor.GREEN + "Cleared tag.").setSound(Sound.ORB_PICKUP).setPitch(1.0f, 1.5f).send(p)
                return
            }
            RankUtils.setTag(p, r)
            ScoreboardSystem.setRank(p, r)
            Message().append("" + ChatColor.GREEN + "Tagged yourself as a " + r.getNameColor() + r).setSound(Sound.ORB_PICKUP).setPitch(1.0f, 1.5f).send(p)
        }

        EventHandler
        public fun onInventoryClick(ev: InventoryClickEvent) {
            if(!ev.getInventory().getName().equals(ChatUtils.getCustomMsg("Menus") + "Tag Selector")) return
            try {
                if(ev.getCurrentItem().getItemMeta() == null) return
                var p = ev.getWhoClicked() as Player
                var itemName = ev.getCurrentItem().getItemMeta().getDisplayName()
                if(itemName.equals("" + ChatColor.GREEN + "FOUNDER")) {
                    ev.getWhoClicked().closeInventory()
                    try {
                        setTag(p, Rank.FOUNDER)
                    } catch(ex: AreYouDrunkException) {
                        p.sendMessage("" + ChatColor.RED + "You cannot tag yourself as that!")
                    }
                } else if(itemName.equals("" + ChatColor.GREEN + "DEV")) {
                    ev.getWhoClicked().closeInventory()
                    try {
                        setTag(p, Rank.DEV)
                    } catch(ex: AreYouDrunkException) {
                        p.sendMessage("" + ChatColor.RED + "You cannot tag yourself as that!")
                    }
                } else if(itemName.equals("" + ChatColor.GREEN + "ADMIN")) {
                    ev.getWhoClicked().closeInventory()
                    try {
                        setTag(p, Rank.ADMIN)
                    } catch(ex: AreYouDrunkException) {
                        p.sendMessage("" + ChatColor.RED + "You cannot tag yourself as that!")
                    }
                } else if(itemName.equals("" + ChatColor.GREEN + "MOD")) {
                    ev.getWhoClicked().closeInventory()
                    try {
                        setTag(p, Rank.MOD)
                    } catch(ex: AreYouDrunkException) {
                        p.sendMessage("" + ChatColor.RED + "You cannot tag yourself as that!")
                    }
                } else if(itemName.equals("" + ChatColor.GREEN + "HELPER")) {
                    ev.getWhoClicked().closeInventory()
                    try {
                        setTag(p, Rank.HELPER)
                    } catch(ex: AreYouDrunkException) {
                        p.sendMessage("" + ChatColor.RED + "You cannot tag yourself as that!")
                    }
                } else if(itemName.equals("" + ChatColor.GREEN + "VIP")) {
                    ev.getWhoClicked().closeInventory()
                    try {
                        setTag(p, Rank.VIP)
                    } catch(ex: AreYouDrunkException) {
                        p.sendMessage("" + ChatColor.RED + "You cannot tag yourself as that!")
                    }
                } else if(itemName.equals("" + ChatColor.GREEN + "MEMBER+")) {
                    ev.getWhoClicked().closeInventory()
                    try {
                        setTag(p, Rank.MEMBERPLUS)
                    } catch(ex: AreYouDrunkException) {
                        p.sendMessage("" + ChatColor.RED + "You cannot tag yourself as that!")
                    }
                } else if(itemName.equals("" + ChatColor.GREEN + "MEMBER")) {
                    ev.getWhoClicked().closeInventory()
                    try {
                        setTag(p, Rank.MEMBER)
                    } catch(ex: AreYouDrunkException) {
                        p.sendMessage("" + ChatColor.RED + "You cannot tag yourself as that!")
                    }
                } else if(itemName.equals("" + ChatColor.GREEN + "Clear Tag")) {
                    ev.getWhoClicked().closeInventory()
                    try {
                        setTag(p, null)
                    } catch(ex: AreYouDrunkException) {
                        p.sendMessage("" + ChatColor.RED + "You cannot tag yourself as that!")
                    }
                } else if(itemName.equals("" + ChatColor.GREEN + "Back")) {
                    ev.getWhoClicked().closeInventory()
                    MainMenu().show(p)
                    Message().setSound(Sound.WOOD_CLICK).setPitch(1.0f, 1.1f).send(p)
                }

                ev.setCancelled(true)
            } catch(ignore: Exception) {
                // Ignore the error
            }

        }

    }

    public fun tagGUI(sender: CommandSender) {
        TagGUI().show(sender as Player)
    }

}