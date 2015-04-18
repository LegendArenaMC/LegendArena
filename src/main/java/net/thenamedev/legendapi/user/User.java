package net.thenamedev.legendapi.user;

import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.entity.Player;

/**
 * Created on 4/17/15
 *
 * @author ThePixelDev
 */
public interface User {

    Player player();

    Rank rank();

    int tokens();

}
