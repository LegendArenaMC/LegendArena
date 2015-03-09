package net.thenamedev.legendarena.utils;

import net.thenamedev.legendapi.tokens.TokenCore;
import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.*;
import net.thenamedev.legendarena.commands.*;
import net.thenamedev.legendarena.commands.Firework;
import net.thenamedev.legendarena.commands.dev.*;
import net.thenamedev.legendarena.commands.staff.*;
import net.thenamedev.legendarena.extras.hub.hideplayer.*;
import net.thenamedev.legendarena.extras.hub.particles.*;
import net.thenamedev.legendarena.extras.staffchat.*;
import net.thenamedev.legendarena.extras.warp.*;
import net.thenamedev.legendarena.listeners.*;
import org.bukkit.*;
import org.bukkit.entity.*;

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
            return;
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading COMMANDS");
        registerCommands();
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading LISTENERS");
        registerListeners();
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading SCOREBOARD TEAMS");
        ScoreboardUtils.registerTeams();
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading SCHEDULERS");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new ParticleCore(), 10l, 10l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new HubWarper.InitPlayers(), 20l, 20l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new HidePlayers(), 5l, 5l);
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading TOKENS BACKEND");
        TokenCore.init();
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(String.format("%sUtilizing tokens core v%s, codenamed \"%s\".", PluginUtils.msgDebug, TokenCore.ver, TokenCore.verName));
        else
            ChatUtils.broadcast(String.format("%sUtilizing tokens core v%s, codenamed \"%s\".", PluginUtils.msgDebug, TokenCore.ver, TokenCore.verName), Rank.Dev);
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Done loading!");
        init = true;
    }

    private static void registerListeners() {
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading SERVERPINGLISTENER...");
        Bukkit.getPluginManager().registerEvents(new ServerPingListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading HUBWARPER...");
        Bukkit.getPluginManager().registerEvents(new HubWarper(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading CHATLISTENER...");
        Bukkit.getPluginManager().registerEvents(new ChatListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading PLAYERMOVELISTENER...");
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading PLAYERJOINLISTENER...");
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading STAFFCHAT...");
        Bukkit.getPluginManager().registerEvents(new StaffChat(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading WORLDCHANGELISTENER...");
        Bukkit.getPluginManager().registerEvents(new WorldChangeListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading BANHAMMERLISTENER...");
        Bukkit.getPluginManager().registerEvents(new BanHammerListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Done loading listeners!");
    }

    private static void registerCommands() {
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading aliases...");
        registerAliases();
        // Commands
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /motdlist...");
        Bukkit.getPluginCommand("motdlist").setExecutor(new MOTDList()); //MOTD list command [/motdlist]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /firework...");
        Bukkit.getPluginCommand("firework").setExecutor(new Firework()); //Firework command [/firework, /fw]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /fly...");
        Bukkit.getPluginCommand("fly").setExecutor(new Fly()); //Toggle flight command [/fly]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /gadgets...");
        Bukkit.getPluginCommand("gadgets").setExecutor(new Gadgets()); //Hub gadgets toggle command [/gadgets]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /clearchat...");
        Bukkit.getPluginCommand("clearchat").setExecutor(new ClearChat()); //Clearchat command [/clearchat, /cc]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /chat...");
        Bukkit.getPluginCommand("chat").setExecutor(new Chat()); //Chat command [/c, /channel, /sc]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /updatescoreboard...");
        Bukkit.getPluginCommand("updatescoreboard").setExecutor(new UpdateScoreboard()); //Update scoreboard command [/updatescoreboard]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /globalmute...");
        Bukkit.getPluginCommand("globalmute").setExecutor(new GlobalMute()); //Globalmute command [/gmute]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /particles...");
        Bukkit.getPluginCommand("particles").setExecutor(new Particle()); //Particle selector command [/particles, /particle, /ps]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /warn...");
        Bukkit.getPluginCommand("warn").setExecutor(new Warn()); //Warn command [/warn]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /warp...");
        Bukkit.getPluginCommand("warp").setExecutor(new Warp()); //Warp command [/warp]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /freeze...");
        Bukkit.getPluginCommand("freeze").setExecutor(new Freeze()); //Freeze command [/freeze]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /banhammer...");
        Bukkit.getPluginCommand("banhammer").setExecutor(new BanHammer()); //Ban hammer command [/banhammer]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /lalookup...");
        Bukkit.getPluginCommand("lalookup").setExecutor(new LookupUser()); //User lookup command [/lalookup]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Loading /tokens...");
        Bukkit.getPluginCommand("tokens").setExecutor(new Token()); //Tokens command [/tokens]
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Done loading commands; adding aliases...");
        // Aliases
        Bukkit.getPluginCommand("clearchat").setAliases(clearchat); //Clearchat alias
        Bukkit.getPluginCommand("chat").setAliases(chat); //Chat aliases
        Bukkit.getPluginCommand("particles").setAliases(particles); //Particles aliases
        if(LegendArena.debugSwitch)
            ChatUtils.broadcast(PluginUtils.msgDebug + "Done loading aliases!");
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
