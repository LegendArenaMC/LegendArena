package net.thenamedev.legendarena.extras.particles;

import net.thenamedev.legendarena.extras.particles.lib.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class ParticleCore implements Runnable {

    private static final ArrayList<String> flameEffects = new ArrayList<>();
    private static final ArrayList<String> heartEffects = new ArrayList<>();

    public void run() {
        for(String s : heartEffects) {
            Player p = Bukkit.getPlayerExact(s);
            if(!p.isOnline()) {
                heartEffects.remove(s);
                continue;
            }
            ParticleEffect.HEART.display(0, 1, 0, 1, 5, p.getLocation(), 1);
        }
        for(String s : flameEffects) {
            Player p = Bukkit.getPlayerExact(s);
            if(!p.isOnline()) {
                flameEffects.remove(s);
                continue;
            }
            ParticleEffect.FLAME.display(0, 0, 0, 1, 10, p.getLocation(), 1);
        }
    }

    public static void addType(String player, Type type) {
        if(type == Type.HEART) {
            heartEffects.add(player);
        } else if(type == Type.FLAME) {
            flameEffects.add(player);
        }
    }

    public static boolean hasParticles(String player) {
        return flameEffects.contains(player) || heartEffects.contains(player);
    }

    public static void removePlayer(String player) {
        if(!hasParticles(player))
            return; //sanity check
        if(flameEffects.contains(player)) flameEffects.remove(player);
        if(heartEffects.contains(player)) heartEffects.remove(player);
    }

    public enum Type {
        FLAME, HEART
    }

}
