package legendarena.listeners;import legendarena.chat.ChatSystem;import legendarena.emeralds.EmeraldPlayer;import legendarena.api.utils.ChatUtils;import org.bukkit.Bukkit;import org.bukkit.ChatColor;import org.bukkit.event.EventHandler;import org.bukkit.event.EventPriority;import org.bukkit.event.Listener;import org.bukkit.event.player.PlayerJoinEvent;import org.bukkit.event.player.PlayerQuitEvent;/** * Join/quit listener. * * @author ThePixelDev */public class PlayerJoinListener implements Listener {    @EventHandler(priority = EventPriority.HIGHEST)    public void playerJoin(PlayerJoinEvent ev) {        if(!ev.getPlayer().hasPlayedBefore()) {            //fix iConomy giving 30 emeralds to players on first join/etc            if(Bukkit.getPluginManager().getPlugin("iConomy") != null) {                new EmeraldPlayer(ev.getPlayer()).reset();                Bukkit.getLogger().info("(Hopefully) fixed emeralds for " + ev.getPlayer().getName() + ". #BlameiConomy");            }        }        ev.setJoinMessage("");    }    @EventHandler(priority = EventPriority.HIGHEST)    public void playerLeave(PlayerQuitEvent ev) {        ev.setQuitMessage("");        ChatSystem.remove(ev.getPlayer());    }}