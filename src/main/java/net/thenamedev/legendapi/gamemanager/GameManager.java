package net.thenamedev.legendapi.gamemanager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created on 5/16/2015
 *
 * @author ThePixelDev
 */
public class GameManager {

    public static Game getInstance() {
        return new Game(Bukkit.getPluginManager());
    }

    public static GamePlayer getPlayerInstance(Player p, GamePlayerState state) {
        return new GamePlayer(p, state);
    }

}
