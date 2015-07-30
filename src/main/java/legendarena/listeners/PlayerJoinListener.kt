package legendarena.listeners

import legendapi.fanciful.FancyMessage
import legendapi.message.Message
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendarena.chat.ChatSystem
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

public class PlayerJoinListener : Listener {

    EventHandler fun listenForJoin(ev: PlayerJoinEvent) {
        ev.setJoinMessage("")
        if(!ev.getPlayer().hasPlayedBefore()) {
            FancyMessage("Welcome to ")
                        .color(ChatColor.GREEN)
                    .then("Legend Arena")
                        .color(ChatColor.YELLOW)
                        .link("http://thenamedev.net/legendarena/")
                        .itemTooltip(MenuUtils.createItem(Material.PAPER,
                                "" + ChatColor.GREEN + "Click to open the Legend Arena site!"))
                    .then(", ")
                        .color(ChatColor.GREEN)
                    .then(ev.getPlayer().getName())
                        .color(RankUtils.getRank(ev.getPlayer()).getNameColor())
                    .then("!")
                        .color(ChatColor.GREEN)
                    .then("\n\n")
                    .then("As the server is still in alpha, expect bugs. (sorry about that by the way!)")
                        .color(ChatColor.YELLOW)
                    .send(ev.getPlayer())
        }
        if(ChatSystem.isShadowMuted(ev.getPlayer()))
            ChatSystem.notice("Player \"" + ev.getPlayer().getName() + "\" is shadow muted.")
    }

    EventHandler fun listenForQuit(ev: PlayerQuitEvent) {
        ev.setQuitMessage("")
    }

}