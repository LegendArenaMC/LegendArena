package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.tokens.TokenCore;
import net.thenamedev.legendapi.utils.MenuCore;
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
 * @author ThePixelDev
 */
public class CustomizationMenu implements Listener {

    private static Inventory inv;
    private static boolean init = false;

    private static void init(Plugin p) {
        if(init) return; //if we've already initialized the menu, don't do anything
        inv = Bukkit.createInventory(null, 45, ChatColor.BLUE + "Customization Menu");

        inv.setItem(0, MenuCore.createItem(Material.LEATHER_HELMET, ChatColor.BLUE + "Hat", ""));
        inv.setItem(9, MenuCore.createItem(Material.LEATHER_CHESTPLATE, ChatColor.BLUE + "Shirt", ""));
        inv.setItem(18, MenuCore.createItem(Material.LEATHER_LEGGINGS, ChatColor.BLUE + "Pants", ""));
        inv.setItem(27, MenuCore.createItem(Material.LEATHER_BOOTS, ChatColor.BLUE + "Shoes", ""));

        inv.setItem(2, MenuCore.createItem(Material.DIAMOND_HELMET, ChatColor.GREEN + "Diamond Hat", ""));

        Bukkit.getPluginManager().registerEvents(new MainMenu(), p);
        init = true;
    }

    public static void show(Player p) {
        init(Bukkit.getPluginManager().getPlugin("LegendArena"));
        Inventory pInv = Bukkit.createInventory(null, 45, ChatColor.BLUE + "Customization Menu");
        pInv.setContents(inv.getContents());
        p.openInventory(pInv);
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onInventoryClick(InventoryClickEvent ev) {
        try {
            if(!ev.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Customization Menu")) return;
            if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Warp")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("warp");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Particles")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("particles");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Chat")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("chat menu");
            }
            ev.setCancelled(true);
        } catch(Exception ignore) {
            // Ignore the error
        }
    }
}
