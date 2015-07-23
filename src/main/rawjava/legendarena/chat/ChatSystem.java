package legendarena.chat;

import legendapi.log.BukLog;
import legendapi.log.Level;
import legendapi.message.Message;
import legendapi.utils.*;
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
    private static boolean mute = false;
    private static HashMap<UUID, Boolean> muteStatus = new HashMap<>();

    private static boolean allowShadowMute = true;

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
    public static void setGlobalMute(boolean set) {
        mute = set;
        new Message().append(ChatUtils.getCustomMsg("Chat") + "Chat has been " + (set ? "globally muted" : "un-globally muted") + "!");
    }

    /**
     * Shadow-mute a player. Still accepts messages but only shows it to staff and the player in question.
     * @param p The player to shadow-mute
     */
    public static void toggleShadowMute(Player p, String muter) {
        if(!isShadowMuteAllowed()) throw new ShadowMuteNotAllowedException();

        if(isMuted(p)) return;

        if(isShadowMuted(p)) {
            muteStatus.remove(p.getUniqueId());
            notice("Player " + ChatColor.YELLOW + p.getName() + ChatColor.DARK_PURPLE + " has been un-shadow muted by " + ChatColor.YELLOW + muter + ChatColor.DARK_PURPLE + ".");
            return;
        }

        muteStatus.put(p.getUniqueId(), true);
        notice("Player " + ChatColor.YELLOW + p.getName() + ChatColor.DARK_PURPLE + " has been shadow muted by " + ChatColor.YELLOW + muter + ChatColor.DARK_PURPLE + ".");
    }

    public static void toggleMute(Player p, String muter) {
        if(isShadowMuted(p)) return;

        if(isMuted(p)) {
            muteStatus.remove(p.getUniqueId());
            notice("Player " + ChatColor.YELLOW + p.getName() + ChatColor.DARK_PURPLE + " has been un-muted by " + ChatColor.YELLOW + muter + ChatColor.DARK_PURPLE + ".");
            return;
        }

        muteStatus.put(p.getUniqueId(), false);
        notice("Player " + ChatColor.YELLOW + p.getName() + ChatColor.DARK_PURPLE + " has been muted by " + ChatColor.YELLOW + muter + ChatColor.DARK_PURPLE + ".");
    }

    /**
     * Is the player shadow muted?
     * @return If shadow muted, returns true - elsewise false
     */
    public static boolean isShadowMuted(Player p) {
        return isShadowMuteAllowed() && muteStatus.containsKey(p.getUniqueId()) && muteStatus.get(p.getUniqueId());
    }

    /**
     * Is a player muted?
     * @return If muted, returns true - elsewise false
     */
    public static boolean isMuted(Player p) {
        return muteStatus.containsKey(p.getUniqueId()) && !muteStatus.get(p.getUniqueId());
    }

    public static void allowShadowMute(boolean s) {
        allowShadowMute = s;
        notice("Shadow mutes have been " + (s ? "re-allowed" : "disallowed") + "!");
    }

    public static boolean isShadowMuteAllowed() {
        return allowShadowMute;
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
            if(!Rank.MOD.isRanked(p))
                continue;
            p.sendMessage(ChatUtils.getCustomMsg("Notice") + ChatColor.DARK_PURPLE + msg);
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
        //Obligatory "this is MUCH nicer" comment after function re-write

        String msg1 = _msg(msg, p);
        if(msg1 == null)
            return;

        Message built = new Message().append(msg1);

        if(isShadowMuted(p)) {
            Message shadowBuilt = new Message().append(ChatColor.RED + "[SHADOWMUTED]" + ChatColor.WHITE + built.toString());
            shadowBuilt.broadcast(Rank.MOD);
            built.send(p);

            new BukLog(Bukkit.getPluginManager().getPlugin("LegendArena")).log(Level.INFO, ChatColor.stripColor(shadowBuilt.toString()));

            return;
        }

        built.broadcast(getChannel(p).getRank());
        new BukLog(Bukkit.getPluginManager().getPlugin("LegendArena")).log(Level.INFO, ChatColor.stripColor(built.toString()));
    }

    private static String _msg(String m, Player p) {
        switch(getChannel(p)) {
            case ADMIN:
                return Channel.ADMIN.getFormat().replace("{USERDISPLAY}", new ChatSystemUtils().getFormattedName(p)).replace("{MESSAGE}", m);
            case ALERT:
                return Channel.ALERT.getFormat().replace("{USERDISPLAY}", new ChatSystemUtils().getFormattedName(p)).replace("{MESSAGE}", m);
            case STAFF:
                return Channel.STAFF.getFormat().replace("{USERDISPLAY}", new ChatSystemUtils().getFormattedName(p)).replace("{MESSAGE}", m);
            case GLOBAL:
                return new ChatSystemUtils().getChatMessage(m, p);

            default:
                //bitch like hell, because this really should NOT be triggered in any case, no matter how logical it is
                notice("Error: Player " + ChatColor.YELLOW + p.getName() + ChatColor.RED + " is trying to use an invalid channel (" + ChatColor.YELLOW + getChannel(p) + ChatColor.RED + ") - THIS SHOULD NOT HAPPEN!");
                return null;
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

}
