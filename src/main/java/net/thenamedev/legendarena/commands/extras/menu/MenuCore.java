package net.thenamedev.legendarena.commands.extras.menu;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

/**
 * @author TheNameMan
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

    public static ItemStack createItem(Material m, String n, String l) {
        ItemStack i = new ItemStack(m);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(n);
        im.setLore(Collections.singletonList(l));
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(ItemStack i, String n, String l) {
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(n);
        im.setLore(Collections.singletonList(l));
        i.setItemMeta(im);
        return i;
    }

}
