package legendarena.listeners;

import legendapi.utils.ChatUtils;
import legendarena.utils.MOTDUtils;
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
        String msg = MOTDUtils.getRandomMOTD();
        ChatColor randomColour = ChatUtils.getRandomColour();
        ChatColor randomColour2 = ChatUtils.getRandomColour();
        if(randomColour == randomColour2)
            while(randomColour == randomColour2)
                randomColour = ChatUtils.getRandomColour(); //thanks one if() pass for not doing it
        ev.setMotd(MessageFormat.format("{0}Legend Arena {1}'{'{2}'}'{3}\n{4}//{5}{6}", randomColour, ChatColor.YELLOW, MOTDUtils.getNotice().toUpperCase(), randomColour, ChatColor.DARK_GRAY, randomColour2, MOTDUtils.getRandomMOTD()));
    }

}
