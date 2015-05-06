package net.thenamedev.legendapi.chat;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.LegendArena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created on 4/17/15
 *
 * @author ThePixelDev
 */
public class ChatSystem {

    private static HashMap<UUID, Channels> channels = new HashMap<>();

    public enum Channels {
        ADMIN(Rank.ADMIN, ChatColor.RED + "ADMIN" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "{USERDISPLAY} " + ChatColor.DARK_RED + PluginUtils.chars[1] + ChatColor.RED + " {MESSAGE}"),
        ALERT(Rank.HELPER, ChatColor.RED + "ALERT" + ChatColor.YELLOW + "({USERDISPLAY}" + ChatColor.YELLOW + ") " + ChatColor.GOLD + "{MESSAGE}"),
        STAFF(Rank.HELPER, ChatColor.RED + "STAFF" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "{USERDISPLAY} " + ChatColor.DARK_RED + PluginUtils.chars[1] + ChatColor.DARK_GREEN + " {MESSAGE}");

        private Rank rank;
        private String format;

        Channels(Rank rank, String format) {
            this.rank = rank;
            this.format = format;
        }

        public Rank getRank() {
            return rank;
        }

        public String getFormat() {
            return format;
        }
    }

    public static Channels getChannel(Player p) {
        if(!channels.containsKey(p.getUniqueId()))
            return null;
        return channels.get(p.getUniqueId());
    }

    public static void add(Player p, Channels channel) {
        UUID pUUID = p.getUniqueId();
        if(channels.containsKey(pUUID))
            channels.remove(pUUID);
        channels.put(pUUID, channel);
    }

    public static void remove(Player p) {
        if(!channels.containsKey(p.getUniqueId()))
            return;
        channels.remove(p.getUniqueId());
    }

    public static void notice(String msg) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!Rank.isRanked(p, Rank.MOD))
                continue;
            p.sendMessage(ChatColor.GOLD + "NOTICE" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + msg);
        }
    }

    public static void msg(Player p, String msg) {
        if(getChannel(p) == null) {
            if(LegendArena.isChatMuted())
                if(!Rank.isRanked(p, Rank.HELPER)) {
                    p.sendMessage(PluginUtils.msgError + "Chat is currently muted!");
                    return;
                }
            ChatUtils.broadcast(ChatUtils.getFormattedChat(msg, p));
            return;
        }

        //noinspection ConstantConditions
        switch(getChannel(p)) {
            case ADMIN:
                for(Player p1 : Bukkit.getOnlinePlayers()) {
                    if(!Rank.isRanked(p1, Rank.ADMIN))
                        continue;
                    p1.sendMessage(Channels.ADMIN.getFormat().replace("{USERDISPLAY}", Rank.getFormattedName(p)).replace("{MESSAGE}", msg));
                }
                break;
            case ALERT:
                for(Player p1 : Bukkit.getOnlinePlayers())
                    p1.sendMessage(Channels.ALERT.getFormat().replace("{USERDISPLAY}", Rank.getFormattedName(p)).replace("{MESSAGE}", msg));
                break;
            case STAFF:
                for(Player p1 : Bukkit.getOnlinePlayers()) {
                    if(!Rank.isRanked(p1, Rank.HELPER))
                        continue;
                    p1.sendMessage(Channels.STAFF.getFormat().replace("{USERDISPLAY}", Rank.getFormattedName(p)).replace("{MESSAGE}", msg));
                }
                break;
        }
    }

}
