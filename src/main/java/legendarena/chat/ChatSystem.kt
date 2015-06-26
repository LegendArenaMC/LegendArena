package legendarena.chat

import legendapi.exceptions.PlayerNotOnlineException
import legendapi.message.Message
import legendapi.regex.RegexUtils
import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.*

class ChatSystem {

    private val channels = HashMap<UUID, legendarena.chat.Channel>()
    private val mutedPlayers = ArrayList<UUID>()
    private var mute = false

    /**
     * Is the chat currently globally muted?
     * @return The global mute status
     */
    public fun isChatMuted(): Boolean {
        return mute
    }

    /**
     * Set the global mute status
     * @param set Should global mute be on or off?
     */
    public fun setChatMuted(set: Boolean) {
        mute = set
    }

    /**
     * Get a parsed chat message.
     * @param m The message
     * *
     * @param p The player
     * *
     * @return The parsed message
     */
    private fun getParsedChatMessage(m: String, p: Player): String {
        var msg = m
        if(Troll.sheepleTroll.contains(p.getUniqueId())) {
            if(RegexUtils.endsWith(m, "[?!.]"))
                msg += " Wake up, sheeple!"
            else
                msg += "! Wake up, sheeple!"
        }

        if (Rank.isRanked(p, Rank.YOUTUBE))
            return ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', msg.replace("[tm]", "™"))
        else
            return ChatColor.GRAY + msg
    }

    /**
     * Get a formatted name
     * @param p The player
     * @return The formatted name
     */
    public fun getFormattedName(p: Player): String {
        return Rank.getRankPrefix(Rank.getRank(p)) + ChatColor.RESET + (if (Rank.getRank(p) !== Rank.MEMBER) " " else "") + p.getName()
    }

    /**
     * Get a chat message
     * @param msg The message
     * @param p The player
     * @return The chat message
     */
    public fun getChatMessage(msg: String, p: Player): String {
        return getFormattedName(p) + " " + ChatColor.GRAY + ChatUtils.chars[1] + " " + getParsedChatMessage(msg, p)
    }

    /**
     * Gets the channel of a certain player.
     * @param p The player to get the channel of
     * @return The player's channel
     */
    public fun getChannel(p: Player): legendarena.chat.Channel? {
        if (!channels.containsKey(p.getUniqueId()))
            return legendarena.chat.Channel.GLOBAL
        return channels.get(p.getUniqueId())
    }

    /**
     * Adds a player to a certain channel.
     * @param p The player to target
     * @param channel The channel to add the player to
     * @throws NotEnoughPermissionsException If the player is not ranked to the required rank
     * @throws PlayerNotOnlineException If the player is null/is not online
     */
    public fun add(p: Player?, channel: legendarena.chat.Channel) {
        if (p == null || !p.isOnline())
            throw PlayerNotOnlineException()
        val pUUID = p.getUniqueId()
        if (channels.containsKey(pUUID))
            channels.remove(pUUID)
        channels.put(pUUID, channel)
    }

    /**
     * Removes a player from all special channels.
     * @param p The player to target
     */
    public fun remove(p: Player) {
        if (!channels.containsKey(p.getUniqueId()))
            return
        channels.remove(p.getUniqueId())
    }

    /**
     * Sends a staff notice to all online staff.
     * @param msg The notice to send
     */
    public fun notice(msg: String) {
        for (p in Bukkit.getOnlinePlayers()) {
            if (!Rank.isRanked(p, Rank.MOD))
                continue
            p.sendMessage(ChatUtils.getCustomMsg("Notice") + ChatColor.RED + msg)
        }
    }

    /**
     * Sends a chat message as a player.

     * Pro-tip: To send a message in a certain channel temporarily, set the player's channel to the needed channel, send the message, and set the channel back to what it was.
     * @param p The player to send the message as
     * *
     * @param msg The message to send
     */
    public fun msg(p: Player, msg: String) {
        when(getChannel(p)) {
            Channel.ADMIN -> for (p1 in Bukkit.getOnlinePlayers()) {
                if(!Rank.isRanked(p1, Rank.ADMIN))
                    continue
                p1.sendMessage(ChatUtils.getCustomMsg("Admin Chat") + p.getName() + ChatColor.GRAY + ": " + ChatColor.RED + ChatColor.translateAlternateColorCodes('&', msg))
            }
            Channel.ALERT -> for(p1 in Bukkit.getOnlinePlayers())
                p1.sendMessage(ChatUtils.getCustomMsg("Alert") + ChatColor.RED + ChatColor.translateAlternateColorCodes('&', msg))
            Channel.STAFF -> for(p1 in Bukkit.getOnlinePlayers()) {
                if(!Rank.isRanked(p1, Rank.HELPER))
                    continue
                p1.sendMessage(ChatUtils.getCustomMsg("Staff Chat") + p.getName() + ChatColor.GRAY + ": " + ChatColor.GREEN + ChatColor.translateAlternateColorCodes('&', msg))
            }

            Channel.GLOBAL -> {
                if(isChatMuted())
                    if(!Rank.isRanked(p, Rank.YOUTUBE)) {
                        p.sendMessage(ChatUtils.Messages.errorMsg + "Chat is currently muted!")
                        return
                    }
                Message().append(getChatMessage(msg, p)).broadcast()
            }
        }
    }

    public fun msg(p: Player, msg: String, c: legendarena.chat.Channel) {
        val tmp = getChannel(p)
        add(p, c)
        msg(p, msg)
        if (tmp == null || tmp === legendarena.chat.Channel.GLOBAL)
            remove(p)
        else
            add(p, tmp)
    }

    /**
     * Chat listener. Sends all chat messages to the chat system.

     * @author ThePixelDev
     */
    public class ChatListener : Listener {

        EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
        public fun onChat(ev: AsyncPlayerChatEvent) {
            if (RegexUtils.contains(ev.getMessage(), "[Tt]hank [Mm]r [Ss]ketal")) {
                ev.getPlayer().sendMessage(ChatColor.BLUE + "You're actually going to thank an internet meme here? You must need some friends.")
                ev.setCancelled(true)
                return
            }
            msg(ev.getPlayer(), ev.getMessage())
            ev.setCancelled(true) //fuck it, what's the worst that could happen[tm]
        }

    }

}
