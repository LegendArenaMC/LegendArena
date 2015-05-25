package net.thenamedev.legendarena.utils;

import net.thenamedev.legendapi.LegendAPI;
import net.thenamedev.legendapi.core.emeralds.EmeraldsCore;
import net.thenamedev.legendapi.utils.ChatUtils;
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

import static net.thenamedev.legendapi.utils.ChatUtils.Messages.*;

/**
 * @author ThePixelDev
 */
public class InitUtils {

    private static boolean init = false;

    private final static List<String> chat = new ArrayList<>();
    private final static List<String> particles = new ArrayList<>();
    private final static List<String> stafflist = new ArrayList<>();
    private final static List<String> punish = new ArrayList<>();

    public static void pluginInit() {
        if(init)
            return;
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading CONFIG", debugMsg));
        FileConfiguration conf = Bukkit.getPluginManager().getPlugin("LegendArena").getConfig();
        conf.options().copyDefaults(true);
        Bukkit.getPluginManager().getPlugin("LegendArena").saveConfig();
        MOTDRandomizer.setNotice(conf.getString("motdNotice"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading LegendArena plugin using API version v%s, codenamed \"%s\".", debugMsg, LegendAPI.apiVersion, LegendAPI.versionName));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading COMMANDS", debugMsg));
        registerCommands();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading LISTENERS", debugMsg));
        registerListeners();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading SCHEDULERS", debugMsg));
        registerSchedulers();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading EMERALDS CORE", debugMsg));
        EmeraldsCore.init();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sDone loading!", debugMsg));
        init = true;
    }

    private static void registerSchedulers() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new ParticleCore(), 10l, 10l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new ParticleCore.ColorfulEffects(), 3l, 3l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new HubWarper.InitPlayers(), 20l, 20l);
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sSchedulers loaded!", debugMsg));
    }

    private static void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new ServerPingListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new HubWarper(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new ChatListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new CommandFilter(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sListeners loaded!", debugMsg));
    }

    private static void registerCommands() {
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading aliases...", debugMsg));
        registerAliases();
        // Commands
        Bukkit.getPluginCommand("firework").setExecutor(new Firework()); //Firework command [/firework, /fw]
        Bukkit.getPluginCommand("help").setExecutor(new Help()); //Help command [/help]
        Bukkit.getPluginCommand("gadgets").setExecutor(new Gadgets()); //Hub gadgets toggle command [/gadgets]
        Bukkit.getPluginCommand("particles").setExecutor(new Particle()); //Particle selector command [/particles, /particle, /ps]
        Bukkit.getPluginCommand("warp").setExecutor(new Warp()); //Warp command [/warp]
        Bukkit.getPluginCommand("troll").setExecutor(new Troll()); //Troll command [/troll]
        Bukkit.getPluginCommand("stafflist").setExecutor(new StaffList()); //Staff list command [/stafflist, /sl, /liststaff]
        Bukkit.getPluginCommand("dev").setExecutor(new Dev()); //Dev Tools command [/dev]
        Bukkit.getPluginCommand("staff").setExecutor(new Staff()); //Freeze command [/freeze]
        Bukkit.getPluginCommand("emeralds").setExecutor(new EmeraldCmd()); //Emeralds command [/emeralds]
        Bukkit.getPluginCommand("chat").setExecutor(new Chat()); //Chat command [/chat, /c]
        Bukkit.getPluginCommand("explodeallthethings").setExecutor(new ExplodeAllTheThings()); //Explodeallthethings command
        // Aliases
        Bukkit.getPluginCommand("particles").setAliases(particles); //Particles aliases
        Bukkit.getPluginCommand("chat").setAliases(chat); //Chat aliases
        Bukkit.getPluginCommand("stafflist").setAliases(stafflist); //Stafflist aliases
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sCommands loaded!", debugMsg));
    }

    private static void registerAliases() {
        particles.add("particle");
        particles.add("ps");
        stafflist.add("sl");
        stafflist.add("liststaff");
        chat.add("c");
        chat.add("say");
        chat.add("sc");
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sAliases loaded!", debugMsg));
    }

}
