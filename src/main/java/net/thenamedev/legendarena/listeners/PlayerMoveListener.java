package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendarena.commands.staff.Freeze;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author ThePixelDev
 */
public class PlayerMoveListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent ev) {
        if(!Freeze.frozenPlayers.contains(ev.getPlayer().getUniqueId()))
            return;
        //Hacky workaround to get players to not have their head twitch uncontrollably when they try to move when frozen (I have no idea why but that seems like a good troll idea)
        if(ev.getFrom().getX() == ev.getTo().getX() && ev.getFrom().getY() == ev.getTo().getY() && ev.getFrom().getZ() == ev.getTo().getZ())
            return;
        ev.getPlayer().teleport(ev.getFrom());
    }

}
