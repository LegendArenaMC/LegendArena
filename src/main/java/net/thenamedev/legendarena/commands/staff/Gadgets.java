package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.OldRank;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.hub.warp.HubWarper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author TheNameMan
 */
public class Gadgets implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return true; //Do nothing if it's not a player
        }
        if(!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD));
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
