package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.commands.Help;
import net.thenamedev.legendarena.extras.menu.staff.TestMenu;
import net.thenamedev.legendarena.extras.particles.ParticleCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class Dev implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0) {
            if(!Rank.isRanked(sender, Rank.FOUNDER))
                return false;
            sender.sendMessage(ChatColor.YELLOW + "--.{ Server Info }.--");
            sender.sendMessage(ChatColor.BLUE + "Free memory: " + Runtime.getRuntime().freeMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Used memory: " + Runtime.getRuntime().totalMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Max memory: " + Runtime.getRuntime().maxMemory() + " bits");
            sender.sendMessage(ChatColor.BLUE + "Day: " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            sender.sendMessage(ChatColor.BLUE + "Month: " + Calendar.getInstance().get(Calendar.MONTH));
            sender.sendMessage(ChatColor.YELLOW + "--.{ Server Info }.--");
            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.YELLOW + "--.{ Debug Info }.--");
            sender.sendMessage(ChatColor.BLUE + "Particle amount: " + ParticleCore.amountOfActiveParticles((Player) sender));
            sender.sendMessage(ChatColor.YELLOW + "--.{ Debug Info }.--");
            return true;
        }

        if(args[0].equalsIgnoreCase("thejoke")) {
            if(!Rank.isRanked(sender, Rank.ADMIN))
                return false;
            ChatUtils.broadcast("the joke");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast(" ");
            ChatUtils.broadcast("                    your head");
            return true;
            // don't ask. just don't.
        } else if(args[0].equalsIgnoreCase("teapot")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER))
                return false;
            ChatUtils.broadcast(String.format("%sHTTP/1.1 418 I'm a teapot", PluginUtils.msgWarning));
            return true;
        } else if(args[0].equalsIgnoreCase("testmsgs")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER))
                return false;
            sender.sendMessage(PluginUtils.msgDebug + "Hello, world");
            sender.sendMessage(PluginUtils.msgError + "Hello, world");
            sender.sendMessage(PluginUtils.msgNormal + "Hello, world");
            sender.sendMessage(PluginUtils.msgWarning + "Hello, world");
            return true;
        } else if(args[0].equalsIgnoreCase("testranks")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER))
                return false;
            sender.sendMessage(PluginUtils.msgDebug + "FOUNDER: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.FOUNDER));
            sender.sendMessage(PluginUtils.msgDebug + "ADMIN: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.ADMIN));
            sender.sendMessage(PluginUtils.msgDebug + "SRMOD: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.SRMOD));
            sender.sendMessage(PluginUtils.msgDebug + "MOD: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.MOD));
            sender.sendMessage(PluginUtils.msgDebug + "HELPER: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.HELPER));
            sender.sendMessage(PluginUtils.msgDebug + "TWITCH: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.VIP));
            sender.sendMessage(PluginUtils.msgDebug + "MEMBER: " + ChatColor.RESET + ChatUtils.getFormattedChat("Testing, 123", "Tester", Rank.MEMBER));
            return true;
        } else if(args[0].equalsIgnoreCase("menutest")) {
            if(!Rank.isRanked(sender, Rank.FOUNDER))
                return false;
            if(args.length == 1) {
                sender.sendMessage(Help.getFormattedHelpMsg("/dev menutest <slots>", "Opens a testing menu with a specified amount of slots."));
                return true;
            }
            try {
                //we don't give a shit about the return for this call, unless it's an exception
                //noinspection ResultOfMethodCallIgnored
                Integer.parseInt(args[1]);
            } catch(Exception ex) {
                sender.sendMessage(ChatColor.RED + "The input \"" + args[1] + "\" is not an integer!");
                return true;
            }
            try {
                TestMenu testing = new TestMenu();
                testing.init(Integer.parseInt(args[1]));
                testing.show((Player) sender);
            } catch(Exception ex) {
                sender.sendMessage("Error: " + ex.getMessage());
            }
            return true;
        }
        return false;
    }
}
