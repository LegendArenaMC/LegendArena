package net.thenamedev.legendarena.listeners;import net.thenamedev.legendarena.core.*;import net.thenamedev.legendarena.staffchat.*;import net.thenamedev.legendarena.utils.*;import org.bukkit.*;import org.bukkit.entity.*;import org.bukkit.event.*;import org.bukkit.event.player.*;/** * @author TheNameMan */public class PlayerJoinListener implements Listener {    @EventHandler(priority = EventPriority.HIGHEST)    public void playerJoin(PlayerJoinEvent ev) {        ev.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + Rank.getFormattedName(ev.getPlayer()));        if(!ev.getPlayer().hasPlayedBefore()) {            ev.getPlayer().sendMessage(PluginUtils.msgNormal + "Welcome to Legend Arena, " + ev.getPlayer().getPlayerListName() + ". Do " + ChatColor.DARK_PURPLE + "/warp" + ChatColor.GREEN + " to get around the server.");            ChatUtils.shootFireworks(ev.getPlayer());            ChatUtils.shootFireworks(ev.getPlayer());        }    }    @EventHandler(priority = EventPriority.HIGH)    public void playerLeave(PlayerQuitEvent ev) {        Player p = ev.getPlayer();        ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + Rank.getFormattedName(ev.getPlayer()));        if(SCType.getType(ev.getPlayer().getUniqueId()) != null)            SCType.clearStaffchat(ev.getPlayer().getUniqueId());    }    @EventHandler(priority = EventPriority.HIGHEST)    public void playerLogin(PlayerLoginEvent ev) {        Player p = ev.getPlayer();        if(ev.getPlayer().getName().contains(" ")) {            ev.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Invalid username. (try changing your name to something without a space?)"); //filter them out due to their name having a space in it, to keep them from bypassing commands (somehow)            StaffChat.notice(ChatColor.RED + "Player \"" + p.getName() + "\" tried to join with an invalid name (space in name).", "Join Filter"); //let online staff now that it was filtered        }    }}