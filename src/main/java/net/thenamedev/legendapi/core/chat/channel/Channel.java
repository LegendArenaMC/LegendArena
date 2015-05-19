package net.thenamedev.legendapi.core.chat.channel;

import net.thenamedev.legendapi.utils.Rank;

/**
 * Use this to register a channel, and call register() when a RegisterChannelsEvent event is caught.<br><br>
 *
 * @author ThePixelDev
 */
public interface Channel {

    /**
     * @return Channel name
     */
    String name();

    /**
     * Rank required to use this channel.
     * @return Rank required - null/Member if no rank requirements
     */
    Rank rankRequired();

    /**
     * The message format. Replacement text includes:
     * <ul>
     *     <li>{MESSAGE} - The message</li>
     *     <li>{DISPLAYNAME} - The player's (formatted) display name</li>
     *     <li>{RANK} - The player's rank</li>
     * </ul>
     * @return The chat format
     */
    String msgFormat();

}
