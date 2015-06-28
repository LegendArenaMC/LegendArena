package legendarena.listeners

import legendapi.message.Message
import legendapi.utils.ChatUtils
import legendarena.motd.MOTDUtils
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerListPingEvent

class ServerPingListener: Listener {

    EventHandler fun listenForPing(ev: ServerListPingEvent) {
        var random = MOTDUtils.getRandomMOTD()
        var randomC1: ChatColor = ChatUtils.getRandomColour()
        var randomC2: ChatColor = ChatUtils.getRandomColour()

        var built = Message().append(randomC1).toString() + "Legend Arena" + Message()
                        .append(ChatColor.YELLOW).toString() + " {" + MOTDUtils.getNotice() + "}\n" + Message()
                        .append(ChatColor.GRAY).toString() + "#" + Message()
                        .append(randomC2).toString() + random

        ev.setMotd(built)
    }

}