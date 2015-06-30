package legendarena.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerJoinListener : Listener {

    EventHandler fun listenForJoin(ev: PlayerJoinEvent) {
        ev.setJoinMessage("")

    }

    EventHandler fun listenForQuit(ev: PlayerQuitEvent) {
        ev.setQuitMessage("")
    }

}