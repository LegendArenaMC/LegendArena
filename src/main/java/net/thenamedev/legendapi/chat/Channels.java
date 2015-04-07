package net.thenamedev.legendapi.chat;

import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.ChatColor;

/**
 * Created on 4/3/2015
 *
 * @author ThePixelDev
 */
public enum Channels {

    GENERAL(Rank.MEMBER, ""),
    DEVALERTS(Rank.FOUNDER, ""),
    ADMIN(Rank.ADMIN, ""),
    STAFF(Rank.HELPER, ""),
    ANNOUNCE(Rank.HELPER, ChatColor.RED + "ALERT " + ChatColor.DARK_GRAY + PluginUtils.chars[1] + ChatColor.BLUE + "%s" + ChatColor.DARK_GRAY + " | " + ChatColor.YELLOW + "%s");

    private Rank permission;
    private String msgFormat;

    Channels(Rank permission, String msgFormat) {
        this.msgFormat = msgFormat;
        this.permission = permission;
    }

    public Rank getRankRequired() {
        return permission;
    }

    public String getMsgFormat() {
        return msgFormat;
    }

}
