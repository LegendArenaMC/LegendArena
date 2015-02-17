package net.thenamedev.legendapi.minigames;

import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public abstract class MinigameAPI {

    @NotNull
    public abstract String minigameName();

    @NotNull
    public abstract MinigameInfo info();

}
