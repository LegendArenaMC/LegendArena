package net.thenamedev.legendarena.extras.hub.particles;

import net.thenamedev.legendarena.extras.hub.particles.lib.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class ParticleCore implements Runnable {

    private static final ArrayList<String> slimeEffects = new ArrayList<>();
    private static final ArrayList<String> enchantEffects = new ArrayList<>();
    private static final ArrayList<String> heartEffects = new ArrayList<>();
    private static final ArrayList<String> portalEffects = new ArrayList<>();
    private static final ArrayList<String> villagerEffects = new ArrayList<>();
    private static final ArrayList<String> villager2Effects = new ArrayList<>();
    private static final ArrayList<String> critEffects = new ArrayList<>();
    private static final ArrayList<String> fireworkEffects = new ArrayList<>();

    public void run() {
        for(final String s : heartEffects) {
            Player p = Bukkit.getPlayerExact(s);
            if(p == null) {
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    @Override
                    public void run() {
                        heartEffects.remove(s);
                    }
                });
                continue;
            }
            ParticleEffect.HEART.display(1, 1, 1, 1, 5, p.getLocation(), 30);
        }
        for(final String s : slimeEffects) {
            Player p = Bukkit.getPlayerExact(s);
            if(p == null) {
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    @Override
                    public void run() {
                        slimeEffects.remove(s);
                    }
                });
                continue;
            }
            ParticleEffect.SLIME.display(0, 0, 0, 1, 10, p.getLocation(), 30);
        }
        for(final String s : portalEffects) {
            Player p = Bukkit.getPlayerExact(s);
            if(p == null) {
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    @Override
                    public void run() {
                        portalEffects.remove(s);
                    }
                });
                continue;
            }
            ParticleEffect.PORTAL.display(0, 0, 0, 1, 35, p.getLocation(), 30);
        }
        for(final String s : enchantEffects) {
            Player p = Bukkit.getPlayerExact(s);
            if(p == null) {
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    @Override
                    public void run() {
                        enchantEffects.remove(s);
                    }
                });
                continue;
            }
            ParticleEffect.ENCHANTMENT_TABLE.display(0, 0, 0, 3, 35, p.getLocation(), 30);
        }
        for(final String s : villagerEffects) {
            Player p = Bukkit.getPlayerExact(s);
            if(p == null) {
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    @Override
                    public void run() {
                        villagerEffects.remove(s);
                    }
                });
                continue;
            }
            ParticleEffect.VILLAGER_HAPPY.display(1, 1, 1, 1, 10, p.getLocation(), 30);
        }
        for(final String s : villager2Effects) {
            Player p = Bukkit.getPlayerExact(s);
            if(p == null) {
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    @Override
                    public void run() {
                        villager2Effects.remove(s);
                    }
                });
                continue;
            }
            ParticleEffect.VILLAGER_ANGRY.display(1, 1, 1, 1, 10, p.getLocation(), 30);
        }
        for(final String s : critEffects) {
            Player p = Bukkit.getPlayerExact(s);
            if(p == null) {
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    @Override
                    public void run() {
                        critEffects.remove(s);
                    }
                });
                continue;
            }
            ParticleEffect.CRIT_MAGIC.display(1, 1, 1, 1, 10, p.getLocation(), 30);
        }
        for(final String s : fireworkEffects) {
            Player p = Bukkit.getPlayerExact(s);
            if(p == null) {
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), new Runnable() {
                    @Override
                    public void run() {
                        fireworkEffects.remove(s);
                    }
                });
                continue;
            }
            ParticleEffect.FIREWORKS_SPARK.display(1, 1, 1, 1, 10, p.getLocation(), 30);
        }
    }

    public static void addType(String player, Type type) {
        removePlayer(player);
        if(type == Type.HEART) {
            heartEffects.add(player);
        } else if(type == Type.SLIME) {
            slimeEffects.add(player);
        } else if(type == Type.PORTAL) {
            portalEffects.add(player);
        } else if(type == Type.ENCHANT) {
            enchantEffects.add(player);
        } else if(type == Type.HAPPYVILL) {
            villagerEffects.add(player);
        } else if(type == Type.ANGRYVILL) {
            villager2Effects.add(player);
        } else if(type == Type.CRIT) {
            critEffects.add(player);
        } else if(type == Type.FIREWORK) {
            fireworkEffects.add(player);
        }
    }

    public static boolean hasParticles(String player) {
        return slimeEffects.contains(player)
                || heartEffects.contains(player)
                || portalEffects.contains(player)
                || enchantEffects.contains(player)
                || villagerEffects.contains(player)
                || villager2Effects.contains(player)
                || critEffects.contains(player)
                || fireworkEffects.contains(player);
    }

    public static void removePlayer(String player) {
        if(!hasParticles(player))
            return; //sanity check
        if(slimeEffects.contains(player)) slimeEffects.remove(player);
        if(portalEffects.contains(player)) portalEffects.remove(player);
        if(heartEffects.contains(player)) heartEffects.remove(player);
        if(enchantEffects.contains(player)) enchantEffects.remove(player);
        if(villagerEffects.contains(player)) villagerEffects.remove(player);
        if(villager2Effects.contains(player)) villager2Effects.remove(player);
        if(critEffects.contains(player)) critEffects.remove(player);
        if(fireworkEffects.contains(player)) fireworkEffects.remove(player);
    }

    public enum Type {
        SLIME,
        HEART,
        PORTAL,
        HAPPYVILL,
        ANGRYVILL,
        ENCHANT,
        CRIT,
        FIREWORK
    }

}
