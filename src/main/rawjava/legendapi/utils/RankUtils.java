package legendapi.utils;

import legendapi.exceptions.DeprecatedException;
import legendapi.fanciful.FancyMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankUtils {

    public static Rank getRank(Player p) {
        if(Rank.DEV.isRanked(p)) return Rank.DEV; //ignore me, just forceful overriding of the founder rank with the dev rank
        else if(Rank.FOUNDER.isRanked(p)) return Rank.FOUNDER;
        else if(Rank.ADMIN.isRanked(p)) return Rank.ADMIN;
        else if(Rank.MOD.isRanked(p)) return Rank.MOD;
        else if(Rank.HELPER.isRanked(p)) return Rank.HELPER;
        else if(Rank.VIP.isRanked(p)) return Rank.VIP;
        else if(Rank.MEMBERPLUS.isRanked(p)) return Rank.MEMBERPLUS;

        else return Rank.MEMBER;
    }

    public static void fancyNoPermissions(Rank r, CommandSender p) {
        //return ChatUtils.getCustomMsg("Rank") + "Minimum rank required: " + ChatColor.YELLOW + r;
        new FancyMessage("Rank")
                    .color(ChatColor.GREEN)
                .then(" " + ChatUtils.specialCharacters[1] + " ")
                    .color(ChatColor.DARK_GRAY)
                .then("Minimum rank required: ")
                    .color(ChatColor.BLUE)
                .then(r.toString())
                    .color(ChatColor.RED)
                    .tooltip(ChatColor.GREEN + "How do I get this rank?\n" + ChatColor.YELLOW + howToGetRank(r))
                .send(p);
    }

    private static String howToGetRank(Rank r) {
        switch(r) {
            case MEMBERPLUS:
                return "Buy the Member Plus rank on the store! [coming soon[tm]]";
            case VIP:
                return "Run a YouTube channel with 5k+ subs, or stream with 3k+ viewers. Viewbots/subbots do not count.";
            case HELPER:
                return "Apply when we're looking for staff!";
            case MOD:
                return "Rank up from Helper by being a helpful staff member!";
            case ADMIN:
                return "Same as moderator, rank up from Mod by being a helpful staff member!";
            case DEV:
                return "Help out with the LA plugins enough that we hire you onto the dev team!";
            case FOUNDER:
                return "Sell your soul to Legend Arena. (just kidding - only founders gets this rank (duh))";

            default:
                return "Error - rank " + r + " was not recognized by howToGetRank()! (or it's the Member rank, in which case the plugin is drunk)";
        }
    }

}
