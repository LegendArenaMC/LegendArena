package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendarena.commands.Help;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created on 5/24/2015
 *
 * @author ThePixelDev
 */
public class Punish implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(Help.getFormattedHelpMsg("/punish <action> <player> [additional args]", "Runs a punishment action against a player."));
        }
        return true;
    }

}
