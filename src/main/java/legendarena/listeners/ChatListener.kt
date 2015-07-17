package legendarena.listeners

import legendarena.chat.ChatSystemOld
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener : Listener {

    EventHandler fun listenForChat(ev: AsyncPlayerChatEvent) {
        ev.setCancelled(true)
        ChatSystemOld.msg(ev.getPlayer(), ev.getMessage())
    }

}