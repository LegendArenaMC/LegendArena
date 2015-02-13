package me.thenameman.legendarena.utils;

import me.thenameman.legendarena.core.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

/**
 * @author TheNameMan
 */
public class TutorialUtils {

    private enum Type {
        GETTINGSTARTED, PLUGDJ, SITE, STAFFAPP, UNKNOWN
    }

    public static void hub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return;
        }
        if(args.length == 0) {
            tutorialList(sender);
        } else {
            Type type = parse(args);
            switch(type) {
                case GETTINGSTARTED:
                    gettingStarted(sender);
                    break;
                case PLUGDJ:
                    plugdj(sender);
                    break;
                case SITE:
                    //
                    break;
                case STAFFAPP:
                    //
                    break;
                case UNKNOWN:
                    sender.sendMessage(ChatColor.RED + "We don't know what you're saying :(" + (LegendArena.debugSwitch ? " (" + type + ")" : ""));
            }
        }

    }

    private static Type parse(String[] args) {
        if(args[0].equalsIgnoreCase("gettingstarted"))
            return Type.GETTINGSTARTED;
        else if(args[0].equalsIgnoreCase("plugdj") || args[0].equalsIgnoreCase("music"))
            return Type.PLUGDJ;
        else if(args[0].equalsIgnoreCase("site") || args[0].equalsIgnoreCase("forums"))
            return Type.SITE;

        else
            return Type.UNKNOWN;
    }

    private static void tutorialList(CommandSender sender) {
        sender.sendMessage(ChatColor.GREEN + "----- .[ " + ChatColor.BLUE + "Tutorial List" + ChatColor.GREEN + " ]. -----");
        sender.sendMessage(ChatColor.YELLOW + "GETTINGSTARTED " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + " How to get started on the server.");
        sender.sendMessage(ChatColor.YELLOW + "SITE " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + " Info on the forums (or as some people would call it, site).");
        sender.sendMessage(ChatColor.YELLOW + "PLUGDJ " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + " Info on the Plug.DJ room, and how to join.");
        sender.sendMessage(ChatColor.GREEN + "----- .[ " + ChatColor.BLUE + "Tutorial List" + ChatColor.GREEN + " ]. -----");
    }

    private static void gettingStarted(CommandSender sender) {
        //
    }

    private static void plugdj(CommandSender sender) {
        //
    }

}
