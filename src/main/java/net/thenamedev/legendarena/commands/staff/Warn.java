package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendarena.extras.warn.*;
import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.utils.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class Warn implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, Command label, String labelString, @NotNull String[] args) {
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /warn <player> <reason>");
        } else if(args.length == 1) {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /warn <player> <reason>");
        } else {
            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(ChatColor.RED + "That player was not found!");
            } else {
                @NotNull WarnBackend warn = new WarnBackend();
                Player warnPlayer = Bukkit.getPlayer(args[0]);
                args[0] = "";
                @NotNull String reason = ChatUtils.formatCast(args);
                warn.setup(warnPlayer, (Player) sender, reason);
                warn.run();
            }
        }
        return true;
    }

}
