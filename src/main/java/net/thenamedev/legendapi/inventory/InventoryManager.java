package net.thenamedev.legendapi.inventory;

import net.thenamedev.legendapi.inventory.action.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import java.util.*;

/**
 * Allows easier creation of GUI menus.<br><br>
 *
 * To use this util, get an instance of this via <code>new InventoryManager()</code>. And please, for the love of pete:<br><br>
 *
 * <strong>DO NOT HAND CODE THE MENUS IF THIS WORKS JUST AS WELL</strong>
 * @author TheNameMan
 */
public class InventoryManager implements Listener {

    Inventory inv;

    ItemStack[] invItems;
    int slots = 0;
    String invName;
    List<Action> actionList;

    /**
     * This REQUIRES you to have run setInvItems(ItemStack[]), setSlots(int), setActions(List&lt;Action>) and setInvName(String) first!
     */
    public void init() {
        if(invItems == null)
            throw new NullPointerException("Run setInvItems() first, you derp!");
        if(invName == null || invName.equals(""))
            throw new NullPointerException("Run setInvName() first, you derp!");
        if(slots == 0)
            throw new NullPointerException("Run setSlots() first, you derp!");

        inv = Bukkit.createInventory(null, slots, invName);

        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("LegendArena"));
    }

    public void setInvName(String name) {
        invName = name;
    }

    @Deprecated
    public void setInvItems(ItemStack[] items) {
        invItems = items;
    }

    public void setInvItems(HashMap<Integer, ItemStack> items) {
        for(Integer slot : items.keySet()) {
            inv.setItem(slot, items.get(slot));
        }
    }

    public void show(Player p) {
        if(inv != null) {
            p.openInventory(inv);
        }
    }

    public void setActions(List<Action> list) {
        actionList = list;
    }

    public void setSlots(int amount) {
        slots = amount;
    }

}
