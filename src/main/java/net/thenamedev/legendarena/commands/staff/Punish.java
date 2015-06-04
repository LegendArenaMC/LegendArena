package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.commands.Help;
import net.thenamedev.legendarena.commands.backends.punish.ShameCage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
//import net.thenamedev.legendarena.commands.backends.punish.KickCmd;

/**
 * Punish command.
 *
 * @author ThePixelDev
 */
public class Punish implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD));
            return true;
        }
        if(args.length == 0) {
            help(sender);
        } else {
            if(args[0].equalsIgnoreCase("ban")) {
                //
            } else if(args[0].equalsIgnoreCase("kick")) {
                //KickCmd
            } else if(args[0].equalsIgnoreCase("shamecage")) {
                if(args.length == 1) {
                    sender.sendMessage(Help.getFormattedHelpMsg("/punish shamecage <player>", "Places a player in a cage of shame."));
                    return true;
                }
                if(Bukkit.getPlayer(args[1]) == null) {
                    sender.sendMessage(ChatUtils.Messages.errorMsg + "The player \"" + args[1] + "\" was not found.");
                    return true;
                }
                ShameCage.run(sender, Bukkit.getPlayer(args[1]));
            }
        }
        return true;
    }

    private void help(CommandSender sender) {
        sender.sendMessage(Help.getFormattedHeader("Punish Actions"));
        sender.sendMessage(Help.getFormattedHelpMsg("/punish ban <player> <reason>", "Bans a player."));
        sender.sendMessage(Help.getFormattedHelpMsg("/punish kick <player> <reason>", "Kicks a player."));
        sender.sendMessage(Help.getFormattedHelpMsg("/punish shamecage <player>", "Places a player in a cage of shame."));
    }

}
