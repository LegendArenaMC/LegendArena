package net.thenamedev.legendapi.core.events;

import net.thenamedev.legendapi.core.chat.channel.Channel;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

/**
 * Created on 5/13/2015
 *
 * @author ThePixelDev
 */
public class RegisterChannelsEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private static final ArrayList<Channel> channels = new ArrayList<>();

    public RegisterChannelsEvent() {}

    public void register(Channel c) {
        channels.add(c);
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
