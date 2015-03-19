package net.thenamedev.legendapi.utils;

import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.minigames.Minigame;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created on 3/4/2015
 *
 * @author ThePixelDev
 */
public class PlayerUtils {

    private static HashMap<UUID, Minigame> playersInMinigames = new HashMap<>();

    public static void addPlayer(Minigame mg, UUID p) {
        if(Bukkit.getPlayer(p) == null) throw new MistakesWereMadeException("Player cannot be null");
        if(mg == null) throw new MistakesWereMadeException("Minigame cannot be null");
        playersInMinigames.put(p, mg);
    }

    public static List<UUID> getPlayers(Minigame mg) {
        List<UUID> list = new ArrayList<>();
        for(UUID a : playersInMinigames.keySet()) {
            if(playersInMinigames.get(a) != mg)
                continue;
            list.add(a);
        }
        return list;
    }

    public static Minigame getPlayerMinigame(UUID p) {
        if(!playersInMinigames.containsKey(p))
            return null;
        return playersInMinigames.get(p);
    }

    public static boolean isInMinigame(UUID p) {
        return getPlayerMinigame(p) != null;
    }

}
