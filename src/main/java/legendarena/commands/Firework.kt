package legendarena.commands

import legendapi.emeralds.EmeraldsCore
import legendapi.utils.ChatUtils
import legendapi.utils.Cooldown
import legendapi.utils.PluginUtils
import legendapi.utils.Rank
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class Firework : CommandExecutor {

    private val cooldown = HashMap<UUID, Cooldown>()

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(sender !is Player) {
            sender.sendMessage("Sorry - you can only do this as a player :(")
            return true
        }
        if(!Rank.isRanked(sender, Rank.MEMBERPLUS)) {
            if(EmeraldsCore.getEmeralds(sender) < 15) {
                sender.sendMessage(ChatUtils.Messages.errorMsg + "You don't have enough emeralds to use this! (you require 15 emeralds to use this as a member)")
                return true
            }
            EmeraldsCore.removeEmeralds(sender, 15, false)
        }
        if(cooldown.containsKey(sender.getUniqueId()) && !cooldown.get(sender.getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(sender.getUniqueId()).getTimeRemaining())
            return true
        }
        PluginUtils.shootFireworks(sender)
        //3 second cooldown
        cooldown.put(sender.getUniqueId(), Cooldown(3.0))
        return true
    }

}