package net.thenamedev.legendarena.minigame;

import net.thenamedev.legendapi.core.annotations.SoonTM;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.HubWarper;
import org.bukkit.Bukkit;
import net.thenamedev.legendarena.utils.BMTWordUtils;
import org.bukkit.Location;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * Build My Thing - Soon[tm]
 *
 * @author ThePixelDev
 */
@SoonTM
public class BuildMyThing {

    private static boolean running = false;
    private static String word;
    private static HashMap<UUID, Boolean> players = new HashMap<>();

    public static Location getBuilderLocation() {
        return new Location(Bukkit.getWorld("buildmything"), 0, 100, 0);
    }

    public static void start() {
        if(running)
            return;
        running = true;
    }

    public static void end() {
        if(!running)
            return;
        for(UUID u : players.keySet()) {
            Player p = Bukkit.getPlayer(u);
            leave(p);
        }
        running = false;
    }

    public static String generateWord() {
        String genWord;
        Random r = new Random();
        int a = r.nextInt(BMTWordUtils.words.length);
        genWord = BMTWordUtils.words[a];
        return genWord;
    }

    public static void join(Player p) {
        if(running && !Rank.isRanked(p, Rank.YOUTUBE))
            //TODO: Implement spectator system
            return;
        if(!HubWarper.isExempt(p.getUniqueId())) HubWarper.toggleExemption(p.getUniqueId());
        players.put(p.getUniqueId(), false);
    }

    public static void leave(Player p) {
        if(HubWarper.isExempt(p.getUniqueId())) HubWarper.toggleExemption(p.getUniqueId());
        players.remove(p.getUniqueId());
    }

    public static void sendToPlayers(String msg) {
        for(UUID u : players.keySet()) {
            Player p = Bukkit.getPlayer(u);
            p.sendMessage(ChatUtils.getCustomMsg("BMT") + msg);
        }
    }

    public static void setBuilder(Player p) {
        removeBuilder();
        word = generateWord();
        
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
