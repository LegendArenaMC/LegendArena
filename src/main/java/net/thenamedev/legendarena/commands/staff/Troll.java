package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created on 4/4/2015
 *
 * @author ThePixelDev
 */
public class Troll implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.isRanked(sender, Rank.SRMOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.SRMOD));
            return true;
        }
        //
        return true;
    }
}
