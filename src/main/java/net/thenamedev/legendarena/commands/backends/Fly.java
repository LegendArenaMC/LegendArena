package net.thenamedev.legendarena.commands.backends;

import net.thenamedev.legendapi.utils.Cooldown;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author TheNameMan
 */
public class Fly {

    private static HashMap<UUID, Cooldown> cooldown = new HashMap<>();

    public static void run(CommandSender sender) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return; //Do nothing if it's not a player
        }
        if(!Rank.isRanked(sender, Rank.HELPER)) {
            sender.sendMessage(Rank.noPermissions(Rank.HELPER));
            return;
        }
        if(cooldown.containsKey(((Player) sender).getUniqueId()) && !cooldown.get(((Player) sender).getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(((Player) sender).getUniqueId()).getTimeRemaining());
            return;
        }
        if(((Player) sender).getAllowFlight()) {
            sender.sendMessage(PluginUtils.msgNormal + "Disabled flight!");
            ((Player) sender).setAllowFlight(false);
            ((Player) sender).setFlying(false);
        } else {
            sender.sendMessage(PluginUtils.msgNormal + "Enabled flight!");
            ((Player) sender).setAllowFlight(true);
        }
        //3 second cooldown
        cooldown.put(((Player) sender).getUniqueId(), new Cooldown(3));
    }

}
