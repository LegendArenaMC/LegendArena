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
        ev.setCancelled(true);
    }

}
