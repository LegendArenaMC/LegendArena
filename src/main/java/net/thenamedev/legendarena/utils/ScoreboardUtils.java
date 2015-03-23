package net.thenamedev.legendarena.utils;

import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.OldRank;
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
    public static final Scoreboard sb = manager.getNewScoreboard();

    //I trust you to not override these. Don't be evil.

    public static Team admin = null;
    public static Team mod = null;
    public static Team srmod = null;
    public static Team helper = null;
    public static Team memberPlus = null;
    public static Team member = null;

    public static Team twitch = null;
    public static Team youtube = null;
    public static Team famous = null;

    //Special ranks
    //public static Team dev = null;
    public static Team founder = null;

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
            return !member.hasPlayer(p);
        //else if(p == null)
            //throw new MistakesWereMadeException("Player cannot be null");
        //if(!p.isOnline())
            //throw new MistakesWereMadeException("Player is not online");
        return !getScoreboardTeam(p).hasPlayer(p);
    }

    @SuppressWarnings("ConstantConditions")
    public static void registerTeams() {
        //Init the teams
        if(admin == null)
            admin = sb.registerNewTeam("Admins");
        if(srmod == null)
            srmod = sb.registerNewTeam("SrMod");
        if(mod == null)
            mod = sb.registerNewTeam("Mods");
        if(helper == null)
            helper = sb.registerNewTeam("Helpers");
        if(member == null)
            member = sb.registerNewTeam("Members");
        if(famous == null)
            famous = sb.registerNewTeam("Famous");
        if(twitch == null)
            twitch = sb.registerNewTeam("Twitch");
        if(youtube == null)
            youtube = sb.registerNewTeam("YouTube");
        if(memberPlus == null)
            memberPlus = sb.registerNewTeam("MemberPlus");

        //Special teams
        //if(dev == null)
            //dev = sb.registerNewTeam("Dev");
        if(founder == null)
            founder = sb.registerNewTeam("Founder");

        try {
            //Set prefixes
            mod.setPrefix("§5Mod §8| §a");
            srmod.setPrefix("§cSrMod §8 | §a");
            helper.setPrefix("§1Helper §8| §a");
            twitch.setPrefix("§6Streamer §8| §5");
            youtube.setPrefix("§6YouTuber §8| §5");
            famous.setPrefix("§6Famous §8| §5");
            member.setPrefix("§9Member §8| §e");
            memberPlus.setPrefix("§9Member+ §8| §5");
            admin.setPrefix("§4Admin §8| §a");
            founder.setPrefix("§4§lFounder §8| §a");
            //dev.setPrefix("§4Dev §8| §a");
        } catch(NullPointerException ex) {
            Bukkit.getLogger().severe("Error while registering nnameplate/tab list prefixes - exiting...");
            return;
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new FixThings(), 40l, 40l);
    }

    @SuppressWarnings("ConstantConditions")
    public static void removePlayer(Player p) {
        try {
            member.removePlayer(p);
            famous.removePlayer(p);
            twitch.removePlayer(p);
            youtube.removePlayer(p);
            helper.removePlayer(p);
            mod.removePlayer(p);
            admin.removePlayer(p);
            //dev.removePlayer(p);
            founder.removePlayer(p);
        } catch(NullPointerException ignore) {}
    }

    public static class FixThings implements Runnable {
        public void run() {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(!requiresRefresh(p))
                    continue;
                if(p == null || !p.isOnline())
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
        switch(r) {
            case FOUNDER:
                return founder;
            //case DEV:
                //return dev;
            case ADMIN:
                return admin;
            case MOD:
                return mod;
            case SRMOD:
                return mod;
            case HELPER:
                return helper;
            case FAMOUS:
                return famous;
            case TWITCH:
                return twitch;
            case YOUTUBE:
                return youtube;
            case MEMBERPLUS:
                return memberPlus;

            default:
                return member;
        }
    }

}
