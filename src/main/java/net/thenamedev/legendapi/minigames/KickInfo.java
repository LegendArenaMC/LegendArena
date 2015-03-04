package net.thenamedev.legendapi.minigames;

import org.bukkit.entity.Player;

/**
 * Created on 3/3/2015
 *
 * @author ThePixelDev
 */
public interface KickInfo {

    /**
     * Is it a kick?
     * @return True if kick, false if left
     */
    boolean isKick();

    /**
     * This can be null, but ONLY if isKick() returns FALSE.
     * @return The staff member who kicked the target.
     */
    Player kicker();

    /**
     * THIS SHOULD NOT BE NULL.
     * @return The player to kick.
     */
    Player target();

}
