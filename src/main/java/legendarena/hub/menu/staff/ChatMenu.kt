package legendarena.hub.menu.staff

import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendarena.chat.ChatSystem
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

class ChatMenu {

    private var inv: Inventory? = null

    public constructor() {
        inv = Bukkit.createInventory(null, 9, ChatUtils.getCustomMsg("Menus") + "Chat Menu")

        inv!!.setItem(8, MenuUtils.createItem(Material.BED, "" + ChatColor.GREEN + "⇐ Back"))

        inv!!.setItem(0, createItem(Material.STAINED_GLASS, "" + ChatColor.GREEN + "Global", "Click to join " + ChatColor.GRAY + "GLOBAL" + ChatColor.BLUE + " channel.", 8))
        inv!!.setItem(1, createItem(Material.STAINED_GLASS, "" + ChatColor.GREEN + "Dev", "Click to join " + ChatColor.DARK_PURPLE + "DEV" + ChatColor.BLUE + " channel.", 10))
        inv!!.setItem(2, createItem(Material.STAINED_GLASS, "" + ChatColor.GREEN + "Admin", "Click to join " + ChatColor.DARK_RED + "ADMIN" + ChatColor.BLUE + " channel.", 14))
        inv!!.setItem(3, createItem(Material.STAINED_GLASS, "" + ChatColor.GREEN + "Staff", "Click to join " + ChatColor.GREEN + "STAFF" + ChatColor.BLUE + " channel.", 5))
        inv!!.setItem(4, createItem(Material.STAINED_CLAY, "" + ChatColor.GREEN + "Alert", "Click to join " + ChatColor.RED + "ALERT" + ChatColor.BLUE + " channel.", 14))
    }

    public fun show(p: Player) {
        var pinv = Bukkit.createInventory(null, 9, ChatUtils.getCustomMsg("Menus") + "Chat Menu")
        pinv.setContents(inv!!.getContents())
        pinv!!.setItem(7, MenuUtils.createItem(Material.PAPER, "" + ChatColor.GREEN + "Current Channel", "" + ChatSystem.getChannelName(p)))
        p.openInventory(pinv)
    }

    /**
     * Yes, I am this lazy.
     *
     * Don't ask.
     */
    internal fun createItem(material: Material, name: String, lore: String, color: Short): ItemStack {
        val i = ItemStack(material, 1, color)
        val im = i.getItemMeta()
        im.setDisplayName(name)
        im.setLore(Arrays.asList<String>("" + ChatColor.BLUE + lore))
        i.setItemMeta(im)
        return i
    }

}
