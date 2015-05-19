package net.thenamedev.legendarena.commands.backends.staff;

import net.thenamedev.legendapi.core.chat.ChatSystem;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author TheNameMan
 */
public class GlobalMute {

    public static void run(CommandSender sender) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return; //Do nothing if it's not a player
        }
        if(!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD));
            return;
        }
        if(ChatSystem.isChatMuted()) {
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(ChatColor.BLUE + "Staff member " + ChatUtils.getFormattedName((Player) sender) + ChatColor.BLUE + " has lifted the global mute.");
            ChatUtils.broadcast(" ");
            ChatSystem.setChatMuted(false);
        } else {
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(ChatColor.BLUE + "Staff member " + ChatUtils.getFormattedName((Player) sender) + ChatColor.BLUE + " has globally muted the chat.");
            ChatUtils.broadcast(" ");
            ChatSystem.setChatMuted(true);
        }
    }

}
