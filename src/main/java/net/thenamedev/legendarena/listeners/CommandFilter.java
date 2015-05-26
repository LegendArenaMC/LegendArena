package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.message.Message;
import net.thenamedev.legendapi.message.MessageType;
import net.thenamedev.legendapi.utils.ChatUtils;
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
        if(isCmd(ev.getMessage().toLowerCase())) {
            ev.setCancelled(true); //cancel the event
            ev.getPlayer().kickPlayer("I am an idiot for trying to view the plugins!"); //and kick them for being a giant douchebag and trying to use /pl, /plugins, et cetera
        } else if(isCmd(ev.getMessage().toLowerCase(), "/me", "/bukkit:me")) {
            ev.setCancelled(true); //cancel the event
            ev.getPlayer().chat("I am a nubcake for trying to do /me!");
        } else if(isCmd(ev.getMessage().toLowerCase(), "/?", "/bukkit:?", "/bukkit:help")) {
            ev.setCancelled(true);
            new Message(MessageType.SUBTITLE).append(ChatUtils.getCustomMsg("Command Filter") + "Nice try...").send(ev.getPlayer());
        }
    }

    private boolean isCmd(String exec, String... cmd) {
        boolean is = false;
        for(String c : cmd) {
            if(exec.startsWith(c + " ")) {
                is = true;
                break;
            } else if(exec.equals(c)) {
                is = true;
                break;
            }
        }
        return is;
    }

}
