package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.commands.Help;
import net.thenamedev.legendarena.commands.backends.punish.Freeze;
import net.thenamedev.legendarena.commands.backends.punish.WarnCmd;
import net.thenamedev.legendarena.commands.backends.staff.ClearChat;
import net.thenamedev.legendarena.commands.backends.staff.GlobalMute;
import net.thenamedev.legendarena.commands.backends.staff.Info;
import net.thenamedev.legendarena.commands.backends.staff.MOTDList;
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
        if(args.length >= 1 && args[0].equalsIgnoreCase("monstercat")) { //sshh....
            sender.sendMessage(ChatUtils.getCustomMsg("Irrelevent Jokes") + "WELCOME, TO THE MONSTERCAT PODCAST");
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
                sender.sendMessage(ChatColor.YELLOW + "----.{ Staff [1/1] }.----");
                sender.sendMessage(Help.getFormattedHelpMsg("/staff help [page]", "Displays this menu, or optionally, a help page."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff info <player>", "Gets info about a specified player."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff chat <various suboptions...>", "Chat managment tools."));
                sender.sendMessage(Help.getFormattedHelpMsg("/staff motd [various suboptions...]", "MOTD-related info."));
                sender.sendMessage(ChatColor.YELLOW + "----.{ Staff [1/1] }.----");
                break;
            default:
                sender.sendMessage(ChatUtils.Messages.errorMsg + "I don't know what help page you mean :(");
                break;
        }
    }

}
