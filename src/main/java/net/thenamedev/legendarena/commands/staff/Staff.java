package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.commands.Help;
import net.thenamedev.legendarena.commands.backends.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 3/19/2015
 *
 * @author ThePixelDev
 */
public class Staff implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length >= 1 && args[0].equalsIgnoreCase("monstercat")) { //sshh....
            sender.sendMessage(String.format("%sIrrelevent Jokes %s%s %sThat's a bad kitty!", ChatColor.BLUE, ChatColor.GRAY, PluginUtils.chars[1], ChatColor.LIGHT_PURPLE));
            return true;
        }
        if(!Rank.isRanked(sender, Rank.HELPER)) {
            sender.sendMessage(Rank.noPermissions(Rank.HELPER));
            return true;
        }
        if(args.length == 0) {
            help(sender, "1");
        } else {
            if(args[0].equalsIgnoreCase("help")) {
                if(args.length == 1)
                    help(sender, "1");
                else
                    help(sender, args[1]);
            } else if(args[0].equalsIgnoreCase("info")) {
                if(args.length == 1)
                    sender.sendMessage(Help.getFormattedHelpMsg("/staff info <player>", "Gets info about a specified player."));
                else
                    Info.run(sender, args);
            } else if(args[0].equalsIgnoreCase("freeze")) {
                if(args.length == 1)
                    sender.sendMessage(Help.getFormattedHelpMsg("/staff freeze <player>", "Freezes (or unfreezes) a specified player."));
                else
                    Freeze.run(sender, args);
            } else if(args[0].equalsIgnoreCase("motd")) {
                MOTDList.run(sender, args);
            } else if(args[0].equalsIgnoreCase("chat")) {
                if(args.length == 1) {
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Chat Management suboptions:");
                    sender.sendMessage(ChatColor.YELLOW + "- CLEARCHAT [reason]");
                    sender.sendMessage(ChatColor.YELLOW + "- GLOBALMUTE");
                } else {
                    if(args[1].equalsIgnoreCase("globalmute")) {
                        GlobalMute.run(sender);
                    } else if(args[1].equalsIgnoreCase("clearchat")) {
                        ClearChat.run(sender, args);
                    } else {
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Chat Management suboptions:");
                        sender.sendMessage(ChatColor.YELLOW + "- CLEARCHAT [reason]");
                        sender.sendMessage(ChatColor.YELLOW + "- GLOBALMUTE");
                    }
                }
            } else if(args[0].equalsIgnoreCase("fly")) {
                Fly.run(sender);
            } else if(args[0].equalsIgnoreCase("warn")) {
                Warn.run(sender, args);
            } else if(args[0].equalsIgnoreCase("pmctroll")) {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    help(sender, "unknown");
                    return true;
                }
                if(args.length == 1) {
                    sender.sendMessage(Help.getFormattedHelpMsg("/staff pmctroll <player>", "Trolls a PMC nub and changes their display name to \"OP\"."));
                } else {
                    @SuppressWarnings("deprecation") Player t = Bukkit.getPlayer(args[1]);
                    if(t == null) {
                        sender.sendMessage(PluginUtils.msgWarning + "The player \"" + args[1] + "\" was not found :(");
                        return true;
                    }
                }
            }

            else {
                help(sender, "unknown");
            }
        }
        return true;
    }

    private void help(CommandSender sender, String page) {
        switch(page) {
            case "1":
                sender.sendMessage(ChatColor.YELLOW + "----.{ Staff [1/2] }.----");
                sender.sendMessage(Help.getFormattedHelpMsg("/staff help [page]", "Displays this menu, or optionally, a help page."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff info <player>", "Gets info about a specified player."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff freeze <player>", "Freezes (or unfreezes) a specified player."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff fly", "Take to the skies and SOAR!"));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff warn <player> [reason]", "Warns a player, with an optional reason."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff chat <various suboptions...>", "Chat managment tools."));
                sender.sendMessage(ChatColor.YELLOW + "----.{ Staff [1/2] }.----");
                break;
            case "2":
                sender.sendMessage(ChatColor.YELLOW + "----.{ Staff [2/2] }.----");
                sender.sendMessage(Help.getFormattedHelpMsg("/staff motd [various suboptions...]", "MOTD-related info."));
                sender.sendMessage(ChatColor.YELLOW + "----.{ Staff [2/2] }.----");
                break;
            case "unknown":
                sender.sendMessage(PluginUtils.msgWarning + "I don't know what command you mean :(");
                break;
            default:
                sender.sendMessage(PluginUtils.msgWarning + "I don't know what help page you mean :(");
                break;
        }
    }

}
