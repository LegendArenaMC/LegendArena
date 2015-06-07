package legendarena.commands;

import legendarena.api.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Help command of stuff.
 *
 * @author ThePixelDev
 */
public class Help implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        sender.sendMessage(String.format("%sIt looks like you want help.\n \nMaybe you could ask an admin?", ChatUtils.getCustomMsg("Extras")));
        return true;
    }
    
    public static String getFormattedHelpMsg(String cmd, String desc) {
        return String.format("%s%s %s%s//%s %s", ChatColor.YELLOW, cmd, ChatColor.DARK_GRAY, ChatColor.BOLD, ChatColor.GREEN, desc);
    }

    public static String getFormattedHeader(String header) {
        return String.format("%s-•- [%s%s%s] -•-", ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.stripColor(header), ChatColor.LIGHT_PURPLE);
    }

}
