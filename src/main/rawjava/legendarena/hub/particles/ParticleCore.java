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
    private static ArrayList<UUID> multiSelect = new ArrayList<>();

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

    public static boolean allowMultiParticles(UUID u) {
        return multiSelect.contains(u);
    }

    public static void toggleMultiParticles(UUID u) {
        if(allowMultiParticles(u))
            multiSelect.remove(u);
        else
            multiSelect.add(u);
    }

    public enum ParticleType {

        FIRE(ParticleEffect.FLAME),
        ENDER(ParticleEffect.PORTAL),
        ENCHANT(ParticleEffect.ENCHANTMENT_TABLE),
        CLOUD(ParticleEffect.CLOUD),
        REDSTONE(ParticleEffect.REDSTONE),
        COLOURFUL(ParticleEffect.SPELL);

        private ParticleEffect particle = null;

        ParticleType(ParticleEffect particle) {
            this.particle = particle;
        }

        public ParticleEffect getEffect() {
            return particle;
        }

        public void play(Player p) {
            switch(getEffect()) {
                case SPELL:
                    playColourful(p.getLocation());

                default:
                    getEffect().display(1, 1, 1, 1, 3, p.getLocation(), 30);
            }
        }

        public void play(Location l) {
            switch(getEffect()) {
                case SPELL:
                    playColourful(l);

                default:
                    getEffect().display(1, 1, 1, 1, 3, l, 30);
            }
        }

        private void playColourful(Location l) {
            Random r = new Random();
            int randomR = r.nextInt(255) + 1;
        }

    }

}
