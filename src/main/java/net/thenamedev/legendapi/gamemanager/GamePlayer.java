package net.thenamedev.legendapi.gamemanager;

import net.thenamedev.legendapi.core.emeralds.EmeraldPlayer;
import net.thenamedev.legendapi.core.exceptions.PlayerNotOnlineException;
import org.bukkit.entity.Player;

/**
 * Game player.
 *
 * @author ThePixelDev
 */
public class GamePlayer {

    private Player p;
    private GamePlayerState state;
    private EmeraldPlayer ep;

    public GamePlayer(Player p, GamePlayerState state) {
        this.p = p;
        this.ep = new EmeraldPlayer(p);
        this.state = state;
    }

    /**
     * Get the player instance
     * @return The player instance
     * @throws PlayerNotOnlineException If the player is not online
     */
    public Player getPlayer() {
        if(p == null) throw new PlayerNotOnlineException();
        return p;
    }

    /**
     * Get the state of the player
     * @return The player's state
     * @see net.thenamedev.legendapi.gamemanager.GamePlayerState
     */
    public GamePlayerState getState() {
        return state;
    }

    /**
     * Set the state of the player
     * @param state The state to set to
     */
    public void setState(GamePlayerState state) {
        this.state = state;
    }

    /**
     * Get's the player's emeralds instance.
     * @return The player's Emeralds instance
     */
    public EmeraldPlayer getEmeralds() {
        return ep;
    }

}
