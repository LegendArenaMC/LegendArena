package net.thenamedev.legendarena.extras.staffchat;import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;import org.bukkit.entity.Player;import java.util.ArrayList;import java.util.List;import java.util.UUID;/** * Backend staff chat utils. * @author ThePixelDev */@Deprecatedpublic enum SCType {    /**     * Admin chat (yes, I am that lazy that I can't be bothered to actually rename it from GM to ADMIN)     */    GM,    /**     * Moderator staff chat     */    MOD,    /**     * General staff chat     */    STAFF,    /**     * "Backend" notifications staff chat     */    NOTIFICATION,    /**     * Global broadcast system for mods and up. Essentially a notifications system like /say, but doesn't requre a command to be run every message to broadcast.     */    ALERT,    /**     * VIP channel     */    VIP,    /**     * Helper staff channel     */    HELPER,    /**     * Public chat.     */    PUBLIC;    volatile static List<UUID> gm = new ArrayList<>();    volatile static List<UUID> mod = new ArrayList<>();    volatile static List<UUID> notify = new ArrayList<>();    volatile static List<UUID> vip = new ArrayList<>();    volatile static List<UUID> staff = new ArrayList<>();    volatile static List<UUID> helper = new ArrayList<>();    volatile static List<UUID> alert = new ArrayList<>();    volatile static List<UUID> noStaffchat = new ArrayList<>();    public static void setCanSeeStaffchat(UUID p, boolean canSee) {        if(!canSee) {            noStaffchat.add(p);        } else {            noStaffchat.remove(p);        }    }    public static boolean canSeeStaffchat(UUID p) {        return !noStaffchat.contains(p);    }    public static boolean hasStaffchat(UUID p, SCType type) {        return type == GM && gm.contains(p) ||                type == MOD && mod.contains(p) ||                type == STAFF && staff.contains(p) ||                type == ALERT && alert.contains(p) ||                type == VIP && vip.contains(p) ||                type == NOTIFICATION && notify.contains(p) ||                type == HELPER && helper.contains(p);    }    public static SCType getType(UUID p) {        if(gm.contains(p)) return GM;        else if(notify.contains(p)) return NOTIFICATION;        else if(mod.contains(p)) return MOD;        else if(alert.contains(p)) return ALERT;        else if(staff.contains(p)) return STAFF;        else if(helper.contains(p)) return HELPER;        else if(vip.contains(p)) return VIP;        else return PUBLIC;    }    public static void addUser(UUID p, SCType type) {        if(type == NOTIFICATION) {            clearStaffchat(p);            notify.add(p);        } else if(type == GM) {            clearStaffchat(p);            gm.add(p);        } else if(type == HELPER) {            clearStaffchat(p);            helper.add(p);        } else if(type == MOD) {            clearStaffchat(p);            mod.add(p);        } else if(type == STAFF) {            clearStaffchat(p);            staff.add(p);        } else if(type == ALERT) {            clearStaffchat(p);            alert.add(p);        } else if(type == VIP) {            clearStaffchat(p);            vip.add(p);        } else if(type == PUBLIC) {            throw new MistakesWereMadeException("..you're kidding.. right? (hint: clear a player's channels to put them in the public channel)");        }    }    /**     * Clears ALL channels. Only use this when required or disabling the plugin.     */    public static void clearStaffchatTypes() {        staff.clear(); //clear the STAFF channel player list        gm.clear(); //clear the GM channel player list        alert.clear(); //clear the ALERT channel player list        mod.clear(); //clear the MOD channel player list        vip.clear(); //clear the VIP channel player list        notify.clear(); //clear the NOTIFY channel player list        helper.clear(); //clear the HELPER channel player list    }    /**     * Removes a player from all staff chat channels.     * @param p The player to target (their UUID is what we need, not a Player instance (pst: you can use a player instance as an argument, but it'll go to a different method if you do))     */    public static void clearStaffchat(UUID p) {        if(staff.contains(p)) staff.remove(p); //remove them from the channel "staff"        if(gm.contains(p)) gm.remove(p); //remove them from the channel "admin"        if(alert.contains(p)) alert.remove(p); //remove them from the channel "alert"        if(mod.contains(p)) mod.remove(p); //remove them from the channel "mod"        if(helper.contains(p)) helper.remove(p); //remove them from the channel "helper"        if(notify.contains(p)) notify.remove(p); //remove them from the channel "notify"        if(vip.contains(p)) vip.remove(p); //remove them from the channel "vip"    }    public static void clearStaffChat(Player p) {        clearStaffchat(p.getUniqueId());    }}