package net.thenamedev.legendapi.punish;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by thepixeldev on 5/7/15.
 */
public class Kick {

    public static void kick(Player p, String reason, Player staff) {
        String staffName = (staff == null ? "CONSOLE" : ChatUtils.getFormattedName(staff));
        p.kickPlayer(ChatUtils.Messages.getCustomMsg("Punish") + "You were kicked for " + ChatColor.YELLOW + "\"" + ChatColor.translateAlternateColorCodes('&', reason) + ChatColor.YELLOW + "\" " + ChatColor.BLUE + "by " + staffName + ChatColor.BLUE + ".");
    }

}
