package net.thenamedev.legendarena.utils;

import net.thenamedev.legendapi.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.*;

/**
 * Tutorial used is <a href="http://bukkit.org/threads/tutorial-scoreboards-teams-with-the-bukkit-api.139655/">here</a>
 * <br><br>
 * I'm not sure if I need to give official credit, but I might as well say this much..
 * @author TheNameMan
 */
public class ScoreboardUtils {

    private static final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private static final Scoreboard sb = manager.getNewScoreboard();

    //I trust you to not override these. Don't be evil.

    @Nullable
    public static Team gm = null;
    @Nullable
    public static Team mod = null;
    @Nullable
    public static Team helper = null;
    @Nullable
    public static Team vip = null;
    @Nullable
    public static Team member = null;

    //Special ranks
    @Nullable
    public static Team dev = null;
    @Nullable
    public static Team owner = null;

    public static void addPlayerToTeam(@Nullable Team t, @NotNull Player p) {
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
        if(getScoreboardTeam(p) == null) {
            return true;
        }
        if(!getScoreboardTeam(p).hasPlayer(p)) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static void registerTeams() {
        //Init the teams
        gm = sb.registerNewTeam("GMs");
        mod = sb.registerNewTeam("Mods");
        helper = sb.registerNewTeam("Helpers");
        member = sb.registerNewTeam("Members");
        vip = sb.registerNewTeam("VIPs");

        //Special teams
        dev = sb.registerNewTeam("Dev");
        owner = sb.registerNewTeam("Owner");

        try {
            //Set prefixes
            mod.setPrefix("§5Mod §8| §6");
            helper.setPrefix("§1Helper §8| §a");
            vip.setPrefix("§6VIP §8| §b");
            member.setPrefix("§9Member §8| §e");
            gm.setPrefix("§4GM §8| §c");

            //Special ranks prefix/suffixes
            owner.setPrefix("§aOwner §8| §4");
            dev.setPrefix("§4Dev §8| §c");
        } catch(NullPointerException ex) {
            Bukkit.getLogger().severe("Error while registering prefixes - exiting...");
            return;
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

    public static class FixThings implements Runnable {
        public void run() {
            for(@NotNull Player p : Bukkit.getOnlinePlayers()) {
                if(!requiresRefresh(p)) {
                    continue;
                }
                removePlayer(p);
                addPlayerToTeam(getScoreboardTeam(p), p);
            }
        }
    }

    /**
     * Gets the correct scoreboard team for the specified player.
     * @param p The player to target
     * @return The targeted team (null if member/unknown)
     */
    public static Team getScoreboardTeam(Player p) {
        Rank r = Rank.getRank(p);
        if(r == Rank.Owner) {
            return ScoreboardUtils.owner;
        } else if(r == Rank.Dev) {
            return ScoreboardUtils.dev;
        } else if(r == Rank.GM) {
            return ScoreboardUtils.gm;
        } else if(r == Rank.Mod || r == Rank.SrMod) { //srmod is stuck in here temporarily so I don't have to make another team right now
            return ScoreboardUtils.mod;
        } else if(r == Rank.Helper) {
            return ScoreboardUtils.helper;
        } else if(r == Rank.VIP) {
            return ScoreboardUtils.vip;
        } else {
            return null;
        }
    }

}
