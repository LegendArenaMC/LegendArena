package net.thenamedev.legendapi.core.chat.channel;

import net.thenamedev.legendapi.utils.Rank;

/**
 * Created on 5/13/2015
 *
 * @author ThePixelDev
 */
public class Global implements Channel {

    public String name() {
        return "Global";
    }

    public Rank rankRequired() {
        return null;
    }

    public String msgFormat() {
        return "";
    }

}
