package net.thenamedev.legendarena.extras.particles;

import net.thenamedev.legendarena.extras.particles.lib.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * @author ThePixelDev
 */
public class ParticleCore implements Runnable {

    private static final ArrayList<UUID> slimeEffects = new ArrayList<>();
    private static final ArrayList<UUID> enchantEffects = new ArrayList<>();
    private static final ArrayList<UUID> heartEffects = new ArrayList<>();
    private static final ArrayList<UUID> portalEffects = new ArrayList<>();
    private static final ArrayList<UUID> villagerEffects = new ArrayList<>();
    private static final ArrayList<UUID> villager2Effects = new ArrayList<>();
    private static final ArrayList<UUID> fireworkEffects = new ArrayList<>();
    private static final ArrayList<UUID> colorfulEffects = new ArrayList<>();

    @SuppressWarnings("deprecation")
    public void run() {
        for(final UUID s : heartEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.HEART.display(1, 1, 1, 1, 5, p.getLocation(), 30);
        }
        for(final UUID s : slimeEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.SLIME.display(0, 0, 0, 1, 10, p.getLocation(), 30);
        }
        for(final UUID s : portalEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.PORTAL.display(0, 0, 0, 1, 35, p.getLocation(), 30);
        }
        for(final UUID s : enchantEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.ENCHANTMENT_TABLE.display(0, 0, 0, 3, 35, p.getLocation(), 30);
        }
        for(final UUID s : villagerEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.VILLAGER_HAPPY.display(1, 1, 1, 1, 10, p.getLocation(), 30);
        }
        for(final UUID s : villager2Effects) {
            Player p = Bukkit.getPlayer(s);
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
        for(final UUID s : fireworkEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.FIREWORKS_SPARK.display(1, 1, 1, 1, 10, p.getLocation(), 30);
        }
    }

    @SuppressWarnings("deprecation")
    public static void addType(String player, Type type) {
        removePlayer(player);
        switch(type) {
            case HEART:
                heartEffects.add(Bukkit.getPlayer(player).getUniqueId());
                break;
            case SLIME:
                slimeEffects.add(Bukkit.getPlayer(player).getUniqueId());
                break;
            case PORTAL:
                portalEffects.add(Bukkit.getPlayer(player).getUniqueId());
                break;
            case ENCHANT:
                enchantEffects.add(Bukkit.getPlayer(player).getUniqueId());
                break;
            case HAPPYVILL:
                villagerEffects.add(Bukkit.getPlayer(player).getUniqueId());
                break;
            case ANGRYVILL:
                villager2Effects.add(Bukkit.getPlayer(player).getUniqueId());
                break;
            case FIREWORK:
                fireworkEffects.add(Bukkit.getPlayer(player).getUniqueId());
                break;
            case COLORFULEFFCTS:
                colorfulEffects.add(Bukkit.getPlayer(player).getUniqueId());
                break;
        }
    }

    @SuppressWarnings("deprecation")
    public static boolean hasParticles(String player) {
        return slimeEffects.contains(Bukkit.getPlayer(player).getUniqueId())
                || heartEffects.contains(Bukkit.getPlayer(player).getUniqueId())
                || portalEffects.contains(Bukkit.getPlayer(player).getUniqueId())
                || enchantEffects.contains(Bukkit.getPlayer(player).getUniqueId())
                || villagerEffects.contains(Bukkit.getPlayer(player).getUniqueId())
                || villager2Effects.contains(Bukkit.getPlayer(player).getUniqueId())
                || fireworkEffects.contains(Bukkit.getPlayer(player).getUniqueId())
                || colorfulEffects.contains(Bukkit.getPlayer(player).getUniqueId());
    }

    @SuppressWarnings("deprecation")
    public static void removePlayer(String player) {
        if(!hasParticles(player))
            return; //sanity check
        if(slimeEffects.contains(Bukkit.getPlayer(player).getUniqueId())) slimeEffects.remove(Bukkit.getPlayer(player).getUniqueId());
        if(portalEffects.contains(Bukkit.getPlayer(player).getUniqueId())) portalEffects.remove(Bukkit.getPlayer(player).getUniqueId());
        if(heartEffects.contains(Bukkit.getPlayer(player).getUniqueId())) heartEffects.remove(Bukkit.getPlayer(player).getUniqueId());
        if(enchantEffects.contains(Bukkit.getPlayer(player).getUniqueId())) enchantEffects.remove(Bukkit.getPlayer(player).getUniqueId());
        if(villagerEffects.contains(Bukkit.getPlayer(player).getUniqueId())) villagerEffects.remove(Bukkit.getPlayer(player).getUniqueId());
        if(villager2Effects.contains(Bukkit.getPlayer(player).getUniqueId())) villager2Effects.remove(Bukkit.getPlayer(player).getUniqueId());
        if(fireworkEffects.contains(Bukkit.getPlayer(player).getUniqueId())) fireworkEffects.remove(Bukkit.getPlayer(player).getUniqueId());
        if(colorfulEffects.contains(Bukkit.getPlayer(player).getUniqueId())) colorfulEffects.remove(Bukkit.getPlayer(player).getUniqueId());
    }

    public enum Type {
        SLIME,
        HEART,
        PORTAL,
        HAPPYVILL,
        ANGRYVILL,
        ENCHANT,
        FIREWORK,
        COLORFULEFFCTS
    }

    public static class ColorfulEffects implements Runnable {

        public void run() {
            for(final UUID s : colorfulEffects) {
                @SuppressWarnings("deprecation") Player p = Bukkit.getPlayer(s);
                if(p == null)
                    continue;
                //Ah, massively overkill amount of particles, how I love you...
                Random r = new Random();
                Location l = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 0.5, p.getLocation().getZ());
                ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
                ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
                ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
                ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
                ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
                ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
                ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
            }
        }

    }

}
