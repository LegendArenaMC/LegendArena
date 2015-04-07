package net.thenamedev.legendapi.chat;

import net.thenamedev.legendapi.utils.MenuCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created on 4/6/2015
 *
 * @author ThePixelDev
 */
public class ChatMenu {

    private static boolean isInit = false;
    private static Inventory inv;

    public static void init() {
        if(isInit)
            return;
        inv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Chat Selector");

        inv.setItem(0, MenuCore.createItem(Material.DRAGON_EGG, ChatColor.BLUE + "Admin", ""));
        inv.setItem(1, MenuCore.createItem(Material.APPLE, ChatColor.BLUE + "Staff", ""));
        inv.setItem(2, MenuCore.createItem(Material.ANVIL, ChatColor.BLUE + "Announce", ""));

        inv.setItem(21, MenuCore.createItem(Material.BED, ChatColor.BLUE + "Global", ""));
        //
        inv.setItem(23, MenuCore.createItem(Material.getMaterial(2258), ChatColor.RED + "Show Staff Chat", ""));

        isInit = true;
    }

    public static void show(Player p) {
        init();
        Inventory playerInv = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Chat Selector");
        playerInv.setContents(inv.getContents());
        p.openInventory(playerInv);
    }

}
