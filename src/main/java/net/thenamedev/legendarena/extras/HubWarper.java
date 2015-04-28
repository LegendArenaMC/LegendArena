package net.thenamedev.legendarena.extras;

import net.thenamedev.legendapi.utils.MenuCore;
import net.thenamedev.legendapi.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by thepixeldev on 3/24/15.
 */
public class HubWarper implements Listener {

    private static final List<UUID> exempt = new ArrayList<>();

    @Deprecated
    public static final List<UUID> hidePlayers = new ArrayList<>();
    @Deprecated
    public static final List<UUID> hidePlayersHolding = new ArrayList<>();

    public static void toggleExemption(UUID p) {
        if(isExempt(p))
            exempt.remove(p);
        else
            exempt.add(p);
    }

    public static boolean isExempt(UUID p) {
        return exempt.contains(p);
    }

    private static ItemStack getWarper() {
        return MenuCore.createItem(Material.WATCH, ChatColor.GREEN + "Warper", ChatColor.BLUE + "Warp around the server; in style.");
    }

    private static ItemStack getSoonTM() {
        return MenuCore.createItem(Material.STAINED_GLASS_PANE, "Soon[tm]", "User error; insert new user and press any key to continue.");
    }

    @EventHandler(ignoreCancelled = true)
    public void listenForDrop(PlayerDropItemEvent ev) {
        if(isExempt(ev.getPlayer().getUniqueId())) return;
        ev.setCancelled(true);
    }

    @EventHandler
    public void listenForInteract(PlayerInteractEvent ev) {
        try {
            if(isExempt(ev.getPlayer().getUniqueId())) return;
            ev.setCancelled(true);
            if(ev.getItem().getItemMeta().getDisplayName().contains("Warp")) {
                ev.getPlayer().performCommand("warp");
            } else if(ev.getItem().getItemMeta().getDisplayName().contains("Soon")) {
                ev.getPlayer().sendMessage(PluginUtils.msgNormal + "soon[tm]");
            }
        } catch(Exception ex) {
            //ignore
        }
    }

    public static class InitPlayers implements Runnable {

        public void run() {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(exempt.contains(p.getUniqueId())) continue;
                p.getInventory().clear();
                p.getInventory().setItem(3, getWarper());
                p.getInventory().setItem(5, getSoonTM());
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1, true));
            }
        }

    }
}
