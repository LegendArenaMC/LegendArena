package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.core.emeralds.EmeraldsCore;
import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.menu.staff.StaffMenu;
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
        inv = Bukkit.createInventory(null, 45, ChatUtils.getCustomMsg("Menus") + "Main Menu");

        inv.setItem(4, MenuCore.createItem(Material.WATCH, ChatColor.GREEN + "Warper", ""));
        inv.setItem(40, MenuCore.createItem(Material.JUKEBOX, ChatColor.GREEN + "Music", ""));
        inv.setItem(25, MenuCore.createItem(Material.YELLOW_FLOWER, ChatColor.GREEN + "Particles", ""));

        Bukkit.getPluginManager().registerEvents(new MainMenu(), p);
        init = true;
    }

    public static void show(Player p) {
        init(Bukkit.getPluginManager().getPlugin("LegendArena"));
        Inventory pInv = Bukkit.createInventory(null, 45, ChatUtils.getCustomMsg("Menus") + "Main Menu");
        pInv.setContents(inv.getContents());
        pInv.setItem(19, MenuCore.createItem(Material.EMERALD, ChatColor.GREEN + "Emeralds", ChatColor.YELLOW + "You have " + ChatColor.RED + EmeraldsCore.getEmeralds(p) + ChatColor.YELLOW + " emeralds!"));
        if(Rank.isRanked(p, Rank.HELPER)) {
            pInv.setItem(22, MenuCore.createItem(Material.PAPER, ChatColor.GREEN + "Staff Tools", ""));
        }
        p.openInventory(pInv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent ev) {
        try {
            if(!ev.getInventory().getName().equalsIgnoreCase(ChatUtils.getCustomMsg("Menus") + "Main Menu")) return;
            if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Warp")) {
                ev.getWhoClicked().closeInventory();
                MinigameMenu.show((Player) ev.getWhoClicked());
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Particles")) {
                if(Rank.isRanked(ev.getWhoClicked(), Rank.MEMBERPLUS)) {
                    if(!(EmeraldsCore.getEmeralds((Player) ev.getWhoClicked()) > 15)) {
                        ev.getWhoClicked().closeInventory();
                        ActionBarAPI.sendActionBar((Player) ev.getWhoClicked(), ChatUtils.Messages.errorMsg + "You are not a Member+, nor have more than 15 emeralds!");
                    }
                }
                ev.getWhoClicked().closeInventory();
                ParticleMenu.show((Player) ev.getWhoClicked());
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Staff Tools")) {
                ev.getWhoClicked().closeInventory();
                StaffMenu.show((Player) ev.getWhoClicked());
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Music")) {
                ev.getWhoClicked().closeInventory();
                ev.getWhoClicked().sendMessage(ChatColor.RED + "Music selector coming soon, to a Legend Arena server near you.");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Emeralds")) {
                ev.getWhoClicked().closeInventory();
                EmeraldMenu.show((Player) ev.getWhoClicked());
            }
            ev.setCancelled(true);
        } catch(Exception ignore) {
            // Ignore the error
        }
    }
}
