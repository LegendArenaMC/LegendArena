package net.thenamedev.legendarena.commands.backends;

import net.thenamedev.legendapi.LegendAPI;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author ThePixelDev
 */
public class Freeze {

    public static final ArrayList<UUID> frozenPlayers = new ArrayList<>();

    public static void run(CommandSender sender, String[] args) {
        if(!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD));
            return;
        }
        if(args.length < 2) {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /freeze <player>");
        } else {
            if(Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage(PluginUtils.msgWarning + ChatColor.RED + "The player \"" + ChatColor.YELLOW + args[0] + ChatColor.RED + "\" was not found!");
                return;
            }
            if(Rank.isRanked(Bukkit.getPlayer(args[0]), Rank.MOD)) {
                sender.sendMessage(PluginUtils.msgNormal + "You must be fun at parties.");
                return;
            }
            if(!frozenPlayers.contains(Bukkit.getPlayer(args[1]).getUniqueId())) {
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
                frozenPlayers.add(p.getUniqueId());
                p.sendMessage(PluginUtils.msgNormal + "You were frozen!");
                sender.sendMessage(PluginUtils.msgNormal + "Froze player " + p.getName() + " successfully.");
            } else {
                Player p = Bukkit.getPlayer(args[0]);
                p.setCanPickupItems(true);
                if(!Rank.isRanked(p, Rank.ADMIN)) p.setSleepingIgnored(false);
                try { p.removePotionEffect(PotionEffectType.SLOW_DIGGING); } catch (Exception ignore) {
                    if(LegendAPI.debug)
                        sender.sendMessage(PluginUtils.msgDebug + "Could not remove MINING FATIGUE.");
                }
                try { p.removePotionEffect(PotionEffectType.JUMP); } catch(Exception ignore) {
                    if(LegendAPI.debug)
                        sender.sendMessage(PluginUtils.msgDebug + "Could not remove JUMP BOOST.");
                }
                try { p.removePotionEffect(PotionEffectType.WEAKNESS); } catch(Exception ignore) {
                    if(LegendAPI.debug)
                        sender.sendMessage(PluginUtils.msgDebug + "Could not remove WEAKNESS.");
                }
                p.setMaximumNoDamageTicks(0);
                if(Rank.isRanked(p, Rank.MOD) || (p.getWorld().getName().equalsIgnoreCase("plotworld") || p.getWorld().getName().equalsIgnoreCase("freebuild")))
                    p.setAllowFlight(true);
                p.setWalkSpeed((float) 0.2);
                frozenPlayers.remove(p.getUniqueId());
                p.sendMessage(PluginUtils.msgNormal + "You were unfrozen!");
                sender.sendMessage(PluginUtils.msgNormal + "Unfroze player " + p.getName() + " successfully.");
            }
        }
    }

}
