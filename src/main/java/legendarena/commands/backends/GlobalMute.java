package legendarena.commands.backends;

import legendarena.chat.ChatSystem;
import legendarena.api.utils.ChatUtils;
import legendarena.api.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Global mute staff command.
 *
 * @author ThePixelDev
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
            ChatUtils.broadcast(ChatUtils.getCustomMsg("Chat Management") + "Staff member " + ChatColor.YELLOW + sender.getName() + ChatColor.BLUE + " has lifted the global mute.");
            ChatUtils.broadcast(" ");
            ChatSystem.setChatMuted(false);
        } else {
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(ChatUtils.getCustomMsg("Chat Management") + "Staff member " + ChatColor.YELLOW + sender.getName() + ChatColor.BLUE + " has globally muted the chat.");
            ChatUtils.broadcast(" ");
            ChatSystem.setChatMuted(true);
        }
    }

}
