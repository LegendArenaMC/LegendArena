package legendarena.listeners

import legendapi.message.Message
import legendapi.utils.ChatUtils
import legendarena.motd.MOTDUtils
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerListPingEvent

public class ServerPingListener: Listener {

    EventHandler fun listenForPing(ev: ServerListPingEvent) {
        ev.setMotd(MOTDUtils.getBuiltMOTD())
    }

}