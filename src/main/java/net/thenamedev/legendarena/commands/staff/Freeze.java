package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class Freeze implements CommandExecutor {

    public static final ArrayList<UUID> frozenPlayers = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /freeze <player>");
            return true;
        } else {
            try {
                if(Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(ChatColor.RED + "The player \"" + ChatColor.YELLOW + args[0] + ChatColor.RED + "\" was not found!");
                    return true;
                }
            } catch(Exception ex) { //legacy try/catch - too lazy to remove it ;-;
                sender.sendMessage(PluginUtils.msgError + "Encountered an error while checking if the player is online. Exiting. (error is dumped out in the console, by the way)");
                ex.printStackTrace();
                return true;
            }
            if(Rank.getRank(Bukkit.getPlayer(args[0]), Rank.Mod)) {
                sender.sendMessage(PluginUtils.msgNormal + "You must be fun at parties.");
                return true;
            }
            if(!frozenPlayers.contains(Bukkit.getPlayer(args[0]).getUniqueId())) {
                Player p = Bukkit.getPlayer(args[0]);
                p.setCanPickupItems(false);
                p.setSleepingIgnored(true);
                p.setMaximumNoDamageTicks(40);
                p.setAllowFlight(false);
                p.resetMaxHealth();
                p.setFlying(false);
                p.setWalkSpeed((float) 0.0);
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100000, 100, true));
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 130, true));
                p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100000, 20, true));
                sender.sendMessage(PluginUtils.msgNormal + "Froze player " + p.getName() + " successfully.");
                frozenPlayers.add(p.getUniqueId());
            } else {
                Player p = Bukkit.getPlayer(args[0]);
                p.setCanPickupItems(true);
                if(!Rank.getRank(p, Rank.GM)) p.setSleepingIgnored(false);
                try { p.removePotionEffect(PotionEffectType.SLOW_DIGGING); } catch (Exception ignore) {
                    if(LegendArena.debugSwitch)
                        sender.sendMessage(PluginUtils.msgDebug + "Could not remove MINING FATIGUE.");
                }
                try { p.removePotionEffect(PotionEffectType.JUMP); } catch(Exception ignore) {
                    if(LegendArena.debugSwitch)
                        sender.sendMessage(PluginUtils.msgDebug + "Could not remove JUMP BOOST.");
                }
                try { p.removePotionEffect(PotionEffectType.WEAKNESS); } catch(Exception ignore) {
                    if(LegendArena.debugSwitch)
                        sender.sendMessage(PluginUtils.msgDebug + "Could not remove WEAKNESS.");
                }
                p.setMaximumNoDamageTicks(0);
                if(Rank.getRank(p, Rank.Mod) || (p.getWorld().getName().equalsIgnoreCase("plotworld") || p.getWorld().getName().equalsIgnoreCase("freebuild")))
                    p.setAllowFlight(true);
                p.setWalkSpeed((float) 0.2);
                frozenPlayers.remove(p.getUniqueId());
                sender.sendMessage(ChatColor.GREEN + "Unfroze player " + p.getName() + " successfully.");
            }
        }
        return true;
    }

}
