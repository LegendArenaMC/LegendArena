package net.thenamedev.legendapi.gamemanager;

/**
 * Created on 5/23/2015
 *
 * @author ThePixelDev
 */
public enum GamePlayerState {

    SPEC(false, false),
    PLAYING(true, false),
    DEAD(false, true);

    private boolean canInteract;
    private boolean isOut;

    GamePlayerState(boolean canInteract, boolean isOut) {
        this.canInteract = canInteract;
        this.isOut = isOut;
    }

    public boolean isOut() {
        return isOut;
    }

    public boolean canInteractWithGame() {
        return canInteract;
    }
}
