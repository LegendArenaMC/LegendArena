package legendarena.hub;

import legendarena.api.utils.MenuUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

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

}
