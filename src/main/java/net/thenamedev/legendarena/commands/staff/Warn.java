package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendarena.commands.extras.warn.*;
import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.utils.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

/**
 * @author TheNameMan
 */
public class Warn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command label, String labelString, String[] args) {
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(ChatColor.BLUE + "Usage: /warn <player> <reason>");
        } else if(args.length == 1) {
            sender.sendMessage(ChatColor.BLUE + "Usage: /warn <player> <reason>");
        } else {
            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(ChatColor.RED + "That player was not found!");
            } else {
                WarnBackend warn = new WarnBackend();
                Player warnPlayer = Bukkit.getPlayer(args[0]);
                args[0] = "";
                String reason = ChatUtils.formatCast(args);
                warn.setup(warnPlayer, (Player) sender, reason);
                warn.run();
            }
        }
        return true;
    }

}
