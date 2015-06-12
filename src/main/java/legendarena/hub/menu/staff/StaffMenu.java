package legendarena.hub.menu.staff;

import legendarena.chat.ChatSystem;
import legendarena.api.utils.ChatUtils;
import legendarena.api.utils.MenuUtils;
import legendarena.hub.menu.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

/**
 * Staff menu.
 *
 * @author ThePixelDev
 */
public class StaffMenu implements Listener {

    private static Inventory inv;
    private static boolean init = false;

    private static void init(Plugin p) {
        if(init) return; //if we've already initialized the staff menu, don't do anything
        inv = Bukkit.createInventory(null, 27, ChatUtils.getCustomMsg("Menus") + "Staff Menu");

        inv.setItem(4, MenuUtils.createItem(Material.BED, ChatColor.GRAY + "⇐ Back", ""));

        Bukkit.getPluginManager().registerEvents(new StaffMenu(), p);
        init = true;
    }

    public static void show(Player p) {
        init(Bukkit.getPluginManager().getPlugin("LegendArena"));
        Inventory pInv = Bukkit.createInventory(null, 27, ChatUtils.getCustomMsg("Menus") + "Staff Menu");
        pInv.setContents(inv.getContents());
        pInv.setItem(19, MenuUtils.createItem(Material.GLASS, ChatColor.GREEN + "Chat Selector", ChatColor.BLUE + "Current channel: " + ChatColor.RED + (ChatSystem.getChannel(p) == null ? "PUBLIC" : ChatSystem.getChannel(p))));
        pInv.setItem(22, MenuUtils.createItem(Material.BARRIER, ChatColor.GREEN + "Global Mute", ChatColor.BLUE + "Current status: " + ChatColor.RED + (ChatSystem.isChatMuted() ? "ON" : "OFF") + ChatColor.GRAY + " (click to toggle)"));
        p.openInventory(pInv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent ev) {
        try {
            if(!ev.getInventory().getName().equalsIgnoreCase(ChatUtils.getCustomMsg("Menus") + "Staff Menu")) return;
            if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Chat")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("chat menu");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("⇐ Back")) {
                ev.getWhoClicked().closeInventory();
                MainMenu.show((Player) ev.getWhoClicked());
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Global Mute")) {
                ((Player) ev.getWhoClicked()).performCommand("staff chat globalmute");
                ev.getInventory().setItem(22, MenuUtils.createItem(Material.BARRIER, ChatColor.GREEN + "Global Mute", ChatColor.BLUE + "Current status: " + ChatColor.RED + (ChatSystem.isChatMuted() ? "ON" : "OFF") + ChatColor.GRAY + " (click to toggle)"));
            }
            ev.setCancelled(true);
        } catch(Exception ignore) {
            // Ignore the error
        }
    }
}
