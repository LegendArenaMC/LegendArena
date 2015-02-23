package net.thenamedev.legendarena.commands;

import net.thenamedev.legendapi.tokens.*;
import net.thenamedev.legendapi.utils.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

/**
 * @author TheNameMan
 */
public class Token implements CommandExecutor {

    private void help(CommandSender sender) {
        sender.sendMessage(ChatUtils.formattedCmd("Staff Commands", true));
        sender.sendMessage(ChatColor.YELLOW + "/tokens add <player> <amount> "+ ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + " Adds tokens to a player's account.");
        sender.sendMessage(ChatColor.YELLOW + "/tokens remove <player> <amount> "+ ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + " Takes tokens from a player's account.");
        sender.sendMessage(ChatUtils.formattedCmd("User Commands", true));
        sender.sendMessage(ChatColor.YELLOW + "/tokens info " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + " Shows your token info (such as amount of tokens)");
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        TokenCore.init(); //make sure the token core is indeed initalized
        if(args.length == 0) {
            help(sender);
        } else {
            if(args[0].equalsIgnoreCase("info")) {
                sender.sendMessage(ChatUtils.formattedCmd("Your Tokens", true));
                sender.sendMessage(ChatColor.YELLOW + "Amount " + ChatColor.YELLOW + PluginUtils.chars[1] + ChatColor.GREEN + " " + TokenCore.getTokens((Player) sender));
                sender.sendMessage(ChatColor.YELLOW + "Booster " + ChatColor.YELLOW + PluginUtils.chars[1] + ChatColor.GREEN + " Coming soon" + PluginUtils.chars[6] + " (for all the EULA fanboys - the boosters will be private and earned in game by playing minigames)");
            } else if(args[0].equalsIgnoreCase("version")) {
                sender.sendMessage(ChatColor.GOLD + "Utilizing tokens core version " + TokenCore.ver + ".");
            } else if(args[0].equalsIgnoreCase("add")) {
                if(!Rank.getRank(sender, Rank.Mod)) {
                    Rank.noPermissions(sender, Rank.Mod);
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
                    TokenCore.addTokens(p, add, true);
                }
            } else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("take")) {
                if(!Rank.getRank(sender, Rank.Mod)) {
                    Rank.noPermissions(sender, Rank.Mod);
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
                        sender.sendMessage(ChatColor.RED + "\"" + args[2] + "\" is not a double!");
                        return true;
                    }
                    if(remove > TokenCore.getTokens(p)) {
                        sender.sendMessage(ChatColor.RED + "That player does not have that many tokens!");
                        return true;
                    }
                    sender.sendMessage(ChatColor.GREEN + "Removing " + remove + " token(s)...");
                    TokenCore.removeTokens(p, remove, true);
                }
            }

            else {
                help(sender);
            }
        }
        return true;
    }

}
