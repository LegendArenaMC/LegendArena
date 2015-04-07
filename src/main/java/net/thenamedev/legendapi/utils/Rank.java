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
     * VIP rank.
     */
    VIP,
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
                return (p.getName().equals("ThePixelDev") || p.getName().equals("ThePixelDevin"));
            case ADMIN:
                return p.hasPermission("legendarena.rank.admin");
            case SRMOD:
                return p.hasPermission("legendarena.rank.srmod");
            case MOD:
                return p.hasPermission("legendarena.rank.mod");
            case HELPER:
                return p.hasPermission("legendarena.rank.helper");
            case VIP:
                return p.hasPermission("legendarena.rank.vip");

            default:
                return false;
        }
    }

    /**
     * @param p The player to check
     * @return The player's rank
     */
    public static Rank getRank(Player p) {
        if(p.getName().equals("ThePixelDev") || p.getName().equals("ThePixelDevin")) return FOUNDER;
        else if(p.hasPermission("legendarena.rank.admin")) return ADMIN;
        else if(p.hasPermission("legendarena.rank.srmod")) return SRMOD;
        else if(p.hasPermission("legendarena.rank.mod")) return MOD;
        else if(p.hasPermission("legendarena.rank.vip")) return VIP;
        else return MEMBER;
    }

    /**
     * Legacy method.
     * @deprecated Use isRanked(Player, Rank).
     */
    public static boolean getRank(Player p, Rank r) {
        return isRanked(p, r);
    }

    /**
     * Gets the rank prefix (i.e. colored rank name) for a rank.
     * @param r The rank to get the prefix for
     * @return The rank prefix requested
     */
    public static String getRankPrefix(Rank r) {
        switch(r) {
            case FOUNDER:
                return ChatColor.DARK_RED + "" + ChatColor.BOLD + "Founder";
            case ADMIN:
                return ChatColor.DARK_RED + "Admin";
            case SRMOD:
                return ChatColor.DARK_RED + "SrMod";
            case MOD:
                return ChatColor.RED + "Mod";
            case HELPER:
                return ChatColor.BLUE + "Helper";
            case VIP:
                return ChatColor.GOLD + "VIP";

            default:
                return ChatColor.GRAY + "Member";
        }
    }

    /**
     * Gets a colored name, depending on the player's rank.
     * @param p The player in question
     * @return The player's colored name, depending on their rank.
     */
    public static String getFormattedName(Player p) {
        Rank r = getRank(p);
        switch(r) {
            case FOUNDER:
                return ChatUtils.randomRainbow(p.getName());
            case ADMIN:
                return ChatColor.DARK_RED + p.getName();
            case SRMOD:
                return ChatColor.DARK_RED + p.getName();
            case MOD:
                return ChatColor.RED + p.getName();
            case HELPER:
                return ChatColor.BLUE + p.getName();
            case VIP:
                return ChatColor.GOLD + p.getName();
            default:
                return ChatColor.GRAY + p.getName();
        }
    }

    public static String noPermissions(Rank r) {
        switch(r) {
            case FOUNDER:
                return PluginUtils.msgWarning + "Minimum rank required: " + ChatColor.GOLD + "FOUNDER";
            case ADMIN:
                return PluginUtils.msgWarning + "Minimum rank required: " + ChatColor.GOLD + "ADMIN";
            case SRMOD:
                return PluginUtils.msgWarning + "Minimum rank required: " + ChatColor.GOLD + "SRMOD";
            case MOD:
                return PluginUtils.msgWarning + "Minimum rank required: " + ChatColor.GOLD + "MOD";
            case HELPER:
                return PluginUtils.msgWarning + "Minimum rank required: " + ChatColor.GOLD + "HELPER";
            case VIP:
                return PluginUtils.msgWarning + "Minimum rank required: " + ChatColor.GOLD + "VIP";
        }
        return null;
    }

    public static String getFormattedName(String name, Rank rank) {
        switch(rank) {
            case FOUNDER:
                return ChatUtils.randomRainbow(name);
            case ADMIN:
                return ChatColor.BLUE + name;
            case SRMOD:
                return ChatColor.BLUE + name;
            case MOD:
                return ChatColor.AQUA + name;
            case HELPER:
                return ChatColor.AQUA + name;
            case VIP:
                return ChatColor.GOLD + name;
            default:
                return ChatColor.GRAY + name;
        }
    }

}
