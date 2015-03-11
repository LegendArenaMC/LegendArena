package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendarena.extras.motd.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.server.*;

/**
 * @author ThePixelDev
 */
public class ServerPingListener implements Listener {

    @EventHandler
    public void onPingEvent(ServerListPingEvent ev) {
        String msg = MOTDRandomizer.randomize();
        ev.setMotd(String.format("%s%s %s", PluginUtils.msgNormal, msg, ChatColor.YELLOW + "{PUBLIC ALPHA}"));
    }

}
