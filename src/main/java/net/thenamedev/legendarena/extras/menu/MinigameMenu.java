package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.inventory.*;
import net.thenamedev.legendapi.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;

import java.util.*;

/**
 * @author ThePixelDev
 */
public class MinigameMenu implements Listener {

    private static InventoryManager invMan = new InventoryManager();

    public MinigameMenu(Plugin plugin) {
        //Lists

        // Begin item list mayhem
        HashMap<Integer, ItemStack> items = new HashMap<>();
        items.put(1, MenuCore.createItem(Material.GOLD_SPADE, ChatColor.GREEN + "Spleef"));
        items.put(7, MenuCore.createItem(Material.EGG, ChatColor.GREEN + "Splegg"));
        items.put(13, MenuCore.createItem(Material.COMMAND, ChatColor.GREEN + "Hub"));
        items.put(19, MenuCore.createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), ChatColor.RED + "Coming Soon[tm]", ChatColor.YELLOW + "Check back later!"));
        items.put(25, MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Survival Games"));

        // Begin action list mayhem

        //Init stuffs
        invMan.setInvName(ChatColor.GREEN + "Warper");
        invMan.setActions(null);
        invMan.setSlots(27);
        invMan.init();
        invMan.setInvItems(items);
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
