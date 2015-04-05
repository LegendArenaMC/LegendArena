package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.utils.MenuCore;
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
 * Created on 4/4/2015
 *
 * @author ThePixelDev
 */
public class TrollMenu implements Listener {

    public enum Trolls {
        FREEZE("staff freeze", true, MenuCore.createItem(Material.ICE, ChatColor.BLUE + "Freeze", "")),
        CLEAR("clear", true, MenuCore.createItem(Material.CAULDRON, ChatColor.BLUE + "Clear Inventory", "")),
        GMON("gm", false, MenuCore.createItem(Material.LEVER, ChatColor.BLUE + "GameMode", "")),
        GMOFF("gm", false, MenuCore.createItem(Material.REDSTONE_TORCH_ON, ChatColor.BLUE + "GameMode", ""));

        private String trollCmd;
        private ItemStack item;
        private boolean requiresSelector;

        Trolls(String trollCmd, boolean requiresSelector, ItemStack item) {
            this.trollCmd = trollCmd;
            this.item = item;
            this.requiresSelector = requiresSelector;
        }

        public String getCmd() {
            return trollCmd;
        }
        public boolean requiresSelector() {
            return requiresSelector;
        }
        public ItemStack getItem() {
            return item;
        }
    }

    private Inventory inv;

    public TrollMenu(Plugin p) {
        inv = Bukkit.createInventory(null, 18, ChatColor.BLUE + "Particle Selector");

        ItemStack hearts = MenuCore.createItem(Material.WOOL, ChatColor.GREEN + "Hearts", ChatColor.BLUE + "Heart particles. Yay!");
        ItemStack slime = MenuCore.createItem(Material.SLIME_BALL, ChatColor.GREEN + "Slime", ChatColor.BLUE + "Slime particles. Yay!");
        ItemStack portal = MenuCore.createItem(Material.ENDER_PORTAL_FRAME, ChatColor.GREEN + "Portal", ChatColor.BLUE + "Portal particles. Yay!");
        ItemStack enchant = MenuCore.createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + "Enchant", ChatColor.BLUE + "Enchant particles. Yay!");
        ItemStack villager = MenuCore.createItem(Material.DIAMOND_BLOCK, ChatColor.GREEN + "Happy Villager", ChatColor.BLUE + "Happy villager particles. Yay!");
        ItemStack villager2 = MenuCore.createItem(Material.DIRT, ChatColor.GREEN + "Angry Villager", ChatColor.BLUE + "Angry villager particles. Yay!");
        ItemStack crit = MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Crit", ChatColor.BLUE + "Critical Hit particles. Yay!");
        ItemStack fw = MenuCore.createItem(Material.FIREWORK, ChatColor.GREEN + "Firework Spark", ChatColor.BLUE + "Firework Spark particles. Yay!");
        ItemStack coloEff = MenuCore.createItem(Material.POTION, ChatColor.GREEN + "Colourful Effect", ChatColor.BLUE + "Colourful Effect particles. Yay!");

        ItemStack off = MenuCore.createItem(Material.REDSTONE_LAMP_OFF, ChatColor.RED + "Off", ChatColor.BLUE + "Turns off your currently displaying particles. Aww.");

        inv.setItem(0, hearts);
        inv.setItem(1, slime);
        inv.setItem(2, portal);
        inv.setItem(3, enchant);
        inv.setItem(4, villager);
        inv.setItem(5, villager2);
        inv.setItem(6, crit);
        inv.setItem(7, fw);
        inv.setItem(8, coloEff);

        inv.setItem(17, off);

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
            if("" == "a") {
                //
            }

            else { //failsafe
                e.setCancelled(true);
            }
        } catch(Exception ignore) {
            // Ignore the error
        }
    }

}
