package net.thenamedev.legendapi.chat;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
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
        ADMIN(Rank.ADMIN, ChatColor.RED + "ADMIN" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "{USERDISPLAY} " + ChatColor.DARK_RED + PluginUtils.chars[1] + ChatColor.BLUE + " {MESSAGE}"),
        ALERT(Rank.HELPER, ChatColor.RED + "ALERT" + ChatColor.YELLOW + "({USERDISPLAY}" + ChatColor.YELLOW + ") " + ChatColor.GREEN + "{MESSAGE}"),
        STAFF(Rank.HELPER, ChatColor.RED + "STAFF" + ChatColor.DARK_GRAY + " | " + ChatColor.RED + "{USERDISPLAY} " + ChatColor.DARK_RED + PluginUtils.chars[1] + ChatColor.BLUE + " {MESSAGE}");

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

    public static void msg(Player p, String msg) {
        Rank pR = Rank.getRank(p);

        if(getChannel(p) == null) {
            ChatUtils.broadcast(ChatUtils.getFormattedChat(msg, p));
            return;
        }

        //noinspection ConstantConditions
        switch(getChannel(p)) {
            case ADMIN:
                break;
            case ALERT:
                break;
            case STAFF:
                break;
        }
    }

}
