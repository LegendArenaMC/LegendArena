package net.thenamedev.legendapi.utils;

import net.thenamedev.legendapi.message.Message;
import net.thenamedev.legendapi.message.MessageType;
import org.bukkit.entity.Player;

/**
 * Use <code>new Message(MessageType.ACTIONBAR).append("Some text")</code>.<br><br>
 *
 * Code stolen from here: https://github.com/ConnorLinfoot/ActionBarAPI
 *
 * @see net.thenamedev.legendapi.message.Message
 * @see net.thenamedev.legendapi.message.MessageType
 */
@Deprecated
public class ActionBarAPI {

    public static void sendActionBar(Player player, String message) {
        new Message(MessageType.ACTIONBAR).append(message).send(player);
    }
}