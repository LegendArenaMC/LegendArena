package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.ChatUtils;
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
        ChatColor randomColour = ChatUtils.getRandomColour();
        ChatColor randomColour2 = ChatUtils.getRandomColour();
        if(randomColour == randomColour2)
            randomColour2 = ChatUtils.getRandomColour();
        ev.setMotd(randomColour + "Legend Arena " + ChatColor.YELLOW + "{" + MOTDRandomizer.getNotice().toUpperCase() + "}" + randomColour
                + "\n" + randomColour2 + MOTDRandomizer.getRandomMOTD());
    }

}
