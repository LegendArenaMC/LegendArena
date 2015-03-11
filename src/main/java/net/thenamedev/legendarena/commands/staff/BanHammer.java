package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.banhammer.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

/**
 * @author TheNameMan
 */
public class BanHammer implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return true; //Do nothing if it's not a player
        }
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        ((Player) sender).getInventory().addItem(BanHammerManager.getBanHammerItem(sender.getName()));
        return true;
    }

}
