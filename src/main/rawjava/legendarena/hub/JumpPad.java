package legendarena.hub;

import legendapi.utils.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class JumpPad {

    public static void jump(Player p) {
        p.setVelocity(p.getLocation().getDirection().multiply(3.0D).setY(1));
    }

    public static class JumpPadListener implements Listener {

        private HashMap<UUID, Cooldown> cooldown = new HashMap<>();

        @EventHandler
        public void listenForMove(final PlayerMoveEvent ev) {
            if(ev.getPlayer().isFlying()) return;
            if(cooldown.containsKey(ev.getPlayer().getUniqueId()))
                if(!cooldown.get(ev.getPlayer().getUniqueId()).done())
                    return;
            if(ev.getPlayer().getLocation().getBlock().getType() == Material.IRON_PLATE && ev.getPlayer().getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.REDSTONE_BLOCK) {
                cooldown.put(ev.getPlayer().getUniqueId(), new Cooldown(0.05));
                Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    public void run() {
                        jump(ev.getPlayer());
                    }
                }, 1l);
            }
        }

    }

    public static class Timer implements Runnable {

        private HashMap<UUID, Cooldown> cooldown = new HashMap<>();

        public void run() {
            for(final Player p : Bukkit.getOnlinePlayers()) {
                //noinspection deprecation
                if(!p.isOnGround()) return;
                if(cooldown.containsKey(p.getUniqueId()))
                    if(!cooldown.get(p.getUniqueId()).done())
                        return;
                if(p.getLocation().getBlock().getType() == Material.IRON_PLATE && p.getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.REDSTONE_BLOCK) {
                    cooldown.put(p.getUniqueId(), new Cooldown(0.05));
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                        public void run() {
                            jump(p);
                        }
                    }, 2l);
                }
            }
        }

    }

}
