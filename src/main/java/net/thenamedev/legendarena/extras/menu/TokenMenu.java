package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.inventory.*;
import net.thenamedev.legendapi.inventory.action.*;
import net.thenamedev.legendarena.extras.menu.core.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class TokenMenu implements Menu {

    private static InventoryManager i = new InventoryManager();

    boolean init = false;

    public TokenMenu() {
        HashMap<Integer, ItemStack> items = new HashMap<>();
        items.put(1, MenuCore.createItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "", ""));

        List<Action> actions = new ArrayList<>();
        actions.add(new Action() {
            public String itemName() {
                return "Tokens";
            }

            public boolean useContains() {
                return true;
            }

            public void whenClicked(Player p) {
                //
            }
        });
        //InventoryManager init things
        i.setInvName(ChatColor.GREEN + "Tokens Menu");
        i.init();
        i.setInvItems(items);
    }

    public void show(Player p) {
        i.show(p);
    }

}
