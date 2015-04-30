package net.thenamedev.legendarena.utils;

import net.thenamedev.legendapi.LegendAPI;
import net.thenamedev.legendapi.tokens.TokenCore;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.commands.*;
import net.thenamedev.legendarena.commands.staff.*;
import net.thenamedev.legendarena.extras.particles.ParticleCore;
import net.thenamedev.legendarena.extras.HubWarper;
import net.thenamedev.legendarena.extras.MOTDRandomizer;
import net.thenamedev.legendarena.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.thenamedev.legendapi.utils.PluginUtils.msgDebug;
import static net.thenamedev.legendapi.utils.PluginUtils.msgWarning;

/**
 * @author ThePixelDev
 */
public class InitUtils {

    private static boolean init = false;

    private final static List<String> chat = new ArrayList<>();
    private final static List<String> particles = new ArrayList<>();
    private final static List<String> stafflist = new ArrayList<>();

    public static void pluginInit() {
        if(init)
            return;
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading CONFIG", msgDebug));
        FileConfiguration conf = Bukkit.getPluginManager().getPlugin("LegendArena").getConfig();
        conf.options().copyDefaults(true);
        Bukkit.getPluginManager().getPlugin("LegendArena").saveConfig();
        MOTDRandomizer.setNotice(conf.getString("motdNotice"));
        if(conf.getBoolean("liveServer")) {
            if(LegendAPI.extraDebug)
                ChatUtils.broadcast(String.format("%sEXTRA DEBUG IS ON, YET THE LIVE SERVER FLAG IS SET TO TRUE - THIS SHOULD NOT HAPPEN!!!", msgWarning), Rank.ADMIN);
            LegendAPI.extraDebug = false;
        }
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading LegendArena plugin using API version v%s, codenamed \"%s\".", msgDebug, LegendAPI.apiVersion, LegendAPI.versionName));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading COMMANDS", msgDebug));
        registerCommands();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading LISTENERS", msgDebug));
        registerListeners();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading SCHEDULERS", msgDebug));
        registerSchedulers();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading TOKENS BACKEND", msgDebug));
        TokenCore.init();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sDone loading!", msgDebug));
        init = true;
    }

    private static void registerSchedulers() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new ParticleCore(), 10l, 10l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new ParticleCore.ColorfulEffects(), 2l, 2l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new HubWarper.InitPlayers(), 20l, 20l);
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sSchedulers loaded!", msgDebug));
    }

    private static void registerListeners() {
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading SERVERPINGLISTENER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new ServerPingListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading HUBWARPER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new HubWarper(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading CHATLISTENER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new ChatListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading PLAYERMOVELISTENER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading PLAYERJOINLISTENER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading COMMANDFILTER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new CommandFilter(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sListeners loaded!", msgDebug));
    }

    private static void registerCommands() {
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading aliases...", msgDebug));
        registerAliases();
        // Commands
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /firework...", msgDebug));
        Bukkit.getPluginCommand("firework").setExecutor(new Firework()); //Firework command [/firework, /fw]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /help...", msgDebug));
        Bukkit.getPluginCommand("help").setExecutor(new Help()); //Help command [/help]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /gadgets...", msgDebug));
        Bukkit.getPluginCommand("gadgets").setExecutor(new Gadgets()); //Hub gadgets toggle command [/gadgets]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /particles...", msgDebug));
        Bukkit.getPluginCommand("particles").setExecutor(new Particle()); //Particle selector command [/particles, /particle, /ps]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /warp...", msgDebug));
        Bukkit.getPluginCommand("warp").setExecutor(new Warp()); //Warp command [/warp]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /troll...", msgDebug));
        Bukkit.getPluginCommand("troll").setExecutor(new Troll()); //Troll command [/troll]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /chickenmayhem...", msgDebug));
        Bukkit.getPluginCommand("stafflist").setExecutor(new StaffList()); //Staff list command [/stafflist, /sl, /liststaff]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /dev...", msgDebug));
        Bukkit.getPluginCommand("dev").setExecutor(new Dev()); //Dev Tools command [/dev]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /staff...", msgDebug));
        Bukkit.getPluginCommand("staff").setExecutor(new Staff()); //Freeze command [/freeze]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /tokens...", msgDebug));
        Bukkit.getPluginCommand("tokens").setExecutor(new Token()); //Tokens command [/tokens]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /chat...", msgDebug));
        Bukkit.getPluginCommand("chat").setExecutor(new Chat()); //Chat command [/chat, /c]
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sDone loading main commands; now loading aliases...", msgDebug));
        // Aliases
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /particles aliases...", msgDebug));
        Bukkit.getPluginCommand("particles").setAliases(particles); //Particles aliases
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /chat aliases...", msgDebug));
        Bukkit.getPluginCommand("chat").setAliases(chat);
        if(LegendAPI.extraDebug)
            ChatUtils.broadcast(String.format("%sLoading /stafflist aliases...", msgDebug));
        Bukkit.getPluginCommand("stafflist").setAliases(stafflist);
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sCommands loaded!", msgDebug));
    }

    private static void registerAliases() {
        particles.add("particle");
        particles.add("ps");
        stafflist.add("sl");
        stafflist.add("liststaff");
        chat.add("c");
        chat.add("sc");
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sAliases loaded!", msgDebug));
    }

}
