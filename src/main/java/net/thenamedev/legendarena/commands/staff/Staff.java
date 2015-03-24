package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.commands.Help;
import net.thenamedev.legendarena.commands.backends.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created on 3/19/2015
 *
 * @author ThePixelDev
 */
public class Staff implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.isRanked(sender, Rank.HELPER)) {
            sender.sendMessage(Rank.noPermissions(Rank.HELPER));
            return true;
        }
        if(args.length == 0) {
            help(sender, "1");
        } else {
            if(args[0].equalsIgnoreCase("help")) {
                if(args.length == 1) {
                    help(sender, "1");
                } else {
                    help(sender, args[1]);
                }
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
            } else if(args[0].equalsIgnoreCase("chtmng")) {
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
            } else if(args[0].equalsIgnoreCase("banhammer")) {
                BanHammer.run(sender);
            } else if(args[0].equalsIgnoreCase("chat")) {
                Chat.run(sender, args);
            } else if(args[0].equalsIgnoreCase("fly")) {
                Fly.run(sender, args);
            }

            else if(args[0].equals("TEAPOT")) {
                if(!Rank.isRanked(sender, Rank.FOUNDER)) {
                    help(sender, "unknown");
                    return true;
                }
                //yes, this is my attempt at a horrible joke - don't ask.
                ChatUtils.broadcast(String.format("%HTTP/1.1 418 I'm a teapot", PluginUtils.msgWarning));
            } else if(args[0].equals("TESTMSGS")) {
                if(!Rank.isRanked(sender, Rank.FOUNDER)) {
                    help(sender, "unknown");
                    return true;
                }
                sender.sendMessage(PluginUtils.msgDebug + "Hello, world");
                sender.sendMessage(PluginUtils.msgError + "Hello, world");
                sender.sendMessage(PluginUtils.msgNormal + "Hello, world");
                sender.sendMessage(PluginUtils.msgWarning + "Hello, world");
            } else {
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
                sender.sendMessage(Help.getFormattedHelpMsg("/staff vanish [player]", "Poof."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff fly", "Take to the skies and SOAR!"));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff chat <channel> [msg]", "Change your chat channels."));
                sender.sendMessage(ChatColor.YELLOW + "----.{ Staff [1/2] }.----");
                break;
            case "2":
                sender.sendMessage(ChatColor.YELLOW + "----.{ Staff [2/2] }.----");
                sender.sendMessage(Help.getFormattedHelpMsg("/staff chtmng <various suboptions...>", "Chat managment tools."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff banhammer", "Receive the all-mighty ban hammer."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff motd [various suboptions...]", "MOTD-related info."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff scoreboard [--update]", "Shows the teams registered, and optionally updates the scoreboard."));
                sender.sendMessage(ChatColor.YELLOW + "----.{ Staff [2/2] }.----");
                break;
            case "unknown":
                sender.sendMessage(PluginUtils.msgWarning + "I don't know what command you mean :(");
                help(sender, "1");
                break;
            default:
                sender.sendMessage("I don't know what help page you mean ;-;");
                break;
        }
    }

}
