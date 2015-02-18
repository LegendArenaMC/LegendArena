package net.thenamedev.legendarena.commands.dev;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.extras.motd.*;
import net.thenamedev.legendarena.utils.*;
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
        return true;
    }

}
