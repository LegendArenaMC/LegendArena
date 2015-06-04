package legendarena.api.gamemanager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Game manager. That's it. No, seriously, that's it.
 *
 * @author ThePixelDev
 */
public class GameManager {

    /**
     * Get a new Game instance.
     * @return A new Game instance
     * @see net.thenamedev.legendapi.gamemanager.Game
     */
    public static Game getInstance() {
        return new Game(Bukkit.getPluginManager());
    }

    /**
     * Get a new GamePlayer instance
     * @param p The player to get an instance of
     * @param state The player's state
     * @return The new GamePlayer instance
     * @see net.thenamedev.legendapi.gamemanager.GamePlayer
     * @see net.thenamedev.legendapi.gamemanager.GamePlayerState
     * @see Player
     */
    public static GamePlayer getPlayerInstance(Player p, GamePlayerState state) {
        return new GamePlayer(p, state);
    }

}
