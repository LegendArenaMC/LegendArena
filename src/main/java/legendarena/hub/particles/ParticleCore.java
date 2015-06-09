package legendarena.hub.particles;

import legendarena.hub.particles.lib.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Particle core.
 *
 * @author ThePixelDev
 */
public class ParticleCore implements Runnable {

    private static final List<UUID> slimeEffects = new ArrayList<>();
    private static final List<UUID> enchantEffects = new ArrayList<>();
    private static final List<UUID> heartEffects = new ArrayList<>();
    private static final List<UUID> portalEffects = new ArrayList<>();
    private static final List<UUID> flameEffects = new ArrayList<>();
    private static final List<UUID> villagerEffects = new ArrayList<>();
    private static final List<UUID> villager2Effects = new ArrayList<>();
    private static final List<UUID> fireworkEffects = new ArrayList<>();
    private static final List<UUID> colorfulEffects = new ArrayList<>();

    public static final List<UUID> multiParicle = new ArrayList<>();

    @SuppressWarnings("deprecation")
    public void run() {
        for(final UUID s : heartEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.HEART.display(1, 1, 1, 1, 2, p.getLocation(), 30);
        }
        for(final UUID s : slimeEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.SLIME.display(0, 0, 0, 1, 5, p.getLocation(), 30);
        }
        for(final UUID s : portalEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.PORTAL.display(0, 0, 0, 1, 20, p.getLocation(), 30);
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
            ParticleEffect.VILLAGER_HAPPY.display(1, 1, 1, 1, 5, p.getLocation(), 30);
        }
        for(final UUID s : villager2Effects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.VILLAGER_ANGRY.display(1, 1, 1, 1, 5, p.getLocation(), 30);
        }
        for(final UUID s : fireworkEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            ParticleEffect.FIREWORKS_SPARK.display(1, 1, 1, 1, 3, p.getLocation(), 30);
        }
        for(final UUID s : colorfulEffects) {
            Player p = Bukkit.getPlayer(s);
            if(p == null)
                continue;
            Random r = new Random();
            Location l = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1.2, p.getLocation().getZ());
            ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
            ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
            ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
            ParticleEffect.SPELL_MOB.display(new ParticleEffect.OrdinaryColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)), l, 1);
        }
    }

    @SuppressWarnings("deprecation")
    public static void addType(String player, Type type) {
        Player p = Bukkit.getPlayer(player);
        if(!multiParicle.contains(p.getUniqueId()))
            removePlayer(p);
        switch(type) {
            case HEART:
                heartEffects.add(p.getUniqueId());
                break;
            case SLIME:
                slimeEffects.add(p.getUniqueId());
                break;
            case PORTAL:
                portalEffects.add(p.getUniqueId());
                break;
            case ENCHANT:
                enchantEffects.add(p.getUniqueId());
                break;
            case HAPPYVILL:
                villagerEffects.add(p.getUniqueId());
                break;
            case ANGRYVILL:
                villager2Effects.add(p.getUniqueId());
                break;
            case FIREWORK:
                fireworkEffects.add(p.getUniqueId());
                break;
            case COLORFULEFFCTS:
                colorfulEffects.add(p.getUniqueId());
                break;
            case FLAME:
                flameEffects.add(p.getUniqueId());
                break;
            default:
                throw new RuntimeException("That isn't a known particle effect!");
        }
    }

    public static int amountOfActiveParticles(Player p) {
        int ids = 0;
        if(slimeEffects.contains(p.getUniqueId()))
            ids++;
        if(heartEffects.contains(p.getUniqueId()))
            ids++;
        if(portalEffects.contains(p.getUniqueId()))
            ids++;
        if(enchantEffects.contains(p.getUniqueId()))
            ids++;
        if(villager2Effects.contains(p.getUniqueId()))
            ids++;
        if(villager2Effects.contains(p.getUniqueId()))
            ids++;
        if(fireworkEffects.contains(p.getUniqueId()))
            ids++;
        if(colorfulEffects.contains(p.getUniqueId()))
            ids++;
        if(flameEffects.contains(p.getUniqueId()))
            ids++;
        return ids;
    }

    public static void removePlayer(Player p) {
        if(p == null)
            return; //sanity check
        if(amountOfActiveParticles(p) < 1)
            return; //sanity check
        if(slimeEffects.contains(p.getUniqueId())) slimeEffects.remove(p.getUniqueId());
        if(portalEffects.contains(p.getUniqueId())) portalEffects.remove(p.getUniqueId());
        if(heartEffects.contains(p.getUniqueId())) heartEffects.remove(p.getUniqueId());
        if(enchantEffects.contains(p.getUniqueId())) enchantEffects.remove(p.getUniqueId());
        if(villagerEffects.contains(p.getUniqueId())) villagerEffects.remove(p.getUniqueId());
        if(villager2Effects.contains(p.getUniqueId())) villager2Effects.remove(p.getUniqueId());
        if(fireworkEffects.contains(p.getUniqueId())) fireworkEffects.remove(p.getUniqueId());
        if(colorfulEffects.contains(p.getUniqueId())) colorfulEffects.remove(p.getUniqueId());
        if(flameEffects.contains(p.getUniqueId())) flameEffects.remove(p.getUniqueId());
    }

    public enum Type {
        SLIME,
        HEART,
        PORTAL,
        HAPPYVILL,
        ANGRYVILL,
        ENCHANT,
        FIREWORK,
        COLORFULEFFCTS,
        FLAME
    }

}
