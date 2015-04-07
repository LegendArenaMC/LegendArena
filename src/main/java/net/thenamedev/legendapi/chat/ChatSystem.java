package net.thenamedev.legendapi.chat;

import net.thenamedev.legendapi.exceptions.NotEnoughPermissionsException;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.LegendArena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created on 4/4/2015
 *
 * @author ThePixelDev
 */
public class ChatSystem {

    private static HashMap<UUID, Channels> channels = new HashMap<>();
    private static List<UUID> ignoreStaffChat = new ArrayList<>();
    private static boolean isSqlSetup = false;

    public static void setChannel(UUID p, Channels c) {
        Player verify = Bukkit.getPlayer(p);
        if(!Rank.isRanked(verify, c.getRankRequired()))
            throw new NotEnoughPermissionsException();
        channels.put(p, c);
    }

    public static Channels getChannel(UUID p) {
        return channels.get(p);
    }

    public static boolean isInGlobal(UUID p) {
        return !channels.containsKey(p);
    }

    public static boolean ignoresStaffChat(UUID p) {
        return ignoreStaffChat.contains(p);
    }

    public static void toggleIgnore(UUID p) {
        if(ignoresStaffChat(p))
            ignoreStaffChat.add(p);
        else
            ignoreStaffChat.remove(p);
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
        } else
            new ChatMessage(getChannel(p.getUniqueId()), msg, p).run();
    }

}
