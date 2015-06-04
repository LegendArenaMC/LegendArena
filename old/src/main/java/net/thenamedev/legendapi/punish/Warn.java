package net.thenamedev.legendapi.punish;

import net.thenamedev.legendapi.message.Message;
import net.thenamedev.legendapi.message.MessageType;
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
    CommandSender staff;
    String reason;
    String staffFormattedMsg = "";

    public Warn(Player warnPlayer, CommandSender staff, String reason) {
        this.warnPlayer = warnPlayer;
        this.staff = staff;
        this.reason = reason;

        staffFormattedMsg = String.format(
                "%sStaff member %s has warned player %s for \"%s\".",

                ChatUtils.getCustomMsg("Punish"),
                ChatUtils.getFormattedName((Player) staff) + ChatColor.LIGHT_PURPLE,
                ChatUtils.getFormattedName(warnPlayer) + ChatColor.LIGHT_PURPLE,
                ChatColor.YELLOW + reason + ChatColor.LIGHT_PURPLE
        );
    }

    public void run() {
        ChatUtils.broadcast(
                staffFormattedMsg,
                Rank.HELPER
        );

        new Message(MessageType.TITLE).append(ChatUtils.getCustomMsg("Punish") + "Warned by " + ChatColor.YELLOW + staff.getName() + ChatColor.BLUE + " for:").send(warnPlayer);
        new Message(MessageType.SUBTITLE).append(ChatColor.YELLOW + reason).send(Sound.ANVIL_LAND, 2, warnPlayer);
    }

}
