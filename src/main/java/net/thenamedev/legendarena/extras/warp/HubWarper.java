package net.thenamedev.legendarena.extras.warp;import net.thenamedev.legendapi.utils.*;import net.thenamedev.legendarena.extras.menu.core.*;import org.bukkit.*;import org.bukkit.entity.*;import org.bukkit.event.*;import org.bukkit.event.block.*;import org.bukkit.event.inventory.*;import org.bukkit.event.player.*;import org.bukkit.inventory.*;import org.bukkit.potion.*;import org.jetbrains.annotations.*;import java.util.*;/** * @author TheNameMan */public class HubWarper implements Listener {    public static final List<UUID> exempt = new ArrayList<>();    public static final List<UUID> hidePlayers = new ArrayList<>();    public static final List<UUID> hidePlayersHolding = new ArrayList<>();    @NotNull    private HashMap<UUID, Cooldown> cooldown = new HashMap<>();    public static ItemStack getWarperItem() {        return MenuCore.createItem(Material.WATCH, ChatColor.GREEN + "Warper", ChatColor.BLUE + "Warp around the server, in style.");    }    public static ItemStack getParticlesItem() {        return MenuCore.createItem(Material.GHAST_TEAR, ChatColor.GOLD + "{VIP}" + ChatColor.GREEN + " Particle Selector", ChatColor.BLUE + "Select the particles you have.");    }    public static ItemStack getFlyItem() {        return MenuCore.createItem(Material.FEATHER, ChatColor.GOLD + "{VIP}" + ChatColor.GREEN + " Fly", ChatColor.BLUE + "Become weightless and FLY!");    }    public static ItemStack getHidePlayersItem() {        return MenuCore.createItem(Material.REDSTONE_TORCH_ON, ChatColor.GREEN + "Hide Players", ChatColor.BLUE + "Hide players from view. [Only affects the hub!]");    }    public static ItemStack getFWItem() {        return MenuCore.createItem(Material.FIREWORK, ChatColor.GOLD + "{VIP}" + ChatColor.GREEN + " Firework", ChatColor.BLUE + "Become weightless and FLY!");    }    public static void initPlayer(@NotNull Player p) {        if(p.getWorld() != Bukkit.getWorld("hub")) {            return;        }        if(exempt.contains(p.getUniqueId()))            return;        p.getInventory().clear();        p.getInventory().setItem(0, getFlyItem());        p.getInventory().setItem(2, getParticlesItem());        p.getInventory().setItem(4, getWarperItem());        p.getInventory().setItem(6, getHidePlayersItem());        p.getInventory().setItem(8, getFWItem());        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 2, true));    }    @EventHandler     public void listenForClick(@NotNull PlayerInteractEvent ev) {        try {            if(ev.getAction() != Action.RIGHT_CLICK_AIR && ev.getAction() != Action.RIGHT_CLICK_BLOCK) return;            if(!ev.getPlayer().getWorld().getName().equalsIgnoreCase("hub")) return;            if(ev.getItem().getItemMeta().getDisplayName().contains("Warper")) {                ev.getPlayer().performCommand("warp");                ev.setCancelled(true);            } else if(ev.getItem().getItemMeta().getDisplayName().contains("Particle Selector")) {                ev.getPlayer().performCommand("particles");                ev.setCancelled(true);            } else if(ev.getItem().getItemMeta().getDisplayName().contains("Fly")) {                ev.getPlayer().performCommand("fly");                ev.setCancelled(true);            } else if(ev.getItem().getItemMeta().getDisplayName().contains("Firework")) {                ev.getPlayer().performCommand("firework");                ev.setCancelled(true);            } else if(ev.getItem().getItemMeta().getDisplayName().contains("Hide Players") || ev.getItem().getItemMeta().getDisplayName().contains("Show Players")) {                ev.setCancelled(true);                if(cooldown.containsKey(ev.getPlayer().getUniqueId()) && !cooldown.get(ev.getPlayer().getUniqueId()).done()) {                    ev.getPlayer().sendMessage(cooldown.get(ev.getPlayer().getUniqueId()).getTimeRemaining());                    return;                }                if(hidePlayers.contains(ev.getPlayer().getUniqueId())) {                    hidePlayers.remove(ev.getPlayer().getUniqueId());                    ev.getPlayer().sendMessage(PluginUtils.msgNormal + "Players now shown!");                } else {                    hidePlayers.add(ev.getPlayer().getUniqueId());                    ev.getPlayer().sendMessage(PluginUtils.msgNormal + "Players now hidden!");                }                //3 second cooldown                cooldown.put(ev.getPlayer().getUniqueId(), new Cooldown(3));            }        } catch(NullPointerException ignore) {}    }    @EventHandler    public void listenForDrop(@NotNull PlayerDropItemEvent ev) {        try {            if(exempt.contains(ev.getPlayer().getUniqueId())) return;            if(ev.getItemDrop().getWorld() != Bukkit.getWorld("hub")) return;            ev.setCancelled(true);        } catch(NullPointerException ignore) {}    }    @EventHandler    public void listenForPickupItem(@NotNull PlayerPickupItemEvent ev) {        try {            if(exempt.contains(ev.getPlayer().getUniqueId())) return;            if(ev.getPlayer().getWorld() != Bukkit.getWorld("hub")) return;            ev.setCancelled(true);        } catch(NullPointerException ignore) {}    }    @EventHandler    public void listenForInventoryInteract(@NotNull InventoryClickEvent ev) {        try {            if(ev.getWhoClicked().getWorld() == Bukkit.getWorld("hub")) {                if(exempt.contains(ev.getWhoClicked().getUniqueId()))                    return;                ev.setCancelled(true);            }        } catch(NullPointerException ignore) {Bukkit.getLogger().info("t");}    }    public static class InitPlayers implements Runnable {        public void run() {            for(@NotNull Player p : Bukkit.getOnlinePlayers()) {                initPlayer(p);            }        }    }}