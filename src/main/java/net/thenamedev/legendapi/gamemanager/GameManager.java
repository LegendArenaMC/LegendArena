package net.thenamedev.legendapi.gamemanager;

import org.bukkit.Bukkit;

/**
 * Created on 5/16/2015
 *
 * @author ThePixelDev
 */
public class GameManager {

    public static Game getInstance() {
        return new Game(Bukkit.getPluginManager());
    }

}
