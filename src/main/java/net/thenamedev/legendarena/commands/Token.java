package net.thenamedev.legendarena.commands;

import net.thenamedev.legendapi.tokens.TokenCore;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author TheNameMan
 */
public class Token implements CommandExecutor {

    private void help(CommandSender sender) {
        sender.sendMessage(Help.getFormattedHeader("Staff Commands"));
        sender.sendMessage(Help.getFormattedHelpMsg("/tokens add <player> <amount>", "Adds tokens to a player's account."));
        sender.sendMessage(Help.getFormattedHelpMsg("/tokens remove <player> <amount>", "Takes tokens from a player's account."));
        sender.sendMessage(Help.getFormattedHelpMsg("/tokens reset <player>", "Resets a player's tokens to 0."));
        sender.sendMessage(Help.getFormattedHeader("User Commands"));
        sender.sendMessage(Help.getFormattedHelpMsg("/tokens info", "Shows your tokens info."));
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player))
            return true;
        try {
            TokenCore.init(); //make sure the token core is indeed initalized
        } catch(Exception ex) {
            sender.sendMessage(ChatColor.RED + "Cannot initialize tokens core. Report this error to Pixel:" + ex.getMessage());
        }
        if(args.length == 0) {
            help(sender);
        } else {
            if(args[0].equalsIgnoreCase("info")) {
                sender.sendMessage(Help.getFormattedHeader("Your Tokens Info"));
                sender.sendMessage(ChatColor.YELLOW + "Amount " + ChatColor.YELLOW + PluginUtils.chars[1] + ChatColor.GREEN + " " + TokenCore.getTokens((Player) sender));
            } else if(args[0].equalsIgnoreCase("version")) {
                sender.sendMessage(ChatColor.GOLD + "Utilizing tokens core v" + TokenCore.ver + ", codenamed \"" + TokenCore.verName + "\".");
            } else if(args[0].equalsIgnoreCase("add")) {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN));
                    return true;
                }
                if(args.length <= 2) {
                    sender.sendMessage(ChatColor.YELLOW + "/tokens add <player> <amount> "+ ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + " Adds tokens to a player's account.");
                } else {
                    Player p = Bukkit.getPlayer(args[1]);
                    if(p == null) {
                        sender.sendMessage(ChatColor.RED + "That player wasn't found!");
                        return true;
                    }
                    int add;
                    try {
                        add = Integer.parseInt(args[2]);
                    } catch(Exception ex) {
                        sender.sendMessage(ChatColor.RED + "\"" + args[2] + "\" is not a double!");
                        return true;
                    }
                    sender.sendMessage(ChatColor.GREEN + "Adding " + add + " token(s)...");
                    TokenCore.addTokens(p, add, true, false);
                }
            } else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("take")) {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN));
                    return true;
                }
                if(args.length <= 2) {
                    sender.sendMessage(ChatColor.YELLOW + "/tokens remove <player> <amount> "+ ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + " Takes tokens from a player's account.");
                } else {
                    Player p = Bukkit.getPlayer(args[1]);
                    if(p == null) {
                        sender.sendMessage(ChatColor.RED + "That player wasn't found!");
                        return true;
                    }
                    int remove;
                    try {
                        remove = Integer.parseInt(args[2]);
                    } catch(Exception ex) {
                        sender.sendMessage(ChatColor.RED + "\"" + args[2] + "\" is not an integer!");
                        return true;
                    }
                    if(remove > TokenCore.getTokens(p)) {
                        sender.sendMessage(ChatColor.RED + "That player does not have that many tokens!");
                        return true;
                    }
                    sender.sendMessage(ChatColor.GREEN + "Removing " + remove + " token(s)...");
                    TokenCore.removeTokens(p, remove, true, false);
                }
            } else if(args[0].equalsIgnoreCase("reset")) {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN));
                    return true;
                }
                if(args.length < 2) {
                    sender.sendMessage(ChatColor.YELLOW + "/tokens reset <player> "+ ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + " Takes tokens from a player's account.");
                } else {
                    Player p = Bukkit.getPlayer(args[1]);
                    if(Rank.isRanked(p, Rank.SRMOD)) {
                        sender.sendMessage(PluginUtils.msgWarning + "You must be fun at parties.");
                        return true;
                    }
                    if(p == null) {
                        sender.sendMessage(ChatColor.RED + "That player was not found!");
                        return true;
                    }
                    sender.sendMessage(ChatColor.GREEN + "Resetting player's tokens...");
                    TokenCore.resetTokens(p, true, Rank.getFormattedName((Player) sender));
                }
            }

            else {
                help(sender);
            }
        }
        return true;
    }

}
