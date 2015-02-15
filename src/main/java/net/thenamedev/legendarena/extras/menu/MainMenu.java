package net.thenamedev.legendarena.extras.menu;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerTeleportEvent.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class MainMenu implements Listener {

    private Inventory inv;

    public MainMenu(Plugin plugin) {

        // Initialize the inventory
        inv = Bukkit.getServer().createInventory(null, 36, ChatColor.BLUE + "Warper");

        // Set the items
        @NotNull ItemStack plots = MenuCore.createItem(Material.BRICK, ChatColor.GREEN + "Plots");
        @NotNull ItemStack freebuild = MenuCore.createItem(Material.GOLDEN_CARROT, ChatColor.GREEN + "Freebuild");
        @NotNull ItemStack survival = MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Survival");
        @NotNull ItemStack hub = MenuCore.createItem(Material.COMMAND, ChatColor.GREEN + "The Hub");
        @NotNull ItemStack shop = MenuCore.createItem(Material.BAKED_POTATO, ChatColor.GREEN + "Shop");
        @NotNull ItemStack minigames = MenuCore.createItem(Material.WORKBENCH, ChatColor.GREEN + "Minigames", ChatColor.BLUE + "Minigame list >>");

        @NotNull ItemStack comingsoon = MenuCore.createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), ChatColor.RED + "Coming Soon[tm]", ChatColor.RED + "Check back later on this one!");

        // Item clones
        ItemStack comingsoon1 = comingsoon.clone();
        ItemStack comingsoon2 = comingsoon.clone();
        ItemStack comingsoon3 = comingsoon.clone();
        ItemStack comingsoon4 = comingsoon.clone();

        // Place the items
        inv.setItem(3, plots);
        inv.setItem(6, survival);
        inv.setItem(2, freebuild);
        inv.setItem(13, hub);
        inv.setItem(4, comingsoon);
        inv.setItem(11, comingsoon3);
        inv.setItem(12, comingsoon1);
        inv.setItem(14, comingsoon2);
        inv.setItem(15, comingsoon4);
        inv.setItem(5, shop);
        inv.setItem(31, minigames);

        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void show(@NotNull Player p) {
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent e) {
        if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        try {
            if(e.getCurrentItem().getItemMeta() == null) return;
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Hub")) {
				e.setCancelled(true);
				@NotNull Player p = (Player) e.getWhoClicked();
				p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " HUB...");
				p.teleport(Bukkit.getWorld("hub").getSpawnLocation(), TeleportCause.PLUGIN);
				e.getWhoClicked().closeInventory();
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Survival")) {
				e.setCancelled(true);
				@NotNull Player p = (Player) e.getWhoClicked();
				p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SURVIVAL...");
				p.teleport(new Location(Bukkit.getWorld("world"), 479.5, 67.0, 328.50), TeleportCause.PLUGIN);
				e.getWhoClicked().closeInventory();
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Plots")) {
				e.setCancelled(true);
				@NotNull Player p = (Player) e.getWhoClicked();
				p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " PLOTS...");
				p.teleport(Bukkit.getWorld("plotworld").getSpawnLocation(), TeleportCause.PLUGIN);
				e.getWhoClicked().closeInventory();
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Shop")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SHOP...");
                p.teleport(new Location(Bukkit.getWorld("hub"), 29.50, 4.0, -28.50), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Freebuild")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " FREEBUILD...");
                p.teleport(Bukkit.getWorld("freebuild").getSpawnLocation(), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Minigames")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                e.getWhoClicked().closeInventory();
                MenuInv.minimenu.show(p);
            } else { //failsafe
                e.setCancelled(true);
            }
		} catch(Exception ignore) {
			// Ignore the error
		}
    }

}
