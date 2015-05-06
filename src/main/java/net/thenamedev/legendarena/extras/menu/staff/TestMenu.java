package net.thenamedev.legendarena.extras.menu.staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @author ThePixelDev
 */
public class TestMenu {

    private static Inventory inv;
    private static boolean init = false;

    public void init(int amountOfSlots) {
        if(init) return; //if we've already initialized the emeralds menu, don't do anything
        inv = Bukkit.createInventory(null, amountOfSlots, ChatColor.BLUE + "Testing Menu");

        init = true;
    }

    public void show(Player p) {
        p.openInventory(inv);
    }

}
