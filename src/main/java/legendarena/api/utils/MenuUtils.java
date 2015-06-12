package legendarena.api.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Menu core. Really only used for creating items.<br><br>
 *
 * Also, I swear I was drunk while originally making this class. I swear.
 *
 * @author ThePixelDev
 */
public class MenuUtils {

    public static ItemStack createItem(Material material, String name) {
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(Material material, String name, String... lores) {
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lores));
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(Material material, String name, int amount) {
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        i.setAmount(amount);
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack createItem(Material material, String name, int amount, String... lores) {
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lores));
        i.setAmount(amount);
        i.setItemMeta(im);
        return i;
    }

}
