package net.thenamedev.legendapi.inventory;

import net.thenamedev.legendapi.inventory.action.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
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

    boolean useOnOpen = false;
    InvOpenAction invOpenAction;

    ItemStack[] invItems;
    int slots = 0;
    String invName;
    List<Action> actionList;
    String plName = "LegendArena"; //This is the only field that is pre-set.

    /**
     * This REQUIRES you to have run setInvItems(ItemStack[]), setSlots(int), setActions(List&lt;Action>) and setInvName(String) first!
     */
    public void init() {
        if(invItems != null)
            throw new NullPointerException("Run setInvItems() AFTER this, you derp!");
        if(invName == null || invName.equals(""))
            throw new NullPointerException("Run setInvName() first, you derp!");
        if(slots < 1)
            throw new NullPointerException("Run setSlots() first, you derp!");

        inv = Bukkit.createInventory(null, slots, invName);
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin(plName));
    }

    /**
     * If this is not set, it defaults to "LegendArena". This is only added for compatibility.
     * @param name The plugin name to use
     */
    public void setPluginName(String name) {
        plName = name;
    }

    public void setUseOnOpen() {
        if(useOnOpen)
            return;
        useOnOpen = true;
    }

    public void setOpenAction(InvOpenAction ac) {
        if(!useOnOpen)
            throw new NullPointerException();
        invOpenAction = ac;
    }

    public void setInvName(String name) {
        invName = name;
    }

    public void setInvItems(HashMap<Integer, ItemStack> items) {
        if(inv == null)
            throw new NullPointerException("Run init() first, THEN this (setInvItems())!");
        for(Integer slot : items.keySet())
            inv.setItem(slot, items.get(slot));
    }

    public void show(Player p) {
        if(inv != null) {
            Inventory pInv = Bukkit.createInventory(null, slots, invName);
            pInv.setContents(inv.getContents());
            p.openInventory(pInv);
        }
    }

    public void setActions(List<Action> list) {
        actionList = list;
    }

    public void setSlots(int amount) {
        slots = amount;
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent ev) {
        if(!useOnOpen)
            return;
        if(!Arrays.equals(ev.getInventory().getContents(), inv.getContents()))
            return;
        invOpenAction.act((Player) ev.getPlayer(), ev.getInventory());
    }

    @EventHandler
    public void onClick(InventoryClickEvent ev) {
        try {
            if(!ev.getInventory().getName().equalsIgnoreCase(invName))
                return;
            for(Action a : actionList)
                if(a.useContains())
                    if(ev.getCurrentItem().getItemMeta().getDisplayName().contains(a.itemName()))
                        a.whenClicked((Player) ev.getWhoClicked());
                    else
                    if(ev.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(a.itemName()))
                        a.whenClicked((Player) ev.getWhoClicked());
        } catch(Exception ignore) {
            //ignore
        }
    }

}
