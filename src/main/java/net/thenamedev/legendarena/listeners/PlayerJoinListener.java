package net.thenamedev.legendarena.listeners;import net.thenamedev.legendapi.core.chat.ChatSystem;import net.thenamedev.legendapi.core.emeralds.EmeraldPlayer;import net.thenamedev.legendapi.utils.ChatUtils;import org.bukkit.Bukkit;import org.bukkit.ChatColor;import org.bukkit.event.EventHandler;import org.bukkit.event.EventPriority;import org.bukkit.event.Listener;import org.bukkit.event.player.PlayerJoinEvent;import org.bukkit.event.player.PlayerQuitEvent;/** * Join/quit listener. * * @author ThePixelDev */public class PlayerJoinListener implements Listener {    @EventHandler(priority = EventPriority.HIGHEST)    public void playerJoin(PlayerJoinEvent ev) {        if(!ev.getPlayer().hasPlayedBefore()) {            //fix iConomy giving 30 emeralds to players on first join/etc            if(Bukkit.getPluginManager().getPlugin("iConomy") != null) {                new EmeraldPlayer(ev.getPlayer()).reset();                Bukkit.getLogger().info("(Hopefully) fixed emeralds for " + ev.getPlayer().getName() + ".");            }        }        ev.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatUtils.getFormattedName(ev.getPlayer()));        //TODO: Sidebar of magically magic things (...what? oh admit it, you've had to come up with a dumb TODO like this before too)        if(ev.getPlayer().getName().equalsIgnoreCase("SkyTheKidRS")) {            //if this ever gets ran, well - i'll be damned            if(Bukkit.getPlayer("ThePixelDev") != null)                Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {                    public void run() {                        Bukkit.getPlayer("ThePixelDev").chat("inb4omgitssky");                    }                }, 80l);        }    }    @EventHandler(priority = EventPriority.HIGH)    public void playerLeave(PlayerQuitEvent ev) {        ev.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatUtils.getFormattedName(ev.getPlayer()));        ChatSystem.remove(ev.getPlayer());    }}