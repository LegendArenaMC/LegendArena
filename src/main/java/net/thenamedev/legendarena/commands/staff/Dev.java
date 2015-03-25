package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by thepixeldev on 3/24/15.
 */
public class Dev implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0) {
            if(!Rank.isRanked(sender, Rank.FOUNDER)) return true;
            sender.sendMessage(ChatColor.YELLOW + "--.{ Server Info }.--");
            sender.sendMessage(ChatColor.BLUE + "Free memory: " + Runtime.getRuntime().freeMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Used memory: " + Runtime.getRuntime().totalMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Max memory: " + Runtime.getRuntime().maxMemory() + " bits");
            sender.sendMessage(ChatColor.YELLOW + "--.{ Server Info }.--");
            return true;
        }

        if(args[0].equalsIgnoreCase("thejoke")) {
            if(!Rank.isRanked(sender, Rank.ADMIN)) {
                return true;
            }
            ChatUtils.broadcast("the joke");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast("              your head");
        } else if(args[0].equals("TEAPOT")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER)) {
                return true;
            }
            ChatUtils.broadcast(String.format("%sHTTP/1.1 418 I'm a teapot", PluginUtils.msgWarning));
        } else if(args[0].equals("TESTMSGS")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER)) {
                return true;
            }
            sender.sendMessage(PluginUtils.msgDebug + "Hello, world");
            sender.sendMessage(PluginUtils.msgError + "Hello, world");
            sender.sendMessage(PluginUtils.msgNormal + "Hello, world");
            sender.sendMessage(PluginUtils.msgWarning + "Hello, world");
        } else if(args[0].equalsIgnoreCase("testRankNames")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER)) {
                return true;
            }
            sender.sendMessage(PluginUtils.msgDebug + "FOUNDER: " + ChatColor.RESET + Rank.getFormattedName("Tester", Rank.FOUNDER));
            sender.sendMessage(PluginUtils.msgDebug + "ADMIN: " + ChatColor.RESET + Rank.getFormattedName("Tester", Rank.ADMIN));
            sender.sendMessage(PluginUtils.msgDebug + "SRMOD: " + ChatColor.RESET + Rank.getFormattedName("Tester", Rank.SRMOD));
            sender.sendMessage(PluginUtils.msgDebug + "MOD: " + ChatColor.RESET + Rank.getFormattedName("Tester", Rank.MOD));
            sender.sendMessage(PluginUtils.msgDebug + "HELPER: " + ChatColor.RESET + Rank.getFormattedName("Tester", Rank.HELPER));
            sender.sendMessage(PluginUtils.msgDebug + "TWITCH: " + ChatColor.RESET + Rank.getFormattedName("Tester", Rank.TWITCH));
            sender.sendMessage(PluginUtils.msgDebug + "YOUTUBE: " + ChatColor.RESET + Rank.getFormattedName("Tester", Rank.YOUTUBE));
            sender.sendMessage(PluginUtils.msgDebug + "FAMOUS: " + ChatColor.RESET + Rank.getFormattedName("Tester", Rank.FAMOUS));
            sender.sendMessage(PluginUtils.msgDebug + "MEMBER+: " + ChatColor.RESET + Rank.getFormattedName("Tester", Rank.MEMBERPLUS));
            sender.sendMessage(PluginUtils.msgDebug + "MEMBER: " + ChatColor.RESET + Rank.getFormattedName("Tester", Rank.MEMBER));
        }
        return true;
    }
}
