package net.thenamedev.legendarena.utils;

import net.thenamedev.legendarena.commands.*;
import net.thenamedev.legendarena.commands.staff.*;
import net.thenamedev.legendarena.extras.history.*;
import net.thenamedev.legendarena.listeners.*;
import net.thenamedev.legendarena.extras.staffchat.*;
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
        init = true;
    }

    public static void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new ChatListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new MobSpawnListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        Bukkit.getPluginManager().registerEvents(new StaffChat(), Bukkit.getPluginManager().getPlugin("LegendArena"));

    }

    static List<String> firework = new ArrayList<>();
    static List<String> clearchat = new ArrayList<>();
    static List<String> chat = new ArrayList<>();

    public static void registerCommands() {
        registerAliases();
        // Commands
        Bukkit.getPluginCommand("namehistory").setExecutor(new UsernameHistory()); //Username history command [/namehistory]
        Bukkit.getPluginCommand("say").setExecutor(new Say()); //Say command [/say]
        Bukkit.getPluginCommand("firework").setExecutor(new Firework()); //Firework command [/fw]
        Bukkit.getPluginCommand("clearchat").setExecutor(new ClearChat()); //Clearchat command [/cc]
        Bukkit.getPluginCommand("chat").setExecutor(new Chat()); //Chat command [/c, /channel, /sc]
        Bukkit.getPluginCommand("globalmute").setExecutor(new GlobalMute()); //Globalmute command [/gmute]
        Bukkit.getPluginCommand("userinfo").setExecutor(new Info()); //Userinfo command [/userinfo]
        Bukkit.getPluginCommand("warn").setExecutor(new Warn()); //Warn command [/warn]
        Bukkit.getPluginCommand("warp").setExecutor(new Warp()); //Warp command [/warp]
        Bukkit.getPluginCommand("op").setExecutor(new Op()); //Op command [/op]
        Bukkit.getPluginCommand("deop").setExecutor(new Deop()); //Deop command [/deop]
        Bukkit.getPluginCommand("freeze").setExecutor(new Freeze()); //Freeze command [/freeze]
        // Aliases
        Bukkit.getPluginCommand("clearchat").setAliases(clearchat); //Clearchat alias
        Bukkit.getPluginCommand("chat").setAliases(chat); //Chat aliases
        Bukkit.getPluginCommand("firework").setAliases(firework); //Firework alias
    }

    private static void registerAliases() {
        firework.add("fw");
        clearchat.add("cc");
        chat.add("sc");
        chat.add("c");
    }

}
