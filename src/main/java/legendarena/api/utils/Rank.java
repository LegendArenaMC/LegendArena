package legendarena.api.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Rank utilities.
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

    /**
     * Get a pre-built no permissions message.
     * @param r The rank to get the no permissions message of
     * @return The built no permissions message
     */
    public static String noPermissions(Rank r) {
        return ChatUtils.Messages.getCustomMsg("Rank") + "Minimum rank required: " + ChatColor.RED + r;
    }

}
