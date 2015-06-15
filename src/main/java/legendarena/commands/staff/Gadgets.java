package legendarena.commands.staff;

import legendapi.utils.ChatUtils;
import legendapi.utils.Rank;
import legendarena.hub.HubWarper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Gadgets toggle command.
 *
 * @author ThePixelDev
 */
public class Gadgets implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return true; //Do nothing if it's not a player
        }
        if(!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD));
            return true;
        }
        if(args.length == 0) {
            if(HubWarper.isExempt(((Player) sender).getUniqueId())) {
                HubWarper.toggleExemption(((Player) sender).getUniqueId());
                sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Removed from gadgets exempt list.");
            } else {
                HubWarper.toggleExemption(((Player) sender).getUniqueId());
                sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Added to gadgets exempt list.");
            }
        } else {
            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(ChatUtils.Messages.errorMsg + "Player \"" + args[0] + "\" was not found.");
            } else {
                Player p = Bukkit.getPlayer(args[0]);
                UUID u = p.getUniqueId();
                if(HubWarper.isExempt(u)) {
                    HubWarper.toggleExemption(u);
                    sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Removed player " + p.getName() + " from gadgets exemption list.");
                } else {
                    HubWarper.toggleExemption(u);
                    sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Added player " + p.getName() + " to gadgets exemption list.");
                }
            }
        }
        return true;
    }

}
