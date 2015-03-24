package net.thenamedev.legendarena.listeners;import net.thenamedev.legendapi.utils.ChatUtils;import net.thenamedev.legendapi.utils.OldRank;import net.thenamedev.legendapi.utils.Rank;import net.thenamedev.legendarena.commands.backends.Vanish;import net.thenamedev.legendarena.extras.staffchat.SCType;import net.thenamedev.legendarena.extras.staffchat.StaffChat;import net.thenamedev.legendarena.utils.ScoreboardUtils;import org.bukkit.Bukkit;import org.bukkit.ChatColor;import org.bukkit.entity.Player;import org.bukkit.event.EventHandler;import org.bukkit.event.EventPriority;import org.bukkit.event.Listener;import org.bukkit.event.player.PlayerJoinEvent;import org.bukkit.event.player.PlayerLoginEvent;import org.bukkit.event.player.PlayerLoginEvent.Result;import org.bukkit.event.player.PlayerQuitEvent;import java.util.UUID;/** * @author ThePixelDev */public class PlayerJoinListener implements Listener {    @EventHandler(priority = EventPriority.HIGHEST)    public void playerJoin(PlayerJoinEvent ev) {        ev.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + Rank.getFormattedName(ev.getPlayer()));        if(!ev.getPlayer().hasPlayedBefore()) {            ChatUtils.shootFireworks(ev.getPlayer());            ChatUtils.shootFireworks(ev.getPlayer());        }        for(UUID p : Vanish.vanishedPlayers) {            Player h = Bukkit.getPlayer(p);            if(h == null)                continue;            ev.getPlayer().hidePlayer(h);        }        ScoreboardUtils.addPlayerToTeam(ScoreboardUtils.getScoreboardTeam(ev.getPlayer()), ev.getPlayer());    }    @EventHandler(priority = EventPriority.HIGH)    public void playerLeave(PlayerQuitEvent ev) {        ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + Rank.getFormattedName(ev.getPlayer()));        SCType.clearStaffchat(ev.getPlayer().getUniqueId());    }    @EventHandler(priority = EventPriority.HIGHEST)    public void playerLogin(PlayerLoginEvent ev) {        Player p = ev.getPlayer();        if(ev.getPlayer().getName().contains(" ")) {            ev.disallow(Result.KICK_OTHER, "Invalid username. (try changing your name to something without a space?)"); //filter them out due to their name having a space in it, to keep them from bypassing commands (somehow)            StaffChat.notice(ChatColor.RED + "Player \"" + p.getName() + "\" tried to join with an invalid name (tripped filter: space in name).", "Join Filter"); //let online staff now that it was filtered        }        //    }}