package net.thenamedev.legendarena.commands.backends;

import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.staffchat.StaffChat;
import net.thenamedev.legendarena.utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
        StaffChat.notice("The nametag system's scoreboard has forcefully been updated. Unless things are broken, throw " + sender.getName() + " off a bridge. (please don't literally do this)", "Nametag System");
        sender.sendMessage(PluginUtils.msgNormal + "Updating scoreboard... (if there's a lot of people on this may take a bit)");
        Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new ScoreboardUtils.FixThings());
        sender.sendMessage(PluginUtils.msgNormal + "Scoreboard updated.");
        return true;
    }

}
