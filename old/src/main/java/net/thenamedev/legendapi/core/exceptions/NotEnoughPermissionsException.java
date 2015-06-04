package net.thenamedev.legendapi.core.exceptions;

/**
 * Thrown when a function does no sanity checks in itself and calls a function which requires a certain player rank.
 *
 * @author ThePixelDev
 */
public class NotEnoughPermissionsException extends RuntimeException {

    public NotEnoughPermissionsException() {
        super();
    }

    /**
     * This is really made the way it is so plugins can catch it, get the message, and (if needed) run it against player.hasPermission()
     * @param permission The permission needed
     */
    public NotEnoughPermissionsException(String permission) {
        super(permission);
    }

}
