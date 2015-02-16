package net.thenamedev.legendarena.utils;

import net.thenamedev.legendarena.commands.*;
import net.thenamedev.legendarena.commands.dev.*;
import net.thenamedev.legendarena.commands.staff.*;
import net.thenamedev.legendarena.extras.history.*;
import net.thenamedev.legendarena.extras.particles.*;
import net.thenamedev.legendarena.listeners.*;
import net.thenamedev.legendarena.extras.staffchat.*;
import net.thenamedev.legendarena.utils.ScoreboardUtils.MakeSureNothingBreaks;
import org.bukkit.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class InitUtils {

    private static boolean init = false;

    public static void pluginInit() {
        if(init)
            throw new NullPointerException();
        registerListeners();
        registerCommands();
        ScoreboardUtils.registerTeams();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new ParticleCore(), 10l, 10l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new MakeSureNothingBreaks(), 40l, 40l);
        init = true;
    }

    private static void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new ChatListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new MobSpawnListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new StaffChat(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new BanHammerListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
    }

    private final static List<String> firework = new ArrayList<>();
    private final static List<String> say = new ArrayList<>();
    private final static List<String> clearchat = new ArrayList<>();
    private final static List<String> chat = new ArrayList<>();
    private final static List<String> particles = new ArrayList<>();

    private static void registerCommands() {
        registerAliases();
        // Commands
        Bukkit.getPluginCommand("namehistory").setExecutor(new UsernameHistory()); //Username history command [/namehistory]
        Bukkit.getPluginCommand("say").setExecutor(new Say()); //Say command [/say, /alert]
        Bukkit.getPluginCommand("firework").setExecutor(new Firework()); //Firework command [/firework, /fw]
        Bukkit.getPluginCommand("clearchat").setExecutor(new ClearChat()); //Clearchat command [/clearchat, /cc]
        Bukkit.getPluginCommand("chat").setExecutor(new Chat()); //Chat command [/c, /channel, /sc]
        Bukkit.getPluginCommand("updatescoreboard").setExecutor(new UpdateScoreboard()); //Update scoreboard command [/updatescoreboard]
        Bukkit.getPluginCommand("globalmute").setExecutor(new GlobalMute()); //Globalmute command [/gmute]
        Bukkit.getPluginCommand("userinfo").setExecutor(new Info()); //Userinfo command [/userinfo]
        Bukkit.getPluginCommand("particles").setExecutor(new Particle()); //Particle selector command [/particles, /particle, /ps]
        Bukkit.getPluginCommand("warn").setExecutor(new Warn()); //Warn command [/warn]
        Bukkit.getPluginCommand("warp").setExecutor(new Warp()); //Warp command [/warp]
        Bukkit.getPluginCommand("freeze").setExecutor(new Freeze()); //Freeze command [/freeze]
        Bukkit.getPluginCommand("banhammer").setExecutor(new BanHammer()); //Ban hammer command [/banhammer]
        // Aliases
        Bukkit.getPluginCommand("clearchat").setAliases(clearchat); //Clearchat alias
        Bukkit.getPluginCommand("chat").setAliases(chat); //Chat aliases
        Bukkit.getPluginCommand("say").setAliases(say); //Say alias
        Bukkit.getPluginCommand("particles").setAliases(particles); //Particles aliases
        Bukkit.getPluginCommand("firework").setAliases(firework); //Firework alias
    }

    private static void registerAliases() {
        firework.add("fw");
        clearchat.add("cc");
        chat.add("sc");
        say.add("alert");
        chat.add("c");
        particles.add("particle");
        particles.add("ps");
    }

}
