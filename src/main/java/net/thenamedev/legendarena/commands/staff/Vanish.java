package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Created on 3/9/2015
 *
 * @author ThePixelDev
 */
public class Vanish implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, Command label, String labelString, @NotNull String[] args) {
        if(!Rank.getRank(sender, Rank.VIP)) {
            Rank.noPermissions(sender, Rank.VIP);
            return true;
        }
        //
        return true;
    }

}
