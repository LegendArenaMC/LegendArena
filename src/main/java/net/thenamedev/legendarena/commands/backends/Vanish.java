package net.thenamedev.legendarena.commands.backends;

import net.thenamedev.legendapi.LegendAPI;
import net.thenamedev.legendapi.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created on 3/9/2015
 *
 * @author ThePixelDev
 */
public class Vanish {

    private static HashMap<UUID, Cooldown> cooldown = new HashMap<>();
    public static List<UUID> vanishedPlayers = new ArrayList<>();

    public static void run(CommandSender sender, String[] args) {
        if(!Rank.isRanked(sender, Rank.HELPER)) {
            sender.sendMessage(Rank.noPermissions(Rank.HELPER));
            return;
        }
        if(cooldown.containsKey(((Player) sender).getUniqueId()) && !cooldown.get(((Player) sender).getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(((Player) sender).getUniqueId()).getTimeRemaining());
            return;
        }
        try {
            if(args.length == 1 || args.length > 2) {
                vanishPlayer((Player) sender, false, null);
            } else {
                if(!Rank.isRanked(sender, Rank.SRMOD)) {
                    vanishPlayer((Player) sender, false, null);
                    return;
                }
                if(Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(PluginUtils.msgWarning + "That player (\"" + args[1] + "\") was not found!");
                } else {
                    Player target = Bukkit.getPlayer(args[1]);
                    if(Rank.isRanked(target, Rank.SRMOD)) {
                        sender.sendMessage(PluginUtils.msgWarning + "You must be fun at parties.");
                    }
                    vanishPlayer(target, true, (Player) sender);
                }
            }
        } catch(Exception ex) {
            if(LegendAPI.debug)
                sender.sendMessage(LegendAPI.debugCat + "Huh. Looks like debug cat found a bug. Error message: " + ex.getMessage() + "\n(full error log in console)");
            ex.printStackTrace();
        }
        cooldown.put(((Player) sender).getUniqueId(), new Cooldown(10));
    }

    private static void vanishPlayer(Player target, boolean forced, Player sender) {
        if(forced) {
            if(vanishedPlayers.contains(target.getUniqueId())) {
                target.sendMessage(PluginUtils.msgNormal + "You were unvanished by the staff member " + OldRank.getFormattedName(sender) + ChatColor.LIGHT_PURPLE + "!");
                vanishedPlayers.remove(target.getUniqueId());
                sender.sendMessage(PluginUtils.msgNormal + "Unvanished " + target.getName() + ".");
            } else {
                target.sendMessage(PluginUtils.msgNormal + "You were vanished by the staff member " + OldRank.getFormattedName(sender) + ChatColor.LIGHT_PURPLE + "!");
                vanishedPlayers.add(target.getUniqueId());
                sender.sendMessage(PluginUtils.msgNormal + "Vanished " + target.getName() + ".");
            }
        } else {
            if(PlayerUtils.getPlayerMinigame(sender.getUniqueId()) == null) {
                if(vanishedPlayers.contains(sender.getUniqueId())) {
                    sender.sendMessage(PluginUtils.msgNormal + "You are now visible! *poof*");
                    vanishedPlayers.remove(target.getUniqueId());
                    ChatUtils.broadcast(PluginUtils.msgNormal + "Player " + target.getName() + " has unvanished.", Rank.MOD);

                } else {
                    sender.sendMessage(PluginUtils.msgNormal + "You are now invisible! *poof*");
                    vanishedPlayers.add(target.getUniqueId());
                    ChatUtils.broadcast(PluginUtils.msgNormal + "Player " + target.getName() + " has vanished.", Rank.MOD);
                }
            }
        }
    }

}
