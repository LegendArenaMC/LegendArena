package net.thenamedev.legendarena.extras.warn;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;

/**
 * @author TheNameMan
 */
public class WarnBackend implements Runnable {

    Player warnPlayer;
    Player staff;
    String reason;

    public void run() {
        ChatUtils.broadcast(
                ChatColor.RED + "Legend Arena " + ChatColor.GRAY
                        + PluginUtils.chars[1] + ChatColor.BLUE
                        + " Staff member " + ChatColor.RED
                        + staff.getName() + ChatColor.BLUE
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
    }

    public void setup(Player warned, Player staffMember, String reason) {
        if(warned == null || staffMember == null || reason == null) return;
        this.reason = reason;
        warnPlayer = warned;
        staff = staffMember;
    }

}
