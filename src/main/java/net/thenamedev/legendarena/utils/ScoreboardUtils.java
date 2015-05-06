package net.thenamedev.legendarena.utils;

import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

/**
 * Created on 3/30/15
 *
 * @author ThePixelDev
 */
public class ScoreboardUtils {

    private static final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private static final Scoreboard board = manager.getNewScoreboard();
    private static boolean init = false;

    public static final Teams teams = new Teams();

    public static void init() {
        if(init) return;

        Bukkit.getScheduler().runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("LegendArena"), new MainTimer(), 40l, 40l);

        init = true;
    }

    public static boolean needsUpdate(Player p) {
        return false;
    }

    @SuppressWarnings("AccessStaticViaInstance")
    public static void removePlayer(Player p) {
        teams.founder.removePlayer(p);
    }

    public static void registerPlayer(Player p) {
        init();
        if(!needsUpdate(p)) return;
        removePlayer(p);
        switch(Rank.getRank(p)) {
            case FOUNDER:
                break;
        }
    }

    private static class MainTimer implements Runnable {

        public void run() {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(!needsUpdate(p)) continue;
                registerPlayer(p);
            }
        }

    }

    private static class Teams {

        public static Team founder;
        public static Team admin;
        public static Team srmod;
        public static Team mod;
        public static Team helper;
        public static Team vip;
        public static Team member;

        public Teams() {
            founder = board.registerNewTeam("Founders");
            admin = board.registerNewTeam("Admins");
            srmod = board.registerNewTeam("SrMods");
            mod = board.registerNewTeam("Mods");
            helper = board.registerNewTeam("Helpers");
            vip = board.registerNewTeam("VIPs");
            member = board.registerNewTeam("Members");

            founder.setPrefix(Rank.getRankPrefix(Rank.FOUNDER) + ChatColor.GRAY + " | " + ChatColor.DARK_AQUA);
            admin.setPrefix(Rank.getRankPrefix(Rank.ADMIN) + ChatColor.GRAY + " | " + ChatColor.DARK_RED);
        }

    }

}
