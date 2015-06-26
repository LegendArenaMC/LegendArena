package legendarena.commands.staff;

import legendapi.utils.Rank;
import legendarena.commands.Help;
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
 * Troll command. Mwhahahaha!
 *
 * @author ThePixelDev
 */
public class Troll implements CommandExecutor {

    public static final List<UUID> sheepleTroll = new ArrayList<>();

    @SuppressWarnings("deprecation") //shut up, Bukkit.getPlayer(String) warnings. please. SHUT UP FOR FUCKS SAKES, SHUT UP SHUT UP SHUT THE FUCK UP </rant>
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD));
            return true;
        }
        if(args.length == 0) {
            help(sender);
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
                p.chat("MY SPLEEEEEENN!");
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
                p.chat("Penguins are superior to panes of glass.");
            }
        } else if(args[0].equalsIgnoreCase("sheeple")) {
            if(args.length == 1) {
                sender.sendMessage(Help.getFormattedHelpMsg("/troll sheeple <player>", "It's all a conspiracy! Wake up, sheeple!"));
            } else {
                Player p = Bukkit.getPlayer(args[1]);
                if(p == null) {
                    sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + args[1] + ChatColor.RED + " was not found!");
                    return true;
                }
                sheepleTroll.add(p.getUniqueId());
                p.chat("It's all a conspiracy!");
            }
        } else if(args[0].equalsIgnoreCase("fakeban")) {
            if(args.length == 1) {
                sender.sendMessage(Help.getFormattedHelpMsg("/troll sheeple <player>", "It's all a conspiracy! Wake up, sheeple!"));
            } else {
                Player p = Bukkit.getPlayer(args[1]);
                if(p == null) {
                    sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + args[1] + ChatColor.RED + " was not found!");
                    return true;
                }
                sheepleTroll.add(p.getUniqueId());
                p.kickPlayer("You were banned!\nReason:\n \n" + ChatColor.RED + "The ban hammer has spoken!");
            }
        } else if(args[0].equalsIgnoreCase("end")) {
            if(args.length == 1) {
                sender.sendMessage(Help.getFormattedHelpMsg("/troll end <player>", "Stop all trolls on a player."));
            } else {
                Player p = Bukkit.getPlayer(args[1]);
                if(p == null) {
                    sender.sendMessage(ChatColor.RED + "Player " + ChatColor.YELLOW + args[1] + ChatColor.RED + " was not found!");
                    return true;
                }
                if(sheepleTroll.contains(p.getUniqueId())) sheepleTroll.remove(p.getUniqueId());
                for(PotionEffect potion : p.getActivePotionEffects())
                    p.removePotionEffect(potion.getType());
            }
        }

        else {
            help(sender);
            return true;
        }

        return true;
    }

    private void help(CommandSender sender) {
        sender.sendMessage(Help.getFormattedHeader("Troll Utils"));
        sender.sendMessage(Help.getFormattedHelpMsg("/troll rekt <player>", "When you're REKTing so hard you just gotta REKT someone who you wern't even REKTing. (I'll show myself the door -Pixel)"));
        sender.sendMessage(Help.getFormattedHelpMsg("/troll fakeban <player>", "Aww. You were kicked from the server. Did you make someone angry?"));
        sender.sendMessage(Help.getFormattedHelpMsg("/troll pmc <player>", "Hi, I'm from PMC, can I have OP to re- AHHHH MY SPLEEEEEN"));
        sender.sendMessage(Help.getFormattedHelpMsg("/troll penguins <player>", "Penguins are superior to panes of glass."));
        sender.sendMessage(Help.getFormattedHelpMsg("/troll sheeple <player>", "It's all a conspiracy! Wake up, sheeple!"));
        sender.sendMessage(Help.getFormattedHelpMsg("/troll end <player>", "Stop all trolls on a player."));
    }

}
