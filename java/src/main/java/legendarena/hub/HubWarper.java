package legendarena.hub;

import legendapi.message.Message;
import legendapi.message.MessageType;
import legendapi.utils.Cooldown;
import legendapi.utils.CooldownUtils;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Hub warper.
 *
 * @author ThePixelDev
 */
public class HubWarper implements Listener {

    private static final List<UUID> exempt = new ArrayList<>();
    private static HashMap<UUID, Cooldown> cooldown = new HashMap<>();

    public static void toggleExemption(UUID p) {
        if(isExempt(p))
            exempt.remove(p);
        else
            exempt.add(p);
    }

    public static boolean isExempt(UUID p) {
        return exempt.contains(p);
    }

    private static ItemStack getCustomization() {
        return MenuUtils.createItem(Material.NETHER_STAR, ChatColor.GREEN + "Main Menu", "");
    }

    @EventHandler(ignoreCancelled = true)
    public void listenForDrop(PlayerDropItemEvent ev) {
        if(isExempt(ev.getPlayer().getUniqueId())) return;
        ev.setCancelled(true);
    }

    @EventHandler
    public void listenForInteract(PlayerInteractEvent ev) {
        try {
            if(!isExempt(ev.getPlayer().getUniqueId()))
                ev.setCancelled(true);
            if(ev.getAction() != Action.RIGHT_CLICK_AIR && ev.getAction() != Action.RIGHT_CLICK_BLOCK)
                return;
            if(ev.getItem().equals(getCustomization())) {
                if(cooldown.containsKey(ev.getPlayer().getUniqueId())) {
                    Cooldown c = cooldown.get(ev.getPlayer().getUniqueId());
                    if(!c.done()) {
                        new Message(MessageType.ACTIONBAR).append(ChatColor.RED + "Ow, that hurts! :( ( " + c.getTimeRemaining() + ChatColor.RED + " )").send(ev.getPlayer());
                        return;
                    }
                }
                MainMenu.show(ev.getPlayer());
                if(!ev.isCancelled())
                    ev.setCancelled(true);
                cooldown.put(ev.getPlayer().getUniqueId(), CooldownUtils.getCooldown(2));
            }
        } catch(Exception ex) {
            //ignore
        }
    }

    public static class InitPlayers implements Runnable {

        public void run() {
            for(final Player p : Bukkit.getOnlinePlayers()) {
                if(exempt.contains(p.getUniqueId())) continue;
                if(!Rank.isRanked(p, Rank.ADMIN)) p.getInventory().clear();
                p.getInventory().setItem(4, getCustomization());
                //TODO: Obligatory "Fucking fix me you moron Pixel"
                //fuck you too async (this is just a memory leak waiting to happen, by the way)
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    public void run() { p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1, true)); }
                });
            }
        }

    }
}
