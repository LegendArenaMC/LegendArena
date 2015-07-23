package legendarena.hub.particles;

import legendarena.hub.particles.lib.ParticleEffect;
import org.bukkit.Location;

public class ParticleCore implements Runnable {

    public void run() {
        //
    }

    public static void playEffect(Location l, ParticleEffect p, float speed, int amount) {
        p.display(0, 0, 0, speed, amount, l, 100);
    }

    public static void playEffect(Location l, ParticleEffect p, float speed, int amount, int radius) {
        p.display(0, 0, 0, speed, amount, l, radius);
    }

    public static void playEffect(int disperse, Location l, ParticleEffect p, float speed, int amount, int radius) {
        p.display(disperse, 0, disperse, speed, amount, l, radius);
    }

    public static void playEffect(int disperse, int yDisperse, Location l, ParticleEffect p, float speed, int amount, int radius) {
        p.display(disperse, yDisperse, disperse, speed, amount, l, radius);
    }

}
