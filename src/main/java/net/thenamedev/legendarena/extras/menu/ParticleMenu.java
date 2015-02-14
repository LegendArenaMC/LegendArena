package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendarena.extras.menu.core.*;
import net.thenamedev.legendarena.extras.particles.*;
import net.thenamedev.legendarena.extras.particles.ParticleCore.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class ParticleMenu implements Menu, Listener {

    private Inventory inv;

    public ParticleMenu(Plugin p) {
        inv = Bukkit.getServer().createInventory(null, 9, ChatColor.BLUE + "Particle Selector");

        ItemStack hearts = MenuCore.createItem(Material.WOOL, ChatColor.GREEN + "Hearts", ChatColor.BLUE + "Heart particles. Yay!");
        ItemStack flame = MenuCore.createItem(Material.SADDLE, ChatColor.GREEN + "Flame", ChatColor.BLUE + "Flame particles. Yay!");

        ItemStack off = MenuCore.createItem(Material.REDSTONE_LAMP_OFF, ChatColor.RED + "Off", ChatColor.BLUE + "Turns off your currently displaying particles. Aww.");

        inv.setItem(0, hearts);
        inv.setItem(1, flame);
        inv.setItem(8, off);

        Bukkit.getServer().getPluginManager().registerEvents(this, p);
    }

    public void show(@NotNull Player p) {
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent e) {
        if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        if(e.getCurrentItem().getItemMeta() == null) return;
        try {
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Flame")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.RED + "FLAME" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.FLAME);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Hearts")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.RED + "HEART" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.HEART);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Off")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Turned off particles. I feel sad now.");
                ParticleCore.removePlayer(p.getName());
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
