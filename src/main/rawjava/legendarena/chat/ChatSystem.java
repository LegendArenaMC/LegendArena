package legendarena.chat;

import legendapi.message.Message;
import legendapi.utils.ChatUtils;
import legendapi.utils.Rank;
import legendapi.utils.RankUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class ChatSystem {

    private static ArrayList<Channel> channels = new ArrayList<>();
    private static boolean globalMute = false;
    private static ArrayList<UUID> shadowMuted = new ArrayList<>();

    public static void setGlobalMute(boolean globalMute) {
        ChatSystem.globalMute = globalMute;

        if(globalMute) {
            new Message().append(" ").broadcast();
            new Message().append(ChatUtils.getCustomMsg("Chat Management") + "Shh... chat has been globally muted...").broadcast();
            new Message().append(" ").broadcast();
        } else {
            new Message().append(" ").broadcast();
            new Message().append(ChatUtils.getCustomMsg("Chat Management") + "Chat has been un-globally muted. You may now speak.").broadcast();
            new Message().append(" ").broadcast();
        }
    }

    public static boolean isChatGloballyMuted() {
        return globalMute;
    }

    public static void shadowMute(Player p) {
        if(isShadowMuted(p)) {
            shadowMuted.remove(p.getUniqueId());
            return;
        }
        shadowMuted.add(p.getUniqueId());
    }

    public static boolean isShadowMuted(Player p) {
        return shadowMuted.contains(p.getUniqueId());
    }

    public static void msg(Player p, String msg) {
        //
    }

    private static String getParsedChatMessage(String msg, Player p) {
        if(Rank.YOUTUBE.isRanked(p))
            return ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', msg.replace("[tm]", "™"));
        else
            return ChatColor.GRAY + msg;
    }

    public static String getFormattedName(Player p) {
        return RankUtils.getRankPrefix(RankUtils.getRank(p)) + ChatColor.RESET + (RankUtils.getRank(p) != Rank.MEMBER ? " " : "") + p.getName();
    }

    public static String getChatMessage(String msg, Player p) {
        return getFormattedName(p) + " " + ChatColor.GRAY + ChatUtils.chars[1] + " " + getParsedChatMessage(msg, p);
    }

}
