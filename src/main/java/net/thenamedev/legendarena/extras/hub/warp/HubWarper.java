package net.thenamedev.legendarena.extras.hub.warp;import net.thenamedev.legendapi.utils.Cooldown;import net.thenamedev.legendapi.utils.MenuCore;import net.thenamedev.legendapi.utils.PlayerUtils;import net.thenamedev.legendapi.utils.PluginUtils;import org.bukkit.Bukkit;import org.bukkit.ChatColor;import org.bukkit.Material;import org.bukkit.entity.Player;import org.bukkit.event.EventHandler;import org.bukkit.event.Listener;import org.bukkit.event.block.Action;import org.bukkit.event.inventory.InventoryClickEvent;import org.bukkit.event.player.PlayerDropItemEvent;import org.bukkit.event.player.PlayerInteractEvent;import org.bukkit.event.player.PlayerPickupItemEvent;import org.bukkit.inventory.ItemStack;import org.bukkit.potion.PotionEffect;import org.bukkit.potion.PotionEffectType;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.UUID;/** * @author ThePixelDev */public class HubWarper implements Listener {    public static final List<UUID> exempt = new ArrayList<>();    public static final List<UUID> hidePlayers = new ArrayList<>();    public static final List<UUID> hidePlayersHolding = new ArrayList<>();    private HashMap<UUID, Cooldown> cooldown = new HashMap<>();    public static ItemStack getWarperItem() {        return MenuCore.createItem(Material.WATCH, ChatColor.GREEN + "Warper", ChatColor.BLUE + "Warp around the server, in style.");    }    public static ItemStack getParticlesItem() {        return MenuCore.createItem(Material.GHAST_TEAR, ChatColor.GOLD + "{VIP}" + ChatColor.GREEN + " Particle Selector", ChatColor.BLUE + "Select the particles you have.");    }    public static ItemStack getFlyItem() {        return MenuCore.createItem(Material.FEATHER, ChatColor.GOLD + "{VIP}" + ChatColor.GREEN + " Fly", ChatColor.BLUE + "Become weightless and FLY!");    }    public static ItemStack getHidePlayersItem() {        return MenuCore.createItem(Material.REDSTONE_TORCH_ON, ChatColor.GREEN + "Hide Players", ChatColor.BLUE + "Hide players from view. [Only affects the hub!]");    }    public static ItemStack getFWItem() {        return MenuCore.createItem(Material.FIREWORK, ChatColor.GOLD + "{VIP}" + ChatColor.GREEN + " Firework", ChatColor.BLUE + "Launch a firework!");    }    public static void initPlayer(Player p) {        if(exempt.contains(p.getUniqueId())) return;        if(PlayerUtils.getPlayerMinigame(p.getUniqueId()) != null) return;        p.getInventory().clear();        p.getInventory().setItem(0, getWarperItem());        p.getInventory().setItem(1, getHidePlayersItem());        p.getInventory().setItem(6, getFlyItem());        p.getInventory().setItem(7, getParticlesItem());        p.getInventory().setItem(8, getFWItem());        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 2, true));    }    @EventHandler     public void listenForClick(PlayerInteractEvent ev) {        try {            if(ev.getAction() != Action.RIGHT_CLICK_AIR && ev.getAction() != Action.RIGHT_CLICK_BLOCK) return;            if(PlayerUtils.getPlayerMinigame(ev.getPlayer().getUniqueId()) != null) return;            if(ev.getItem().getItemMeta().getDisplayName().contains("Warper")) {                ev.getPlayer().performCommand("warp");                ev.setCancelled(true);            } else if(ev.getItem().getItemMeta().getDisplayName().contains("Particle Selector")) {                ev.getPlayer().performCommand("particles");                ev.setCancelled(true);            } else if(ev.getItem().getItemMeta().getDisplayName().contains("Fly")) {                ev.getPlayer().performCommand("fly");                ev.setCancelled(true);            } else if(ev.getItem().getItemMeta().getDisplayName().contains("Firework")) {                ev.getPlayer().performCommand("firework");                ev.setCancelled(true);            } else if(ev.getItem().getItemMeta().getDisplayName().contains("Hide Players") || ev.getItem().getItemMeta().getDisplayName().contains("Show Players")) {                ev.setCancelled(true);                if(cooldown.containsKey(ev.getPlayer().getUniqueId()) && !cooldown.get(ev.getPlayer().getUniqueId()).done()) {                    ev.getPlayer().sendMessage(cooldown.get(ev.getPlayer().getUniqueId()).getTimeRemaining());                    return;                }                if(hidePlayers.contains(ev.getPlayer().getUniqueId())) {                    hidePlayers.remove(ev.getPlayer().getUniqueId());                    ev.getPlayer().sendMessage(PluginUtils.msgNormal + "Players now shown!");                } else {                    hidePlayers.add(ev.getPlayer().getUniqueId());                    ev.getPlayer().sendMessage(PluginUtils.msgNormal + "Players now hidden!");                }                //2 second cooldown                cooldown.put(ev.getPlayer().getUniqueId(), new Cooldown(2));            }        } catch(NullPointerException ignore) {}    }    @EventHandler    public void listenForDrop(PlayerDropItemEvent ev) {        try {            if(exempt.contains(ev.getPlayer().getUniqueId())) return;            if(PlayerUtils.getPlayerMinigame(ev.getPlayer().getUniqueId()) != null) return;            ev.setCancelled(true);        } catch(NullPointerException ignore) {}    }    @EventHandler    public void listenForPickupItem(PlayerPickupItemEvent ev) {        try {            if(exempt.contains(ev.getPlayer().getUniqueId())) return;            if(PlayerUtils.getPlayerMinigame(ev.getPlayer().getUniqueId()) != null) return;            ev.setCancelled(true);        } catch(NullPointerException ignore) {}    }    @EventHandler    public void listenForInventoryInteract(InventoryClickEvent ev) {        try {            if(ev.getWhoClicked().getWorld() == Bukkit.getWorld("hub")) {                if(exempt.contains(ev.getWhoClicked().getUniqueId()))                    return;                if(PlayerUtils.getPlayerMinigame(ev.getWhoClicked().getUniqueId()) != null) return;                ev.setCancelled(true);            }        } catch(NullPointerException ignore) {}    }    public static class InitPlayers implements Runnable {        public void run() {            for(Player p : Bukkit.getOnlinePlayers()) {                initPlayer(p);            }        }    }}