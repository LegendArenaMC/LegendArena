package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.motd.*;
import org.bukkit.ChatColor;
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
        ev.setMotd(String.format("%s%s\n%s", PluginUtils.msgNormal, msg, (ChatUtils.getNotice() == null ? "" : ChatUtils.getNotice())));
    }

}
