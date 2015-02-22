package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.hub.motd.*;
import org.bukkit.event.*;
import org.bukkit.event.server.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class ServerPingListener implements Listener {

    @EventHandler
    public void onPingEvent(@NotNull ServerListPingEvent ev) {
        String msg = MOTDRandomizer.randomize();
        ev.setMotd(PluginUtils.msgNormal + msg);
        //
    }

}
