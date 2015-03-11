package net.thenamedev.legendarena.listeners;import com.connorlinfoot.titleapi.*;import net.thenamedev.legendapi.LegendAPI;import net.thenamedev.legendapi.utils.*;import net.thenamedev.legendarena.extras.staffchat.*;import net.thenamedev.legendarena.utils.*;import org.bukkit.*;import org.bukkit.entity.*;import org.bukkit.event.*;import org.bukkit.event.player.*;import org.bukkit.event.player.PlayerLoginEvent.*;/** * @author ThePixelDev */public class PlayerJoinListener implements Listener {    @EventHandler(priority = EventPriority.HIGHEST)    public void playerJoin(PlayerJoinEvent ev) {        ev.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + Rank.getFormattedName(ev.getPlayer()));        if(!ev.getPlayer().hasPlayedBefore()) {            ChatUtils.shootFireworks(ev.getPlayer());            ChatUtils.shootFireworks(ev.getPlayer());        }        if(!ev.getPlayer().hasPlayedBefore() || LegendAPI.debug)            TitleAPI.sendTitle(ev.getPlayer(), 10, 40, 10, "§cWelcome to Legend Arena,", "§a" + ev.getPlayer().getName() + " (fyi: you only see this on the first join)");        TitleAPI.sendTabTitle(ev.getPlayer(), ChatColor.DARK_GREEN + "Legend Arena", ChatColor.GREEN + "IP: " + ChatColor.DARK_PURPLE + "mc.thenamedev.net");        ScoreboardUtils.addPlayerToTeam(ScoreboardUtils.getScoreboardTeam(ev.getPlayer()), ev.getPlayer());    }    @EventHandler(priority = EventPriority.HIGH)    public void playerLeave(PlayerQuitEvent ev) {        ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + Rank.getFormattedName(ev.getPlayer()));        SCType.clearStaffchat(ev.getPlayer().getUniqueId());    }    @EventHandler(priority = EventPriority.HIGHEST)    public void playerLogin(PlayerLoginEvent ev) {        Player p = ev.getPlayer();        if(ev.getPlayer().getName().contains(" ")) {            ev.disallow(Result.KICK_OTHER, "Invalid username. (try changing your name to something without a space?)"); //filter them out due to their name having a space in it, to keep them from bypassing commands (somehow)            StaffChat.notice(ChatColor.RED + "Player \"" + p.getName() + "\" tried to join with an invalid name (space in name).", "Join Filter"); //let online staff now that it was filtered        }    }}