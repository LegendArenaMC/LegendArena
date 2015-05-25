package net.thenamedev.legendapi.gamemanager;

import net.thenamedev.legendapi.core.exceptions.PlayerNotOnlineException;
import org.bukkit.entity.Player;

/**
 * Created on 5/23/2015
 *
 * @author ThePixelDev
 */
public class GamePlayer {

    private Player p;
    private GamePlayerState state;

    public GamePlayer(Player p, GamePlayerState state) {
        this.p = p;
        this.state = state;
    }

    public Player getPlayer() {
        if(p == null) throw new PlayerNotOnlineException();
        return p;
    }

    public GamePlayerState getState() {
        return state;
    }

    public void setState(GamePlayerState state) {
        this.state = state;
    }
}
