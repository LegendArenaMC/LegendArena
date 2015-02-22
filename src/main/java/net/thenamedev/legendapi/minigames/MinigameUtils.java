package net.thenamedev.legendapi.minigames;

import net.thenamedev.legendapi.minigames.Minigame.*;

/**
 * @author TheNameMan
 */
public class MinigameUtils {

    public static Minigame getMinigameInstance(final Info info, final MinigameActions actions) {
        return new Minigame() {
            public Info info() {
                return info;
            }

            public MinigameActions actions() {
                return actions;
            }
        };
    }



}
