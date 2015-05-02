package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.utils.MenuCore;
import net.thenamedev.legendapi.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        items.put(4, MenuCore.createItem(Material.BED, ChatColor.GRAY + "⇐ Back", ""));
        items.put(21, MenuCore.createItem(Material.DISPENSER, ChatColor.GREEN + "Hub"));
        items.put(23, MenuCore.createItem(Material.STAINED_CLAY, ChatColor.GREEN + "Build My Thing"));

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
                p.teleport(Bukkit.getWorld("world").getSpawnLocation());
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Build My Thing")) {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatColor.GREEN + "Totally not a hint towards an actual minigame that works, nope, no hints here </sarcasm>");
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("⇐ Back")) {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                MainMenu.show((Player) e.getWhoClicked());
            }

            else { //failsafe
                e.setCancelled(true);
            }
        } catch(Exception ignore) {
            // Ignore the error
        }
    }

}
