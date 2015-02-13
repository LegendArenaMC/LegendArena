package me.thenameman.legendarena.commands;

import me.thenameman.legendarena.core.*;
import me.thenameman.legendarena.staffchat.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

/**
 * @author TheNameMan
 */
public class Debug implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.Dev)) {
            sender.sendMessage("Unknown command. Type \"/help\" for help."); // Pretend the command wasn't found
            return true;
        }
        if(!(sender instanceof Player)) {
            return true;
        }
        if(LegendArena.debugSwitch) {
            LegendArena.debugSwitch = false;
            StaffChat.notice(ChatColor.RED + Rank.getFormattedName((Player) sender) + ChatColor.RED + " has DISABLED debug mode.", "Debug Utils");
        } else {
            LegendArena.debugSwitch = true;
            StaffChat.notice(ChatColor.RED + Rank.getFormattedName((Player) sender) + ChatColor.RED + " has ENABLED debug mode.", "Debug Utils");
        }
        return true;
    }

}
