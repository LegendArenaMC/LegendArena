package net.thenamedev.legendarena.utils;

import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

/**
 * Tutorial used is <a href="http://bukkit.org/threads/tutorial-scoreboards-teams-with-the-bukkit-api.139655/">here</a>
 * <br><br>
 * I'm not sure if I need to give "official" credit, but I might as well say this much..
 * @author ThePixelDev
 */
public class ScoreboardUtils {

    private static final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private static final Scoreboard sb = manager.getNewScoreboard();

    //I trust you to not override these. Don't be evil.

    public static Team gm = null;
    public static Team mod = null;
    public static Team srmod = null;
    public static Team helper = null;
    public static Team vip = null;
    public static Team memberPlus = null;
    public static Team member = null;

    //Special ranks
    public static Team dev = null;
    public static Team owner = null;

    public static void addPlayerToTeam(Team t, Player p) {
        p.setScoreboard(sb);
        if(t == null) {
            if(member != null) {
                member.addPlayer(p);
            }
            return; //Add the player to the member team and exit the call - we don't want NPE's spamming console
        }
        t.addPlayer(p);
    }

    public static boolean requiresRefresh(Player p) {
        if(getScoreboardTeam(p) == member)
            return (member == null || !member.hasPlayer(p));
        else if(p == null)
            throw new MistakesWereMadeException("Player cannot be null");
        //if(!p.isOnline())
            //throw new MistakesWereMadeException("Player is not online");
        return getScoreboardTeam(p).hasPlayer(p);
    }

    @SuppressWarnings("ConstantConditions")
    public static void registerTeams() {
        //Init the teams
        if(gm == null)
            gm = sb.registerNewTeam("GMs");
        if(srmod == null)
            srmod = sb.registerNewTeam("SrMod");
        if(mod == null)
            mod = sb.registerNewTeam("Mods");
        if(helper == null)
            helper = sb.registerNewTeam("Helpers");
        if(member == null)
            member = sb.registerNewTeam("Members");
        if(vip == null)
            vip = sb.registerNewTeam("VIPs");
        if(memberPlus == null)
            memberPlus = sb.registerNewTeam("MemberPlus");

        //Special teams
        if(dev == null)
            dev = sb.registerNewTeam("Dev");
        if(owner == null)
            owner = sb.registerNewTeam("Owner");

        try {
            //Set prefixes
            mod.setPrefix("§5Mod §8| §a");
            srmod.setPrefix("§cSrMod §8 | §a");
            helper.setPrefix("§1Helper §8| §a");
            vip.setPrefix("§6VIP §8| §5");
            member.setPrefix("§9Member §8| §e");
            memberPlus.setPrefix("§9Member+ §8| §5");
            gm.setPrefix("§4GM §8| §a");
            owner.setPrefix("§4Owner §8| §a");
            dev.setPrefix("§4Dev §8| §a");
        } catch(NullPointerException ex) {
            Bukkit.getLogger().severe("Error while registering nnameplate/tab list prefixes - exiting...");
            return;
        }

        try {
            mod.setSuffix(" §8| §5" + PluginUtils.chars[6]);
            srmod.setSuffix(" §8| §5" + PluginUtils.chars[6]);
            helper.setSuffix(" §8| §5" + PluginUtils.chars[6]);
            gm.setSuffix(" §8| §5" + PluginUtils.chars[6]);
            owner.setSuffix(" §8| §5" + PluginUtils.chars[8]);
            dev.setSuffix(" §8| §5" + PluginUtils.chars[8]);
        } catch(NullPointerException ex) {
            Bukkit.getLogger().warning("Cannot register suffixes; continuing (hey, the suffixes are just asthetical anyways..)");
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new FixThings(), 40l, 40l);
    }

    @SuppressWarnings("ConstantConditions")
    public static void removePlayer(Player p) {
        try {
            member.removePlayer(p);
            vip.removePlayer(p);
            helper.removePlayer(p);
            mod.removePlayer(p);
            gm.removePlayer(p);
            dev.removePlayer(p);
            owner.removePlayer(p);
        } catch(NullPointerException ignore) {}
    }

    // 10/10 best class name ever -IGN
    public static class FixThings implements Runnable {
        public void run() {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(!requiresRefresh(p))
                    continue;
                if(p == null)
                    continue;
                removePlayer(p);
                addPlayerToTeam(getScoreboardTeam(p), p);
            }
        }
    }

    /**
     * Gets the correct scoreboard team for the specified player.
     * @param p The player to target
     * @return The targeted team
     */
    public static Team getScoreboardTeam(Player p) {
        Rank r = Rank.getRank(p);
        if(r == Rank.Owner) {
            return owner;
        } else if(r == Rank.Dev) {
            return dev;
        } else if(r == Rank.GM) {
            return gm;
        } else if(r == Rank.Mod || r == Rank.SrMod) {
            return mod;
        } else if(r == Rank.Helper) {
            return helper;
        } else if(r == Rank.VIP) {
            return vip;
        } else {
            return member;
        }
    }

}
