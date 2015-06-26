package legendarena.commands.backends;

import legendapi.message.Message;
import legendapi.utils.Cooldown;
import legendapi.utils.CooldownUtils;
import legendarena.chat.ChatSystem;
import legendapi.utils.ChatUtils;
import legendapi.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 * Global mute staff command.
 *
 * @author ThePixelDev
 */
public class GlobalMute {

    private static Cooldown c = null;

    public static void run(CommandSender sender) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return; //Do nothing if it's not a player
        }
        if(!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD));
            return;
        }
        if(c != null && !c.done()) {
            new Message().append(MessageFormat.format("{0} (this is a GLOBAL cooldown!)", c.getTimeRemaining()));
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
        c = CooldownUtils.getCooldown(5);
    }

}
