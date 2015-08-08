package legendapi.utils;

import org.bukkit.ChatColor;
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

    public static String noPermissions(Rank r) {
        return ChatUtils.getCustomMsg("Rank") + "Minimum rank required: " + ChatColor.YELLOW + r;
    }

}
