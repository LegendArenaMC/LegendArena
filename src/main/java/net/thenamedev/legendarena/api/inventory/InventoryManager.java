package net.thenamedev.legendarena.api.inventory;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;

import java.util.*;

/**
 * Allows easier creation of GUI menus.<br><br>
 *
 * To use this, get a new version of this via <code>new InventoryManager()</code>.
 * @author TheNameMan
 */
public class InventoryManager implements Listener {

    ItemStack[] invItems;
    String invName;
    Inventory inv;

    /**
     * This REQUIRES you to have run setInvItems(ItemStack[]) and setInvName(String) first!
     */
    public void init() {
        if(invItems == null)
            throw new NullPointerException("Run setInvItems() first, doofus!");
        if(invName == null || invName.equals(""))
            throw new NullPointerException("Run setInvName() first, doofus!");

        //inv = Bukkit.cre

        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin("LegendArena"));
    }

    public void setInvName(String name) {
        invName = name;
    }

    public void setInvItems(ItemStack[] items) {
        invItems = items;
    }

    public void show(Player p) {
        //
    }

    public void setEventProperties(List<EventProperties> list) {
        //
    }

    public class EventProperties {

        //

    }
}
