package net.thenamedev.gamemanager;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by thepixeldev on 5/4/15.
 */
public class GameManager extends JavaPlugin {

    public void onEnable() {
        //TODO: Setup internal tools for stuffs
    }

    public void onDisable() {
        //TODO: Stop every minigame's listeners, timers, etc
    }

    public static Minigame createInstance() {
        return new Minigame() {
            @Override
            public void start() {
                //
            }

            @Override
            public int currentTimeLeft() {
                return 0;
            }
        };
    }

}
