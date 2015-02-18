package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendarena.commands.backends.*;
import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.utils.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class LookupUser implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return true; //Do nothing if it's not a player
        }
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /lalookup <history|info|bans> <player>");
            return true;
        }
        if(args[0].equalsIgnoreCase("history")) {
            args[0] = "";
            UsernameHistory.run(sender, args);
        } else if(args[0].equalsIgnoreCase("info")) {
            args[0] = "";
            Info.run(sender, args);
        } else if(args[0].equalsIgnoreCase("bans")) {
            args[0] = "";
            Bans.run(sender, args);
        } else {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /lalookup <history|info|bans> <player>");
            return true;
        }
        return true;
    }

}
