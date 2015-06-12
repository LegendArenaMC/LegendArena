package legendarena.hub.menu.staff;

import legendarena.api.utils.MenuUtils;
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
 * Chat selector menu.
 *
 * @author ThePixelDev
 */
public class ChatMenu implements Listener {

    private static Inventory inv;
    private static boolean init = false;

    private static void init(Plugin p) {
        if(init) return; //if we've already initialized the chat menu, don't do anything
        inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Chat Selector");

        inv.setItem(4, MenuUtils.createItem(Material.BED, ChatColor.GRAY + "⇐ Back", ""));

        inv.setItem(19, MenuUtils.createItem(Material.BEDROCK, ChatColor.GREEN + "Global", ""));
        inv.setItem(23, MenuUtils.createItem(Material.APPLE, ChatColor.GREEN + "Alert", ""));
        inv.setItem(24, MenuUtils.createItem(Material.APPLE, ChatColor.GREEN + "Admin", ""));
        inv.setItem(25, MenuUtils.createItem(Material.APPLE, ChatColor.GREEN + "Staff", ""));

        Bukkit.getPluginManager().registerEvents(new ChatMenu(), p);
        init = true;
    }

    public static void show(Player p) {
        init(Bukkit.getPluginManager().getPlugin("LegendArena"));
        Inventory pInv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Chat Selector");
        pInv.setContents(inv.getContents());
        p.openInventory(pInv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent ev) {
        try {
            if(!ev.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Chat Selector")) return;
            if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Global")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("chat off");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Alert")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("chat alert");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Admin")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("chat admin");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Staff")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("chat staff");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("⇐ Back")) {
                ev.getWhoClicked().closeInventory();
                StaffMenu.show((Player) ev.getWhoClicked());
            }
            ev.setCancelled(true);
        } catch(Exception ignore) {
            // Ignore the error
        }
    }
}
