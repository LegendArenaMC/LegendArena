package net.thenamedev.legendarena.extras.menu;

import net.thenamedev.legendapi.inventory.InventoryManager;
import net.thenamedev.legendapi.inventory.action.Action;
import net.thenamedev.legendapi.utils.MenuCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ThePixelDev
 */
public class MinigameMenu implements Listener {

    public MinigameMenu(Plugin plugin) {
        //Lists

        // Begin item list mayhem
        HashMap<Integer, ItemStack> items = new HashMap<>();
        items.put(1, MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Survival Games"));
        items.put(7, MenuCore.createItem(Material.EGG, ChatColor.GREEN + "Chicken Mayhem"));
        items.put(13, MenuCore.createItem(Material.SNOW_BALL, ChatColor.GREEN + "Plugin: Now FOSS!", ChatColor.YELLOW + "https://github.com/LegendArenaMC/LegendArena"));
        items.put(19, MenuCore.createItem(Material.COMMAND, ChatColor.GREEN + "Hub"));
        items.put(25, MenuCore.createItem(Material.BRICK, ChatColor.GREEN + "Build My Thing"));

        // Begin action list mayhem

        List<Action> actionList = new ArrayList<>();
        actionList.add(new Action() {
            @Override
            public String itemName() {
                return "Chicken Mayhem";
            }

            @Override
            public boolean useContains() {
                return true;
            }

            @Override
            public boolean cancelEvent() {
                return true;
            }

            @Override
            public boolean closeInv() {
                return true;
            }

            @Override
            public void whenClicked(Player p) {
                p.sendMessage(ChatColor.YELLOW + "soon[tm]");
            }
        });
        actionList.add(new Action() {
            @Override
            public String itemName() {
                return "Build My Thing";
            }

            @Override
            public boolean useContains() {
                return true;
            }

            @Override
            public boolean cancelEvent() {
                return true;
            }

            @Override
            public boolean closeInv() {
                return true;
            }

            @Override
            public void whenClicked(Player p) {
                p.sendMessage(ChatColor.YELLOW + "soon[tm]");
            }
        });
        actionList.add(new Action() {
            @Override
            public String itemName() {
                return "Hub";
            }

            @Override
            public boolean useContains() {
                return true;
            }

            @Override
            public boolean cancelEvent() {
                return true;
            }

            @Override
            public boolean closeInv() {
                return true;
            }

            @Override
            public void whenClicked(Player p) {
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " HUB...");
                p.teleport(Bukkit.getWorld("hub").getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
            }
        });
        actionList.add(new Action() {
            @Override
            public String itemName() {
                return "Hub";
            }

            @Override
            public boolean useContains() {
                return true;
            }

            @Override
            public boolean cancelEvent() {
                return true;
            }

            @Override
            public boolean closeInv() {
                return true;
            }

            @Override
            public void whenClicked(Player p) {
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " HUB...");
                p.teleport(Bukkit.getWorld("hub").getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
            }
        });
        actionList.add(new Action() {
            @Override
            public String itemName() {
                return "Survival Games";
            }

            @Override
            public boolean useContains() {
                return true;
            }

            @Override
            public boolean cancelEvent() {
                return true;
            }

            @Override
            public boolean closeInv() {
                return true;
            }

            @Override
            public void whenClicked(Player p) {
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SURVIVAL GAMES...");
                p.teleport(new Location(Bukkit.getWorld("hub"), -282.50, 5.50, 36.50), PlayerTeleportEvent.TeleportCause.PLUGIN);
            }
        });
        actionList.add(new Action() {
            @Override
            public String itemName() {
                return "Now FOSS!";
            }

            @Override
            public boolean useContains() {
                return true;
            }

            @Override
            public boolean cancelEvent() {
                return true;
            }

            @Override
            public boolean closeInv() {
                return true;
            }

            @Override
            public void whenClicked(Player p) {
                p.sendMessage(ChatColor.GREEN + "GitHub repo:" + ChatColor.DARK_PURPLE + " https://github.com/LegendArenaMC/LegendArena");
                p.sendMessage(ChatColor.GREEN + "Oh - by the way - FOSS means Free and Open Source Software. Just saying.");
            }
        });

        //Init stuffs
        /*invMan.setInvName(ChatColor.GREEN + "Warper");
        invMan.setActions(actionList);
        invMan.setSlots(27);
        invMan.setInvItems(items);
        invMan.init();*/
    }

    public void show(Player p) {
        //invMan.show(p);
    }

    /*@EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        try {
            if(e.getCurrentItem().getItemMeta() == null) return;
            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Survival Games")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SURVIVAL GAMES...");
                p.teleport(new Location(Bukkit.getWorld("hub"), -282.50, 5.50, 36.50), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Spleef")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SPLEEF...");
                p.teleport(Bukkit.getWorld("spleef").getSpawnLocation(), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Splegg")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " SPLEGG...");
                p.teleport(Bukkit.getWorld("splegg").getSpawnLocation(), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Hub")) {
                e.setCancelled(true);
                @NotNull Player p = (Player) e.getWhoClicked();
                p.sendMessage(ChatColor.GREEN + "Warping you to" + ChatColor.RED + " HUB...");
                p.teleport(Bukkit.getWorld("hub").getSpawnLocation(), TeleportCause.PLUGIN);
                e.getWhoClicked().closeInventory();
            }

            else { //failsafe
                e.setCancelled(true);
            }
        } catch(Exception ignore) {
            // Ignore the error
        }
    }*/

}
