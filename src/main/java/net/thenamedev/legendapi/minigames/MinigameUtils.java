package net.thenamedev.legendapi.minigames;

/**
 * To use this, create a new instance via <code>new MinigameUtils(Minigame)</code>.
 */
public class MinigameUtils {

    Minigame targetMG;
    long gameTimer;

    public MinigameUtils(Minigame mg) {
        targetMG = mg;
        //gameTimer = mg.info().
    }

    public String cooldownMsg(int left, String item) {
        return "";
    }

    public String timeLeftMsg(int left) {
        return "";
    }

}
