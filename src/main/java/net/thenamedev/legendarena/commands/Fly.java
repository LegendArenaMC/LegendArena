package net.thenamedev.legendarena.commands;

import net.thenamedev.legendapi.utils.Cooldown;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author TheNameMan
 */
public class Fly implements CommandExecutor {

    private HashMap<UUID, Cooldown> cooldown = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return true; //Do nothing if it's not a player
        }
        if(!Rank.getRank(sender, Rank.VIP)) {
            Rank.noPermissions(sender, Rank.VIP);
            return true;
        }
        if(Rank.getRank(sender) == Rank.VIP && !((Player) sender).getWorld().getName().equalsIgnoreCase("hub")) {
            sender.sendMessage(PluginUtils.msgWarning + "You can only do this in the hub!");
            ((Player) sender).setAllowFlight(false);
            ((Player) sender).setFlying(false);
            return true;
        }
        if(cooldown.containsKey(((Player) sender).getUniqueId()) && !cooldown.get(((Player) sender).getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(((Player) sender).getUniqueId()).getTimeRemaining());
            return true;
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
        return true;
    }

}
