package net.thenamedev.legendapi.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 3/21/2015
 *
 * @author ThePixelDev
 */
public enum Rank {

    /**
     * Special founder role. Only given to Pixel and Jaden.
     */
    FOUNDER,
    /**
     * Administrator rank.
     */
    ADMIN,
    /**
     * Senior Mod staff rank. Requires a staff member to be a moderator for at minimum three months (or so).
     */
    SRMOD,
    /**
     * Moderator staff rank.
     */
    MOD,
    /**
     * Helper staff rank.
     */
    HELPER,
    /**
     * "YouTuber" rank.
     */
    YOUTUBE,
    /**
     * "Donor" rank. Only gives cosmetic options.
     */
    MEMBERPLUS,
    /**
     * Default user rank. This is only here as a "catch-all return", really.
     */
    MEMBER;

    /**
     * @param p The player to check
     * @param r The rank to look for
     * @return If the player is ranked after all (true) or not (false)
     */
    public static boolean isRanked(CommandSender p, Rank r) {
        switch(r) {
            case FOUNDER:
                return (p.getName().equals("ThePixelDev") || p.getName().equals("JadenJFilms"));
            case ADMIN:
                return p.hasPermission("legendarena.rank.admin");
            case SRMOD:
                return p.hasPermission("legendarena.rank.srmod");
            case MOD:
                return p.hasPermission("legendarena.rank.mod");
            case HELPER:
                return p.hasPermission("legendarena.rank.helper");
            case YOUTUBE:
                return p.hasPermission("legendarena.rank.yt");
            case MEMBERPLUS:
                return p.hasPermission("legendarena.rank.memberplus");

            default:
                return false;
        }
    }

    /**
     * @param p The player to check
     * @return The player's rank
     */
    public static Rank getRank(Player p) {
        if(p.getName().equals("ThePixelDev") || p.getName().equals("JadenJFilms")) return FOUNDER;
        else if(p.hasPermission("legendarena.rank.admin")) return ADMIN;
        else if(p.hasPermission("legendarena.rank.srmod")) return SRMOD;
        else if(p.hasPermission("legendarena.rank.mod")) return MOD;
        else if(p.hasPermission("legendarena.rank.helper")) return HELPER;
        else if(p.hasPermission("legendarena.rank.yt")) return YOUTUBE;
        else if(p.hasPermission("legendarena.rank.memeberplus")) return MEMBERPLUS;
        else return MEMBER;
    }

    /**
     * Gets the rank prefix (i.e. colored rank name) for a rank.
     * @param r The rank to get the prefix for
     * @return The rank prefix requested
     */
    public static String getRankPrefix(Rank r) {
        switch(r) {
            case FOUNDER:
                return ChatColor.DARK_RED + "" + ChatColor.BOLD + "FOUNDER";
            case ADMIN:
                return ChatColor.DARK_RED + "ADMIN";
            case SRMOD:
                return ChatColor.DARK_RED + "SRMOD";
            case MOD:
                return ChatColor.RED + "MOD";
            case HELPER:
                return ChatColor.RED + "HELPER";
            case YOUTUBE:
                return ChatColor.GOLD + "YT";
            case MEMBERPLUS:
                return ChatColor.BLUE + "MEMBER+";

            default:
                return ChatColor.GRAY + "";
        }
    }

    public static String noPermissions(Rank r) {
        switch(r) {
            case FOUNDER:
                return ChatUtils.Messages.getCustomMsg("Rank") + "Minimum rank required: " + ChatColor.GOLD + "FOUNDER";
            case ADMIN:
                return ChatUtils.Messages.getCustomMsg("Rank") + "Minimum rank required: " + ChatColor.GOLD + "ADMIN";
            case SRMOD:
                return ChatUtils.Messages.getCustomMsg("Rank") + "Minimum rank required: " + ChatColor.GOLD + "SRMOD";
            case MOD:
                return ChatUtils.Messages.getCustomMsg("Rank") + "Minimum rank required: " + ChatColor.GOLD + "MOD";
            case HELPER:
                return ChatUtils.Messages.getCustomMsg("Rank") + "Minimum rank required: " + ChatColor.GOLD + "HELPER";
            case YOUTUBE:
                return ChatUtils.Messages.getCustomMsg("Rank") + "Minimum rank required: " + ChatColor.GOLD + "YT";
            case MEMBERPLUS:
                return ChatUtils.Messages.getCustomMsg("Rank") + "Minimum rank required: " + ChatColor.GOLD + "MEMBER+";
        }
        return null;
    }
}
