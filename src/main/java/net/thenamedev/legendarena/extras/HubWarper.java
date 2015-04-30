package net.thenamedev.legendarena.extras;

import net.thenamedev.legendapi.utils.MenuCore;
import net.thenamedev.legendarena.extras.menu.MainMenu;
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

    public static void toggleExemption(UUID p) {
        if(isExempt(p))
            exempt.remove(p);
        else
            exempt.add(p);
    }

    public static boolean isExempt(UUID p) {
        return exempt.contains(p);
    }

    private static ItemStack getCustomization() {
        return MenuCore.createItem(Material.NETHER_STAR, ChatColor.GREEN + "Main Menu", "");
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
            if(ev.getItem().getItemMeta().getDisplayName().contains("Main Menu")) {
                MainMenu.show(ev.getPlayer());
            }
        } catch(Exception ex) {
            //ignore
        }
    }

    public static class InitPlayers implements Runnable {

        public void run() {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(exempt.contains(p.getUniqueId())) continue;
                p.getInventory().clear(); //TODO: Give this inventory clear call a way to have mercy on mods+
                p.getInventory().setItem(4, getCustomization());
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1, true));
            }
        }

    }
}
