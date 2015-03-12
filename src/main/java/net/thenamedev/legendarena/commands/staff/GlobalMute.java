package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.LegendArena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author TheNameMan
 */
public class GlobalMute implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return true; //Do nothing if it's not a player
        }
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(LegendArena.isChatMuted()) {
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(ChatColor.BLUE + "Staff member " + Rank.getFormattedName((Player) sender) + ChatColor.BLUE + " has lifted the global mute.");
            ChatUtils.broadcast(" ");
            LegendArena.toggleChatMute();
        } else {
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(ChatColor.BLUE + "Staff member " + Rank.getFormattedName((Player) sender) + ChatColor.BLUE + " has globally muted the chat.");
            ChatUtils.broadcast(" ");
            LegendArena.toggleChatMute();
        }
        return true;
    }

}
