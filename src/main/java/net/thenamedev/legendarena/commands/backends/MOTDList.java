package net.thenamedev.legendarena.commands.backends;

import net.thenamedev.legendapi.chat.ChatSystem;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.MOTDRandomizer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * @author TheNameMan
 */
public class MOTDList {

    public static void run(CommandSender sender, String[] args) {
        if(!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD));
            return;
        }
        if(args.length == 1) {
            sender.sendMessage(PluginUtils.msgNormal + "List of MOTD messages: " + MOTDRandomizer.getList().length + " (DO YOU SEE WHAT I MEAN BY \"SO LARGE\" NOW?)");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Current notice: " + MOTDRandomizer.getNotice());
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "To view/set the MOTD notice: /staff motd notice [notice]");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "To view the MOTD list: /staff motd list");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "To view a random MOTD: /staff motd random");
        } else if(args[1].equalsIgnoreCase("list")) {
            String[] list = MOTDRandomizer.getList();
            String finishedList = PluginUtils.msgNormal;
            for(String list1 : list) {
                if(finishedList.equals(PluginUtils.msgNormal)) {
                    finishedList = finishedList + " \"" + list1 + "\"";
                } else {
                    finishedList = finishedList + ", \"" + list1 + "\"";
                }
            }
            sender.sendMessage(finishedList);
        } else if(args[1].equalsIgnoreCase("notice")) {
            if(args.length == 2) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Current notice: " + ChatColor.GREEN + MOTDRandomizer.getNotice());
            } else {
                if(!Rank.isRanked(sender, Rank.SRMOD)) {
                    sender.sendMessage(Rank.noPermissions(Rank.SRMOD));
                    return;
                }
                ChatSystem.notice("Staff member " + sender.getName() + " has changed the MOTD notice from \"" + MOTDRandomizer.getNotice() + "\" to \"" + ChatUtils.formatCast(args, 0, 1) + "\"");
                MOTDRandomizer.setNotice(ChatUtils.formatCast(args, 0, 1));
            }
        } else if(args[1].equalsIgnoreCase("random")) {
            sender.sendMessage(PluginUtils.msgNormal + MOTDRandomizer.randomize());
        }
    }

}
