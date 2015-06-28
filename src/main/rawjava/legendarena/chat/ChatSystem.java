package legendarena.chat;

import legendapi.message.Message;
import legendapi.utils.RegexUtils;
import legendapi.utils.ChatUtils;
import legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
     * Get a parsed chat message.
     * @param msg The message
     * @param p The player
     * @return The parsed message
     */
    private static String getParsedChatMessage(String msg, Player p) {
        if(Rank.isRanked(p, Rank.YOUTUBE))
            return ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', msg.replace("[tm]", "™").replace("#LoveWins", "#LoveWins " + ChatUtils.chars[9]));
        else
            return ChatColor.GRAY + msg;
    }

    /**
     * Get a formatted name
     * @param p The player
     * @return The formatted name
     */
    public static String getFormattedName(Player p) {
        return Rank.getRankPrefix(Rank.getRank(p)) + ChatColor.RESET + (Rank.getRank(p) != Rank.MEMBER ? " " : "") + p.getName();
    }

    /**
     * Get a chat message
     * @param msg The message
     * @param p The player
     * @return The chat message
     */
    public static String getChatMessage(String msg, Player p) {
        return getFormattedName(p) + " " + ChatColor.GRAY + ChatUtils.chars[1] + " " + getParsedChatMessage(msg, p);
    }

    /**
     * Channel list.
     */
    public enum Channel {
        ADMIN(Rank.ADMIN, ChatColor.RED + "ADMIN" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "{USERDISPLAY} " + ChatColor.DARK_RED + ChatUtils.chars[1] + ChatColor.RED + " {MESSAGE}"),
        ALERT(Rank.HELPER, ChatColor.RED + "ALERT" + ChatColor.YELLOW + "({USERDISPLAY}" + ChatColor.YELLOW + ") " + ChatColor.GOLD + "{MESSAGE}"),
        STAFF(Rank.HELPER, ChatColor.RED + "STAFF" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "{USERDISPLAY} " + ChatColor.DARK_RED + ChatUtils.chars[1] + ChatColor.DARK_GREEN + " {MESSAGE}"),
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
     */
    public static void add(Player p, Channel channel) {
        if(p == null || !p.isOnline())
            throw new NullPointerException();
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
            p.sendMessage(ChatUtils.getCustomMsg("Notice") + ChatColor.RED + msg);
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
                    p1.sendMessage(Channel.ADMIN.getFormat().replace("{USERDISPLAY}", getFormattedName(p)).replace("{MESSAGE}", msg));
                }
                break;
            case ALERT:
                for(Player p1 : Bukkit.getOnlinePlayers())
                    p1.sendMessage(Channel.ALERT.getFormat().replace("{USERDISPLAY}", getFormattedName(p)).replace("{MESSAGE}", msg));
                break;
            case STAFF:
                for(Player p1 : Bukkit.getOnlinePlayers()) {
                    if(!Rank.isRanked(p1, Rank.HELPER))
                        continue;
                    p1.sendMessage(Channel.STAFF.getFormat().replace("{USERDISPLAY}", getFormattedName(p)).replace("{MESSAGE}", msg));
                }
                break;

            case GLOBAL:
                if(isChatMuted())
                    if(!Rank.isRanked(p, Rank.YOUTUBE)) {
                        p.sendMessage(ChatUtils.Messages.errorMsg + "Chat is currently muted!");
                        return;
                    }
                new Message().append(getChatMessage(msg, p)).broadcast();
                break;
        }
    }

    public static void msg(Player p, String msg, Channel c) {
        Channel tmp = getChannel(p);
        add(p, c);
        msg(p, msg);
        if(tmp == null || tmp == Channel.GLOBAL)
            remove(p);
        else
            add(p, tmp);
    }

    /**
     * Chat listener. Sends all chat messages to the chat system.
     *
     * @author ThePixelDev
     */
    public static class ChatListener implements Listener {

        @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
        public void onChat(AsyncPlayerChatEvent ev) {
            if(RegexUtils.contains(ev.getMessage(), "[Tt]hank [Mm]r [Ss]ketal")) {
                ev.getPlayer().sendMessage(ChatColor.BLUE + "You're actually going to thank an internet meme here? You must need some friends.");
                ev.setCancelled(true);
                return;
            }
            msg(ev.getPlayer(), ev.getMessage());
            ev.setCancelled(true); //fuck it, what's the worst that could happen[tm]
        }

    }
}
