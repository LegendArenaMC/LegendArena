package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendarena.extras.MOTDRandomizer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.text.MessageFormat;

/**
 * Server MOTD ping listener.
 *
 * @author ThePixelDev
 */
public class ServerPingListener implements Listener {

    @EventHandler
    public void onPingEvent(ServerListPingEvent ev) {
        String msg = MOTDRandomizer.randomize();
        ChatColor randomColour = ChatUtils.getRandomColour();
        ChatColor randomColour2 = ChatUtils.getRandomColour();
        if(randomColour == randomColour2)
            randomColour2 = ChatUtils.getRandomColour();
        ev.setMotd(MessageFormat.format("{0}Legend Arena {1}'{'{2}'}'{3}\n{4}//{5}{6}", randomColour, ChatColor.YELLOW, MOTDRandomizer.getNotice().toUpperCase(), randomColour, ChatColor.DARK_GRAY, randomColour2, MOTDRandomizer.getRandomMOTD()));
    }

}
