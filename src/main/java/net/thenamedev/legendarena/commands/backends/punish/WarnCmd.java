package net.thenamedev.legendarena.commands.backends.punish;

import net.thenamedev.legendapi.punish.Warn;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author TheNameMan
 */
public class WarnCmd {

    @SuppressWarnings("deprecation")
    public static void run(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return; //Do nothing if it's not a player
        }
        if(args.length == 1)
            sender.sendMessage(ChatUtils.getCustomMsg("Punish") + "Usage: /punish warn <player> <reason>");
        else {
            if(Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage(ChatUtils.Messages.errorMsg + "The player \"" + args[1] + "\" wasn't found!"); //sanity check
                return;
            }

            if(args.length == 2)
                new Warn(Bukkit.getPlayer(args[1]), sender, "Rule violation").run(); //default warn reason
            else
                new Warn(Bukkit.getPlayer(args[1]), sender, ChatUtils.formatCast(args, 0, 1)).run(); //custom warn reason
        }
    }

}
