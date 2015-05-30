package net.thenamedev.legendapi.punish;

import net.thenamedev.legendapi.core.events.BanEvent;
import net.thenamedev.legendapi.core.exceptions.CancelledEventException;
import net.thenamedev.legendapi.core.exceptions.PlayerAlreadyBannedException;
import net.thenamedev.legendapi.message.Message;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Day;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by thepixeldev on 5/7/15.
 */
public class Ban {

    public static class NameBans {

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
            if(reason.equals("")) reason = "Rule violation";
            BanEvent banevent = new BanEvent(reason, staff, player);
            Bukkit.getPluginManager().callEvent(banevent);
            if(banevent.isCancelled())
                throw new CancelledEventException();
            if(Bukkit.getPlayer(player) != null) {
                String expiryDate = (expiry == null ? "Never" : Day.parseMonth(expiry.getMonth()) + " " + expiry.getDay() + ", " + expiry.getYear() + " at " + expiry.getHours() + ":" + expiry.getMinutes() + " " + expiry.getTimezoneOffset());
                Bukkit.getPlayer(player).kickPlayer(ChatUtils.getCustomMsg("Punish") + "Banned by \"" + ChatUtils.getFormattedName(staff) + ChatColor.BLUE + "\" for reason \"" + ChatColor.YELLOW + reason + ChatColor.BLUE + "\", which expires in " + ChatColor.YELLOW + expiryDate + ChatColor.BLUE + ".");
            }
            Bukkit.getBanList(BanList.Type.NAME).addBan(player, reason, expiry, null);
            new Message().append(ChatUtils.getCustomMsg("Punish"))
                    .append("Staff member ")
                    .append(ChatColor.WHITE + staff.getName() + ChatColor.BLUE)
                    .append(" has banned player \"")
                    .append(ChatColor.YELLOW + player + ChatColor.BLUE)
                    .append("\" for \"")
                    .append(ChatColor.YELLOW + reason + ChatColor.BLUE)
                    .append("\".")
                    .broadcast();
        }

        /**
         * Unban the player
         * @param player The player to unban
         * @param staff The staff unbanning the player
         */
        public static void unban(String player, Player staff) {
            Bukkit.getBanList(BanList.Type.NAME).pardon(player);

            //Yes, yes, I get it, probably the worst possible way to make this, but, well - fuck it[tm]
            new Message().append(ChatUtils.getCustomMsg("Punish"))
                    .append("Staff member ")
                    .append(ChatUtils.getFormattedName(staff))
                    .append(ChatColor.BLUE + " has unbanned player \"")
                    .append(ChatColor.YELLOW + player)
                    .append(ChatColor.BLUE + "\".")
                    .broadcast();
        }

    }

    public static class IPBans {

        private static BanList banList = Bukkit.getBanList(BanList.Type.IP);

        /**
         * Is the player banned?
         * @param player The player to check if banned
         * @return True/false if banned or not
         */
        public static boolean isBanned(String player) {
            return banList.isBanned(player);
        }

        /**
         * Bans a player. Simple as that.
         * @param player The player to ban
         * @param expiry The date for the ban to expire on (null for never)
         * @param reason The reason to ban the player for
         * @param staff The staff member banning the player
         * @throws PlayerAlreadyBannedException If the player is already banned.
         */
        public static void ban(String player, String internalreason, Date expiry, Player staff) {
            if(isBanned(player)) throw new PlayerAlreadyBannedException();
            if(internalreason.equals("")) internalreason = "Rule violation";
            String expiryDate = (expiry == null ? "Never" : Day.parseMonth(expiry.getMonth()) + " " + expiry.getDay() + ", " + expiry.getYear() + " at " + expiry.getHours() + ":" + expiry.getMinutes() + " " + expiry.getTimezoneOffset());
            String reason = new Message().append(MessageFormat.format("{0}You were banned by \"{1}{2}{3}\" for \"{4}{5}{6}\".\n \nThe ban expires on {7}{8}{9}.\n \nYou can appeal the ban on the subreddit:\n{10}reddit.com/r/LegendArena", ChatUtils.getCustomMsg("Punish"), ChatColor.YELLOW, staff.getName(), ChatColor.BLUE, ChatColor.YELLOW, internalreason, ChatColor.BLUE, ChatColor.YELLOW, expiryDate, ChatColor.BLUE, ChatColor.GREEN)).toString();
            BanEvent banevent = new BanEvent(reason, staff, player);
            Bukkit.getPluginManager().callEvent(banevent);
            if(banevent.isCancelled())
                throw new CancelledEventException();
            Player target = null;
            for(Player p : Bukkit.getOnlinePlayers())
                if(p.getAddress().getHostName().equals(player))
                    target = p;
            if(target != null)
                target.kickPlayer(reason);
            Bukkit.getBanList(BanList.Type.NAME).addBan(player, reason, expiry, null);
        }

        /**
         * Unban the player
         * @param player The player to unban
         * @param staff The staff unbanning the player
         */
        public static void unban(String player, Player staff) {
            banList.pardon(player);

            new Message().append(ChatUtils.getCustomMsg("Punish"))
                    .append("")
                    .broadcast(Rank.HELPER);
        }

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
