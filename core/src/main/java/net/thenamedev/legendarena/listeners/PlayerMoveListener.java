package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendarena.utils.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class PlayerMoveListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(@NotNull PlayerMoveEvent ev) {
        if(!PluginUtils.frozenPlayers.contains(ev.getPlayer().getUniqueId()))
            return;
        //Hacky workaround to get players to not have their head twitch uncontrollably when they try to move when frozen (I have no idea why but that seems like a good troll idea)
        if(ev.getFrom().getX() == ev.getTo().getX() && ev.getFrom().getY() == ev.getTo().getY() && ev.getFrom().getZ() == ev.getTo().getZ())
            return;
        ev.getPlayer().teleport(ev.getFrom());
    }

}
