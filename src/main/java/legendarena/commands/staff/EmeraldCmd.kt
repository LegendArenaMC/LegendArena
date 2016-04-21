package legendarena.commands.staff

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class EmeraldCmd : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        sender.sendMessage("" + ChatColor.RED + "This is completely and utterly broken.")
        return true
    }

}