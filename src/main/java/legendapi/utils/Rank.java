/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public enum Rank {

    /**
     * Founder rank. (really just a renamed Owner rank in a sense)
     *
     * Polls SpecialListUtils like the Dev rank.
     */
    FOUNDER("", 7),
    /**
     * Developer rank.
     *
     * When the Rank class is asked if the member is a Developer, it polls the SpecialStaffListUtils class.
     */
    DEV("", 6),
    /**
     * Administrator rank.
     */
    ADMIN("legendarena.rank.admin", 5),
    /**
     * Moderator rank.
     */
    MOD("legendarena.rank.mod", 4),
    /**
     * Helper rank.
     */
    HELPER("legendarena.rank.helper", 3),
    /**
     * VIP rank.
     *
     * Requirements (any of the following):
     *
     * - 1,500+ subs (YT) / 2,000+ followers (Twitch) and 4+ videos on LA / stream at least three times on LA
     *
     * - Avg. 1k+ views per video / avg. 700+ viewers per stream
     *
     * - Founder/Owner (whichever one you prefer I guess) descretion (overrides the above two)
     */
    VIP("legendarena.rank.vip", 2),
    /**
     * Donor rank. Only gives cosmetic options.
     *
     * Planned to be 10 dollars in the Legend Arena store.
     */
    MEMBERPLUS("legendarena.rank.memberplus", 1),
    /**
     * Default user rank. This is only here as a catch-all, really. Not much else use.
     */
    MEMBER("", 0);

    private int internalId;
    private String permission;

    Rank(String permission, int internalId) {
        this.permission = permission;
        this.internalId = internalId;
    }

    /**
     * Is a player ranked?
     */
    public boolean isRanked(CommandSender p) {
        if(p instanceof ConsoleCommandSender) return true;

        switch(this) {
            case MEMBER:
                return true;
            case DEV:
                return SpecialStaffListUtils.isDeveloper((Player) p);
            case FOUNDER:
                return SpecialStaffListUtils.isFounder((Player) p);

            default:
                return p.hasPermission(permission);
        }
    }

    /**
     * Yes, I realize it's Color, not Colour. Fuck it, too lazy to go through more code to fix it.
     */
    public ChatColor getNameColour() {
        switch(this) {
            case FOUNDER:
                return ChatColor.LIGHT_PURPLE;
            case DEV:
                return ChatColor.DARK_PURPLE;
            case ADMIN:
                return ChatColor.DARK_RED;
            case MOD:
                return ChatColor.RED;
            case HELPER:
                return ChatColor.AQUA;
            case VIP:
                return ChatColor.GOLD;
            case MEMBERPLUS:
                return ChatColor.BLUE;

            default:
                return ChatColor.GRAY;
        }
    }

    public int getInternalId() {
        return internalId;
    }

}
