package net.thenamedev.legendarena.extras.warn;

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
public class WarnBackend {

    Player warnPlayer;

    String formattedMsg = "";
    String staffFormattedMsg = "";

    public WarnBackend(Player warnPlayer, CommandSender staff, String reason) {
        this.warnPlayer = warnPlayer;

        staffFormattedMsg = String.format(
                "%sStaff member %s has warned player %s for %s.",

                PluginUtils.msgNormal,
                Rank.getFormattedName((Player) staff) + ChatColor.LIGHT_PURPLE,
                Rank.getFormattedName(warnPlayer) + ChatColor.LIGHT_PURPLE,
                ChatColor.YELLOW + reason + ChatColor.LIGHT_PURPLE
        );
        formattedMsg = String.format(
                "%sYou were warned by staff member %s for %s.",

                PluginUtils.msgNormal,
                Rank.getFormattedName((Player) staff) + ChatColor.LIGHT_PURPLE,
                ChatColor.YELLOW + reason + ChatColor.LIGHT_PURPLE
        );
    }

    public void run() {
        ChatUtils.broadcast(
                staffFormattedMsg,
                Rank.HELPER
        );

        warnPlayer.sendMessage(ChatColor.RED + "--  --");
        warnPlayer.sendMessage(" ");
        warnPlayer.sendMessage(formattedMsg);
        warnPlayer.sendMessage(" ");
        warnPlayer.sendMessage(ChatColor.RED + "--  --");
        warnPlayer.playSound(warnPlayer.getLocation(), Sound.DONKEY_DEATH, 2, 2);
    }

}
