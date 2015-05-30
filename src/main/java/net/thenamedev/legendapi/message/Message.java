package net.thenamedev.legendapi.message;

import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created on 5/24/2015
 *
 * @author ThePixelDev
 */
public class Message {

    private MessageType type;
    private StringBuilder builder;
    private int[] times = {1, 4, 1};

    public Message() {
        type = MessageType.CHAT;
        builder = new StringBuilder();
    }

    public Message(MessageType type) {
        this.type = type;
        builder = new StringBuilder();
    }

    public Message append(String append) {
        builder.append(append);
        return this;
    }

    public void send(Player... players) {
        String built = toString();
        for(Player p : players) {
            switch(type) {
                case CHAT:
                    p.sendMessage(built);
                    break;
                case ACTIONBAR:
                    MessageUtilities.sendActionbar(p, built);
                    break;
                case SUBTITLE:
                    MessageUtilities.sendSubtitle(p, built, times[0], times[1], times[2]);
                    break;
                case TITLE:
                    MessageUtilities.sendTitle(p, built, times[0], times[1], times[2]);
                    break;
            }
        }
    }

    public void broadcast() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            send(p);
        }
    }

    public String toString() {
        return builder.toString();
    }

    public void setDisplayTime(int in, int stay, int out) {
        if(!(type == MessageType.TITLE || type == MessageType.SUBTITLE))
            throw new ClassCastException("Cannot set display time for chat or action bar messages");
        times[0] = in;
        times[1] = stay;
        times[2] = out;
    }

    public int[] getDisplayTimes() {
        return times.clone();
    }

    public void broadcast(Rank r) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!Rank.isRanked(p, r))
                continue;
            send(p);
        }
    }
}
