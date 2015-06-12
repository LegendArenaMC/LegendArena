package legendarena.commands.backends;

import legendarena.chat.ChatSystem;
import legendarena.api.utils.ChatUtils;
import legendarena.api.utils.Rank;
import legendarena.commands.Help;
import legendarena.api.utils.MOTDRandomizer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * MOTD Randomizer tools for staff.
 *
 * @author TheNameMan
 */
public class MOTDList {

    public static void run(CommandSender sender, String[] args) {
        if(!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD));
            return;
        }
        if(args.length == 1) {
            sender.sendMessage(Help.getFormattedHeader("MOTD Randomizer"));
            sender.sendMessage(Help.getFormattedHelpMsg("Amount of MOTDs", MOTDRandomizer.getList().length + " (DO YOU SEE WHAT I MEAN BY \"SO LARGE\" NOW?)"));
            sender.sendMessage(Help.getFormattedHelpMsg("Current notice", "\"" + MOTDRandomizer.getNotice() + "\""));
            sender.sendMessage(Help.getFormattedHelpMsg("/staff motd notice [notice]", "Sets the MOTD notice"));
        } else if(args[1].equalsIgnoreCase("list")) {
            String[] list = MOTDRandomizer.getList();
            String finishedList = ChatUtils.getCustomMsg("MOTD");
            for(String list1 : list) {
                if(finishedList.equals(ChatUtils.getCustomMsg("MOTD"))) {
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
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN));
                    return;
                }
                ChatSystem.notice("Staff member " + sender.getName() + " has changed the MOTD notice from \"" + MOTDRandomizer.getNotice() + "\" to \"" + ChatUtils.formatCast(args, 0, 1) + "\"");
                MOTDRandomizer.setNotice(ChatUtils.formatCast(args, 0, 1));
            }
        }
    }

}
