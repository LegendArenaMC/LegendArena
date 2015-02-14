package net.thenamedev.legendarena.extras.menu.staffmenu;import net.thenamedev.legendarena.extras.menu.*;import net.thenamedev.legendarena.extras.staffchat.*;import net.thenamedev.legendarena.utils.*;import org.bukkit.*;import org.bukkit.entity.*;import org.bukkit.event.*;import org.bukkit.event.inventory.*;import org.bukkit.inventory.*;import org.bukkit.plugin.*;import org.jetbrains.annotations.*;/** * @author TheNameMan */@SuppressWarnings("deprecation")public class ChatMenu implements Listener {    private final Inventory inv;    private final Inventory safeInv;    public ChatMenu(Plugin plugin) {        inv = Bukkit.getServer().createInventory(null, 27, ChatColor.DARK_BLUE + "Chat Selector");        ItemStack admin = MenuCore.createItem(Material.BEDROCK, ChatColor.GREEN + "ADMIN", ChatColor.BLUE + "Admin channel.");        ItemStack mod = MenuCore.createItem(Material.GOLD_NUGGET, ChatColor.GREEN + "MOD", ChatColor.BLUE + "Mod channel.");        ItemStack staff = MenuCore.createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "STAFF", ChatColor.BLUE + "Staff channel.");        ItemStack alert = MenuCore.createItem(Material.DISPENSER, ChatColor.GREEN + "ALERT", ChatColor.BLUE + "Alert channel. [basically a bulk /say]");        ItemStack vip = MenuCore.createItem(Material.EMERALD, ChatColor.GREEN + "VIP", ChatColor.BLUE + "VIP channel.");        ItemStack notify = MenuCore.createItem(Material.BED, ChatColor.GREEN + "NOTIFY", ChatColor.BLUE + "Staff notifications channel.");        ItemStack helper = MenuCore.createItem(Material.SADDLE, ChatColor.GREEN + "HELPER", ChatColor.BLUE + "Helper channel.");        ItemStack note = MenuCore.createItem(Material.PAPER, ChatColor.BLUE + "Notes!", ChatColor.BLUE + "Channels in " + ChatColor.RED + "RED" + ChatColor.BLUE + " you cannot join.", ChatColor.BLUE + "Channels in " + ChatColor.GREEN + "GREEN" + ChatColor.BLUE + " you can join.", "", ChatColor.BLUE + "The channel you are in will be highlighted by", ChatColor.BLUE + "an enchantment glow on the channel's item.");        ItemStack off = MenuCore.createItem(Material.REDSTONE_LAMP_OFF, ChatColor.GREEN + "OFF", ChatColor.BLUE + "Exit all channels and go back to global chat.");        ItemStack so = MenuCore.createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14), ChatColor.RED + "Coming Soon[tm]", ChatColor.RED + "Check back later on this one!");        ItemStack so1 = so.clone();        inv.setItem(0, notify);        inv.setItem(1, admin);        inv.setItem(2, mod);        inv.setItem(3, staff);        inv.setItem(4, alert);        inv.setItem(5, vip);        inv.setItem(6, helper);        inv.setItem(7, so);        inv.setItem(8, so1);        inv.setItem(22, off);        inv.setItem(23, note);        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);        safeInv = Bukkit.getServer().createInventory(null, 27, ChatColor.DARK_BLUE + "Chat Selector"); //make a new inventory as a backup        safeInv.setContents(inv.getContents()); //clone the items    }    public void show(@NotNull Player p) {        Inventory playerInv = Bukkit.getServer().createInventory(null, 27, ChatColor.DARK_BLUE + "Chat Selector"); //Give the player a clean copy so other players don't interfere        playerInv.setContents(safeInv.getContents());        p.openInventory(playerInv);    }    @EventHandler    public void onInvOpen(@NotNull InventoryOpenEvent ev) {        Inventory curInv = ev.getInventory();        if(!curInv.getName().equalsIgnoreCase(inv.getName())) return;        curInv.clear(); //Clear the current inventory        curInv.setContents(safeInv.getContents()); //Set the inventory contents with a known good copy (just to make sure)        boolean canSeeStaffchat = SCType.canSeeStaffchat(ev.getPlayer().getUniqueId());        ItemStack toggleSee;        if(canSeeStaffchat) {            toggleSee = MenuCore.createItem(Material.GREEN_RECORD, ChatColor.BLUE + "Toggle Staff Chat Visibility", ChatColor.RED + "Click to disable");        } else {            //I know I know, terrible hack to get this to be a Blocks record, but it works, that's all that matters            toggleSee = MenuCore.createItem(Material.getMaterial(2258), ChatColor.BLUE + "Toggle Staff Chat Visibility", ChatColor.GREEN + "Click to enable");        }        curInv.setItem(21, toggleSee);        SCUtils.setItem((Player) ev.getPlayer(), curInv);        SCUtils.setRankPermissions((Player) ev.getPlayer(), curInv);    }    //Not adding @EventHandler here just yet just so it doesn't break everything    //So please. Do not use this yet. There is a reason why there is a @Deprecated annotation here.    @Deprecated    public void onInvClose(@NotNull InventoryCloseEvent ev) {        if(!ev.getInventory().getName().equalsIgnoreCase(inv.getName()))            return;        ev.getInventory().all(Material.AIR);    }    @EventHandler    public void onInventoryClick(@NotNull InventoryClickEvent e) {        try {            if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;            if(e.getCurrentItem().getItemMeta() == null) return;            if(e.getCurrentItem().getItemMeta().getDisplayName().contains("ADMIN")) {                e.setCancelled(true);                @NotNull Player p = (Player) e.getWhoClicked();                p.performCommand("c admin");                e.getWhoClicked().closeInventory();            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("MOD")) {                e.setCancelled(true);                @NotNull Player p = (Player) e.getWhoClicked();                p.performCommand("c mod");                e.getWhoClicked().closeInventory();            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("HELPER")) {                e.setCancelled(true);                @NotNull Player p = (Player) e.getWhoClicked();                p.performCommand("c helper");                e.getWhoClicked().closeInventory();            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("STAFF")) {                e.setCancelled(true);                @NotNull Player p = (Player) e.getWhoClicked();                p.performCommand("c staff");                e.getWhoClicked().closeInventory();            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("ALERT")) {                e.setCancelled(true);                @NotNull Player p = (Player) e.getWhoClicked();                p.performCommand("c alert");                e.getWhoClicked().closeInventory();            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("VIP")) {                e.setCancelled(true);                @NotNull Player p = (Player) e.getWhoClicked();                p.performCommand("c vip");                e.getWhoClicked().closeInventory();            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("NOTIFY")) {                e.setCancelled(true);                @NotNull Player p = (Player) e.getWhoClicked();                p.performCommand("c notify");                e.getWhoClicked().closeInventory();            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("OFF")) {                e.setCancelled(true);                @NotNull Player p = (Player) e.getWhoClicked();                p.performCommand("c off");                e.getWhoClicked().closeInventory();            } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Toggle")) {                Inventory curInv = e.getInventory();                e.setCancelled(true);                @NotNull Player p = (Player) e.getWhoClicked();                p.performCommand("c show");                curInv.clear(); //Clear the current inventory                curInv.setContents(safeInv.getContents()); //Set the inventory contents with a known good copy (just to make sure)                SCUtils.setItem((Player) e.getWhoClicked(), e.getInventory());                boolean canSeeStaffchat = SCType.canSeeStaffchat(p.getUniqueId());                ItemStack toggleSee;                if(canSeeStaffchat) {                    toggleSee = MenuCore.createItem(Material.GREEN_RECORD, ChatColor.BLUE + "Toggle Staff Chat Visibility", ChatColor.RED + "Click to disable");                } else {                    //I know I know, terrible hack to get this to be a Blocks record, but it works, that's all that matters                    toggleSee = MenuCore.createItem(Material.getMaterial(2258), ChatColor.BLUE + "Toggle Staff Chat Visibility", ChatColor.GREEN + "Click to enable");                }                e.getInventory().setItem(21, toggleSee);            } else { //failsafe                e.setCancelled(true);            }        } catch(Exception ignore) {            // Ignore the error        }    }}