package net.thenamedev.legendarena.listeners;

//import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by thepixeldev on 4/26/15.
 */
public class CommandFilter implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent ev) {
        //if(Rank.isRanked(ev.getPlayer(), Rank.ADMIN))
            //return;
        String cmd = ev.getMessage().toLowerCase();
        if(cmd.startsWith("/pl ") || cmd.equals("/pl") || cmd.startsWith("/plugins ") || cmd.equals("/plugins")) {
            ev.setCancelled(true);
            ev.getPlayer().chat("I am an idiot for trying to view the plugins!");
            ev.getPlayer().kickPlayer("I am an idiot for trying to view the plugins!");
        }
    }

}
