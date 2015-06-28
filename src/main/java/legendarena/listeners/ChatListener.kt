package legendarena.listeners

import legendarena.chat.ChatSystem
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener : Listener {

    EventHandler fun listenForChat(ev: AsyncPlayerChatEvent) {
        ev.setCancelled(true)
        ChatSystem.msg(ev.getPlayer(), ev.getMessage())
    }

}