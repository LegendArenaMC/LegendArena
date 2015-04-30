package net.thenamedev.legendarena.minigame;

import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Chicken Mayhem - A minigame in which breaking blocks spawns chickens (chickens for asthetical reasons (and because - well, why the fuck not)).<br><br>
 *
 * The player with the most blocks broken at the end wins.
 * @author ThePixelDev
 */
public class ChickenMayhem {

    private static Player leader = null;
    private static HashMap<Player, Integer> players = new HashMap<>();

    private static List<Player> playing = new ArrayList<>();

    public static void start() {
        //
    }

    public static void end() {
        //
    }

    private static class MainTimer implements Runnable {

        int timeLeft = 180;

        public void run() {
            timeLeft--;
            if(timeLeft == 5) {

            }
        }

    }

    private static class MainListener implements Listener {

        public void blockBreakListener(BlockBreakEvent ev) {
            Chicken chicken = ev.getBlock().getWorld().spawn(ev.getBlock().getLocation(), Chicken.class);
            chicken.setBaby();
            chicken.setCustomName("Bob");
            if(leader == null) {
                leader = ev.getPlayer();
            } else {
                if(players.get(leader) < players.get(ev.getPlayer()) + 1) {
                    leader = ev.getPlayer();
                }
            }
            players.put(ev.getPlayer(), players.get(ev.getPlayer()) + 1);
        }

    }

}
