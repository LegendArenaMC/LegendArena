package legendarena.commands.staff;

import legendapi.utils.ChatUtils;
import legendapi.utils.Cooldown;
import legendapi.utils.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Fly command of stuff.
 *
 * @author ThePixelDev
 */
public class Fly implements CommandExecutor {

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
            sender.sendMessage(ChatUtils.Messages.getCustomMsg("Flight") + "Disabled flight!");
            ((Player) sender).setAllowFlight(false);
            ((Player) sender).setFlying(false);
        } else {
            sender.sendMessage(ChatUtils.Messages.getCustomMsg("Flight") + "Enabled flight!");
            ((Player) sender).setAllowFlight(true);
        }
        //3 second cooldown
        cooldown.put(((Player) sender).getUniqueId(), new Cooldown(3));
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        run(commandSender);
        return true;
    }

}
