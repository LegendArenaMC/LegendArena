package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by thepixeldev on 4/26/15.
 */
public class CommandFilter implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent ev) {
        if(Rank.isRanked(ev.getPlayer(), Rank.ADMIN))
            return;
        String cmd = ev.getMessage().toLowerCase();
        if(cmd.startsWith("/pl ") || cmd.equals("/pl") || cmd.startsWith("/plugins ") || cmd.equals("/plugins") || cmd.startsWith("/bukkit:pl ") || cmd.equals("/bukkit:pl") || cmd.startsWith("/bukkit:plugins ") || cmd.equals("/bukkit:plugins")) {
            ev.setCancelled(true); //cancel the event
            ev.getPlayer().kickPlayer("I am an idiot for trying to view the plugins!"); //and kick them for being a giant douchebag and trying to use /pl, /plugins, et cetera
        }
    }

}
