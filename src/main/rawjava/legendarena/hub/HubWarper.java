package legendarena.hub;

import legendapi.message.Message;
import legendapi.message.MessageType;
import legendapi.utils.Cooldown;
import legendapi.utils.MenuUtils;
import legendapi.utils.Rank;
import legendarena.hub.menu.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
                if(!Rank.isRanked(p, Rank.ADMIN)) p.getInventory().clear();
                if(p.getInventory().getItem(4) == null || p.getInventory().getItem(4) == MenuUtils.createItem(Material.AIR)) p.getInventory().setItem(4, getCustomization());
                //TODO: Obligatory "Fucking fix me you moron Pixel"
                //fuck you too async (this is just a memory leak waiting to happen, by the way)
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    public void run() {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1, true));
                    }
                });
            }
        }

    }

    public static void toggleExemption(UUID p) {
        if(isExempt(p))
            exempt.remove(p);
        else
            exempt.add(p);
    }

    public static boolean isExempt(UUID p) {
        return exempt.contains(p);
    }

    public static ItemStack getCustomization() {
        return MenuUtils.createItem(Material.NETHER_STAR, ChatColor.GREEN + "Main Menu", "");
    }

}
