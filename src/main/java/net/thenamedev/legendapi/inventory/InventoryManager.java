package net.thenamedev.legendapi.inventory;

import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.inventory.action.Action;
import net.thenamedev.legendapi.inventory.action.InvOpenAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Allows easier creation of GUI menus.<br><br>
 *
 * To use this util, get an instance of this via <code>new InventoryManager()</code>. And please, for the love of pete:<br><br>
 *
 * <strong>DO NOT HAND CODE THE MENUS IF THIS WORKS JUST AS WELL</strong>
 * @author ThePixelDev
 */
public class InventoryManager implements Listener {

    Inventory inv;

    boolean useOnOpen = false;
    InvOpenAction invOpenAction;

    ItemStack[] invItems = null;
    int slots = 0;
    String invName;
    List<Action> actionList;
    String plName = "LegendArena";

    /**
     * This REQUIRES you to have run setSlots(int), setActions(List&lt;Action>) and setInvName(String) first!
     */
    public void init() {
        if(invItems == null)
            throw new MistakesWereMadeException("Run setInvItems() BEFORE this, you derp!");
        if(invName == null || invName.equals(""))
            throw new MistakesWereMadeException("Run setInvName() BEFORE this, you derp!");
        if(slots <= 0)
            throw new MistakesWereMadeException("Run setSlots() BEFORE this, you derp!");
        if(useOnOpen && invOpenAction == null)
            throw new MistakesWereMadeException("The flag useOnOpen was enabled, but the inventory open action event call was not set.");

        inv = Bukkit.createInventory(null, slots, invName);
        inv.setContents(invItems);
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugin(plName));
    }

    /**
     * If this is not set, it defaults to "LegendArena". This is only added for compatibility reasons [if we ever release the API].
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
            throw new MistakesWereMadeException("Turn on useOnOpen (run setUseOnOpen()) first!");
        invOpenAction = ac;
    }

    public void setInvName(String name) {
        invName = name;
    }

    public void setInvItems(HashMap<Integer, ItemStack> items) {
       for(Integer slot : items.keySet())
            invItems[slot] = items.get(slot);
    }

    public void show(Player p) {
        if(inv != null) {
            Inventory pInv = Bukkit.createInventory(null, slots, invName);
            pInv.setContents(invItems);
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
            if(!ev.getInventory().getName().equals(invName))
                return;
            for(Action a : actionList)
                if(a.useContains()) {
                    if(ev.getCurrentItem().getItemMeta().getDisplayName().contains(a.itemName()))
                        a.whenClicked((Player) ev.getWhoClicked());
                } else { //I have to use brackets here. I feel sad now.
                    if(ev.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(a.itemName()))
                        a.whenClicked((Player) ev.getWhoClicked());
                }
        } catch(Exception ignore) {
            //ignore
        }
    }

}
