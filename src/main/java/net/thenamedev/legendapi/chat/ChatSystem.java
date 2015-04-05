package net.thenamedev.legendapi.chat;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.LegendArena;
import net.thenamedev.legendarena.commands.backends.GlobalMute;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created on 4/4/2015
 *
 * @author ThePixelDev
 */
public class ChatSystem {

    private static HashMap<UUID, Channels> channels = new HashMap<>();

    public static void setChannel(UUID p, Channels c) {
        channels.put(p, c);
    }

    public static Channels getChannel(UUID p) {
        return channels.get(p);
    }

    public static boolean isInGlobal(UUID p) {
        return !channels.containsKey(p);
    }

    public static void proccessChat(Player p, String msg) {
        Rank pR = Rank.getRank(p);
        if(isInGlobal(p.getUniqueId())) {
            if(LegendArena.isChatMuted() && !Rank.isRanked(p, Rank.HELPER)) {
                p.sendMessage(ChatColor.RED + "Chat is globally muted!");
                return;
            }
            String chat = ChatUtils.getFormattedChat(msg, p);
            for(Player p1 : Bukkit.getOnlinePlayers()) {
                p1.sendMessage(chat);
            }
            return;
        }
    }

}
