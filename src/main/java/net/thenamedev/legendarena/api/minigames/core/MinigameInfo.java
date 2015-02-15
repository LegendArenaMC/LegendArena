package net.thenamedev.legendarena.api.minigames.core;

import net.thenamedev.legendarena.core.*;

/**
 * @author TheNameMan
 */
public interface MinigameInfo {

    /**
     * The minigame's name.
     * @return The name
     */
    String name();

    /**
     * Use null to signify "All players can join".
     * @return The minimum rank required to join, or null for no minimum rank (everyone can join)
     */
    Rank minJoinRank();

    //

}
