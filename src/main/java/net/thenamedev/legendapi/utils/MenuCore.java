package net.thenamedev.legendapi.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

/**
 * Menu core. Really only used for creating items.
 *
 * @author ThePixelDev
 */
public class MenuCore {

    public static ItemStack createItem(Material m, String n) {
        ItemStack i = new ItemStack(m);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(n);
        im.setLore(Collections.singletonList(ChatColor.BLUE + "Warp to " + n));
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(Material m, String n, String... l) {
        ItemStack i = new ItemStack(m);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(n);
        im.setLore(Arrays.asList(l));
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(Material m, String n, int amount, String... l) {
        ItemStack i = new ItemStack(m);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(n);
        im.setLore(Arrays.asList(l));
        i.setAmount(amount);
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(Material m, String n, int amount) {
        ItemStack i = new ItemStack(m);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(n);
        im.setLore(Collections.singletonList(ChatColor.BLUE + "Warp to " + n));
        i.setAmount(amount);
        i.setItemMeta(im);
        return i;
    }

}
