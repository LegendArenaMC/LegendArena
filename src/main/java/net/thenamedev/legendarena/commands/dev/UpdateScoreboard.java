package net.thenamedev.legendarena.commands.dev;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.staffchat.StaffChat;
import net.thenamedev.legendarena.utils.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

/**
 * @author TheNameMan
 */
public class UpdateScoreboard implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.Dev)) {
            Rank.noPermissions(sender, Rank.Dev);
            return true;
        }
        sender.sendMessage(PluginUtils.msgWarning + "THIS SHOULD ONLY BE USED WHEN THINGS ARE BROKEN. If things aren't broken, you may have just fucked up.");
        StaffChat.notice("The nametag system's scoreboard has forcefully been updated. Unless things are broken, throw " + sender.getName() + " off a bridge.", "Nametag System");
        sender.sendMessage(PluginUtils.msgNormal + "Updating scoreboard... (if there's a lot of people on this may take a bit)");
        Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new ScoreboardUtils.FixThings());
        sender.sendMessage(PluginUtils.msgNormal + "Scoreboard updated.");
        return true;
    }

}
