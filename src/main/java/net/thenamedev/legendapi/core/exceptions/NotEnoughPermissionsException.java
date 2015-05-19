package net.thenamedev.legendapi.core.exceptions;

/**
 * Created on 4/6/2015
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
