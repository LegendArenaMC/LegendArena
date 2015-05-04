package net.thenamedev.legendarena.extras.menu.staff;

import net.thenamedev.legendapi.utils.MenuCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

        int amount = 0;

        while(amount < amountOfSlots + 1) {
            if(amount == 0) {
                amount++;
                continue;
            }
            inv.setItem(amount, MenuCore.createItem(Material.BARRIER, "Testing Barrier", amount, ""));
        }

        init = true;
    }

    public void show(Player p) {
        p.openInventory(inv);
    }

}
