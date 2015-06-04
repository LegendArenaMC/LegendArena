package net.thenamedev.legendapi.core.chat;

import net.thenamedev.legendapi.core.exceptions.NotEnoughPermissionsException;
import net.thenamedev.legendapi.core.exceptions.PlayerNotOnlineException;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created on 4/17/15
 *
 * @author ThePixelDev
 */
public class ChatSystem {

    private static HashMap<UUID, Channel> channels = new HashMap<>();
    private static ArrayList<UUID> mutedPlayers = new ArrayList<>();
    private static boolean mute = false;

    /**
     * Is the chat currently globally muted?
     * @return The global mute status
     */
    public static boolean isChatMuted() {
        return mute;
    }

    /**
     * Set the global mute status
     * @param set Should global mute be on or off?
     */
    public static void setChatMuted(boolean set) {
        mute = set;
    }

    /**
     * Channel list.
     */
    public enum Channel {
        ADMIN(Rank.ADMIN, ChatColor.RED + "ADMIN" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "{USERDISPLAY} " + ChatColor.DARK_RED + PluginUtils.chars[1] + ChatColor.RED + " {MESSAGE}"),
        ALERT(Rank.HELPER, ChatColor.RED + "ALERT" + ChatColor.YELLOW + "({USERDISPLAY}" + ChatColor.YELLOW + ") " + ChatColor.GOLD + "{MESSAGE}"),
        STAFF(Rank.HELPER, ChatColor.RED + "STAFF" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "{USERDISPLAY} " + ChatColor.DARK_RED + PluginUtils.chars[1] + ChatColor.DARK_GREEN + " {MESSAGE}"),
        GLOBAL;

        private Rank rank;
        private String format;

        Channel(Rank rank, String format) {
            this.rank = rank;
            this.format = format;
        }

        Channel() {
            rank = Rank.MEMBER;
        }

        public Rank getRank() {
            return rank;
        }

        public String getFormat() {
            return format;
        }
    }

    /**
     * Gets the channel of a certain player.
     * @param p The player to get the channel of
     * @return The player's channel
     */
    public static Channel getChannel(Player p) {
        if(!channels.containsKey(p.getUniqueId()))
            return Channel.GLOBAL;
        return channels.get(p.getUniqueId());
    }

    /**
     * Adds a player to a certain channel.
     * @param p The player to target
     * @param channel The channel to add the player to
     * @throws NotEnoughPermissionsException If the player is not ranked to the required rank
     * @throws PlayerNotOnlineException If the player is null/is not online
     */
    public static void add(Player p, Channel channel) {
        if(p == null || !p.isOnline())
            throw new PlayerNotOnlineException();
        if(Rank.isRanked(p, channel.getRank()))
            throw new NotEnoughPermissionsException();
        UUID pUUID = p.getUniqueId();
        if(channels.containsKey(pUUID))
            channels.remove(pUUID);
        channels.put(pUUID, channel);
    }

    /**
     * Removes a player from all special channels.
     * @param p The player to target
     */
    public static void remove(Player p) {
        if(!channels.containsKey(p.getUniqueId()))
            return;
        channels.remove(p.getUniqueId());
    }

    /**
     * Sends a staff notice to all online staff.
     * @param msg The notice to send
     */
    public static void notice(String msg) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!Rank.isRanked(p, Rank.MOD))
                continue;
            p.sendMessage(ChatUtils.Messages.getCustomMsg("Notice") + ChatColor.RED + msg);
        }
    }

    /**
     * Sends a chat message as a player.<br><br>
     *
     * Pro-tip: To send a message in a certain channel temporarily, set the player's channel to the needed channel, send the message, and set the channel back to what it was.
     * @param p The player to send the message as
     * @param msg The message to send
     */
    public static void msg(Player p, String msg) {
        switch(getChannel(p)) {
            case ADMIN:
                for(Player p1 : Bukkit.getOnlinePlayers()) {
                    if(!Rank.isRanked(p1, Rank.ADMIN))
                        continue;
                    p1.sendMessage(Channel.ADMIN.getFormat().replace("{USERDISPLAY}", ChatUtils.getFormattedName(p)).replace("{MESSAGE}", msg));
                }
                break;
            case ALERT:
                for(Player p1 : Bukkit.getOnlinePlayers())
                    p1.sendMessage(Channel.ALERT.getFormat().replace("{USERDISPLAY}", ChatUtils.getFormattedName(p)).replace("{MESSAGE}", msg));
                break;
            case STAFF:
                for(Player p1 : Bukkit.getOnlinePlayers()) {
                    if(!Rank.isRanked(p1, Rank.HELPER))
                        continue;
                    p1.sendMessage(Channel.STAFF.getFormat().replace("{USERDISPLAY}", ChatUtils.getFormattedName(p)).replace("{MESSAGE}", msg));
                }
                break;

            case GLOBAL:
                if(isChatMuted())
                    if(!Rank.isRanked(p, Rank.YOUTUBE)) {
                        p.sendMessage(ChatUtils.Messages.errorMsg + "Chat is currently muted!");
                        return;
                    }
                ChatUtils.broadcast(ChatUtils.getFormattedChat(msg, p));
                break;
        }
    }

}
