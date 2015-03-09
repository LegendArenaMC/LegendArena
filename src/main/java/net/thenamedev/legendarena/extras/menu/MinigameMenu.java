package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.inventory.InventoryManager;
import net.thenamedev.legendapi.utils.MenuCore;
import net.thenamedev.legendapi.utils.PluginUtils;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;

/**
 * @author TheNameMan
 */
public class MinigameMenu implements Listener {

    private static InventoryManager invMan = new InventoryManager();

    public MinigameMenu(Plugin plugin) {
        /*inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Minigames");

        */
        ItemStack spl = MenuCore.createItem(Material.EGG, ChatColor.GREEN + "Splegg");
        ItemStack sp = MenuCore.createItem(Material.GOLD_SPADE, ChatColor.GREEN + "Spleef");
        ItemStack hub = MenuCore.createItem(Material.COMMAND, ChatColor.GREEN + "Hub");
        ItemStack more = MenuCore.createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), ChatColor.RED + "Coming Soon[tm]", ChatColor.YELLOW + "Check back later!");
        ItemStack sg = MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Survival Games");
        /*

        inv.setItem(1, sp);
        inv.setItem(7, spl);
		inv.setItem(19, more);
        inv.setItem(13, hub);
        inv.setItem(25, sg);

        Bukkit.getPluginManager().registerEvents(this, plugin);*/
    }

    public void show(Player p) {
        //p.openInventory(inv);
        p.sendMessage(PluginUtils.msgWarning + "The warper is going under minor maintenance right now - sorry - check back soon.");
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        /*if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        try {
            if(e.getCurrentItem().getItemMeta() == null) return;
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Survival Games")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SURVIVAL GAMES...");
                p.teleport(new Location(Bukkit.getWorld("hub"), -282.50, 5.50, 36.50), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Spleef")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SPLEEF...");
                p.teleport(Bukkit.getWorld("spleef").getSpawnLocation(), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Splegg")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SPLEGG...");
                p.teleport(Bukkit.getWorld("splegg").getSpawnLocation(), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Hub")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " HUB...");
                p.teleport(Bukkit.getWorld("hub").getSpawnLocation(), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            }

            else { //failsafe
                e.setCancelled(true);
            }
        } catch(Exception ignore) {
            // Ignore the error
        }*/
    }

}
