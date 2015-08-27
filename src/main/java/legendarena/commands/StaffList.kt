package legendarena.commands

import legendarena.api.fanciful.FancyMessage
import legendarena.api.utils.ChatUtils
import legendarena.api.utils.MenuUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.RankUtils
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
            if(RankUtils.getDisplayRank(p).isStaff()) //respect staff who wish to not be visibly shown as staff via the tag system
                staffOnline.add(p)
        var msg = FancyMessage("-•- [Online Staff] -•-")
                .color(ChatColor.GREEN)
        if(staffOnline.isEmpty()) {
            msg.then("\nThere's no staff online :(")
                        .color(ChatColor.RED)
                    .send(sender)
            return true
        }
        for(p in staffOnline) {
           msg.then("\n")
                    .then(p.getName())
                        .color(RankUtils.getDisplayRank(p).getNameColor())
                        .suggest("/msg " + p.getName() + " ")
                        .tooltip("Click to message " + p.getName())
                    .then(" // ")
                        .color(ChatColor.DARK_GRAY)
                    .then(RankUtils.getDisplayRank(p).toString())
                        .color(RankUtils.getDisplayRank(p).getNameColor())
        }

        msg.send(sender)
        return true
    }

}