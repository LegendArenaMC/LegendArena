package legendarena.listeners

import legendarena.chat.ChatSystem
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

public class ChatListener : Listener {

    @EventHandler fun listenForChat(ev: AsyncPlayerChatEvent) {
        ev.setCancelled(true) //fuck it[tm] (and yes, this will eventually conflict with another plugin, but fuck it)
        ChatSystem.msg(ev.getPlayer(), ev.getMessage())
    }

}