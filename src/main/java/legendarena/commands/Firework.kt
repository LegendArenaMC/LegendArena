package legendarena.commands

//import legendarena.api.message.Message
//import legendarena.api.message.MessageType
import legendarena.api.utils.*
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
//import org.bukkit.entity.Player
import java.util.*

class Firework : CommandExecutor {

    private val cooldown = HashMap<UUID, Cooldown>()

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        /*if(sender !is Player) {
            sender.sendMessage("You can only use this command as a Player, sorry!")
            return true
        }

        if(!Rank.MEMBERPLUS.isRanked(sender)) {
            RankUtils.fancyNoPermissions(Rank.MEMBERPLUS, sender)
            return true
        }

        if(cooldown.containsKey(sender.uniqueId) && !cooldown[sender.uniqueId]!!.done()) {
            sender.sendMessage(cooldown[sender.uniqueId]!!.getTimeRemaining())
            return true
        }

        PluginUtils.shootFireworks(sender)
        Message(MessageType.TITLE).append("" + ChatColor.GREEN + "Woo, fireworks!").send(sender)
        //3 second cooldown
        cooldown.put(sender.uniqueId, Cooldown(3.0))
        return true*/
        sender.sendMessage("" + ChatColor.RED + "This doesn't work in 1.9. Sorry. I'm working on it. (well, I will eventually.) -OdinAir")
        return true
    }

}