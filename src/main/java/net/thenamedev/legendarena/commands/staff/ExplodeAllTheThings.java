package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.commands.Help;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

/**
 * Created on 5/15/2015
 *
 * @author ThePixelDev
 */
public class ExplodeAllTheThings implements CommandExecutor {

    public synchronized static void attack(Player p) {
        attack(p, 120, 80);
    }

    public synchronized static void attack(Player p, int amount, int fuse) {
        for(int i = amount; i > 0; i--) {
            TNTPrimed tnt = p.getLocation().getBlock().getWorld().spawn(p.getLocation().getBlock().getLocation(), TNTPrimed.class);
            tnt.setFuseTicks(fuse);
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.isRanked(sender, Rank.FOUNDER)) {
            sender.sendMessage(Rank.noPermissions(Rank.FOUNDER));
            return true;
        }
        sender.sendMessage(ChatUtils.getCustomMsg("Extras") + "DEPLOYING BOMBS!");
        if(args.length == 0)
            attack((Player) sender);
        else {
            if(args.length == 1) {
                //only the amount was specified
                try {
                    //we genuinely don't give a shit about the return of this if it's anything but an Exception
                    Integer.parseInt(args[0]);
                } catch(Exception ex) {
                    sender.sendMessage(Help.getFormattedHelpMsg("/explodeallthethings [amount] [fuse]", "BOMBS AWAY!"));
                    return true;
                }
                attack((Player) sender, Integer.parseInt(args[0]), 80);
            } else {
                //both amount and fuse were specified
                try {
                    //we genuinely don't give a shit about the return of this if it's anything but an Exception
                    Integer.parseInt(args[0]);
                    Integer.parseInt(args[1]);
                } catch(Exception ex) {
                    sender.sendMessage(Help.getFormattedHelpMsg("/explodeallthethings [amount] [fuse]", "BOMBS AWAY!"));
                    return true;
                }
                attack((Player) sender, Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            }
        }
        return true;
    }

}
