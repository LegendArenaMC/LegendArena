package legendarena.utils;

import legendapi.LegendAPI;
import legendapi.utils.setup.SetupUtils;
import legendarena.chat.ChatSystem;
import legendarena.commands.*;
import legendarena.commands.staff.*;
import legendarena.hub.HubWarper;
import legendarena.hub.particles.ParticleCore;
import legendarena.listeners.CommandFilterListener;
import legendarena.listeners.PlayerJoinListener;
import legendarena.listeners.ServerPingListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Initialization utilities.
 *
 * @author ThePixelDev
 */
public class InitUtils {

    private static boolean init = false;
    private static SetupUtils setup = new SetupUtils(Bukkit.getPluginManager().getPlugin("LegendArena"));

    /**
     * Initalize the entire plugin.
     */
    public static void init() {
        if(init) return;

        setup.announceStatus("Starting Legend Arena plugin v1.0-SNAPSHOT with API version " + LegendAPI.apiVersion + " \"" + LegendAPI.versionCodename + "\".");
        FileConfiguration conf = Bukkit.getPluginManager().getPlugin("LegendArena").getConfig();
        if(!conf.getBoolean("debug")) {
            setup.announceStatus("Debug mode is turned off in the config, thus all further debug messages will be disabled.");
            LegendAPI.debug = false;
        } else
            LegendAPI.debug = true;

        setup.announceStatus("Loading commands...");

        setup.registerCommand(new Staff(), "staff");
        setup.registerCommand(new Help(), "help");
        setup.registerCommand(new EmeraldCmd(), "emeralds");
        setup.registerCommand(new Firework(), "firework");
        setup.registerCommand(new Dev(), "dev");
        setup.registerCommand(new Gadgets(), "gadgets");
        setup.registerCommand(new Particle(), "particles");
        setup.registerCommand(new Fly(), "fly");
        setup.registerCommand(new Warp(), "warp");
        setup.registerCommand(new StaffList(), "stafflist");
        setup.registerCommand(new Chat(), "chat");
        setup.registerCommand(new Troll(), "troll");

        setup.announceStatus("Loaded commands successfully.");
        setup.announceStatus("Loading aliases...");

        setup.setAliases("chat", "c", "sc", "say");
        setup.setAliases("particles", "ps", "particle");
        setup.setAliases("stafflist", "sl", "liststaff");
        setup.setAliases("firework", "fw");

        setup.announceStatus("Loaded aliases successfully.");
        setup.announceStatus("Loading listeners...");

        setup.registerListener(new ServerPingListener());
        setup.registerListener(new PlayerJoinListener());
        setup.registerListener(new ChatSystem.ChatListener());
        setup.registerListener(new CommandFilterListener());
        setup.registerListener(new HubWarper());

        setup.announceStatus("Loaded listeners successfully.");
        setup.announceStatus("Loading timers...");

        setup.registerTimer(new ParticleCore(), 5l);
        setup.registerTimer(new HubWarper.InitPlayers(), 10l);

        setup.announceStatus("Loaded timers sucessfully.");
        //announceStatus("Loading one-off things...");

        //

        //announceStatus("Loaded one-off things sucessfully.");
        setup.announceStatus("Done loading!");

        init = true;
    }

}
