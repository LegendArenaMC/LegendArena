package legendarena.utils;

import legendapi.LegendAPI;
import legendapi.message.Message;
import legendapi.utils.ChatUtils;
import legendapi.utils.Rank;
import legendarena.chat.ChatSystem;
import legendarena.commands.*;
import legendarena.commands.staff.*;
import legendarena.hub.HubWarper;
import legendarena.hub.particles.ParticleCore;
import legendarena.listeners.CommandFilterListener;
import legendarena.listeners.PlayerJoinListener;
import legendarena.listeners.ServerPingListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Initialization utilities.
 *
 * @author ThePixelDev
 */
public class InitUtils {

    private static boolean init = false;

    /**
     * Initalize the entire plugin.
     */
    public static void init() {
        if(init) return;

        announceStatus("Starting Legend Arena plugin v1.0-SNAPSHOT with API version " + LegendAPI.apiVersion + " \"" + LegendAPI.versionCodename + "\".");
        FileConfiguration conf = Bukkit.getPluginManager().getPlugin("LegendArena").getConfig();
        if(!conf.getBoolean("debug")) {
            announceStatus("Debug mode is turned off in the config, thus all further debug messages will be disabled.");
            LegendAPI.debug = false;
        } else
            LegendAPI.debug = true;

        announceStatus("Loading commands...");

        registerCommand(new Staff(), "staff");
        registerCommand(new Help(), "help");
        registerCommand(new EmeraldCmd(), "emeralds");
        registerCommand(new Firework(), "firework");
        registerCommand(new Dev(), "dev");
        registerCommand(new Gadgets(), "gadgets");
        registerCommand(new Particle(), "particles");
        registerCommand(new Fly(), "fly");
        registerCommand(new Warp(), "warp");
        registerCommand(new StaffList(), "stafflist");
        registerCommand(new Chat(), "chat");
        registerCommand(new Troll(), "troll");

        announceStatus("Loaded commands successfully.");
        announceStatus("Loading aliases...");

        setAliases("chat", "c", "sc", "say");
        setAliases("particles", "ps", "particle");
        setAliases("stafflist", "sl", "liststaff");
        setAliases("firework", "fw");

        announceStatus("Loaded aliases successfully.");
        announceStatus("Loading listeners...");

        registerListener(new ServerPingListener());
        registerListener(new PlayerJoinListener());
        registerListener(new ChatSystem.ChatListener());
        registerListener(new CommandFilterListener());
        registerListener(new HubWarper());

        announceStatus("Loaded listeners successfully.");
        announceStatus("Loading timers...");

        registerTimer(new ParticleCore(), 5l);
        registerTimer(new HubWarper.InitPlayers(), 10l);

        announceStatus("Loaded timers sucessfully.");
        //announceStatus("Loading one-off things...");

        //

        //announceStatus("Loaded one-off things sucessfully.");
        announceStatus("Done loading!");

        init = true;
    }

    private static void announceStatus(String m) {
        if(!LegendAPI.debug) return;
        new Message().append(ChatUtils.getCustomMsg("Startup Debug") + m).broadcast(Rank.ADMIN);
    }

    private static void registerListener(Listener l) {
        Bukkit.getPluginManager().registerEvents(l, Bukkit.getPluginManager().getPlugin("LegendArena"));
    }

    private static void registerCommand(CommandExecutor c, String n) {
        Bukkit.getPluginCommand(n).setExecutor(c);
    }

    private static void registerTimer(Runnable r, long t) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("LegendArena"), r, t, t);
    }

    private static void setAliases(String t, String... l) {
        List<String> a = new ArrayList<>();
        Collections.addAll(a, l);
        Bukkit.getPluginCommand(t).setAliases(a);
    }

}
