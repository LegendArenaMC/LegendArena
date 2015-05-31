package net.thenamedev.legendapi.message;

import org.bukkit.entity.Player;

/**
 * Internal message sending magic.
 *
 * @author ThePixelDev
 */
public class MessageUtilities {

    private static final MessageReflector reflector = new MessageReflector();

    /**
     * Send a title to a player.
     * @param player The player to send to
     * @param title The title to send
     * @param in The time to fade in for
     * @param stay The time to stay for
     * @param out The time to fade out for
     */
    protected static void sendTitle(Player player, String title, int in, int stay, int out){
        reflector.send(0, player, title, in, stay, out);
    }

    /**
     * Send a subtitle to a player.
     * @param player The player to send to
     * @param title The title to send
     * @param in The time to fade in for
     * @param stay The time to stay for
     * @param out The time to fade out for
     */
    protected static void sendSubtitle(Player player, String title, int in, int stay, int out){
        reflector.send(1, player, title, in, stay, out);
    }

    /**
     * Send an action bar to a player.
     * @param player The player to send to
     * @param title The message to send
     */
    protected static void sendActionbar(Player player, String title){
        reflector.sendActionbar(player, title);
    }

}
