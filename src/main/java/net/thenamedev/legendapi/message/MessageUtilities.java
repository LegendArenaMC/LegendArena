package net.thenamedev.legendapi.message;

import org.bukkit.entity.Player;

/**
 * Created on 5/24/2015
 *
 * @author ThePixelDev
 */
public class MessageUtilities {

    private static final MessageReflector reflector = new MessageReflector();

    protected static void sendTitle(Player player, String title, int in, int stay, int out){
        reflector.send(0, player, title, in, stay, out);
    }

    protected static void sendSubtitle(Player player, String title, int in, int stay, int out){
        reflector.send(1, player, title, in, stay, out);
    }

    protected static void sendActionbar(Player player, String title){
        reflector.sendActionbar(player, title);
    }

}
