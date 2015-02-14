package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.utils.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

/**
 * Safer-kind of /deop.
 * @author TheNameMan
 */
public class Deop implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.Dev)) {
            Rank.noPermissions(sender, Rank.Owner); //max rank is DEV, so that devs can test without op temporarily and be able to reop themselves when they're done testing without needing console/someone else to reop them
            //also, just fake that deop and op is for the owner only
            return true; //DO NOT GO ANY FURTHER. THIS IS ABSOLUTELY FUCKING IMPORTANT.
        }
        if(args.length == 0) {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /op <player> <--force> <--imsure>");
        } else if(args.length == 1) {
            sender.sendMessage("ARE YOU SURE YOU WANT TO DO THIS?");
            sender.sendMessage("If so, redo this command, but with \"--force --imsure\" on the end (and yes, it has to be on the END, and in that order).");
        } else {
            try {
                if(args[1].equalsIgnoreCase("--force") && args[2].equalsIgnoreCase("--imsure")) {
                    //They really want to do this so continue, so let them
                    if(Bukkit.getPlayerExact(args[0]) == null) {
                        sender.sendMessage(ChatColor.RED + "You did either of two things wrong:\n" +
                                "1. You didn't type out a player's full name (this is required to make sure you don't op a random person);\n" +
                                "2. That player is not online.");
                        return true;
                    }
                    if(!Bukkit.getPlayerExact(args[0]).isOp()) {
                        sender.sendMessage(ChatColor.RED + "That player isn't an op!");
                        return true;
                    }
                    Bukkit.getPlayerExact(args[0]).setOp(false);
                    ChatUtils.broadcast(ChatColor.RED + "Player " + ChatColor.GREEN + args[0] + ChatColor.RED + " is no longer an op! (Opper: " + Rank.getFormattedName((Player) sender) + ChatColor.RED + ")");
                } else {
                    sender.sendMessage("ARE YOU SURE YOU WANT TO DO THIS?");
                    sender.sendMessage("If so, redo this command, but with \"--force --imsure\" on the end (and yes, it has to be on the END, and in that order).");
                }
            } catch(ArrayIndexOutOfBoundsException ex) {
                sender.sendMessage("ARE YOU SURE YOU WANT TO DO THIS?");
                sender.sendMessage("If so, redo this command, but with \"--force --imsure\" on the end (and yes, it has to be on the END, and in that order).");
            }
        }
        return true;
    }

}
