package net.thenamedev.gamemanager;

/**
 * Created by thepixeldev on 5/4/15.
 */
public interface Minigame {

    void start();

    /**
     * Can return 0 if the game isn't running, or if not applicable (for example, an admin-only-started minigame).
     */
    int currentTimeLeft();

}
