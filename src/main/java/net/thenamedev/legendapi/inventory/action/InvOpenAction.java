package net.thenamedev.legendapi.inventory.action;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @author ThePixelDev
 */
public interface InvOpenAction {

    /**
     * Be aware that there are NO ERROR CATCHES from what calls this. If there are ANY ERRORS caused by what you call and you
     *   DO NOT CATCH THEM, remember at the very least, these three points:
     *
     * 1. We are not responsible for error floods
     * 2. You were clearly warned
     * 3. Don't complain about it not working, as this is your only warning we are giving you
     */
    void act(Player p, Inventory inv);

}
