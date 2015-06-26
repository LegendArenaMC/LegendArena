package legendarena.commands.staff;

import legendapi.utils.CalendarUtils;
import legendapi.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Developer utilities command.
 *
 * @author ThePixelDev
 */
public class Dev implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0) {
            if(!Rank.isRanked(sender, Rank.FOUNDER))
                return false;
            sender.sendMessage(ChatColor.YELLOW + "--.{ Server Info }.--");
            sender.sendMessage(ChatColor.BLUE + "Free memory: " + Runtime.getRuntime().freeMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Used memory: " + Runtime.getRuntime().totalMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Max memory: " + Runtime.getRuntime().maxMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Day: " + CalendarUtils.getDate().getDay());
            sender.sendMessage(ChatColor.BLUE + "Month: " + CalendarUtils.getDate().getMonth() + " (" + CalendarUtils.parseMonth() + ")");
            sender.sendMessage(ChatColor.YELLOW + "--.{ Server Info }.--");
            //sender.sendMessage(" ");
            //sender.sendMessage(ChatColor.YELLOW + "--.{ Debug Info }.--");
            //sender.sendMessage(ChatColor.BLUE + "Particle amount: " + ParticleCore.amountOfActiveParticles((Player) sender));
            //sender.sendMessage(ChatColor.YELLOW + "--.{ Debug Info }.--");
            return true;
        }
        return false;
    }
}
