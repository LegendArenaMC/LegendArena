package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.utils.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;

/**
 * @author TheNameMan
 */
public class Freeze implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(PluginUtils.msgNormal + "Usage: /freeze <player>");
            return true;
        } else {
            if(Bukkit.getPlayer(args[1]) != null) {
                if(!PluginUtils.frozenPlayers.contains(Bukkit.getPlayer(args[1]).getUniqueId())) {
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
                    sender.sendMessage(ChatColor.GREEN + "Froze player " + p.getName() + " successfully.");
                    PluginUtils.frozenPlayers.add(p.getUniqueId());
                } else {
                    Player p = Bukkit.getPlayer(args[1]);
                    p.setCanPickupItems(true);
                    p.setSleepingIgnored(false);
                    try { p.removePotionEffect(PotionEffectType.SLOW_DIGGING); } catch (Exception ignore) {}
                    try { p.removePotionEffect(PotionEffectType.JUMP); } catch(Exception ignore) {}
                    p.setMaximumNoDamageTicks(0);
                    if(Rank.getRank(p, Rank.Mod) || (p.getWorld().getName().equalsIgnoreCase("plotworld") || p.getWorld().getName().equalsIgnoreCase("freebuild")))
                        p.setAllowFlight(true);
                    p.setWalkSpeed((float) 0.2);
                    PluginUtils.frozenPlayers.remove(p.getUniqueId());
                    sender.sendMessage(ChatColor.GREEN + "Unfroze player " + p.getName() + " successfully.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Player " + args[1] + " not found!");
            }
        }
        return true;
    }

}
