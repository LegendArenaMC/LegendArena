package net.thenamedev.legendapi.inventory.action;

import org.bukkit.entity.*;

/**
 * @author TheNameMan
 */
public interface Action {

    String itemName();

    boolean useContains();

    void whenClicked(Player p);

}
