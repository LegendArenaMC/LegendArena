package net.thenamedev.legendarena.commands.backends;

import net.ae97.fishbans.api.*;
import net.ae97.fishbans.api.exceptions.*;
import net.thenamedev.legendarena.utils.*;
import org.bukkit.*;
import org.bukkit.command.*;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * @author TheNameMan
 */
public class Bans {

    public static void run(CommandSender sender, String[] args) {
        if(args.length == 0 || args.length == 1) {
            sender.sendMessage(ChatColor.BLUE + "Usage: /lookup bans <player> -[-detailed|d]");
            return;
        }
        if(args.length == 2) {
            basicInfo(sender, args[1]);
        } else {
            if(args[2].equalsIgnoreCase("--detailed") || args[2].equalsIgnoreCase("-d")) {
                detailedInfo(sender, args[1]);
            } else {
                basicInfo(sender, args[1]);
            }
        }
    }

    private static void basicInfo(CommandSender sender, String user) {
        try {
            FishbansPlayer player = Fishbans.getFishbanPlayer(user);
            if(player.getBanCount() == 0) {
                sender.sendMessage(PluginUtils.msgNormal + "No bans found for " + player.getName());
            } else {
                sender.sendMessage(PluginUtils.msgNormal + player.getName() + " has " + ChatColor.BLUE + player.getBanCount() + ChatColor.GREEN + " ban" + (player.getBanCount() == 1 ? "" : "s"));
            }
        } catch(IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Error occured while checking bans for " + user, e);
            sender.sendMessage(PluginUtils.msgError + "An error occured while checking bans: " + e.getMessage());
        } catch(NoSuchUserException e) {
            sender.sendMessage(PluginUtils.msgWarning + "No user with that name exists");
        }
    }

    private static void detailedInfo(CommandSender sender, String user) {
        try {
            FishbansPlayer player = Fishbans.getFishbanPlayer(user);
            if (player.getBanCount() == 0) {
                sender.sendMessage(PluginUtils.msgNormal + "No bans found for " + player.getName());
            } else {
                sender.sendMessage(PluginUtils.msgNormal + player.getName() + " has " + player.getBanCount() + " ban" + (player.getBanCount() == 1 ? "" : "s"));
                for (BanService service : BanService.values()) {
                    List<Ban> bans = player.getBanList(service);
                    if(bans == null || bans.isEmpty()) {
                        continue;
                    }
                    sender.sendMessage(ChatColor.GRAY + "-------------------------------");
                    sender.sendMessage(ChatColor.YELLOW + "Ban Service: " + service.getDisplayName() + (service.isLegacy() ? ChatColor.GRAY + " [LEGACY]" : ""));
                    for (Ban ban : bans) {
                        sender.sendMessage(ChatColor.BLUE + ban.getServer() + ChatColor.DARK_GRAY + " | " + ChatColor.BLUE + ban.getReason());
                    }
                }
            }
        } catch(IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Error occured while checking bans for " + user, e);
            sender.sendMessage(PluginUtils.msgError + "An error occured while checking bans: " + e.getMessage());
        } catch(NoSuchUserException e) {
            sender.sendMessage(PluginUtils.msgWarning + "No user with that name exists");
        }
    }

}
