package legendarena.api.utils;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

/**
 * Menu utilities. Really only used for creating items.<br><br>
 *
 * Also, I swear I was drunk while originally making this class. I swear.
 *
 * @author ThePixelDev
 */
public class MenuUtils {

    public static ItemStack createItem(Material material) {
        return new ItemStack(material);
    }

    public static ItemStack createItem(Material material, String name) {
        ItemStack i = createItem(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(Material material, String name, String... lores) {
        ItemStack i = createItem(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lores));
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(Material material, String name, int amount) {
        ItemStack i = createItem(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        i.setAmount(amount);
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(Material material, String name, int amount, String... lores) {
        ItemStack i = createItem(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lores));
        i.setAmount(amount);
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(Material material, String name, short damage) {
        ItemStack i = new ItemStack(material, 1, damage);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createHead(String playerName) {
        ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta sm = (SkullMeta) i.getItemMeta();
        sm.setOwner(playerName);
        i.setItemMeta(sm);
        return i;
    }

    public static ItemStack createHead(String playerName, String itemName) {
        ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta sm = (SkullMeta) i.getItemMeta();
        sm.setOwner(playerName);
        sm.setDisplayName(itemName);
        i.setItemMeta(sm);
        return i;
    }

}
