package net.thenamedev.legendarena.commands.dev;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.motd.*;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class MOTDList implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.GM)) {
            Rank.noPermissions(sender, Rank.GM);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(PluginUtils.msgWarning + "The MOTD list is no longer displayed by default, due to it being so large.");
            sender.sendMessage(ChatColor.YELLOW + "To show the MOTD list, run this command again, but with any arguments, so you can literally just smash your keyboard (don't actually smash it pls) to show it.");
            sender.sendMessage(PluginUtils.msgNormal + "List of MOTD messages: " + MOTDRandomizer.getList().length + " (DO YOU SEE WHAT I MEAN BY \"SO LARGE\" NOW?)");
        } else {
            @NotNull String[] list = MOTDRandomizer.getList();
            @NotNull String finishedList = PluginUtils.msgNormal;
            for(String list1 : list) {
                if(finishedList.equals(PluginUtils.msgNormal)) {
                    finishedList = finishedList + " \"" + list1 + "\"";
                } else {
                    finishedList = finishedList + ", \"" + list1 + "\"";
                }
            }
            sender.sendMessage(finishedList);
        }
        return true;
    }

}
