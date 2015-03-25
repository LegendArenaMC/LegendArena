package net.thenamedev.legendarena.extras.staffchat;import net.thenamedev.legendapi.LegendAPI;import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;import net.thenamedev.legendapi.utils.ChatUtils;import net.thenamedev.legendapi.utils.PluginUtils;import net.thenamedev.legendapi.utils.Rank;import org.bukkit.Bukkit;import org.bukkit.ChatColor;import org.bukkit.entity.Player;import org.bukkit.event.EventHandler;import org.bukkit.event.EventPriority;import org.bukkit.event.Listener;import org.bukkit.event.player.AsyncPlayerChatEvent;/** * @author ThePixelDev */public class StaffChat implements Listener {    @EventHandler(priority = EventPriority.LOWEST)    public void playerChat(AsyncPlayerChatEvent ev) {        if(SCType.getType(ev.getPlayer().getUniqueId()) != SCType.PUBLIC) {            try {                staffChat(SCType.getType(ev.getPlayer().getUniqueId()), ev.getPlayer(), ChatColor.translateAlternateColorCodes('&', ev.getMessage()));            } catch(NullPointerException ex) {                for(Player p : Bukkit.getOnlinePlayers()) {                    if(!Rank.isRanked(p, Rank.HELPER)) continue;                    p.sendMessage(LegendAPI.debugCat + "The player " + ev.getPlayer().getName() + " has their chat set to an invalid channel, causing the staffChat() method to throw a NullPointerException. Please report this to Pixel!");                    if(LegendAPI.debug)                        p.sendMessage(PluginUtils.msgDebug + "Debug info:\n" + "Channel: " + SCType.getType(ev.getPlayer().getUniqueId()));                }                SCType.clearStaffchat(ev.getPlayer().getUniqueId()); //of course - this isn't failproof, but hopefully it will work            } finally {                ev.setCancelled(true); //finally set the event to be cancelled regardless of if the type was found in the staff chat method or not            }        }    }    public static void staffChat(SCType type, Player p, String msg) {        if(type == SCType.ALERT) {            if(!Rank.isRanked(p, Rank.HELPER)) {                p.sendMessage(ChatColor.DARK_RED + "Why are you in the alert channel?");                SCType.clearStaffchat(p.getUniqueId());                return;            }            alert(msg, p); //Redirect to Alert method        } else if(type == SCType.NOTIFICATION) {            if(!Rank.isRanked(p, Rank.FOUNDER)) {                p.sendMessage(ChatColor.DARK_RED + "Why are you in the notification channel?");                SCType.clearStaffchat(p.getUniqueId());                return;            }            notice(msg, "Staff Notice", false); //Redirect to Notify method        } else if(type == SCType.GM) {            for(Player pl : Bukkit.getOnlinePlayers()) {                if(!Rank.isRanked(p, Rank.ADMIN) || SCType.noStaffchat.contains(pl.getUniqueId()))                    continue; //Continue along the players if the user is not an admin or chose to not show staff chat                pl.sendMessage(getChannelMsg(msg, p, "Admin", true));            }        } else if(type == SCType.MOD) {            for(Player pl : Bukkit.getOnlinePlayers()) {                if(!Rank.isRanked(pl, Rank.MOD) || SCType.noStaffchat.contains(pl.getUniqueId()))                    continue; //Continue along the players if the user is not a moderator or chose to not show staff chat                pl.sendMessage(getChannelMsg(msg, p, "Mod", true));            }        } else if(type == SCType.STAFF) {            for(Player pl : Bukkit.getOnlinePlayers()) {                if(!Rank.isRanked(pl, Rank.HELPER) || SCType.noStaffchat.contains(pl.getUniqueId()))                    continue; //Continue along the players if the user is not a moderator or chose to not show staff chat                pl.sendMessage(getChannelMsg(msg, p, "Staff", true));            }        } else if(type == SCType.HELPER) {            for(Player pl : Bukkit.getOnlinePlayers()) {                if(!Rank.isRanked(pl, Rank.HELPER) || SCType.noStaffchat.contains(pl.getUniqueId()))                    continue; //Continue along the players if the user is not a moderator or chose to not show staff chat                pl.sendMessage(getChannelMsg(msg, p, "Helper", true));            }        } else if(type == SCType.VIP) {            for(Player pl : Bukkit.getOnlinePlayers()) {                if(!Rank.isRanked(pl, Rank.MOD) || SCType.noStaffchat.contains(pl.getUniqueId()))                    continue; //Continue along the players if the user is not a VIP or chose to not show staff chat                pl.sendMessage(getChannelMsg(msg, p, "VIP", true));            }        } else if(type == SCType.PUBLIC) {            for(Player pl : Bukkit.getOnlinePlayers()) {                pl.sendMessage(ChatUtils.getFormattedChat(msg, p));                Bukkit.getLogger().info("Chat >> " + ChatUtils.getFormattedChat(msg, p));            }        } else {            throw new NullPointerException();        }    }    private static String getChannelMsg(String msg, Player p, String name, boolean appendChannel) {        return String.format("%s(%s%s%s) %s %s%s%s %s", ChatColor.BLUE, ChatColor.GREEN, name + (appendChannel ? " Channel" : ""), ChatColor.BLUE, Rank.getFormattedName(p), ChatColor.GRAY, PluginUtils.chars[1], ChatColor.YELLOW, msg);    }    private static String getChannelMsg(String msg, String p, String name, boolean appendChannel) {        return String.format("%s(%s%s%s) %s %s%s%s %s", ChatColor.BLUE, ChatColor.GREEN, name + (appendChannel ? " Channel" : ""), ChatColor.BLUE, p, ChatColor.GRAY, PluginUtils.chars[1], ChatColor.YELLOW, msg);    }    private static String getChannelMsg(String msg, Player p, String name, boolean appendChannel, ChatColor channelNameColor) {        return String.format("%s(%s)%s %s %s%s%s %s", channelNameColor, name + (appendChannel ? " Channel" : ""), ChatColor.BLUE, Rank.getFormattedName(p), ChatColor.GRAY, PluginUtils.chars[1], ChatColor.YELLOW, msg);    }    private static String getChannelMsg(String msg, String p, String name, boolean appendChannel, ChatColor channelNameColor) {        return String.format("%s(%s)%s %s %s%s%s %s", channelNameColor, name + (appendChannel ? " Channel" : ""), ChatColor.BLUE, p, ChatColor.GRAY, PluginUtils.chars[1], ChatColor.YELLOW, msg);    }    private static void alert(String msg, Player p) {        if(ChatColor.stripColor(msg).equals("")) return;        for(Player pl : Bukkit.getOnlinePlayers()) {            pl.sendMessage(getChannelMsg(msg, p, "Alert", false, ChatColor.RED));        }    }    public static void notice(String msg, String notifyType, boolean global) {        if(msg.toCharArray().length == 0 || notifyType.toCharArray().length == 0) throw new MistakesWereMadeException("Notify type or message cannot be blank.");        for(Player pl : Bukkit.getOnlinePlayers()) {            if(!Rank.isRanked(pl, Rank.HELPER) && !global) continue;            pl.sendMessage(getChannelMsg(ChatColor.LIGHT_PURPLE + msg, notifyType, "Notification", false, ChatColor.DARK_GREEN));        }    }    public static void notice(String msg, String notifyType) {        notice(msg, notifyType, false);    }}