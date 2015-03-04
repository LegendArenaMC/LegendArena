package net.thenamedev.legendapi.minigames;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created on 3/2/2015
 *
 * @author ThePixelDev
 */
public interface Kit {

    /**
     * The backend kit name.
     * @return The kit name
     */
    public String backendName();

    /**
     * Items to give - should not be null.
     * @return The items to give
     */
    public HashMap<Integer, ItemStack> items();

    /**
     * Can be null for "no armour".
     * @return The armour, or null for none
     */
    public ItemStack[] armour();

    /**
     * Public [front-end] kit name.
     * @return The kit name to show to players.
     */
    public String publicName();

}
