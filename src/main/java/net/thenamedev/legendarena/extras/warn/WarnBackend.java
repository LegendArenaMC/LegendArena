package net.thenamedev.legendarena.extras.warn;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @author ThePixelDev
 */
public class WarnBackend {

    private Player warnPlayer;
    private Player staff;
    private String reason;

    public void run() {
        ChatUtils.broadcast(
                String.format("%sLegend Arena %s%s%s Staff member %s%s%s warned player %s%s%s for reason: %s%s", ChatColor.RED, ChatColor.GRAY, PluginUtils.chars[1], ChatColor.BLUE, ChatColor.RED, Rank.getFormattedName(staff), ChatColor.BLUE, ChatColor.YELLOW, warnPlayer.getName(), ChatColor.BLUE, ChatColor.LIGHT_PURPLE, reason),
                Rank.HELPER
        );

        warnPlayer.sendMessage("");
        warnPlayer.sendMessage("");
        warnPlayer.sendMessage(
                String.format("%s-=+ {Warned} +=-\n%sWarned by staff member: %s%s\n%sReason: %s%s\n%s-=+ [Warned] +=-", ChatColor.RED, ChatColor.LIGHT_PURPLE, ChatColor.RED, staff.getName(), ChatColor.LIGHT_PURPLE, ChatColor.RED, reason, ChatColor.RED)
        );
        warnPlayer.sendMessage("");
        warnPlayer.sendMessage("");
        warnPlayer.playSound(warnPlayer.getLocation(), Sound.ANVIL_LAND, 2, 1);
    }

    public void setup(Player warned, Player staffMember, String reason) {
        if(warned == null || staffMember == null || reason == null) throw new NullPointerException();
        this.reason = reason;
        warnPlayer = warned;
        staff = staffMember;
    }

}
