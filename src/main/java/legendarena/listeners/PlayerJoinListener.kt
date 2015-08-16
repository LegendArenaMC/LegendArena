package legendarena.listeners

import legendapi.fanciful.FancyMessage
import legendapi.message.Message
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendarena.chat.ChatSystem
import legendarena.hub.HubWarper
import legendarena.scoreboard.ScoreboardSystem
import legendarena.staffutils.VanishUtils
import legendarena.utils.ConfigUtils
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

public class PlayerJoinListener : Listener {

    EventHandler fun listenForJoin(ev: PlayerJoinEvent) {
        ev.setJoinMessage("")
        if(!ev.getPlayer().hasPlayedBefore())
            FancyMessage("Welcome to ")
                        .color(ChatColor.GREEN)
                    .then("Legend Arena")
                        .color(ChatColor.YELLOW)
                        .link("http://thenamedev.net/legendarena/")
                        .tooltip("" + ChatColor.GREEN + "Click to open the Legend Arena site")
                    .then(", ")
                        .color(ChatColor.GREEN)
                    .then(ev.getPlayer().getName())
                        .color(RankUtils.getRank(ev.getPlayer()).getNameColor())
                    .then("!")
                        .color(ChatColor.GREEN)
                    .then("\n\n")
                    //server soon to be in public beta, by the way!
                    //...well, that is, if I can be bothered to work on Never Have I Ever in the next two years.
                    .then("As the server is still in alpha, expect bugs. (sorry about that, by the way!)")
                        .color(ChatColor.YELLOW)
                    .send(ev.getPlayer())
        if(ChatSystem.isShadowMuted(ev.getPlayer()))
            ChatSystem.notice("Player " + ChatColor.YELLOW + ev.getPlayer().getName() + ChatColor.DARK_PURPLE + " is currently shadow muted.")

        VanishUtils.hideVanishedPlayersFrom(ev.getPlayer());

        if(ConfigUtils.config.get("enable.lobbyServer") as Boolean) {
            ev.getPlayer().getInventory().setItem(4, HubWarper.getMainMenu(ev.getPlayer().getName()))
            if(Rank.VIP.isRanked(ev.getPlayer())) {
                ev.getPlayer().setAllowFlight(true)
                ev.getPlayer().setFlying(true)
                if(ev.getPlayer().getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() != Material.AIR)
                    ev.getPlayer().teleport(ev.getPlayer().getLocation().add(0.0, 3.0, 0.0), PlayerTeleportEvent.TeleportCause.PLUGIN)
            }
        }

        ev.getPlayer().addPotionEffect(PotionEffect(PotionEffectType.SPEED, 1000000, 1, true, false))
    }

    EventHandler fun listenForQuit(ev: PlayerQuitEvent) {
        ev.setQuitMessage("")
    }

}