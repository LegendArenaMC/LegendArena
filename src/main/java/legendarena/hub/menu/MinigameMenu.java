package legendarena.hub.menu;

import legendarena.api.utils.ChatUtils;
import legendarena.api.utils.MenuUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

/**
 * Minigame selector menu.
 *
 * @author ThePixelDev
 */
public class MinigameMenu implements Listener {

    private static Inventory inv;
    private static boolean init = false;

    private static void init(Plugin p) {
        if(init) return;

        HashMap<Integer, ItemStack> items = new HashMap<>();
        items.put(4, MenuUtils.createItem(Material.BED, ChatColor.GRAY + "⇐ Back", ""));
        items.put(21, MenuUtils.createItem(Material.DISPENSER, ChatColor.GREEN + "Hub"));
        items.put(23, MenuUtils.createItem(Material.STAINED_CLAY, ChatColor.GREEN + "Build My Thing"));

        inv = Bukkit.createInventory(null, 27, ChatUtils.getCustomMsg("Menus") + "Warper");
        for(int a : items.keySet())
            inv.setItem(a, items.get(a));

        Bukkit.getPluginManager().registerEvents(new MinigameMenu(), p);

        init = true;
    }

    public static void show(Player p) {
        init(Bukkit.getPluginManager().getPlugin("LegendArena"));
        p.closeInventory();
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent ev) {
        if(!ev.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        try {
            if(ev.getCurrentItem().getItemMeta() == null) return;
            if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Hub")) {
                ev.setCancelled(true);
                ev.getWhoClicked().closeInventory();
                Player p = (Player) ev.getWhoClicked();
                p.teleport(Bukkit.getWorld("world").getSpawnLocation());
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Build My Thing")) {
                ev.setCancelled(true);
                ev.getWhoClicked().closeInventory();
                ev.getWhoClicked().sendMessage(ChatColor.GREEN + "Totally not a hint towards an actual minigame that works, nope, no hints here </sarcasm>");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("⇐ Back")) {
                ev.setCancelled(true);
                ev.getWhoClicked().closeInventory();
                MainMenu.show((Player) ev.getWhoClicked());
            }

            else { //failsafe
                ev.setCancelled(true);
            }
        } catch(Exception ignore) {
            // Ignore the error
        }
    }

}
