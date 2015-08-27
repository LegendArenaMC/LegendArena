package legendarena.hub;

import legendarena.api.message.Message;
import legendarena.api.utils.Cooldown;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

@Deprecated
/**
 * Deprecated for the DoubleJump system instead.
 */
public class JumpPad {

    private static HashMap<UUID, Cooldown> cooldown = new HashMap<>();

    public static void jump(Player p) {
        p.setVelocity(p.getLocation().getDirection().multiply(3.0D).setY(1));
        new Message().setSound(Sound.BAT_TAKEOFF).setPitch(1.0f, 1.5f).send(p);
    }

    public static class JumpPadListener implements Listener {

        @EventHandler
        public void listenForMove(final PlayerMoveEvent ev) {
            if(ev.getPlayer().isFlying()) return;
            if(cooldown.containsKey(ev.getPlayer().getUniqueId()))
                if(!cooldown.get(ev.getPlayer().getUniqueId()).done())
                    return;
            if(ev.getPlayer().getLocation().getBlock().getType() == Material.IRON_PLATE && ev.getPlayer().getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.REDSTONE_BLOCK) {
                cooldown.put(ev.getPlayer().getUniqueId(), new Cooldown(0.3)); //I hate to be the person who destroys fun, but it has to be this way ;-; (tl;dr damn you race conditions)
                //new Thread() is to run Thread.sleep() without interrupting the rest of the server
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(150);
                        } catch(InterruptedException e) {
                            //
                        }
                        jump(ev.getPlayer());
                    }
                }).start();
            }
        }

    }

}
