package net.thenamedev.legendarena.extras.banhammer;

import net.thenamedev.legendapi.utils.MenuCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
/**
 * @author ThePixelDev
 */
public class BanHammerManager {

    public static boolean hasBanHammer(Player p) {
        return p.getItemInHand().equals(getBanHammerItem(p.getName()));
    }

    public static ItemStack getBanHammerItem(String playerName) {
        if(playerName == null)
            throw new NullPointerException();

        return MenuCore.createItem(Material.GOLD_SPADE, ChatColor.LIGHT_PURPLE + playerName + ChatColor.RED + "'s Ban Hammer", "Use with caution.");
    }

}
