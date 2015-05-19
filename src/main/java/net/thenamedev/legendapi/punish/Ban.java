package net.thenamedev.legendapi.punish;

import net.thenamedev.legendapi.core.events.BanEvent;
import net.thenamedev.legendapi.core.exceptions.CancelledEventException;
import net.thenamedev.legendapi.core.exceptions.PlayerAlreadyBannedException;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by thepixeldev on 5/7/15.
 */
public class Ban {

    /**
     * Is the player banned?
     * @param player The player to check if banned
     * @return True/false if banned or not
     */
    public static boolean isBanned(String player) {
        return Bukkit.getBanList(BanList.Type.NAME).isBanned(player);
    }

    /**
     * Bans a player. Simple as that.
     * @param player The player to ban
     * @param expiry The date for the ban to expire on (null for never)
     * @param reason The reason to ban the player for
     * @param staff The staff member banning the player
     * @throws PlayerAlreadyBannedException If the player is already banned.
     */
    public static void ban(String player, String reason, Date expiry, Player staff) {
        if(isBanned(player)) throw new PlayerAlreadyBannedException();
        BanEvent banevent = new BanEvent(reason, staff, player);
        Bukkit.getPluginManager().callEvent(banevent);
        if(banevent.isCancelled())
            throw new CancelledEventException();
        Bukkit.getBanList(BanList.Type.NAME).addBan(player, reason, expiry, null);
        ChatUtils.broadcast(ChatUtils.Messages.getCustomMsg("Punish") + "Staff member " + ChatUtils.getFormattedName(staff) + ChatColor.BLUE + " has unbanned player " + ChatColor.YELLOW + player + ChatColor.BLUE + " for the reason \"" + ChatColor.YELLOW + reason + ChatColor.BLUE + "\".");
    }

    /**
     * Unban the player
     * @param player The player to unban
     * @param staff The staff unbanning the player
     */
    public static void unban(String player, Player staff) {
        Bukkit.getBanList(BanList.Type.NAME).pardon(player);
        ChatUtils.broadcast(ChatUtils.Messages.getCustomMsg("Punish") + "Staff member " + ChatUtils.getFormattedName(staff) + ChatColor.BLUE + " has unbanned player " + ChatColor.YELLOW + player + ChatColor.BLUE + ".");
    }

    /**
     * @param toParse The string to parse
     * @return The date parsed, or null if failed to parse
     */
    public static Date parseDay(String toParse) {
        try {
            return DateFormat.getDateInstance().parse(toParse);
        } catch(ParseException e) {
            return null;
        }
    }

}
