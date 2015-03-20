package net.thenamedev.legendarena.commands.backends;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.LegendArena;
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
        if(!Rank.getRank(sender, Rank.Mod)) {
            sender.sendMessage(Rank.noPermissions(Rank.Mod));
            return;
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
    }

}
