package net.thenamedev.legendarena.commands;

import net.thenamedev.legendapi.utils.PluginUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created on 3/19/2015
 *
 * @author ThePixelDev
 */
public class Help implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        sender.sendMessage(String.format("%sIt looks like you want help.\n \nMaybe you could ask a staff member?", PluginUtils.msgNormal));
        return true;
    }
    
    public static String getFormattedHelpMsg(String cmd, String desc) {
        return ChatColor.YELLOW + cmd + " " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD  + "//" + ChatColor.GREEN + " " + desc;
    }

    public static String getFormattedHeader(String header) {
        return ChatColor.LIGHT_PURPLE + "-•- [" + ChatColor.LIGHT_PURPLE + ChatColor.stripColor(header) + ChatColor.LIGHT_PURPLE + "] -•-";
    }

}
