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

    public static class InitPlayers implements Runnable {

        public void run() {
            for(final Player p : Bukkit.getOnlinePlayers()) {
                if(exempt.contains(p.getUniqueId())) continue;
                if(Rank.MOD.isRanked(p)) {
                    if(!p.getInventory().getItem(5).getItemMeta().getDisplayName().equals(getMainMenu(p.getName()).getItemMeta().getDisplayName()))
                        p.getInventory().setItem(5, getMainMenu(p.getName()));
                    p.getInventory().setItem(3, getStaffMenu());
                } else
                if(!p.getInventory().getItem(4).getItemMeta().getDisplayName().equals(getMainMenu(p.getName()).getItemMeta().getDisplayName()))
                    p.getInventory().setItem(4, getMainMenu(p.getName()));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, true, false));
            }
        }

    }

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
