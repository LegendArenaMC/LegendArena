package legendarena.hub;

import legendarena.hub.particles.ParticleCore;
import legendarena.hub.particles.lib.ParticleEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class JumpPad {

    public static void jump(Player p) {
        p.setVelocity(p.getLocation().getDirection().multiply(5.0D).setY(1));
    }

    public static class JumpPadListener implements Listener {

        @EventHandler
        public void listenForMove(PlayerMoveEvent ev) {
            if(ev.getPlayer().getLocation().getBlock().getType() == Material.IRON_PLATE) {
                jump(ev.getPlayer());
                ParticleCore.playEffect(1, ev.getPlayer().getLocation(), ParticleEffect.FIREWORKS_SPARK, 0, 3, 5);
            }
        }

    }

}
