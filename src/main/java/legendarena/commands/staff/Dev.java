package legendarena.commands.staff;

import legendarena.api.utils.ChatUtils;
import legendarena.api.utils.Day;
import legendarena.api.utils.Rank;
import legendarena.hub.particles.ParticleCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            sender.sendMessage(ChatColor.BLUE + "Day: " + Day.getDate().getDay());
            sender.sendMessage(ChatColor.BLUE + "Month: " + Day.getDate().getMonth() + " (" + Day.parseMonth() + ")");
            sender.sendMessage(ChatColor.YELLOW + "--.{ Server Info }.--");
            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.YELLOW + "--.{ Debug Info }.--");
            sender.sendMessage(ChatColor.BLUE + "Particle amount: " + ParticleCore.amountOfActiveParticles((Player) sender));
            sender.sendMessage(ChatColor.YELLOW + "--.{ Debug Info }.--");
            return true;
        }

        if(args[0].equalsIgnoreCase("testranks")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER))
                return false;
            sender.sendMessage(ChatUtils.Messages.debugMsg + "FOUNDER: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.FOUNDER));
            sender.sendMessage(ChatUtils.Messages.debugMsg + "ADMIN: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.ADMIN));
            sender.sendMessage(ChatUtils.Messages.debugMsg + "SRMOD: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.SRMOD));
            sender.sendMessage(ChatUtils.Messages.debugMsg + "MOD: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.MOD));
            sender.sendMessage(ChatUtils.Messages.debugMsg + "HELPER: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.HELPER));
            sender.sendMessage(ChatUtils.Messages.debugMsg + "YOUTUBE: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.YOUTUBE));
            sender.sendMessage(ChatUtils.Messages.debugMsg + "MEMBER: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.MEMBER));
            return true;
        }
        return false;
    }
}
