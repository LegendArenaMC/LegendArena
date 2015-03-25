package net.thenamedev.legendapi.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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

    //The following ranks are not in any particular order; they're basically the same with just different prefixes.

    /**
     * Twitch rank. Essentially a semi-glorified Famous rank.
     */
    TWITCH,
    /**
     * YouTuber rank. Essentially a semi-glorified Famous rank.
     */
    YOUTUBE,
    /**
     * Famous rank (eqivilant to VIP in the previous rank system)
     */
    FAMOUS,

    //The above ranks are not in any particular order; they're basically the same with just different prefixes.

    /**
     * Member+ rank for donors.
     */
    MEMBERPLUS,
    /**
     * Default user rank.
     */
    MEMBER;

    /**
     * @param p The player to check
     * @param r The rank to look for
     * @return If the player is ranked after all (true) or not (false)
     */
    public static boolean isRanked(CommandSender p, Rank r) {
        if((p.getName().equals("ThePixelDev") || p.getName().equals("JadenJFilms")) && p.isOp() && r == FOUNDER) return true;
        else if(p.hasPermission("legendarena.rank.admin") && r == ADMIN) return true;
        else if(p.hasPermission("legendarena.rank.srmod") && r == SRMOD) return true;
        else if(p.hasPermission("legendarena.rank.mod") && r == MOD) return true;
        else if(p.hasPermission("legendarena.rank.helper") && r == HELPER) return true;
        else if(p.hasPermission("legendarena.rank.twitch") && r == TWITCH) return true;
        else if(p.hasPermission("legendarena.rank.youtube") && r == YOUTUBE) return true;
        else if(p.hasPermission("legendarena.rank.famous") && r == FAMOUS) return true;
        else if(p.hasPermission("legendarena.rank.memberplus") && r == MEMBERPLUS) return true;
        else return false;
    }

    /**
     * @param p The player to check
     * @return The player's rank
     */
    public static Rank getRank(Player p) {
        if(p instanceof ConsoleCommandSender) return ADMIN;
        else if(p.getName().equals("ThePixelDev") || p.getName().equals("JadenJFilms")) return FOUNDER;
        else if(p.hasPermission("legendarena.rank.admin")) return ADMIN;
        else if(p.hasPermission("legendarena.rank.srmod")) return SRMOD;
        else if(p.hasPermission("legendarena.rank.mod")) return MOD;
        else if(p.hasPermission("legendarena.rank.helper")) return HELPER;
        else if(p.hasPermission("legendarena.rank.twitch")) return TWITCH;
        else if(p.hasPermission("legendarena.rank.youtube")) return YOUTUBE;
        else if(p.hasPermission("legendarena.rank.vip")) return FAMOUS;
        else if(p.hasPermission("legendarena.rank.memberplus")) return MEMBERPLUS;
        else return MEMBER;
    }

    /**
     * Gets the rank prefix (i.e. colored rank) for a rank.
     * @param r The rank to get the prefix for
     * @return The prefix
     */
    public static String getRankPrefix(Rank r) {
        if(r == FOUNDER) return ChatColor.DARK_RED + "" + ChatColor.BOLD + "Founder";
        else if(r == ADMIN) return ChatColor.RED + "Admin";
        else if(r == SRMOD) return ChatColor.RED + "SrMod";
        else if(r == MOD) return ChatColor.GREEN + "Mod";
        else if(r == HELPER) return ChatColor.GREEN + "Helper";
        else if(r == TWITCH) return ChatColor.GOLD + "Twitch";
        else if(r == YOUTUBE) return ChatColor.GOLD + "YouTuber";
        else if(r == FAMOUS) return ChatColor.GOLD + "Famous";
        else if(r == MEMBERPLUS) return ChatColor.BLUE + "Member+";
        else return ChatColor.GRAY + "Member";
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
                return ChatColor.BLUE + p.getName();
            case SRMOD:
                return ChatColor.BLUE + p.getName();
            case MOD:
                return ChatColor.AQUA + p.getName();
            case HELPER:
                return ChatColor.AQUA + p.getName();
            case TWITCH:
                return ChatColor.DARK_PURPLE + p.getName();
            case YOUTUBE:
                char[] nameChars = p.getName().toCharArray();
                String b = "";
                int c = 0;
                ChatColor color = ChatColor.RED;
                for(char a : nameChars) {
                    if(nameChars.length >= 12) {
                        if(c == 4) {
                            color = (color == ChatColor.RED ? ChatColor.WHITE : ChatColor.RED);
                        }
                        c++;
                    } else if(nameChars.length >= 8 && nameChars.length <= 11) {
                        if(c == 2) {
                            color = (color == ChatColor.RED ? ChatColor.WHITE : ChatColor.RED);
                        }
                        c++;
                    } else {
                        color = (color == ChatColor.RED ? ChatColor.WHITE : ChatColor.RED);
                    }
                    b = b + color + a;
                }
                return b;
            case FAMOUS:
                return ChatColor.GOLD + p.getName();
            case MEMBERPLUS:
                return ChatColor.DARK_BLUE + p.getName();
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
            case TWITCH:
                return PluginUtils.msgWarning + "Minimum rank required: " + ChatColor.GOLD + "TWITCH";
            case YOUTUBE:
                return PluginUtils.msgWarning + "Minimum rank required: " + ChatColor.GOLD + "YOUTUBE";
            case FAMOUS:
                return PluginUtils.msgWarning + "Minimum rank required: " + ChatColor.GOLD + "FAMOUS";
            case MEMBERPLUS:
                return PluginUtils.msgWarning + "Minimum rank required: " + ChatColor.GOLD + "MEMBER+";
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
            case TWITCH:
                return ChatColor.DARK_PURPLE + name;
            case YOUTUBE:
                char[] nameChars = name.toCharArray();
                String b = "";
                int c = 0;
                ChatColor color = ChatColor.RED;
                for(char a : nameChars) {
                    if(nameChars.length >= 12) {
                        if(c == 4) {
                            color = (color == ChatColor.RED ? ChatColor.WHITE : ChatColor.RED);
                        }
                        c++;
                    } else if(nameChars.length >= 8 && nameChars.length <= 11) {
                        if(c == 2) {
                            color = (color == ChatColor.RED ? ChatColor.WHITE : ChatColor.RED);
                        }
                        c++;
                    } else {
                        color = (color == ChatColor.RED ? ChatColor.WHITE : ChatColor.RED);
                    }
                    b = b + color + a;
                }
                return b;
            case FAMOUS:
                return ChatColor.GOLD + name;
            case MEMBERPLUS:
                return ChatColor.DARK_BLUE + name;
            default:
                return ChatColor.GRAY + name;
        }
    }
}
