package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Calendar;

public class Dev implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0) {
            if(!Rank.isRanked(sender, Rank.FOUNDER))
                return true;
            sender.sendMessage(ChatColor.YELLOW + "--.{ Server Info }.--");
            sender.sendMessage(ChatColor.BLUE + "Free memory: " + Runtime.getRuntime().freeMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Used memory: " + Runtime.getRuntime().totalMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Max memory: " + Runtime.getRuntime().maxMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Day: " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            sender.sendMessage(ChatColor.BLUE + "Month: " + Calendar.getInstance().get(Calendar.MONTH));
            sender.sendMessage(ChatColor.YELLOW + "--.{ Server Info }.--");
            return true;
        }

        if(args[0].equalsIgnoreCase("thejoke")) {
            if(!Rank.isRanked(sender, Rank.ADMIN))
                return true;
            ChatUtils.broadcast("the joke");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast("              your head");
            // don't ask. just don't.
        } else if(args[0].equals("TEAPOT")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER))
                return true;
            ChatUtils.broadcast(String.format("%sHTTP/1.1 418 I'm a teapot", PluginUtils.msgWarning));
        } else if(args[0].equals("TESTMSGS")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER))
                return true;
            sender.sendMessage(PluginUtils.msgDebug + "Hello, world");
            sender.sendMessage(PluginUtils.msgError + "Hello, world");
            sender.sendMessage(PluginUtils.msgNormal + "Hello, world");
            sender.sendMessage(PluginUtils.msgWarning + "Hello, world");
        } else if(args[0].equalsIgnoreCase("testRankNames")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER)) {
                return true;
            }
            sender.sendMessage(PluginUtils.msgDebug + "FOUNDER: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.FOUNDER));
            sender.sendMessage(PluginUtils.msgDebug + "ADMIN: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.ADMIN));
            sender.sendMessage(PluginUtils.msgDebug + "SRMOD: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.SRMOD));
            sender.sendMessage(PluginUtils.msgDebug + "MOD: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.MOD));
            sender.sendMessage(PluginUtils.msgDebug + "HELPER: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.HELPER));
            sender.sendMessage(PluginUtils.msgDebug + "TWITCH: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.VIP));
            sender.sendMessage(PluginUtils.msgDebug + "MEMBER: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.MEMBER));
        }
        return true;
    }
}
