package net.thenamedev.legendarena.extras.menu;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.jetbrains.annotations.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class MenuCore {

    @NotNull
    public static ItemStack createItem(@NotNull Material m, String n) {
        @NotNull ItemStack i = new ItemStack(m);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(n);
        im.setLore(Collections.singletonList(ChatColor.BLUE + "Warp to " + n));
        i.setItemMeta(im);
        return i;
    }

    @NotNull
    public static ItemStack createItem(@NotNull Material m, String n, String l) {
        @NotNull ItemStack i = new ItemStack(m);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(n);
        im.setLore(Collections.singletonList(l));
        i.setItemMeta(im);
        return i;
    }

    @NotNull
    public static ItemStack createItem(@NotNull ItemStack i, String n, String l) {
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(n);
        im.setLore(Collections.singletonList(l));
        i.setItemMeta(im);
        return i;
    }

    @NotNull
    public static ItemStack createItem(@NotNull Material m, String n, String... l) {
        @NotNull ItemStack i = new ItemStack(m);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(n);
        im.setLore(Arrays.asList(l));
        i.setItemMeta(im);
        return i;
    }

}
