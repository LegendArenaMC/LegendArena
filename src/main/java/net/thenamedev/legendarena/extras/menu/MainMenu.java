package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.tokens.TokenCore;
import net.thenamedev.legendapi.utils.MenuCore;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendarena.extras.ActionBarAPI;
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
public class MainMenu implements Listener {

    private static Inventory inv;
    private static boolean init = false;

    private static void init(Plugin p) {
        if(init) return; //if we've already initialized the main menu, don't do anything
        inv = Bukkit.createInventory(null, 45, ChatColor.BLUE + "Main Menu");

        inv.setItem(4, MenuCore.createItem(Material.WATCH, ChatColor.GREEN + "Warper", ""));
        inv.setItem(19, MenuCore.createItem(Material.LEATHER_HELMET, ChatColor.GREEN + "Customization", ""));
        inv.setItem(22, MenuCore.createItem(Material.PAPER, ChatColor.GREEN + "Chat Selector", ""));
        inv.setItem(25, MenuCore.createItem(Material.YELLOW_FLOWER, ChatColor.GREEN + "Particles", ""));

        Bukkit.getPluginManager().registerEvents(new MainMenu(), p);
        init = true;
    }

    public static void show(Player p) {
        init(Bukkit.getPluginManager().getPlugin("LegendArena"));
        Inventory pInv = Bukkit.createInventory(null, 45, ChatColor.BLUE + "Main Menu");
        pInv.setContents(inv.getContents());
        pInv.setItem(40, MenuCore.createItem(Material.DOUBLE_PLANT, ChatColor.GREEN + "Tokens", ChatColor.YELLOW + "You have " + ChatColor.RED + TokenCore.getTokens(p) + ChatColor.YELLOW + " tokens!"));
        p.openInventory(pInv);
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onInventoryClick(InventoryClickEvent ev) {
        try {
            if(!ev.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Main Menu")) return;
            if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Warp")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("warp");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Particles")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("particles");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Chat")) {
                ev.getWhoClicked().closeInventory();
                ((Player) ev.getWhoClicked()).performCommand("chat menu");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Customization")) {
                ev.getWhoClicked().closeInventory();
                ActionBarAPI.sendActionBar((Player) ev.getWhoClicked(), PluginUtils.msgNormal + "Customization coming soon[tm]");
            }
            else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Tokens")) {
                ev.getWhoClicked().closeInventory();
                ev.getWhoClicked().sendMessage(ChatColor.RED + "You actually expected this to do something? That's impressive.");
            }
            ev.setCancelled(true);
        } catch(Exception ignore) {
            // Ignore the error
        }
    }
}
