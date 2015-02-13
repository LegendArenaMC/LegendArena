package net.thenamedev.legendarena.menu;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerTeleportEvent.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;

/**
 * @author TheNameMan
 */
public class MinigameMenu implements Listener {

    private Inventory inv;

    public MinigameMenu(Plugin plugin) {
        inv = Bukkit.getServer().createInventory(null, 36, ChatColor.BLUE + "Minigames");

        ItemStack spl;
        ItemStack ba;
        ItemStack sp;
        ItemStack pr;
		ItemStack more;
        ItemStack sg;

        sp = MenuCore.createItem(Material.GOLD_SPADE, ChatColor.GREEN + "Spleef");
		more = MenuCore.createItem(Material.PAPER, ChatColor.RED + "Note", ChatColor.YELLOW + "More minigames are coming soon (as in when we finish them)!");
        spl = MenuCore.createItem(Material.EGG, ChatColor.GREEN + "Splegg");
        pr = MenuCore.createItem(Material.IRON_FENCE, ChatColor.GREEN + "Prison", ChatColor.RED + "Coming soon[tm]...");
        ba = MenuCore.createItem(Material.ARROW, ChatColor.GREEN + "Back", ChatColor.BLUE + "Go back <<");
        sg = MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Survival Games");

        inv.setItem(1, sp);
        inv.setItem(7, spl);
		inv.setItem(13, more);
        inv.setItem(19, pr);
        inv.setItem(31, ba);
        inv.setItem(25, sg);

        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void show(Player p) {
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        if(e.getCurrentItem().getItemMeta() == null) return;
        try {
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Survival Games")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SURVIVAL GAMES...");
                p.teleport(new Location(Bukkit.getWorld("hub"), -87.50, 4.0, -200.50), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Spleef")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SPLEEF...");
                p.teleport(Bukkit.getWorld("spleef").getSpawnLocation(), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Back")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                e.getWhoClicked().closeInventory();
                MenuInv.menu.show(p);
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Splegg")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SPLEGG...");
                p.teleport(Bukkit.getWorld("splegg").getSpawnLocation(), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Prison")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.RED + "That's coming soon[tm]!");
                e.getWhoClicked().closeInventory();
            }

            else { //failsafe
                e.setCancelled(true);
            }
        } catch(Exception ignore) {
            // Ignore the error
        }
    }

}
