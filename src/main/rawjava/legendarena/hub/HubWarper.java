package legendarena.hub;

import legendapi.utils.MenuUtils;
import legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.*;

public class HubWarper {

    private static ArrayList<UUID> exempt = new ArrayList<>();

    public static void toggleExemption(UUID p) {
        if(isExempt(p)) exempt.remove(p);
        else exempt.add(p);
    }

    public static boolean isExempt(UUID p) {
        return exempt.contains(p);
    }

    public static ItemStack getMainMenu(String player) {
        return MenuUtils.createHead(player, ChatColor.GREEN + "Main Menu");
    }

    public static ItemStack getStaffMenu() {
        return MenuUtils.createItem(Material.PAPER, ChatColor.GREEN + "Staff Menu");
    }

}
