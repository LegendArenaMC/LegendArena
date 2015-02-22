package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class Fly implements CommandExecutor {

    @NotNull
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
