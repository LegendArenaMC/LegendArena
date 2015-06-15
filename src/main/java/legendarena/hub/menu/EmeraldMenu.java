package legendarena.hub.menu;

import legendapi.emeralds.EmeraldsCore;
import legendapi.utils.ChatUtils;
import legendapi.utils.MenuUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

/**
 * Emeralds menu. Does nothing but show info.
 *
 * @author ThePixelDev
 */
public class EmeraldMenu implements Listener {

    private static Inventory inv;
    private static boolean init = false;

    private static void init(Plugin p) {
        if(init) return; //if we've already initialized the emeralds menu, don't do anything
        inv = Bukkit.createInventory(null, 45, ChatUtils.getCustomMsg("Menus") + "Emeralds Menu");

        inv.setItem(13, MenuUtils.createItem(Material.BED, ChatColor.GRAY + "⇐ Back", ""));
        inv.setItem(32, MenuUtils.createItem(Material.NETHER_BRICK_ITEM, ChatColor.GREEN + "Booster", ChatColor.RED + "Soon™"));

        Bukkit.getPluginManager().registerEvents(new EmeraldMenu(), p);
        init = true;
    }

    public static void show(Player p) {
        init(Bukkit.getPluginManager().getPlugin("LegendArena"));
        Inventory pInv = Bukkit.createInventory(null, 45, ChatUtils.getCustomMsg("Menus") + "Emeralds Menu");
        pInv.setContents(inv.getContents());
        pInv.setItem(30, MenuUtils.createItem(Material.EMERALD, ChatColor.GREEN + "Emeralds", ChatColor.YELLOW + "You have " + ChatColor.RED + EmeraldsCore.getEmeralds(p) + ChatColor.YELLOW + " emeralds!"));
        p.openInventory(pInv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent ev) {
        try {
            if(!ev.getInventory().getName().equalsIgnoreCase(ChatUtils.getCustomMsg("Menus") + "Emeralds Menu")) return;
            if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Emeralds") || ev.getCurrentItem().getItemMeta().getDisplayName().contains("Booster")) {
                ev.getWhoClicked().closeInventory();
                ev.getWhoClicked().sendMessage("...you really expected that would do something. wow. I'm amazed. no, really, I'm amazed.");
            } else if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("⇐ Back")) {
                ev.getWhoClicked().closeInventory();
                MainMenu.show((Player) ev.getWhoClicked());
            }
            ev.setCancelled(true);
        } catch(Exception ignore) {
            // Ignore the error
        }
    }
}
