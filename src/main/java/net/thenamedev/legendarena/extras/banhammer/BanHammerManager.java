package net.thenamedev.legendarena.extras.banhammer;

import net.thenamedev.legendapi.utils.MenuCore;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.*;

/**
 * @author ThePixelDev
 */
public class BanHammerManager {

    public static boolean hasBanHammer(@NotNull Player p) {
        return p.getItemInHand().equals(getBanHammerItem(p.getName()));
    }

    public static ItemStack getBanHammerItem(@Nullable String playerName) {
        if(playerName == null)
            throw new NullPointerException();
        return MenuCore.createItem(Material.GOLD_SPADE, ChatColor.LIGHT_PURPLE + playerName + ChatColor.RED + "'s Ban Hammer", "Use with caution.");
    }

}
