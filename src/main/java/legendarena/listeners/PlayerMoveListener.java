package legendarena.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Player move listener.
 *
 * @author ThePixelDev
 */
public class PlayerMoveListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent ev) {
        /*if(!Freeze.frozenPlayers.contains(ev.getPlayer().getUniqueId()))
            return;
        //Hacky workaround to get players to not have their head twitch uncontrollably when they try to move it when frozen (I have no idea why but that seems like a good troll idea for horrible people (e.g. hackers, users with stupidly innapropriate names, etc))
        if(ev.getFrom().getX() == ev.getTo().getX() && ev.getFrom().getY() == ev.getTo().getY() && ev.getFrom().getZ() == ev.getTo().getZ())
            return;
        ev.getPlayer().teleport(ev.getFrom());*/
    }

}
