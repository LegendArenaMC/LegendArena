package legendarena.listeners;

import legendapi.message.Message;
import legendapi.message.MessageType;
import legendapi.utils.ChatUtils;
import legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Command filter.
 *
 * @author ThePixelDev
 */
public class CommandFilterListener implements Listener {

    @EventHandler
    public void onCommand(final PlayerCommandPreprocessEvent ev) {
        if(Rank.isRanked(ev.getPlayer(), Rank.ADMIN))
            return;
        String cmd = ev.getMessage().toLowerCase();
        if(isCmd(ev.getMessage().toLowerCase())) {
            ev.setCancelled(true); //cancel the event
            ev.getPlayer().kickPlayer("I am an idiot for trying to view the plugins!"); //and kick them for being a giant douchebag and trying to use /pl, /plugins, et cetera
        } else if(isCmd(ev.getMessage().toLowerCase(), "/me", "/bukkit:me")) {
            ev.setCancelled(true); //cancel the event
        } else if(isCmd(ev.getMessage().toLowerCase(), "/?", "/bukkit:?", "/bukkit:help")) {
            ev.setCancelled(true);
            new Message(MessageType.SUBTITLE).append(ChatUtils.getCustomMsg("Command Filter") + "Nice try...").send(ev.getPlayer());
        } else if(isCmd(ev.getMessage().toLowerCase(), "/op", "/bukkit:op", "/deop", "/bukkit:deop")) {
            if(Rank.isRanked(ev.getPlayer(), Rank.FOUNDER))
                return;
            ev.setCancelled(true); //cancel the event
            new Message(MessageType.TITLE).append("Nice try. It won't work here, bud.").send(Sound.BLAZE_DEATH, 3, ev.getPlayer()); //tell the player it isn't gonna work here
            new Message().append(ChatUtils.getCustomMsg("Command Filter") + "The player " + ChatColor.RED + ev.getPlayer().getName() + ChatColor.BLUE + " tried to use /op or /deop!").broadcast();
            ev.getPlayer().chat("I am a nubcake. Please ban me.");
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                @Override
                public void run() {
                    ev.getPlayer().kickPlayer("Bye!");
                    //TODO: Add ban code
                }
            }, 80l);
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
