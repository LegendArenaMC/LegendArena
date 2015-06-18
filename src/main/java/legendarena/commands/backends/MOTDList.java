package legendarena.commands.backends;

import legendarena.chat.ChatSystem;
import legendapi.utils.ChatUtils;
import legendapi.utils.Rank;
import legendarena.commands.Help;
import legendarena.utils.ListType;
import legendarena.utils.MOTDUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.*;

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
        int length = MOTDUtils.getList().get(ListType.RANDOM).length + MOTDUtils.getList().get(ListType.SONG).length + MOTDUtils.getList().get(ListType.REFERENCE).length;
        if(args.length == 1) {
            sender.sendMessage(Help.getFormattedHeader("MOTD Randomizer"));
            sender.sendMessage(Help.getFormattedHelpMsg("Amount of MOTDs", String.valueOf(length)));
            sender.sendMessage(Help.getFormattedHelpMsg("Current notice", "\"" + MOTDUtils.getNotice() + "\""));
            sender.sendMessage(Help.getFormattedHelpMsg("/staff motd notice [notice]", "Sets the MOTD notice"));
        } else if(args[1].equalsIgnoreCase("list")) {
            HashMap<ListType, String[]> rawList = MOTDUtils.getList();
            String[] rawSongs = rawList.get(ListType.SONG), rawReferences = rawList.get(ListType.REFERENCE), rawRandom = rawList.get(ListType.RANDOM);
            String finishedList = ChatUtils.getCustomMsg("MOTD");
            for(String add : rawSongs)
                finishedList += (finishedList.equals(ChatUtils.getCustomMsg("MOTD")) ? "\"" + add + "\"" : ", \"" + add + "\"");
            for(String add : rawReferences)
                finishedList += (finishedList.equals(ChatUtils.getCustomMsg("MOTD")) ? "\"" + add + "\"" : ", \"" + add + "\"");
            for(String add : rawRandom)
                finishedList += (finishedList.equals(ChatUtils.getCustomMsg("MOTD")) ? "\"" + add + "\"" : ", \"" + add + "\"");
            sender.sendMessage(finishedList);
        } else if(args[1].equalsIgnoreCase("notice")) {
            if(args.length == 2) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Current notice: " + ChatColor.GREEN + MOTDUtils.getNotice());
            } else {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN));
                    return;
                }
                ChatSystem.notice("Staff member " + sender.getName() + " has changed the MOTD notice from \"" + MOTDUtils.getNotice() + "\" to \"" + ChatUtils.formatCast(args, 0, 1).toUpperCase() + "\"");
                MOTDUtils.setNotice(ChatUtils.formatCast(args, 0, 1));
            }
        }
    }

}
