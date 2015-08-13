package legendarena.commands.staff

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
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
            sender.sendMessage("Sorry - you can only do this as a player :(")
            return true //Do nothing if it's not a player
        }
        if(!Rank.MOD.isRanked(sender)) {
            RankUtils.fancyNoPermissions(Rank.MOD, sender)
            return true
        }
        if(args.size() == 0) {
            if(HubWarper.isExempt(sender.getUniqueId())) {
                HubWarper.toggleExemption(sender.getUniqueId())
                sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Removed from gadgets exempt list.")
            } else {
                HubWarper.toggleExemption(sender.getUniqueId())
                sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Added to gadgets exempt list.")
            }
        } else {
            if(Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(ChatUtils.getCustomMsg("" + ChatColor.RED + "Error") + "Player \"" + args[0] + "\" was not found.")
            } else {
                val p = Bukkit.getPlayer(args[0])
                val u = p.getUniqueId()
                if(HubWarper.isExempt(u)) {
                    HubWarper.toggleExemption(u)
                    sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Removed player " + p.getName() + " from gadgets exemption list.")
                } else {
                    HubWarper.toggleExemption(u)
                    sender.sendMessage(ChatUtils.getCustomMsg("Gadgets") + "Added player " + p.getName() + " to gadgets exemption list.")
                }
            }
        }
        return true
    }

}