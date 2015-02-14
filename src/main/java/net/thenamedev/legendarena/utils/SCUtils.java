package net.thenamedev.legendarena.utils;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.extras.menu.*;
import net.thenamedev.legendarena.extras.staffchat.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class SCUtils {

    public static void setItem(@NotNull Player p, @NotNull Inventory inv) {
        @Nullable ItemStack i = getEnchantedItem(p);
        if(i == null) //sanity check
            return;
        ItemMeta im = i.getItemMeta();
        if(im.getDisplayName().contains("ADMIN")) {
            inv.setItem(1, i);
        } else if(im.getDisplayName().contains("NOTIFY")) {
            inv.setItem(0, i);
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

    public static void setRankPermissions(@NotNull Player p, @NotNull Inventory inv) {
        @NotNull Rank r = Rank.getRank(p);
        if(r == Rank.Dev)
            return;

        ItemStack admin = MenuCore.createItem(Material.BEDROCK, ChatColor.RED + "ADMIN", ChatColor.BLUE + "Admin channel.");
        ItemStack mod = MenuCore.createItem(Material.GOLD_NUGGET, ChatColor.RED + "MOD", ChatColor.BLUE + "Mod channel.");
        ItemStack staff = MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.RED + "STAFF", ChatColor.BLUE + "Staff channel.");
        ItemStack alert = MenuCore.createItem(Material.DISPENSER, ChatColor.RED + "ALERT", ChatColor.BLUE + "Alert channel. [basically a bulk /say]");
        ItemStack notify = MenuCore.createItem(Material.BED, ChatColor.RED + "NOTIFY", ChatColor.BLUE + "Staff notifications channel.");
        ItemStack vip = MenuCore.createItem(Material.EMERALD, ChatColor.RED + "VIP", ChatColor.BLUE + "VIP channel.");
        ItemStack helper = MenuCore.createItem(Material.SADDLE, ChatColor.RED + "HELPER", ChatColor.BLUE + "Helper channel.");

        if(r == Rank.Admin) {
            //Cannot access: NOTIFY
            inv.setItem(0, notify);
        } else if(r == Rank.Mod) {
            //Cannot access: ADMIN, NOTIFY
            inv.setItem(1, admin);
            inv.setItem(0, notify);
        } else if(r == Rank.Helper) {
            //Cannnot access: ADMIN, NOTIFY, MOD, ALERT
            inv.setItem(1, admin);
            inv.setItem(0, notify);
            inv.setItem(2, mod);
            inv.setItem(4, alert);
        } else if(r == Rank.VIP) {
            //Cannot access: ADMIN, NOTIFY, MOD, STAFF, ALERT, HELPER
            inv.setItem(1, admin);
            inv.setItem(0, notify);
            inv.setItem(2, mod);
            inv.setItem(3, staff);
            inv.setItem(4, alert);
            inv.setItem(6, helper);
        } else if(r == Rank.Member) {
            //Cannot access: ADMIN, NOTIFY, MOD, STAFF, ALERT, VIP, HELPER
            inv.setItem(1, admin);
            inv.setItem(0, notify);
            inv.setItem(2, mod);
            inv.setItem(3, staff);
            inv.setItem(4, alert);
            inv.setItem(5, vip);
            inv.setItem(6, helper);
        }
    }

    private static ItemStack getEnchantedItem(@NotNull Player p) {
        SCType channel = SCType.getType(p.getUniqueId());
        ItemStack init;
        if(channel == null) {
            init = MenuCore.createItem(Material.REDSTONE_LAMP_OFF, ChatColor.BLUE + "GLOBAL", ChatColor.BLUE + "Global chat. That's really all that needs to be said.");
            init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            return init;
        }
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
                init = MenuCore.createItem(Material.EMERALD, ChatColor.BLUE + "VIP", ChatColor.BLUE + "VIP channel.");
                init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                return init;
            case HELPER:
                init = MenuCore.createItem(Material.SADDLE, ChatColor.BLUE + "HELPER", ChatColor.BLUE + "Helper channel.");
                init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                return init;
            case PUBLIC:
                init = MenuCore.createItem(Material.REDSTONE_LAMP_OFF, ChatColor.BLUE + "OFF", ChatColor.BLUE + "Exit all channels and go back to global chat.");
                init.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                return init;
        }
        return null; //fallback
    }

}
