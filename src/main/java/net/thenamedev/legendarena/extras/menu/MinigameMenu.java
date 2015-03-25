package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.utils.MenuCore;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendarena.extras.hub.particles.ParticleCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

/**
 * @author ThePixelDev
 */
public class MinigameMenu implements Listener {

    Inventory inv;

    public MinigameMenu(Plugin p) {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        //items.put(1, MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Survival Games"));
        //items.put(7, MenuCore.createItem(Material.EGG, ChatColor.GREEN + "Chicken Mayhem"));
        items.put(12, MenuCore.createItem(Material.SNOW_BALL, ChatColor.GREEN + "Plugin: Now FOSS!", ChatColor.YELLOW + "https://notabug.org/LegendArenaMC/LegendArena"));
        items.put(14, MenuCore.createItem(Material.COMMAND, ChatColor.GREEN + "Hub"));
        //items.put(25, MenuCore.createItem(Material.BRICK, ChatColor.GREEN + "Build My Thing"));

        inv = Bukkit.createInventory(null, 27, PluginUtils.msgNormal + "Warper");
        for(int a : items.keySet())
            inv.setItem(a, items.get(a));

        Bukkit.getPluginManager().registerEvents(this, p);
    }

    public void show(Player p) {
        p.closeInventory();
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        try {
            if(e.getCurrentItem().getItemMeta() == null) return;
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Hub")) {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.BLUE + "Warping you to " + ChatColor.RED + "HUB" + ChatColor.BLUE + "...");
                p.teleport(Bukkit.getWorld("hub").getSpawnLocation());
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("FOSS")) {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                //noinspection RedundantCast
                ((Player) e.getWhoClicked()).sendMessage(ChatColor.BLUE + "Git repo: " + ChatColor.BOLD + "https://notabug.org/LegendArenaMC/LegendArena");
            }

            else { //failsafe
                e.setCancelled(true);
            }
        } catch(Exception ignore) {
            // Ignore the error
        }
    }

}
