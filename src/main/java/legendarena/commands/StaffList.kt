package legendarena.commands

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class StaffList : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        val staffOnline = ArrayList<Player>()
        for(p in Bukkit.getOnlinePlayers())
            if (Rank.isRanked(p, Rank.HELPER))
                staffOnline.add(p)
        sender.sendMessage(ChatUtils.getFormattedHeader("Staff Members"))
        if(staffOnline.isEmpty())
            sender.sendMessage("" + ChatColor.RED + "There's no staff online right now :(")
        else
            for(p in staffOnline)
                sender.sendMessage("" + ChatColor.RED + p.getName() + ChatColor.DARK_GRAY + " // " + ChatColor.GREEN + Rank.getRank(p))
        return true
    }

}