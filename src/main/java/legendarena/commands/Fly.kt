package legendarena.commands

import legendapi.utils.ChatUtils
import legendapi.utils.Cooldown
import legendapi.utils.CooldownUtils
import legendapi.utils.Rank
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class Fly : CommandExecutor {

    private val cooldown = HashMap<UUID, Cooldown>()

    fun run(sender: CommandSender) {
        if(sender !is Player) {
            sender.sendMessage("Sorry - you can only do this as a player :(")
            return //Do nothing if it's not a player
        }
        if(!Rank.isRanked(sender, Rank.HELPER)) {
            sender.sendMessage(Rank.noPermissions(Rank.HELPER))
            return
        }
        if(cooldown.containsKey(sender.getUniqueId()) && !cooldown.get(sender.getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(sender.getUniqueId()).getTimeRemaining())
            return
        }
        if(sender.getAllowFlight()) {
            sender.sendMessage(ChatUtils.getCustomMsg("Flight") + "Disabled flight!")
            sender.setAllowFlight(false)
            sender.setFlying(false)
        } else {
            sender.sendMessage(ChatUtils.getCustomMsg("Flight") + "Enabled flight!")
            sender.setAllowFlight(true)
        }
        //3 second cooldown
        cooldown.put(sender.getUniqueId(), CooldownUtils.getCooldown(3))
    }

    override fun onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array<String>): Boolean {
        run(commandSender)
        return true
    }

}