package net.thenamedev.legendapi.core.chat;

import net.thenamedev.legendapi.core.chat.channel.Channel;
import net.thenamedev.legendapi.core.chat.channel.Global;
import net.thenamedev.legendapi.core.events.RegisterChannelsEvent;
import org.bukkit.Bukkit;

import java.util.ArrayList;

/**
 * Created on 5/13/2015
 *
 * @author ThePixelDev
 */
public class Channels {

    private static boolean init = false;
    private static ArrayList<Channel> channels = new ArrayList<>();

    public static void init() {
        if(init) return;

        RegisterChannelsEvent ev = new RegisterChannelsEvent();
        ev.register(new Global());
        Bukkit.getPluginManager().callEvent(ev);

        for(Channel c : ev.getChannels()) {
            //
        }

        init = true;
    }

    public static void register(Channel c) {
        channels.add(c);
    }

}
