package net.thenamedev.legendarena.listeners;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class MobSpawnListener implements Listener {

    @EventHandler
    public void mobSpawn(@NotNull CreatureSpawnEvent ev) {
        if(ev.getEntity().getWorld().equals(Bukkit.getWorld("plotworld")) || ev.getEntity().getWorld().equals(Bukkit.getWorld("freebuild")))
            ev.setCancelled(true); //no, not even staff can spawn mobs
    }

}
