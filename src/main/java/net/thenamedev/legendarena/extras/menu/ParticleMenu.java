package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendarena.extras.menu.core.*;
import net.thenamedev.legendarena.extras.hub.particles.*;
import net.thenamedev.legendarena.extras.hub.particles.ParticleCore.*;
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
        inv = Bukkit.createInventory(null, 18, ChatColor.BLUE + "Particle Selector");

        @NotNull ItemStack hearts = MenuCore.createItem(Material.WOOL, ChatColor.GREEN + "Hearts", ChatColor.BLUE + "Heart particles. Yay!");
        @NotNull ItemStack slime = MenuCore.createItem(Material.SLIME_BALL, ChatColor.GREEN + "Slime", ChatColor.BLUE + "Slime particles. Yay!");
        @NotNull ItemStack portal = MenuCore.createItem(Material.ENDER_PORTAL_FRAME, ChatColor.GREEN + "Portal", ChatColor.BLUE + "Portal particles. Yay!");
        @NotNull ItemStack enchant = MenuCore.createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + "Enchant", ChatColor.BLUE + "Enchant particles. Yay!");
        @NotNull ItemStack villager = MenuCore.createItem(Material.DIAMOND_BLOCK, ChatColor.GREEN + "Happy Villager", ChatColor.BLUE + "Happy villager particles. Yay!");
        @NotNull ItemStack villager2 = MenuCore.createItem(Material.DIRT, ChatColor.GREEN + "Angry Villager", ChatColor.BLUE + "Angry villager particles. Yay!");
        @NotNull ItemStack crit = MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Crit", ChatColor.BLUE + "Critical Hit particles. Yay!");
        @NotNull ItemStack fw = MenuCore.createItem(Material.FIREWORK, ChatColor.GREEN + "Firework Spark", ChatColor.BLUE + "Firework Spark particles. Yay!");

        @NotNull ItemStack off = MenuCore.createItem(Material.REDSTONE_LAMP_OFF, ChatColor.RED + "Off", ChatColor.BLUE + "Turns off your currently displaying particles. Aww.");

        inv.setItem(0, hearts);
        inv.setItem(1, slime);
        inv.setItem(2, portal);
        inv.setItem(3, enchant);
        inv.setItem(4, villager);
        inv.setItem(5, villager2);
        inv.setItem(6, crit);
        inv.setItem(7, fw);

        inv.setItem(17, off);

        Bukkit.getPluginManager().registerEvents(this, p);
    }

    public void show(@NotNull Player p) {
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent e) {
        if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        try {
            if(e.getCurrentItem().getItemMeta() == null) return;
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Slime")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.RED + "SLIME" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.SLIME);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Hearts")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.RED + "HEART" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.HEART);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Portal")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.RED + "PORTAL" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.PORTAL);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Enchant")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.RED + "ENCHANT" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.ENCHANT);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Happy Villager")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.RED + "HAPPY VILLAGER" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.HAPPYVILL);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Angry Villager")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.RED + "ANGRY VILLAGER" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.ANGRYVILL);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Crit")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.RED + "CRIT" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.CRIT);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Firework")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Enabled " + ChatColor.RED + "FIREWORK SPARK" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.FIREWORK);
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
