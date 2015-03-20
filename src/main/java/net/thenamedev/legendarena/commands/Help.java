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
        sender.sendMessage(PluginUtils.msgNormal + "It looks like you want help.\nMaybe you could ask an admin?");
        return true;
    }
    
    public static String getFormattedHelpMsg(String cmd, String desc) {
        return ChatColor.YELLOW + cmd + " " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + " " + desc;
    }

}
