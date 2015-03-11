package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created on 3/9/2015
 *
 * @author ThePixelDev
 */
public class Vanish implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command label, String labelString, String[] args) {
        if(!Rank.getRank(sender, Rank.VIP)) {
            Rank.noPermissions(sender, Rank.VIP);
            return true;
        }
        //
        return true;
    }

}
