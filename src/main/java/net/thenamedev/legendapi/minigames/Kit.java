package net.thenamedev.legendapi.minigames;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created on 3/2/2015
 *
 * @author ThePixelDev
 */
@Deprecated
public interface Kit {

    /**
     * The backend kit name.
     * @return The kit name
     */
    String backendName();

    /**
     * Items to give - should not be null.
     *
     * Layout:
     * - Integer for slot;
     * - ItemStack for the item to put in said slot (use MenuCore.createItem() to create an ItemStack easily)
     *
     * @return The items to give
     */
    HashMap<Integer, ItemStack> items();

    /**
     * Can be null for "no armour". (and for all the US-english-only people, this is armor.)
     * @return The armour, or null for no armour.
     */
    ItemStack[] armour();

    /**
     * Public [front-end] kit name.
     * @return The kit name to show to players.
     */
    String publicName();

}
