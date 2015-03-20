package net.thenamedev.legendarena.commands.backends;

import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.banhammer.BanHammerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author TheNameMan
 */
public class BanHammer {

    public static void run(CommandSender sender) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return; //Do nothing if it's not a player
        }
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return;
        }
        ((Player) sender).getInventory().addItem(BanHammerManager.getBanHammerItem(sender.getName()));
    }

}
