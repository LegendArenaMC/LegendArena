package net.thenamedev.legendarena;

import net.thenamedev.legendapi.gamemanager.Game;
import net.thenamedev.legendapi.gamemanager.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Core of the Never Have I Ever minigame
 *
 * @author ThePixelDev
 */
public class NeverHaveIEver extends JavaPlugin {

    public static Game instance;

    public void onEnable() {
        instance = GameManager.getInstance();
        //instance.registerListener(null);
    }

    public void onDisable() {
        //
    }

}
