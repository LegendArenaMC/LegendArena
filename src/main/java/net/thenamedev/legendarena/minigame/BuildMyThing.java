package net.thenamedev.legendarena.minigame;

import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.HubWarper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by thepixeldev on 4/29/15.
 */
public class BuildMyThing {

    private static boolean running = false;
    private static String word;
    private static HashMap<UUID, Boolean> players = new HashMap<>();

    public static void start() {
        if(running)
            return;
        //
    }

    public static void end() {
        if(!running)
            return;
        for(UUID u : players.keySet()) {
            Player p = Bukkit.getPlayer(u);
            leave(p);
        }
        //
    }

    public static void generateWord() {
        //
    }

    public static void join(Player p) {
        if(!HubWarper.isExempt(p.getUniqueId())) HubWarper.toggleExemption(p.getUniqueId());
        players.put(p.getUniqueId(), false);
        sendToPlayers("Join > " + Rank.getFormattedName(p));
    }

    public static void leave(Player p) {
        if(HubWarper.isExempt(p.getUniqueId())) HubWarper.toggleExemption(p.getUniqueId());
        players.remove(p.getUniqueId());
        sendToPlayers("Quit > " + Rank.getFormattedName(p));
    }

    public static void sendToPlayers(String msg) {
        for(UUID u : players.keySet()) {
            Player p = Bukkit.getPlayer(u);
            p.sendMessage(PluginUtils.msgNormal + "[BMT] " + ChatColor.GREEN + msg);
        }
    }

    public static void setBuilder(Player p) {
        removeBuilder();
        //
    }

    public static void removeBuilder() {
        if(getBuilder() == null)
            return;
        Player builder = getBuilder();
        builder.getInventory().clear();
        players.remove(builder.getUniqueId());
        players.put(builder.getUniqueId(), false);
    }

    public static Player getBuilder() {
        for(UUID u : players.keySet())
            if(players.get(u))
                return Bukkit.getPlayer(u);
        return null;
    }

    private static class MainTimer implements Runnable {

        int timer = 180;
        int cooldown = 5;
        int round = 1;

        public void run() {
            if(!running)
                return;

            if(cooldown > 0) {
                timer = 180;
                cooldown--;
            }
            timer--;
            if(timer == 30) {
                sendToPlayers("30 seconds left!");
            } else if(timer == 20) {
                sendToPlayers("20 seconds left!");
            } else if(timer == 10) {
                sendToPlayers("10 seconds left!");
            } else if(timer == 5) {
                sendToPlayers("5 seconds left!");
            } else if(timer == 4) {
                sendToPlayers("4 seconds left!");
            } else if(timer == 3) {
                sendToPlayers("3 seconds left!");
            } else if(timer == 2) {
                sendToPlayers("2 seconds left!");
            } else if(timer == 1) {
                sendToPlayers("1 second left!");
                sendToPlayers("1 second left!");
                sendToPlayers("1 second left!");
            } else if(timer == 0) {
                cooldown = 15;
                if(round >= 20) {
                    end();
                } else {
                    sendToPlayers("Round " + round + " is now over! The word was " + ChatColor.BOLD + word + ChatColor.GREEN + "!");
                    round++;
                }
            }
        }

    }

}
