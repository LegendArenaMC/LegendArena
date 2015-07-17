package legendarena.commands

import legendapi.utils.ChatUtils
import legendapi.utils.StringUtils
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Help : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        //ignore the hacky workaround for calling java's String.format()
        sender.sendMessage(java.lang.String.format("%sIt looks like you want help.\n \nMaybe you could ask an admin?", ChatUtils.getCustomMsg("Extras")))
        return true
    }

}