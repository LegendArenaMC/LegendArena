package net.thenamedev.legendapi.core.exceptions;

/**
 * Quite simply, called when a player is being banned, but is already banned.<br>
 *
 * Created by thepixeldev on 5/7/15.
 */
public class PlayerAlreadyBannedException extends RuntimeException {

    public PlayerAlreadyBannedException() {
        super();
    }

}
