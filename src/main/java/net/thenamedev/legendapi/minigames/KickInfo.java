package net.thenamedev.legendapi.minigames;

import org.bukkit.entity.Player;

/**
 * Information about a minigame kick or quit.
 *
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
     * THIS SHOULD NOT BE NULL. IF IT IS THE MINIGAME SHOULD MOST CERTANTLY COMPLAIN LIKE HELL ABOUT IT.
     * @return The player to kick.
     */
    Player target();

    /**
     * The reason of quitting. Can be "Kicked: [Optional reason]" if a quit, or "Quit: [Optional reason]" if it's a quit.
     * @return The reason of quitting the game.
     */
    String reason();

}
