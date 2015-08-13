package legendarena.hub.particles;

import legendapi.particles.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class ParticleCore implements Runnable {

    private static HashMap<ParticleType, ArrayList<UUID>> players = new HashMap<>();

    public void run() {
        for(ParticleType t : players.keySet()) {
            for(UUID u : players.get(t)) {
                if(Bukkit.getPlayer(u) == null) continue;

                t.play(Bukkit.getPlayer(u));
            }
        }
    }

    public static void setParticles(ParticleType type, UUID u) {
        clearParticles(u);

        if(!players.containsKey(type)) {
            ArrayList<UUID> built = new ArrayList<>();
            built.add(u);
            players.put(type, built);
            return;
        }

        ArrayList<UUID> list = players.get(type);
        list.add(u);
        players.put(type, list);
    }

    public static void clearParticles(UUID u) {
        for(ParticleType type : players.keySet()) {
            ArrayList<UUID> list = players.get(type);

            if(list.contains(u)) {
                list.remove(u);

                players.put(type, list);
            }
        }
    }

    public static void setParticles(ParticleType type, Player p) {
        setParticles(type, p.getUniqueId());
    }

    public enum ParticleType {

        FIRE(ParticleEffect.FLAME),
        ENDER(ParticleEffect.PORTAL),
        ENCHANT(ParticleEffect.ENCHANTMENT_TABLE),
        REDSTONE(ParticleEffect.REDSTONE),
        CRIT(ParticleEffect.CRIT),
        COLOURFUL(ParticleEffect.SPELL);

        private ParticleEffect particle = null;

        ParticleType(ParticleEffect particle) {
            this.particle = particle;
        }

        public ParticleEffect getEffect() {
            return particle;
        }

        public void play(Player p) {
            Location l = p.getLocation().add(0.0, 0.5, 0.0);
            switch(getEffect()) {
                case SPELL:
                    playColourful(p.getLocation());
                    break;
                case PORTAL:
                    getEffect().display(1, 1, 1, 0, 3, l, 30);
                    break;
                case ENCHANTMENT_TABLE:
                    getEffect().display(1, 1, 1, 0, 3, l, 30);
                    break;
                case CRIT:
                    getEffect().display(1, 1, 1, 0, 10, l, 30);
                    break;
                case FLAME:
                    getEffect().display(1, 1, 1, 0, 3, l, 30);
                    break;

                default:
                    getEffect().display(0, 0, 0, 0, 5, l, 30);
            }
        }

        public void play(Location l) {
            switch(getEffect()) {
                case SPELL:
                    playColourful(l);
                    break;
                case PORTAL:
                    getEffect().display(1, 1, 1, 0, 5, l, 30);
                    break;
                case CRIT:
                    getEffect().display(1, 1, 1, 0, 10, l, 30);
                    break;
                case FLAME:
                    getEffect().display(1, 1, 1, 0, 10, l, 30);
                    break;

                default:
                    getEffect().display(0, 0, 0, 0, 5, l, 30);
            }
        }

        private void playColourful(Location l) {
            Random r = new Random();
            int randomR = r.nextInt(255) + 1;
        }

    }

}
