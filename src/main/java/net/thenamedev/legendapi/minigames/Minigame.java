package net.thenamedev.legendapi.minigames;

import net.thenamedev.legendapi.utils.OldRank;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Base minigame API interface.
 * @author ThePixelDev
 */
public interface Minigame {

    /**
     * Returns the minigame information.
     * @return Minigame info class
     */
    Info info();

    /**
     * Returns the actions you can preform on the minigame.
     * @return The MinigameActions specified
     */
    MinigameActions actions();

    interface MinigameActions {
        /**
         * Joins a player into the game.
         * @param p The player to add
         */
        void joinGame(Player p);

        /**
         * Removes a player from the game.
         * @param kick The info about the kick or quit.
         * @see KickInfo
         */
        void quitGame(KickInfo kick);

        /**
         * Starts the minigame.
         * @param startCool This can be 0 for an instant start (and unless the minigame requires it, it should be an instant start with a 0 second cooldown)
         */
        void start(int startCool);

        /**
         * Ends the minigame.
         * @param endCool This can be 0 for an instant end.
         */
        void end(int endCool);

        /**
         * Toggles whether the minigame is enabled or not.
         */
        void toggleStatus();

        /**
         * Checks if the minigame is enabled
         * @return True if it's enabled, false otherwise
         */
        boolean isEnabled();

    }

    interface Info {

        /**
         * The minigame's name.
         * @return The name
         */
        String name();

        /**
         * Use null to signify "All players can join".
         * @return The minimum rank required to join, or null for no minimum rank (everyone can join)
         */
        OldRank minJoinRank();

        World getWorld();

        ArrayList<UUID> playersInGame();

    }
}
