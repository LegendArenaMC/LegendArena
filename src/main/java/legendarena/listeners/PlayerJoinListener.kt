package legendarena.listeners

import legendarena.api.fanciful.FancyMessage
import legendarena.api.message.Message
import legendarena.api.user.User
import legendarena.api.utils.ChatUtils
import legendarena.api.utils.MenuUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.RankUtils
import legendarena.chat.ChatSystem
import legendarena.chat.Notification
import legendarena.hub.HubWarper
import legendarena.scoreboard.ScoreboardSystem
import legendarena.staffutils.VanishUtils
import legendarena.utils.ConfigUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PlayerJoinListener : Listener {

    @EventHandler fun listenForJoin(ev: PlayerJoinEvent) {
        ev.joinMessage = "" + ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + RankUtils.getRank(ev.player).getNameColor() + ev.player.name
        if(!ev.player.hasPlayedBefore())
            FancyMessage("Welcome to ")
                        .color(ChatColor.GREEN)
                    .then("Legend Arena")
                        .color(ChatColor.YELLOW)
                    .then(", ")
                        .color(ChatColor.GREEN)
                    .then(ev.player.name)
                        .color(RankUtils.getRank(ev.player).getNameColor())
                    .then("!")
                        .color(ChatColor.GREEN)
                    .then("\n\n")
                    //server soon to be in public beta, by the way!
                    //...well, that is, if I can be bothered to work on Never Have I Ever in the next two years.
                    .then("As the server is still in alpha, expect bugs. (sorry about that, by the way!)")
                        .color(ChatColor.YELLOW)
                    .send(ev.player)
        if(ChatSystem.isShadowMuted(ev.player))
            Notification.alert("Player " + ChatColor.YELLOW + ev.player.name + ChatColor.DARK_PURPLE + " is currently shadow muted.")

        VanishUtils.hideVanishedPlayersFrom(ev.player);

        ScoreboardSystem.setRank(ev.player, RankUtils.getDisplayRank(ev.player))

        if(ConfigUtils.config.get("enable.lobbyServer") as Boolean) {
            var needToAddSpeedEffect = true

            for(a in ev.player.activePotionEffects)
                if(a.type == PotionEffectType.SPEED) { //does the player have the speed potion effect active?
                    needToAddSpeedEffect = false
                    break //break out of the loop because we've found they do have the effect
                }

            if(needToAddSpeedEffect)
                ev.player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 1000000, 1, true, false))
            ev.player.inventory.setItem(4, HubWarper.getMainMenu(ev.player.name))
            if(Rank.VIP.isRanked(ev.player)) {
                ev.player.allowFlight = true
                ev.player.isFlying = true
                if(ev.player.location.subtract(0.0, 1.0, 0.0).block.type != Material.AIR)
                    ev.player.teleport(ev.player.location.add(0.0, 3.0, 0.0), PlayerTeleportEvent.TeleportCause.PLUGIN)
            }
        }
    }

    @EventHandler fun listenForQuit(ev: PlayerQuitEvent) {
        ev.quitMessage = "" + ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + User(ev.player).getRank().getNameColor() + ev.player.name
    }

}