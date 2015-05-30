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
@SuppressWarnings("AccessStaticViaInstance")
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

    public static void removePlayer(Player p) {
        teams.founder.removePlayer(p);
    }

    public static void registerPlayer(Player p) {
        init();
        if(!needsUpdate(p)) return;
        removePlayer(p);
        switch(Rank.getRank(p)) {
            case FOUNDER:
                teams.founder.addPlayer(p);
                break;
            case ADMIN:
                teams.admin.addPlayer(p);
                break;
            case SRMOD:
                teams.srmod.addPlayer(p);
                break;
            case MOD:
                teams.mod.addPlayer(p);
                break;
            case HELPER:
                teams.helper.addPlayer(p);
                break;
            case YOUTUBE:
                teams.vip.addPlayer(p);
                break;
            case MEMBERPLUS:
                teams.memberplus.addPlayer(p);
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
        public static Team memberplus;

        public Teams() {
            founder = board.registerNewTeam("Founders");
            admin = board.registerNewTeam("Admins");
            srmod = board.registerNewTeam("SrMods");
            mod = board.registerNewTeam("Mods");
            helper = board.registerNewTeam("Helpers");
            vip = board.registerNewTeam("YouTube");
            memberplus = board.registerNewTeam("MemberPlus");

            founder.setPrefix(Rank.getRankPrefix(Rank.FOUNDER) + ChatColor.RESET + " ");
            admin.setPrefix(Rank.getRankPrefix(Rank.ADMIN) + ChatColor.RESET + " ");
            srmod.setPrefix(Rank.getRankPrefix(Rank.SRMOD) + ChatColor.RESET + " ");
            mod.setPrefix(Rank.getRankPrefix(Rank.MOD) + ChatColor.RESET + " ");
            helper.setPrefix(Rank.getRankPrefix(Rank.HELPER) + ChatColor.RESET + " ");
            vip.setPrefix(Rank.getRankPrefix(Rank.YOUTUBE) + ChatColor.RESET + " ");
            memberplus.setPrefix(Rank.getRankPrefix(Rank.MEMBERPLUS) + ChatColor.RESET + " ");
        }

    }

}
