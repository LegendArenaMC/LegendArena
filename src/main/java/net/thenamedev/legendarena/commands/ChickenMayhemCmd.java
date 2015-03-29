package net.thenamedev.legendarena.commands;

import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.minigame.chickenmayhem.ChickenMayhem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 3/28/15
 *
 * @author ThePixelDev
 */
public class ChickenMayhemCmd implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Help.getFormattedHeader("Chicken Mayhem"));
            sender.sendMessage(Help.getFormattedHelpMsg("/chickenmayhem join", "Join a game of Chicken Mayhem"));
            sender.sendMessage(Help.getFormattedHelpMsg("/chickenmayhem quit", "Quit the Chicken Mayhem game"));
        } else {
            if(args[0].equalsIgnoreCase("join")) {
                ChickenMayhem.join((Player) sender);
            } else if(args[0].equalsIgnoreCase("quit")) {
                try {
                    ChickenMayhem.quit((Player) sender);
                } catch(MistakesWereMadeException ex) {
                    sender.sendMessage(ChatColor.RED + "We couldn't successfully remove you from the minigame :(");
                }
            } else if(args[0].equalsIgnoreCase("start")) {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN));
                    return true;
                }
                if(!ChickenMayhem.isRunning()) {
                    ChickenMayhem.start((Player) sender);
                }
            } else if(args[0].equalsIgnoreCase("stop")) {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN));
                    return true;
                }
                if(ChickenMayhem.isRunning()) {
                    ChickenMayhem.end((Player) sender);
                }
            }
        }
        return true;
    }

}
