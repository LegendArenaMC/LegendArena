package net.thenamedev.legendarena.utils;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.*;

/**
 * Tutorial used is <a href="http://bukkit.org/threads/tutorial-scoreboards-teams-with-the-bukkit-api.139655/">here</a>
 * <br><br>
 * I'm not sure if I need to give official credit, but I might as well say this much
 * @author TheNameMan
 */
public class ScoreboardUtils {

    private static final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private static final Scoreboard sb = manager.getNewScoreboard();

    //I trust you to not override these. Don't be evil.

    @Nullable
    public static Team admin = null;
    @Nullable
    public static Team mod = null;
    @Nullable
    public static Team helper = null;
    @Nullable
    public static Team vip = null;
    @Nullable
    public static Team member = null;

    //Special ranks [only given to super-magical-special people]
    @Nullable
    public static Team opDev = null;
    @Nullable
    public static Team ownOp = null;

    public static void registerTeams() {
        //Init the teams
        admin = sb.registerNewTeam("Admins");
        mod = sb.registerNewTeam("Mods");
        helper = sb.registerNewTeam("Helpers");
        member = sb.registerNewTeam("Members");
        vip = sb.registerNewTeam("VIPs");

        //Special teams
        opDev = sb.registerNewTeam("OpDev");
        ownOp = sb.registerNewTeam("OwnOp");

        //Set prefixes
        mod.setPrefix("§5Mod §8| §6");
        helper.setPrefix("§1Helper §8| §a");
        vip.setPrefix("§6VIP §8| §b");
        member.setPrefix("§9Member §8| §e");

        admin.setPrefix("§aAdmin §8| §c");
        admin.setSuffix("§8 |§5 GM");

        //Special ranks prefix/suffixes
        opDev.setPrefix("§4Dev §8| §c");
        opDev.setSuffix("§8 |§5 GM");

        ownOp.setPrefix("§aOwner §8| §4");
        ownOp.setSuffix("§8 |§5 GM");
    }

    public static void addPlayerToTeam(@Nullable Team t, @NotNull Player p) {
        p.setScoreboard(sb);
        if(t == null) {
            member.addPlayer(p);
            return; //Add the player to the member team and return out - we don't want NPE's spamming console
        }
        t.addPlayer(p);
    }

    /**
     * Small hacky in-line class to make sure everyone is on the same scoreboard
     * <br><br>
     * don't ask why I thought this was a great idea, I was most likely drunk at the time of making this
     */
    public static class MakeSureNothingBreaks implements Runnable {
        public void run() {
            for(@NotNull Player p : Bukkit.getOnlinePlayers()) {
                p.setScoreboard(sb);
            }
        }
    }

}
