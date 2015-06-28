package legendarena.commands;

import legendapi.emeralds.EmeraldsCore;
import legendapi.utils.ChatUtils;
import legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Emeralds command.
 *
 * @author ThePixelDev
 */
public class EmeraldCmd implements CommandExecutor {

    private void help(CommandSender sender) {
        sender.sendMessage(Help.getFormattedHeader("Staff Commands"));
        sender.sendMessage(Help.getFormattedHelpMsg("/emeralds add <player> <amount>", "Adds a specified amount of emeralds to a player's account."));
        sender.sendMessage(Help.getFormattedHelpMsg("/emeralds take <player> <amount>", "Takes a specified amount of emeralds from a player's account."));
        sender.sendMessage(Help.getFormattedHelpMsg("/emeralds reset <player>", "Takes all emeralds from a player's account."));
        sender.sendMessage(Help.getFormattedHeader("User Commands"));
        sender.sendMessage(Help.getFormattedHelpMsg("/emeralds info", "Shows your emeralds info."));
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        try {
            EmeraldsCore.init(); //make sure the emerald core is indeed initalized
        } catch(Exception ex) {
            sender.sendMessage(ChatColor.RED + "Cannot initialize Emerald Core. Reason: " + ex.getMessage() + " ( report this issue here: https://github.com/LegendArenaMC/LegendArena/issues )");
        }
        if(args.length == 0)
            help(sender);
        else {
            if(args[0].equalsIgnoreCase("info")) {
                sender.sendMessage(Help.getFormattedHeader("Your Emerald Info"));
                sender.sendMessage(ChatColor.YELLOW + "Amount " + ChatColor.YELLOW + ChatUtils.chars[1] + ChatColor.GREEN + " " + EmeraldsCore.getEmeralds((Player) sender));
            } else if(args[0].equalsIgnoreCase("add")) {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN));
                    return true;
                }
                if(args.length <= 2)
                    sender.sendMessage(Help.getFormattedHelpMsg("/emeralds add <player> <amount>", "Adds a specified amount of emeralds to a player's account."));
                else {
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
                    sender.sendMessage(ChatColor.GREEN + "Adding " + add + " emerald(s)...");
                    EmeraldsCore.addEmeralds(p, add, true);
                }
            } else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("take")) {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN));
                    return true;
                }
                if(args.length <= 2) {
                    sender.sendMessage(Help.getFormattedHelpMsg("/emeralds take <player> <amount>", "Takes a specified amount of emeralds from a player's account."));
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
                    if(remove > EmeraldsCore.getEmeralds(p)) {
                        sender.sendMessage(ChatColor.RED + "That player does not have that many emeralds!");
                        return true;
                    }
                    sender.sendMessage(ChatColor.GREEN + "Removing " + remove + " emerald(s)...");
                    EmeraldsCore.removeEmeralds(p, remove, true);
                }
            } else if(args[0].equalsIgnoreCase("reset")) {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN));
                    return true;
                }
                if(args.length < 2) {
                    sender.sendMessage(Help.getFormattedHelpMsg("/emeralds reset <player>", "Takes all emeralds from a player's account."));
                } else {
                    Player p = Bukkit.getPlayer(args[1]);
                    if(Rank.isRanked(p, Rank.MOD)) {
                        sender.sendMessage(ChatUtils.getCustomMsg("Staff") + "You must be really fun at parties.");
                        return true;
                    }
                    if(p == null) {
                        sender.sendMessage(ChatColor.RED + "That player was not found!");
                        return true;
                    }
                    sender.sendMessage(ChatColor.GREEN + "Setting player's emerald count to zero...");
                    EmeraldsCore.resetEmeralds(p, true, sender.getName());
                }
            }

            else {
                help(sender);
            }
        }
        return true;
    }

}