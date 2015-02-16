package net.thenamedev.legendarena.commands.dev;

import net.thenamedev.legendarena.core.*;
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
        sender.sendMessage(PluginUtils.msgNormal + "Updating scoreboard...");
        for(Player p : Bukkit.getOnlinePlayers()) {
            ScoreboardUtils.addPlayerToTeam(Rank.getScoreboardTeam(p), p);
        }
        sender.sendMessage(PluginUtils.msgNormal + "Scoreboard updated.");
        return true;
    }

}
