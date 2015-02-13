package me.thenameman.legendarena.utils;

import me.thenameman.legendarena.menu.*;
import me.thenameman.legendarena.staffchat.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

/**
 * @author TheNameMan
 */
public class SCUtils {

    public static void setItem(Inventory inv, Player p) {
        ItemStack i = getEnchantedItem(p);
        if(i == null) //sanity check
            return;
        ItemMeta im = i.getItemMeta();
        if(im.getDisplayName().contains("ADMIN")) {
            inv.setItem(0, i);
        } else if(im.getDisplayName().contains("NOTIFY")) {
            inv.setItem(1, i);
        } else if(im.getDisplayName().contains("MOD")) {
            inv.setItem(2, i);
        } else if(im.getDisplayName().contains("STAFF")) {
            inv.setItem(3, i);
        } else if(im.getDisplayName().contains("ALERT")) {
            inv.setItem(4, i);
        } else if(im.getDisplayName().contains("VIP")) {
            inv.setItem(5, i);
        } else if(im.getDisplayName().contains("HELPER")) {
            inv.setItem(6, i);
        } else {
            inv.setItem(22, i);
        }
    }

    private static ItemStack getEnchantedItem(Player p) {
        SCType channel = SCType.getType(p.getUniqueId());
        if(channel == null)
            return null;
        ItemStack init;
        switch(channel) {
            case ADMIN:
                init = MenuCore.createItem(Material.BEDROCK, ChatColor.BLUE + "ADMIN", ChatColor.BLUE + "Admin channel.");
                init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                return init;
            case NOTIFICATION:
                init = MenuCore.createItem(Material.BED, ChatColor.BLUE + "NOTIFY", ChatColor.BLUE + "Staff notifications channel.");
                init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                return init;
            case MOD:
                init = MenuCore.createItem(Material.GOLD_NUGGET, ChatColor.BLUE + "MOD", ChatColor.BLUE + "Mod channel.");
                init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                return init;
            case STAFF:
                init = MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.BLUE + "STAFF", ChatColor.BLUE + "Staff channel.");
                init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                return init;
            case ALERT:
                init = MenuCore.createItem(Material.DISPENSER, ChatColor.BLUE + "ALERT", ChatColor.BLUE + "Alert channel. [basically a bulk /say]");
                init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                return init;
            case VIP:
                init = MenuCore.createItem(Material.EMERALD, ChatColor.BLUE + "VIP", ChatColor.BLUE + "Alert channel. [basically a bulk /say]");
                init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                return init;
            case HELPER:
                init = MenuCore.createItem(Material.REDSTONE_LAMP_OFF, ChatColor.BLUE + "HELPER", ChatColor.BLUE + "Alert channel. [basically a bulk /say]");
                init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                return init;

            default:
                return null;
        }
    }

}
