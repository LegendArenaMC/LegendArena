package legendarena.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerListPingEvent

class ServerPingListener: Listener {

    @EventHandler fun listenForPing(ev: ServerListPingEvent) {
        ev.setMotd("Legend Arena Kotlin Version WIP")
    }

}