package legendarena.commands

import legendapi.utils.Rank
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Staff: CommandExecutor {

    override fun onCommand(sender: CommandSender, p1: Command, p2: String, args: Array<out String>): Boolean {
        if(!Rank.isRanked(sender, Rank.HELPER)) {
            sender.sendMessage(Rank.noPermissions(Rank.HELPER))
            return true
        }
        if(args.size() == 0) {
            sender.sendMessage("")
            return true
        }
        return true
    }

}
