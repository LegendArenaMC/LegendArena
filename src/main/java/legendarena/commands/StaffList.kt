package legendarena.commands

import legendapi.fanciful.FancyMessage
import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class StaffList : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        val staffOnline = ArrayList<Player>()
        for(p in Bukkit.getOnlinePlayers())
            if(Rank.HELPER.isRanked(p))
                staffOnline.add(p)
        var msg = FancyMessage("-•- [Online Staff] -•-")
                .color(ChatColor.GREEN)
        if(staffOnline.isEmpty()) {
            msg.then("There's no staff online :(")
                        .color(ChatColor.RED)
                    .send(sender)
            return true
        }
        for(p in staffOnline) {
           msg.then("\n")
                    .then(p.getName())
                        .color(RankUtils.getRank(p).getNameColor())
                        .suggest("/msg " + p.getName() + " ")
                        .tooltip("Click to message " + p.getName())
                    .then(" // ")
                        .color(ChatColor.DARK_GRAY)
                    .then(RankUtils.getRank(p).toString())
                        .color(ChatColor.BLUE)
        }

        msg.send(sender)
        return true
    }

}