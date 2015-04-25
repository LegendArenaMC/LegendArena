package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.commands.Help;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 4/4/2015
 *
 * @author ThePixelDev
 */
public class Troll implements CommandExecutor {

    public static List<UUID> penguinTroll = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.isRanked(sender, Rank.SRMOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.SRMOD));
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(Help.getFormattedHeader("Troll Utils"));
            sender.sendMessage(Help.getFormattedHelpMsg("/troll rekt <player>", "When you're REKTing so hard you just gotta REKT someone who you wern't even REKTing. (I'll show myself the door -Pixel)"));
            sender.sendMessage(Help.getFormattedHelpMsg("/troll freeze <player>", "Help! I've been frozen and can't move!"));
            sender.sendMessage(Help.getFormattedHelpMsg("/troll fakeban <player>", "Aww, you were banned. Did you make someone angry?"));
            sender.sendMessage(Help.getFormattedHelpMsg("/troll pmc <player>", "Hi, I'm from PMC, can I have OP to re- AHHHH MY SPLEEEEEN"));
            sender.sendMessage(Help.getFormattedHelpMsg("/troll reverse <player>", "Reverses a player's chat messages, but only on their screen. inb4\"?siht ees syug uoy naC\""));
            sender.sendMessage(Help.getFormattedHelpMsg("/troll doge <player>", "Many doge, much like, very message, wow."));
            sender.sendMessage(Help.getFormattedHelpMsg("/troll penguins <player>", "Penguins are superior to panes of glass."));
            return true;
        }

        else if(args[0].equalsIgnoreCase("pmc")) {
            if(args.length == 1) {
                sender.sendMessage(Help.getFormattedHelpMsg("/troll pmc <player>", "Hi, I'm from PMC, can I have OP to re- AHHHH MY SPLEEEEEN"));
            } else {
                Player p = Bukkit.getPlayer(args[1]);
                if(p == null) {
                    sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + args[1] + ChatColor.RED + " was not found!");
                    return true;
                }
                sender.sendMessage(ChatColor.GREEN + "Trolling " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " with troll " + ChatColor.RED + "PMC" + ChatColor.GREEN + ".");
                p.chat("AAAAHHH, MY SPLEEEEEENN!");
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000, 0, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1000000, 0, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 10, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 10, true, false));
            }
        } else if(args[0].equalsIgnoreCase("penguins")) {
            if(args.length == 1) {
                sender.sendMessage(Help.getFormattedHelpMsg("/troll penguins <player>", "Penguins are superior to panes of glass."));
            } else {
                Player p = Bukkit.getPlayer(args[1]);
                if(p == null) {
                    sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + args[1] + ChatColor.RED + " was not found!");
                    return true;
                }
                sender.sendMessage(ChatColor.GREEN + "Trolling " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " with troll " + ChatColor.RED + "PENGUINS" + ChatColor.GREEN + ".");
                p.chat("Penguins are superior to panes of glass.");

            }
        }
        return true;
    }
}
