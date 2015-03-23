package net.thenamedev.legendarena.commands.backends;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendarena.extras.warn.WarnBackend;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author TheNameMan
 */
public class Warn {

    public static void run(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return; //Do nothing if it's not a player
        }
        if(args.length == 1) {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /warn <player> <reason>");
        } else if(args.length == 2) {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /warn <player> <reason>");
        } else {
            if(Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage(ChatColor.RED + "That player was not found!");
            } else {
                WarnBackend warn = new WarnBackend();
                Player warnPlayer = Bukkit.getPlayer(args[1]);
                args[0] = "";
                args[1] = "";
                String reason = ChatUtils.formatCast(args);
                warn.setup(warnPlayer, (Player) sender, reason);
                warn.run();
            }
        }
    }

}
