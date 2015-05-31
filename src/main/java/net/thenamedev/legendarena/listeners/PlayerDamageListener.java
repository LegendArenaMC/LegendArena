package net.thenamedev.legendarena.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Damage listener for instant respawns.
 *
 * @author ThePixelDev
 */
public class PlayerDamageListener implements Listener {

    public void onDamage(EntityDamageEvent ev) {
        if(!(ev.getEntity() instanceof Player))
            return;
        Player p = (Player) ev.getEntity();
        if(p.getHealth() == 0.0 || p.getHealth() - ev.getDamage() == 0.0) {
            ev.setCancelled(true);
            //p.teleport()
            p.setGameMode(GameMode.SPECTATOR); //temporary gamemode setter
        }
    }

}
