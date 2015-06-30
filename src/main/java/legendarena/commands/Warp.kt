package legendarena.commands

import legendapi.utils.Cooldown
import legendarena.hub.menu.MainMenu
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class Warp : CommandExecutor {

    private val cooldown = HashMap<UUID, Cooldown>()

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(sender !is Player) {
            sender.sendMessage("Sorry - you can only do this as a player :(")
            return true //Do nothing if it's not a player
        }
        if(cooldown.containsKey(sender.getUniqueId()) && !cooldown.get(sender.getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(sender.getUniqueId()).getTimeRemaining())
            return true
        }
        MainMenu.show(sender)
        //2 second cooldown [mainly implemented to prevent people from spamming this - it shouldn't really have that much of an impact on people who aren't trying to spam it]
        cooldown.put(sender.getUniqueId(), Cooldown(2.0))
        return true
    }

}