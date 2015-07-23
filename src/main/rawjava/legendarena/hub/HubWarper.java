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
                if(!Rank.ADMIN.isRanked(p)) p.getInventory().clear();
                try {
                    if(!p.getInventory().getItem(4).getItemMeta().getDisplayName()
                            .equals(getMainMenu().getItemMeta().getDisplayName())) p.getInventory().setItem(4, getMainMenu());
                }
                catch(Exception ex) {
                    p.getInventory().setItem(4, getMainMenu()); //I BROKE IT ALL, MY CONSOLE IS BEING SPAMMED CURRENTLY, SEND HELP
                }
                //fuck you too async (this is just a memory leak waiting to happen, by the way)
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    public void run() {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, true));
                    }
                });
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
        return MenuUtils.createItem(Material.NETHER_STAR, ChatColor.GREEN + "Main Menu");
    }

}
