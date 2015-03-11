package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.motd.*;
import org.bukkit.ChatColor;
import org.bukkit.event.*;
import org.bukkit.event.server.*;
import org.jetbrains.annotations.*;

/**
 * @author ThePixelDev
 */
public class ServerPingListener implements Listener {

    @EventHandler
    public void onPingEvent(@NotNull ServerListPingEvent ev) {
        String msg = MOTDRandomizer.randomize();
        ev.setMotd(String.format("%s%s %s", PluginUtils.msgNormal, msg, ChatColor.YELLOW + "{PUBLIC ALPHA}"));
    }

}
