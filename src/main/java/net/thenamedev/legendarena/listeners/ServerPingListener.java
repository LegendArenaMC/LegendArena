package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendarena.extras.MOTDRandomizer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * @author ThePixelDev
 */
public class ServerPingListener implements Listener {

    @EventHandler
    public void onPingEvent(ServerListPingEvent ev) {
        String msg = MOTDRandomizer.randomize();
        ev.setMotd(String.format("%s%s %s", PluginUtils.msgNormal, msg, ChatColor.YELLOW + "{" + MOTDRandomizer.getNotice().toUpperCase() + "}"));
    }

}
