package legendarena.commands;

import legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Online staff list command.
 *
 * @author ThePixelDev
 */
public class StaffList implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        List<Player> staffOnline = new ArrayList<>();
        for(Player p : Bukkit.getOnlinePlayers())
            if(Rank.isRanked(p, Rank.HELPER))
                staffOnline.add(p);
        sender.sendMessage(Help.getFormattedHeader("Staff Members"));
        if(staffOnline.isEmpty())
            sender.sendMessage(ChatColor.RED + "There's no staff online :(");
        else
            for(Player p : staffOnline)
                sender.sendMessage(ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " // " + ChatColor.GREEN + Rank.getRank(p));
        return true;
    }

}
