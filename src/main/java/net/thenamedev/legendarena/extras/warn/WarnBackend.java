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
                ChatColor.RED + "Legend Arena " + ChatColor.GRAY
                        + PluginUtils.chars[1] + ChatColor.BLUE
                        + " Staff member " + ChatColor.RED
                        + Rank.getFormattedName(staff) + ChatColor.BLUE
                        + " warned player " + ChatColor.YELLOW
                        + warnPlayer.getName() + ChatColor.BLUE
                        + " for reason: " + ChatColor.LIGHT_PURPLE
                        + reason,
                Rank.Mod
        );

        warnPlayer.sendMessage("");
        warnPlayer.sendMessage("");
        warnPlayer.sendMessage(
                ChatColor.RED + "-=+ [Warned] +=-"
                        + '\n'
                        + ChatColor.LIGHT_PURPLE + "Warned by staff member: " + ChatColor.RED + staff.getName()
                        + '\n'
                        + ChatColor.LIGHT_PURPLE + "Reason: " + ChatColor.RED + reason
                        + '\n'
                        + ChatColor.RED + "-=+ [Warned] +=-"
        );
        warnPlayer.sendMessage("");
        warnPlayer.sendMessage("");
        warnPlayer.playSound(warnPlayer.getLocation(), Sound.BLAZE_DEATH, 2, 1);
    }

    public void setup(Player warned, Player staffMember, String reason) {
        if(warned == null || staffMember == null || reason == null) throw new NullPointerException();
        this.reason = reason;
        warnPlayer = warned;
        staff = staffMember;
    }

}
