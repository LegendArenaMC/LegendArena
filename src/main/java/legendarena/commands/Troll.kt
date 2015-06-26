package legendarena.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Troll : CommandExecutor {

    override fun onCommand(sender: CommandSender?, p1: Command?, p2: String?, args: Array<out String>?): Boolean {
        return true;
    }

}