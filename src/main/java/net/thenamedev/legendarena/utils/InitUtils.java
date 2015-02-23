package net.thenamedev.legendarena.utils;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.*;
import net.thenamedev.legendarena.commands.*;
import net.thenamedev.legendarena.commands.dev.*;
import net.thenamedev.legendarena.commands.staff.*;
import net.thenamedev.legendarena.extras.hub.hideplayer.*;
import net.thenamedev.legendarena.extras.hub.particles.*;
import net.thenamedev.legendarena.extras.staffchat.*;
import net.thenamedev.legendarena.extras.warp.*;
import net.thenamedev.legendarena.listeners.*;
import org.bukkit.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class InitUtils {

    private static boolean init = false;

    private final static List<String> clearchat = new ArrayList<>();
    private final static List<String> chat = new ArrayList<>();
    private final static List<String> particles = new ArrayList<>();

    public static void pluginInit() {
        if(init)
            throw new NullPointerException();
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading COMMANDS");
        registerCommands();
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading LISTENERS");
        registerListeners();
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading SCOREBOARD TEAMS");
        ScoreboardUtils.registerTeams();
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading SCHEDULERS");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new ParticleCore(), 10l, 10l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new HubWarper.InitPlayers(), 20l, 20l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new HidePlayers(), 5l, 5l);
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Done loading!");
        init = true;
    }

    private static void registerListeners() {
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading SERVERPINGLISTENER...");
        Bukkit.getPluginManager().registerEvents(new ServerPingListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading HUBWARPER...");
        Bukkit.getPluginManager().registerEvents(new HubWarper(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading CHATLISTENER...");
        Bukkit.getPluginManager().registerEvents(new ChatListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading PLAYERMOVELISTENER...");
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading PLAYERJOINLISTENER...");
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading STAFFCHAT...");
        Bukkit.getPluginManager().registerEvents(new StaffChat(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading WORLDCHANGELISTENER...");
        Bukkit.getPluginManager().registerEvents(new WorldChangeListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading BANHAMMERLISTENER...");
        Bukkit.getPluginManager().registerEvents(new BanHammerListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Done loading listeners!");
    }

    private static void registerCommands() {
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading aliases...");
        registerAliases();
        // Commands
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /motdlist...");
        Bukkit.getPluginCommand("motdlist").setExecutor(new MOTDList()); //MOTD list command [/motdlist]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /firework...");
        Bukkit.getPluginCommand("firework").setExecutor(new Firework()); //Firework command [/firework, /fw]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /fly...");
        Bukkit.getPluginCommand("fly").setExecutor(new Fly()); //Toggle flight command [/fly]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /gadgets...");
        Bukkit.getPluginCommand("gadgets").setExecutor(new Gadgets()); //Hub gadgets toggle command [/gadgets]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /clearchat...");
        Bukkit.getPluginCommand("clearchat").setExecutor(new ClearChat()); //Clearchat command [/clearchat, /cc]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /chat...");
        Bukkit.getPluginCommand("chat").setExecutor(new Chat()); //Chat command [/c, /channel, /sc]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /updatescoreboard...");
        Bukkit.getPluginCommand("updatescoreboard").setExecutor(new UpdateScoreboard()); //Update scoreboard command [/updatescoreboard]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /globalmute...");
        Bukkit.getPluginCommand("globalmute").setExecutor(new GlobalMute()); //Globalmute command [/gmute]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /particles...");
        Bukkit.getPluginCommand("particles").setExecutor(new Particle()); //Particle selector command [/particles, /particle, /ps]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /warn...");
        Bukkit.getPluginCommand("warn").setExecutor(new Warn()); //Warn command [/warn]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /warp...");
        Bukkit.getPluginCommand("warp").setExecutor(new Warp()); //Warp command [/warp]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /freeze...");
        Bukkit.getPluginCommand("freeze").setExecutor(new Freeze()); //Freeze command [/freeze]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /banhammer...");
        Bukkit.getPluginCommand("banhammer").setExecutor(new BanHammer()); //Ban hammer command [/banhammer]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /lalookup...");
        Bukkit.getPluginCommand("lalookup").setExecutor(new LookupUser()); //User lookup command [/lalookup]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Loading /tokens...");
        Bukkit.getPluginCommand("tokens").setExecutor(new Token()); //Tokens command [/tokens]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Done loading commands; adding aliases...");
        // Aliases
        Bukkit.getPluginCommand("clearchat").setAliases(clearchat); //Clearchat alias
        Bukkit.getPluginCommand("chat").setAliases(chat); //Chat aliases
        Bukkit.getPluginCommand("particles").setAliases(particles); //Particles aliases
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast("Done loading aliases!");
    }

    private static void registerAliases() {
        clearchat.add("cc");
        chat.add("say");
        chat.add("sc");
        chat.add("c");
        particles.add("particle");
        particles.add("ps");
    }

}
