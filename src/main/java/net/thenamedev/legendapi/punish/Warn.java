package net.thenamedev.legendapi.punish;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author ThePixelDev
 */
public class Warn {

    Player warnPlayer;
    String formattedMsg = "";
    String staffFormattedMsg = "";

    public Warn(Player warnPlayer, CommandSender staff, String reason) {
        this.warnPlayer = warnPlayer;

        staffFormattedMsg = String.format(
                "%sStaff member %s has warned player %s for \"%s\".",

                ChatUtils.getCustomMsg("Punish"),
                ChatUtils.getFormattedName((Player) staff) + ChatColor.LIGHT_PURPLE,
                ChatUtils.getFormattedName(warnPlayer) + ChatColor.LIGHT_PURPLE,
                ChatColor.YELLOW + reason + ChatColor.LIGHT_PURPLE
        );
        formattedMsg = String.format(
                "%sIssuing staff: %s\n \nReason: %s",

                ChatColor.LIGHT_PURPLE,
                ChatUtils.getFormattedName((Player) staff) + ChatColor.LIGHT_PURPLE,
                ChatColor.YELLOW + reason + ChatColor.LIGHT_PURPLE
        );
    }

    public void run() {
        ChatUtils.broadcast(
                staffFormattedMsg,
                Rank.HELPER
        );

        warnPlayer.sendMessage(ChatColor.RED + "-- WARNING --");
        warnPlayer.sendMessage(" ");
        warnPlayer.sendMessage(formattedMsg);
        warnPlayer.sendMessage(" ");
        warnPlayer.sendMessage(ChatColor.RED + "-- WARNING --");
        warnPlayer.playSound(warnPlayer.getLocation(), Sound.ANVIL_LAND, 2, 2);
    }

}
