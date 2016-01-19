package legendarena.commands.staff

import legendarena.api.utils.ChatUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.RankUtils
import legendarena.api.utils.StringUtils
import legendarena.hub.HubWarper
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Gadgets : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(sender !is Player) {
            sender.sendMessage("You can only use this command as a Player, sorry!")
            return true //Do nothing if it's not a player
        }
        if(!Rank.MOD.isRanked(sender)) {
            RankUtils.fancyNoPermissions(Rank.MOD, sender)
            return true
        }
        if(StringUtils.getSize(args) == 0) {
            if(HubWarper.isExempt(sender.uniqueId)) {
                HubWarper.toggleExemption(sender.uniqueId)
                sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Removed from gadgets exempt list.")
            } else {
                HubWarper.toggleExemption(sender.uniqueId)
                sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Added to gadgets exempt list.")
            }
        } else {
            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(ChatUtils.getCustomMsg("" + ChatColor.RED + "Error") + "Player \"" + args[0] + "\" was not found.")
            } else {
                val p = Bukkit.getPlayer(args[0])
                val u = p.uniqueId
                if(HubWarper.isExempt(u)) {
                    HubWarper.toggleExemption(u)
                    sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Removed player " + p.name + " from gadgets exemption list.")
                } else {
                    HubWarper.toggleExemption(u)
                    sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Added player " + p.name + " to gadgets exemption list.")
                }
            }
        }
        return true
    }

}