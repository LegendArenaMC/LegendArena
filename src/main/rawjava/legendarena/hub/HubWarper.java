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
                if(!Rank.MOD.isRanked(p)) p.getInventory().clear();
                if(Rank.MOD.isRanked(p)) {
                    try {
                        if(p.getInventory().getItem(5) != getMainMenu())
                            p.getInventory().setItem(5, getMainMenu());
                    }
                    catch(Exception ex) {
                        p.getInventory().setItem(5, getMainMenu()); //I BROKE IT ALL, MY TERMINAL IS BEING SPAMMED CURRENTLY, SEND HELP
                    }
                    try {
                        if(p.getInventory().getItem(3) != getStaffMenu())
                            p.getInventory().setItem(3, getStaffMenu());
                    }
                    catch(Exception ex) {
                        p.getInventory().setItem(3, getStaffMenu());
                    }
                    try {
                        if(p.getInventory().getItem(4) == getMainMenu())
                            p.getInventory().setItem(4, MenuUtils.createItem(Material.AIR));
                    }
                    catch(Exception ex) {
                        //do nothing
                    }
                } else {
                    try {
                        if(p.getInventory().getItem(4) != getMainMenu())
                            p.getInventory().setItem(4, getMainMenu());
                    }
                    catch(Exception ex) {
                        p.getInventory().setItem(4, getMainMenu());
                    }
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, true));
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

    public static ItemStack getMainMenu() {
        return MenuUtils.createItem(Material.NETHER_STAR, ChatColor.GREEN + "Main Menu", ChatColor.BLUE + "Right click me to bring up the main menu!");
    }

    public static ItemStack getStaffMenu() {
        return MenuUtils.createItem(Material.PAPER, ChatColor.GREEN + "Staff Menu", ChatColor.BLUE + "Right click me to bring up the staff menu!");
    }

    public static ItemStack getSoonTM() {
        return MenuUtils.createItem(Material.APPLE, ChatColor.GREEN + "Soon[tm]", ChatColor.BLUE + "Hm? What's this? It looks edible... but it seems to be useless...");
    }

}
