package legendarena.message;

import legendarena.api.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Send a message to a player, in the form of a chat message, title, subtitle, or action bar message.
 *
 * @author ThePixelDev
 */
public class Message {

    private MessageType type;
    private StringBuilder builder;
    private int[] times = {1, 4, 1};

    /**
     * Initialize a new Chat message.
     */
    public Message() {
        type = MessageType.CHAT;
        builder = new StringBuilder();
    }

    /**
     * Initalize a new message.
     * @param type The type to send
     */
    public Message(MessageType type) {
        this.type = type;
        builder = new StringBuilder();
    }

    /**
     * Append a string to the message.
     * @param append The string to append
     */
    public Message append(String append) {
        builder.append(append);
        return this;
    }

    /**
     * Send the built message to the players specified
     * @param players The players to send the message to
     */
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

    /**
     * Send the built message to the players specified
     * @param sound The sound to play while sending it
     * @param players The players to send the message to
     */
    public void send(Sound sound, Player... players) {
        String built = toString();
        for(Player p : players) {
            p.playSound(p.getLocation(), sound, 1, 1);
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

    /**
     * Send the built message to the players specified
     * @param sound The sound to play while sending it
     * @param pitch The pitch of the sound
     * @param players The players to send the message to
     */
    public void send(Sound sound, int pitch, Player... players) {
        String built = toString();
        for(Player p : players) {
            p.playSound(p.getLocation(), sound, pitch, pitch);
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

    /**
     * Broadcast the message to the entire server.
     */
    public void broadcast() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            send(p);
        }
    }

    /**
     * Send the message to everyone on the server with the right rank.
     * @param r The rank to check for
     */
    public void broadcast(Rank r) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!Rank.isRanked(p, r))
                continue;
            send(p);
        }
    }

    /**
     * Returns the built message.
     * @return The message built
     */
    public String toString() {
        return builder.toString();
    }

    /**
     * Sets the display time for a Title or Subtitle.
     */
    public Message setDisplayTime(int in, int stay, int out) {
        if(!(type == MessageType.TITLE || type == MessageType.SUBTITLE))
            throw new ClassCastException("Cannot set display time for chat or action bar messages");
        times[0] = in;
        times[1] = stay;
        times[2] = out;
        return this;
    }

    /**
     * Get the display time for a Title or Subtitle.
     * @return The display time, in the layout of in, stay, and out.
     */
    public int[] getDisplayTimes() {
        return times.clone();
    }

}
