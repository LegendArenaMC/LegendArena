package net.thenamedev.legendarena.utils;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.commands.extras.menu.*;
import net.thenamedev.legendarena.commands.extras.staffchat.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import static net.thenamedev.legendarena.commands.extras.menu.MenuCore.createItem;

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

    public static void setRankPermissions(Player p, Inventory inv) {
        Rank r = Rank.getRank(p);
        if(r == Rank.Dev)
            return;

        ItemStack admin = MenuCore.createItem(Material.BEDROCK, ChatColor.RED + "ADMIN", ChatColor.BLUE + "Admin channel.");
        ItemStack mod = MenuCore.createItem(Material.GOLD_NUGGET, ChatColor.RED + "MOD", ChatColor.BLUE + "Mod channel.");
        ItemStack staff = MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.RED + "STAFF", ChatColor.BLUE + "Staff channel.");
        ItemStack alert = MenuCore.createItem(Material.DISPENSER, ChatColor.RED + "ALERT", ChatColor.BLUE + "Alert channel. [basically a bulk /say]");
        ItemStack notify = MenuCore.createItem(Material.BED, ChatColor.RED + "NOTIFY", ChatColor.BLUE + "Staff notifications channel.");
        ItemStack helper = MenuCore.createItem(Material.REDSTONE_LAMP_OFF, ChatColor.RED + "HELPER", ChatColor.BLUE + "Helper channel.");

        if(r == Rank.Admin) {
            //Cannot access: NOTIFY
            inv.setItem(1, notify);
        } else if(r == Rank.Mod) {
            //Cannot access: ADMIN, NOTIFY
            inv.setItem(0, admin);
            inv.setItem(1, notify);
        } else if(r == Rank.Helper) {
            //Cannnot access: ADMIN, NOTIFY, MOD, ALERT
            inv.setItem(0, admin);
            inv.setItem(1, notify);
            inv.setItem(2, mod);
            inv.setItem(4, alert);
        } else if(r == Rank.VIP) {
            //Cannot access: ADMIN, NOTIFY, MOD, STAFF, ALERT, HELPER
            inv.setItem(0, admin);
            inv.setItem(1, notify);
            inv.setItem(2, mod);
            inv.setItem(3, staff);
            inv.setItem(4, alert);
            inv.setItem(6, helper);
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
