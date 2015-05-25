package net.thenamedev.legendapi.message;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created on 5/24/2015
 *
 * @author ThePixelDev
 */
public class Message {

    private ArrayList<Player> targets = new ArrayList<>();

    public Message(MessageType type) {
        //
    }

    public Message(MessageType type, Player... targets) {
        //
    }

}
