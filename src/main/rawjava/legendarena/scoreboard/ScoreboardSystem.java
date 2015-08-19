/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.scoreboard;

import legendapi.utils.Rank;
import legendapi.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ScoreboardSystem {

    private static ScoreboardManager sbm = Bukkit.getScoreboardManager();
    public final static Scoreboard sb = sbm.getNewScoreboard();
    private static Team founder, dev, admin,
            mod, helper, vip,
            memberplus, member;

    private static boolean init = false;

    public static void init() {
        if(init) return; //I seriously hate having to make init() functions.
        //Especially if they're public static.

        founder = sb.getTeam("Founder");
        dev = sb.getTeam("Dev");
        admin = sb.getTeam("Admin");
        mod = sb.getTeam("Mod");
        helper = sb.getTeam("Helper");
        vip = sb.getTeam("VIP");
        memberplus = sb.getTeam("MemberPlus");
        member = sb.getTeam("Member");

        if(founder != null)
            founder.setPrefix("§d");
        else {
            founder = sb.registerNewTeam("Founder");
            founder.setPrefix("§d");
        }
        if(dev != null)
            dev.setPrefix("§5");
        else {
            dev = sb.registerNewTeam("Dev");
            dev.setPrefix("§5");
        }
        if(admin != null)
            admin.setPrefix("§4");
        else {
            admin = sb.registerNewTeam("Admin");
            admin.setPrefix("§4");
        }
        if(mod != null)
            mod.setPrefix("§c");
        else {
            mod = sb.registerNewTeam("Mod");
            mod.setPrefix("§c");
        }
        if(helper != null)
            helper.setPrefix("§a");
        else {
            helper = sb.registerNewTeam("Helper");
            helper.setPrefix("§a");
        }
        if(vip != null)
            vip.setPrefix("§6");
        else {
            vip = sb.registerNewTeam("VIP");
            vip.setPrefix("§6");
        }
        if(memberplus != null)
            memberplus.setPrefix("§9");
        else {
            memberplus = sb.registerNewTeam("MemberPlus");
            memberplus.setPrefix("§9");
        }
        if(member != null)
            member.setPrefix("§7");
        else {
            member = sb.registerNewTeam("Member");
            member.setPrefix("§7");
        }

        init = true;
    }

    public static void setRank(Player p, Rank r) {
        clearTeam(p);
        switch(r) {
            case FOUNDER:
                founder.addEntry(p.getName());
                break;
            case DEV:
                dev.addEntry(p.getName());
                break;
            case ADMIN:
                admin.addEntry(p.getName());
                break;
            case MOD:
                mod.addEntry(p.getName());
                break;
            case HELPER:
                helper.addEntry(p.getName());
                break;
            case VIP:
                vip.addEntry(p.getName());
                break;
            case MEMBERPLUS:
                memberplus.addEntry(p.getName());
                break;
            case MEMBER:
                member.addEntry(p.getName());
                break;
        }
    }

    public static void clearTeam(Player p) {
        founder.removeEntry(p.getName());
        dev.removeEntry(p.getName());
        admin.removeEntry(p.getName());
        mod.removeEntry(p.getName());
        helper.removeEntry(p.getName());
        vip.removeEntry(p.getName());
        memberplus.removeEntry(p.getName());
        member.removeEntry(p.getName());
    }

    public static boolean needsUpdate(Player p) {
        switch(RankUtils.getDisplayRank(p)) {
            case FOUNDER:
                return !founder.hasEntry(p.getName());
            case ADMIN:
                return !admin.hasEntry(p.getName());
            case MOD:
                return !mod.hasEntry(p.getName());
            case HELPER:
                return !helper.hasEntry(p.getName());
            case VIP:
                return !vip.hasEntry(p.getName());
            case MEMBERPLUS:
                return !memberplus.hasEntry(p.getName());
            case MEMBER:
                return !member.hasEntry(p.getName());

            default:
                return true;
        }
    }

    public static class TimerLoop implements Runnable {

        public void run() {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(p.getScoreboard() != sb)
                    p.setScoreboard(sb);
                if(needsUpdate(p))
                    setRank(p, RankUtils.getDisplayRank(p));
            }
        }

    }

}
