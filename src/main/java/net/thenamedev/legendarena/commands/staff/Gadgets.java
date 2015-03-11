package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.hub.warp.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class Gadgets implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return true; //Do nothing if it's not a player
        }
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(args.length == 0) {
            if(HubWarper.exempt.contains(((Player) sender).getUniqueId())) {
                HubWarper.exempt.remove(((Player) sender).getUniqueId());
                sender.sendMessage(PluginUtils.msgNormal + "Removed from gadgets exempt list.");
            } else {
                HubWarper.exempt.add(((Player) sender).getUniqueId());
                sender.sendMessage(PluginUtils.msgNormal + "Added to gadgets exempt list.");
            }
        } else {
            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(ChatColor.RED + "Player \"" + args[0] + "\" was not found.");
            } else {
                Player p = Bukkit.getPlayer(args[0]);
                UUID u = p.getUniqueId();
                if(HubWarper.exempt.contains(u)) {
                    HubWarper.exempt.remove(u);
                    sender.sendMessage(PluginUtils.msgNormal + "Removed player " + p.getName() + " from gadgets exemption list.");
                } else {
                    HubWarper.exempt.add(u);
                    sender.sendMessage(PluginUtils.msgNormal + "Added player " + p.getName() + " to gadgets exemption list.");
                }
            }
        }
        return true;
    }

}
