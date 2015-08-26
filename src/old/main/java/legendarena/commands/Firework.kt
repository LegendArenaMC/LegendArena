package legendarena.commands

import legendapi.emeralds.EmeraldsCore
import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.*
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class Firework : CommandExecutor {

    private val cooldown = HashMap<UUID, Cooldown>()

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(sender !is Player) {
            sender.sendMessage("You can only use this command as a Player, sorry!")
            return true
        }

        if(!Rank.MEMBERPLUS.isRanked(sender)) {
            RankUtils.fancyNoPermissions(Rank.MEMBERPLUS, sender)
            return true
        }

        if(cooldown.containsKey(sender.getUniqueId()) && !cooldown.get(sender.getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(sender.getUniqueId()).getTimeRemaining())
            return true
        }

        PluginUtils.shootFireworks(sender)
        Message(MessageType.TITLE).append("" + ChatColor.GREEN + "Woo, fireworks!").send(sender)
        //3 second cooldown
        cooldown.put(sender.getUniqueId(), Cooldown(3.0))
        return true
    }

}