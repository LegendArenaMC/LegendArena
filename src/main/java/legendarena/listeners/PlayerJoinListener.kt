package legendarena.listeners

import legendapi.message.Message
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerJoinListener : Listener {

    EventHandler fun listenForJoin(ev: PlayerJoinEvent) {
        ev.setJoinMessage("")
        if(!ev.getPlayer().hasPlayedBefore()) {
            Message().append("" + ChatColor.GREEN + "Welcome to Legend Arena, " + ev.getPlayer().getName() + "!").send(ev.getPlayer())
            Message().append("" + ChatColor.GREEN + "Legend Arena is a work-in-progress minigames server. For now, as there are no functioning minigames (sorry! we're working on it!), you can hang out in the hub and talk to others.").send(ev.getPlayer())
            Message().append("" + ChatColor.GREEN + "If you wish to help us get the minigames finished faster, our plugins are all open-source: " + ChatColor.YELLOW + "https://github.com/LegendArenaMC").send(ev.getPlayer())
        }
    }

    EventHandler fun listenForQuit(ev: PlayerQuitEvent) {
        ev.setQuitMessage("")
    }

}