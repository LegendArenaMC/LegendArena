package net.thenamedev.legendapi.gamemanager;

/**
 * Game player states.
 *
 * @author ThePixelDev
 */
public enum GamePlayerState {

    /**
     * Spectator mode - cannot do anything
     */
    SPECTATOR(false, false),
    /**
     * Normal player who is actually playing
     */
    PLAYING(true, false),
    /**
     * The player is dead, similar to spectator, but with more magic (some assembly required (/s))
     */
    DEAD(false, true);

    private boolean canInteract;
    private boolean isOut;

    GamePlayerState(boolean canInteract, boolean isOut) {
        this.canInteract = canInteract;
        this.isOut = isOut;
    }

    /**
     * Is the player dead?
     * @return <code>true</code> if out, <code>false</code> otherwise
     */
    public boolean isOut() {
        return isOut;
    }

    /**
     * Can the player interact in the game (e.g open chests, etc)
     * @return <code>true</code> if the player can interact, <code>false</code> otherwise
     */
    public boolean canInteractWithGame() {
        return canInteract;
    }
}
