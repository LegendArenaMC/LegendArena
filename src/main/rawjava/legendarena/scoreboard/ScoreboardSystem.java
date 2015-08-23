/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.scoreboard;

import legendapi.utils.Rank;
import legendapi.utils.RankUtils;
import legendarena.staffutils.VanishType;
import legendarena.staffutils.VanishUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ScoreboardSystem {

    private static ScoreboardManager sbm = Bukkit.getScoreboardManager();
    public final static Scoreboard sb = sbm.getNewScoreboard();
    private static Team founder, dev, admin,
            mod, helper, vip,
            memberplus, member, spectator;

    private static String[] colorCodes = {
            "§d",
            "§5",
            "§4",
            "§c",
            "§a",
            "§6",
            "§9",
            "§7",
            "§8"
    };

    private static boolean init = false;

    public static void init() {
        if(init) return;

        founder = sb.getTeam("Founder");
        dev = sb.getTeam("Dev");
        admin = sb.getTeam("Admin");
        mod = sb.getTeam("Mod");
        helper = sb.getTeam("Helper");
        vip = sb.getTeam("VIP");
        memberplus = sb.getTeam("MemberPlus");
        member = sb.getTeam("Member");
        spectator = sb.getTeam("Spectator");

        if(founder == null)
            founder = sb.registerNewTeam("Founder");

        founder.setPrefix(colorCodes[0]);

        if(dev == null)
            dev = sb.registerNewTeam("Dev");

        dev.setPrefix(colorCodes[1]);

        if(admin == null)
            admin = sb.registerNewTeam("Admin");

        admin.setPrefix(colorCodes[2]);

        if(mod == null)
            mod = sb.registerNewTeam("Mod");

        mod.setPrefix(colorCodes[3]);

        if(helper == null)
            helper = sb.registerNewTeam("Helper");

        helper.setPrefix(colorCodes[4]);

        if(vip == null)
            vip = sb.registerNewTeam("VIP");

        vip.setPrefix(colorCodes[5]);

        if(memberplus == null)
            memberplus = sb.registerNewTeam("MemberPlus");

        memberplus.setPrefix(colorCodes[6]);

        if(member == null)
            member = sb.registerNewTeam("Member");

        member.setPrefix(colorCodes[7]);

        if(spectator == null)
            spectator = sb.registerNewTeam("Spectator");

        spectator.setPrefix(colorCodes[8]);

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

    public static void setSpectator(Player p) {
        clearTeam(p);
        spectator.addEntry(p.getName());
    }

    public static void removeSpectator(Player p) {
        spectator.removeEntry(p.getName());
        setRank(p, RankUtils.getDisplayRank(p));
    }

    public static boolean isSpectator(Player p) {
        return spectator.hasEntry(p.getName());
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

    public static class Timer implements Runnable {

        public void run() {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(p.getScoreboard() != sb)
                    p.setScoreboard(sb);

                if(needsUpdate(p))
                    setRank(p, RankUtils.getDisplayRank(p));
            }
        }

        private static boolean needsUpdate(Player p) {
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

    }

}
