package net.thenamedev.legendapi.inventory.action;

import org.bukkit.entity.Player;

/**
 * @author ThePixelDev
 */
public interface Action {

    /**
     * What's the name of the item we're listening for?
     */
    String itemName();

    /**
     * Should we use .contains() [True] or .equalsIgnoreCase() [False]?
     */
    boolean useContains();

    /**
     * Should we automatically cancel the event?<br><br>
     *
     * <strong>WARNING: SETTING THIS TO FALSE ALLOWS PLAYERS TO GET THE ITEM.</strong><br><br>
     *
     * Unless you know exactly what you're doing, set this to TRUE.
     */
    boolean cancelEvent();

    /**
     * This is really just for my sanity so I can control if I want to close the inventory or not.
     */
    boolean closeInv();

    /**
     * This automatically cancels the event for you. If you wish to not cancel the event, set cancelEvent() to return FALSE.
     */
    void whenClicked(Player p);

}
