package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.utils.MenuCore;
import net.thenamedev.legendarena.extras.ActionBarAPI;
import net.thenamedev.legendarena.extras.particles.ParticleCore;
import net.thenamedev.legendarena.extras.particles.ParticleCore.Type;
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

/**
 * @author ThePixelDev
 */
public class ParticleMenu implements Listener {

    private Inventory inv;

    public ParticleMenu(Plugin p) {
        inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Particle Selector");

        ItemStack back = MenuCore.createItem(Material.BED, ChatColor.GRAY + "⇐ Back", "");

        ItemStack hearts = MenuCore.createItem(Material.WOOL, ChatColor.GREEN + "Hearts", ChatColor.BLUE + "Heart particles. Yay!");
        ItemStack slime = MenuCore.createItem(Material.SLIME_BALL, ChatColor.GREEN + "Slime", ChatColor.BLUE + "Slime particles. Yay!");
        ItemStack portal = MenuCore.createItem(Material.ENDER_PORTAL_FRAME, ChatColor.GREEN + "Portal", ChatColor.BLUE + "Portal particles. Yay!");
        ItemStack enchant = MenuCore.createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + "Enchant", ChatColor.BLUE + "Enchant particles. Yay!");
        ItemStack villager = MenuCore.createItem(Material.DIAMOND_BLOCK, ChatColor.GREEN + "Happy Villager", ChatColor.BLUE + "Happy villager particles. Yay!");
        ItemStack villager2 = MenuCore.createItem(Material.DIRT, ChatColor.GREEN + "Angry Villager", ChatColor.BLUE + "Angry villager particles. Yay!");
        ItemStack fw = MenuCore.createItem(Material.FIREWORK, ChatColor.GREEN + "Firework Spark", ChatColor.BLUE + "Firework Spark particles. Yay!");
        ItemStack coloEff = MenuCore.createItem(Material.POTION, ChatColor.GREEN + "Colourful Effect", ChatColor.BLUE + "Colourful Effect particles. Yay!");
        ItemStack off = MenuCore.createItem(Material.REDSTONE_LAMP_OFF, ChatColor.RED + "Off", ChatColor.BLUE + "Turns off your currently displaying particles. Aww.");

        inv.setItem(8, off);
        inv.setItem(4, back);

        inv.setItem(18, hearts);
        inv.setItem(19, slime);
        inv.setItem(20, portal);
        inv.setItem(21, enchant);
        inv.setItem(22, villager);
        inv.setItem(23, villager2);
        inv.setItem(24, fw);
        inv.setItem(25, coloEff);

        Bukkit.getPluginManager().registerEvents(this, p);
    }

    public void show(Player p) {
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        try {
            if(e.getCurrentItem().getItemMeta() == null) return;
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Slime")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Enabled " + ChatColor.RED + "SLIME" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.SLIME);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Hearts")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Enabled " + ChatColor.RED + "HEART" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.HEART);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Portal")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Enabled " + ChatColor.RED + "PORTAL" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.PORTAL);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Enchant")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Enabled " + ChatColor.RED + "ENCHANT" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.ENCHANT);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Happy Villager")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Enabled " + ChatColor.RED + "HAPPY VILLAGER" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.HAPPYVILL);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Angry Villager")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Enabled " + ChatColor.RED + "ANGRY VILLAGER" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.ANGRYVILL);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Firework")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Enabled " + ChatColor.RED + "FIREWORK SPARK" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.FIREWORK);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Colourful")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Enabled " + ChatColor.RED + "COLOURFUL EFFECTS" + ChatColor.GREEN + " particles.");
                ParticleCore.addType(p.getName(), Type.COLORFULEFFCTS);
                e.getWhoClicked().closeInventory();
            }

            else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Off")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Disabled particles.");
                ParticleCore.removePlayer(p.getName());
                e.getWhoClicked().closeInventory();
            }

            else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("⇐ Back")) {
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
