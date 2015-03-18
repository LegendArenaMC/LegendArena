/*
    Copyright 2015 Legend Arena Developers

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

package net.thenamedev.legendarena.utils;

import net.thenamedev.legendapi.LegendAPI;
import net.thenamedev.legendapi.tokens.TokenCore;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.commands.*;
import net.thenamedev.legendarena.commands.dev.UpdateScoreboard;
import net.thenamedev.legendarena.commands.staff.*;
import net.thenamedev.legendarena.extras.hub.hideplayer.HidePlayers;
import net.thenamedev.legendarena.extras.hub.particles.ParticleCore;
import net.thenamedev.legendarena.extras.hub.warp.HubWarper;
import net.thenamedev.legendarena.extras.staffchat.StaffChat;
import net.thenamedev.legendarena.listeners.*;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.thenamedev.legendapi.utils.PluginUtils.msgDebug;

/**
 * @author ThePixelDev
 */
public class InitUtils {

    private static boolean init = false;

    private final static List<String> clearchat = new ArrayList<>();
    private final static List<String> chat = new ArrayList<>();
    private final static List<String> particles = new ArrayList<>();

    public static void pluginInit() {
        if(init)
            return;
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading LegendArena plugin using API version v%s, codenamed \"%s\".", msgDebug, LegendAPI.apiVersion, LegendAPI.versionName));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading COMMANDS", msgDebug));
        registerCommands();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading LISTENERS", msgDebug));
        registerListeners();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading SCOREBOARD TEAMS", msgDebug));
        ScoreboardUtils.registerTeams();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading SCHEDULERS", msgDebug));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new ParticleCore(), 10l, 10l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new HubWarper.InitPlayers(), 20l, 20l);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new HidePlayers(), 5l, 5l);
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading TOKENS BACKEND", msgDebug));
        TokenCore.init();
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sUtilizing tokens core v%s, codenamed \"%s\".", msgDebug, TokenCore.ver, TokenCore.verName));
        else
            ChatUtils.broadcast(String.format("%sUtilizing tokens core v%s, codenamed \"%s\".", msgDebug, TokenCore.ver, TokenCore.verName), Rank.Dev);
        Random r = new Random();
        if(r.nextInt(200) == 100)
            if(LegendAPI.debug)
                //see: http://en.wikipedia.org/wiki/Hyper_Text_Coffee_Pot_Control_Protocol#Commands_and_replies
                ChatUtils.broadcast(String.format("%sError 418: I'm a teapot", PluginUtils.msgWarning));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sDone loading!", msgDebug));
        init = true;
    }

    private static void registerListeners() {
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading SERVERPINGLISTENER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new ServerPingListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading HUBWARPER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new HubWarper(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading CHATLISTENER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new ChatListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading PLAYERMOVELISTENER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading PLAYERJOINLISTENER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading STAFFCHAT...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new StaffChat(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading WORLDCHANGELISTENER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new WorldChangeListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading BANHAMMERLISTENER...", msgDebug));
        Bukkit.getPluginManager().registerEvents(new BanHammerListener(), Bukkit.getPluginManager().getPlugin("LegendArena"));
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sDone loading listeners!", msgDebug));
    }

    private static void registerCommands() {
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading aliases...", msgDebug));
        registerAliases();
        // Commands
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /motdlist...", msgDebug));
        Bukkit.getPluginCommand("motdlist").setExecutor(new MOTDList()); //MOTD list command [/motdlist]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /firework...", msgDebug));
        Bukkit.getPluginCommand("firework").setExecutor(new Firework()); //Firework command [/firework, /fw]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /fly...", msgDebug));
        Bukkit.getPluginCommand("fly").setExecutor(new Fly()); //Toggle flight command [/fly]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /gadgets...", msgDebug));
        Bukkit.getPluginCommand("gadgets").setExecutor(new Gadgets()); //Hub gadgets toggle command [/gadgets]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /clearchat...", msgDebug));
        Bukkit.getPluginCommand("clearchat").setExecutor(new ClearChat()); //Clearchat command [/clearchat, /cc]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /chat...", msgDebug));
        Bukkit.getPluginCommand("chat").setExecutor(new Chat()); //Chat command [/c, /channel, /sc]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /updatescoreboard...", msgDebug));
        Bukkit.getPluginCommand("updatescoreboard").setExecutor(new UpdateScoreboard()); //Update scoreboard command [/updatescoreboard]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /globalmute...", msgDebug));
        Bukkit.getPluginCommand("globalmute").setExecutor(new GlobalMute()); //Globalmute command [/gmute]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /particles...", msgDebug));
        Bukkit.getPluginCommand("particles").setExecutor(new Particle()); //Particle selector command [/particles, /particle, /ps]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /warn...", msgDebug));
        Bukkit.getPluginCommand("warn").setExecutor(new Warn()); //Warn command [/warn]
        /*if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /warp...", msgDebug));
        try {
            Bukkit.getPluginCommand("warp").setExecutor(new Warp()); //Warp command [/warp]
        } catch(Exception ex) {
            if(LegendAPI.debug)
                ChatUtils.broadcast(String.format("%sUnable to load /warp - continuing without it...", msgDebug));
        }*/
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /freeze...", msgDebug));
        Bukkit.getPluginCommand("freeze").setExecutor(new Freeze()); //Freeze command [/freeze]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /banhammer...", msgDebug));
        Bukkit.getPluginCommand("banhammer").setExecutor(new BanHammer()); //Ban hammer command [/banhammer]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /lalookup...", msgDebug));
        Bukkit.getPluginCommand("lalookup").setExecutor(new LookupUser()); //User lookup command [/lalookup]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sLoading /tokens...", msgDebug));
        Bukkit.getPluginCommand("tokens").setExecutor(new Token()); //Tokens command [/tokens]
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sDone loading commands; adding aliases...", msgDebug));
        // Aliases
        Bukkit.getPluginCommand("clearchat").setAliases(clearchat); //Clearchat alias
        Bukkit.getPluginCommand("chat").setAliases(chat); //Chat aliases
        Bukkit.getPluginCommand("particles").setAliases(particles); //Particles aliases
        if(LegendAPI.debug)
            ChatUtils.broadcast(String.format("%sDone loading aliases!", msgDebug));
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
