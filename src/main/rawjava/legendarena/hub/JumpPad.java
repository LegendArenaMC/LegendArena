package legendarena.hub;

import legendapi.message.Message;
import legendapi.utils.Cooldown;
import legendarena.hub.particles.ParticleCore;
import legendarena.hub.particles.lib.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class JumpPad {

    public static void jump(Player p) {
        p.setVelocity(p.getLocation().getDirection().multiply(3.0D).setY(1));
        new Message().send(Sound.FIZZ, 6, p);
    }

    public static class JumpPadListener implements Listener {

        private HashMap<UUID, Cooldown> cooldown = new HashMap<>();

        @EventHandler
        public void listenForMove(final PlayerMoveEvent ev) {
            if(!ev.getPlayer().isOnGround()) return;
            if(cooldown.containsKey(ev.getPlayer().getUniqueId()))
                if(!cooldown.get(ev.getPlayer().getUniqueId()).done())
                    return;
            if(ev.getPlayer().getLocation().getBlock().getType() == Material.IRON_PLATE && ev.getPlayer().getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.REDSTONE_BLOCK) {
                cooldown.put(ev.getPlayer().getUniqueId(), new Cooldown(0.05));
                Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    public void run() {
                        jump(ev.getPlayer());
                    }
                }, 2l);
            }
        }

    }

}
