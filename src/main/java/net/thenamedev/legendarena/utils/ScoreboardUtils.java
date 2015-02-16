package net.thenamedev.legendarena.utils;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scoreboard.*;

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

    public static Team admin = null;
    public static Team mod = null;
    public static Team helper = null;
    public static Team vip = null;
    public static Team member = null;

    //Special ranks [only given to super-magical-special people]
    public static Team opDev = null;
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
        mod.setPrefix("§5[Mod] §6");
        helper.setPrefix("§1[Helper] §a");
        vip.setPrefix("§6[VIP] §b");
        member.setPrefix("§9[Member] §e");

        admin.setPrefix("§a[Admin] §c");
        admin.setSuffix("§5 [GM]");

        //Special ranks prefix/suffixes
        opDev.setPrefix("§4[Dev] §c");
        opDev.setSuffix("§5 [GM]");

        ownOp.setPrefix("§a[Owner] §4");
        ownOp.setSuffix("§5 [GM]");
    }

    public static void addPlayerToTeam(Team t, Player p) {
        p.setScoreboard(sb);
        if(t == null) {
            member.addPlayer(p);
            return; //Add the player to the member team and return out - we don't want NPE's spamming console
        }
        t.addPlayer(p);
    }

    /**
     * Small hacky in-line class to make sure everyone is on the same scoreboard
     *
     * don't ask why I thought this was a great idea, I was probably drunk at the time of making this
     */
    public static class MakeSureNothingBreaks implements Runnable {
        public void run() {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.setScoreboard(sb);
            }
        }
    }

}
