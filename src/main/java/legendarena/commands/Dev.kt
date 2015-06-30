package legendarena.commands

import legendapi.message.Message
import legendapi.utils.CalendarUtils
import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Dev : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(!Rank.isRanked(sender, Rank.FOUNDER))
            return false
        //thanks kotlin for not liking function([color] + [string])
        val blue = "" + ChatColor.BLUE
        sender.sendMessage(ChatUtils.getFormattedHeader("Server Info"))
        sender.sendMessage(blue + "Free memory: " + Runtime.getRuntime().freeMemory() + " bits")
        sender.sendMessage(blue + "Used memory: " + Runtime.getRuntime().totalMemory() + " bits")
        sender.sendMessage(blue + "Max memory: " + Runtime.getRuntime().maxMemory() + " bits")
        sender.sendMessage(blue + "Day: " + CalendarUtils.getDate().getDay())
        sender.sendMessage(blue + "Month: " + CalendarUtils.getDate().getMonth() + " (" + CalendarUtils.parseMonth() + ")")
        sender.sendMessage(ChatUtils.getFormattedHeader("Server Info"))
        //sender.sendMessage(" ");
        //sender.sendMessage(ChatColor.YELLOW + "--.{ Debug Info }.--");
        //sender.sendMessage(ChatColor.BLUE + "Particle amount: " + ParticleCore.amountOfActiveParticles((Player) sender));
        //sender.sendMessage(ChatColor.YELLOW + "--.{ Debug Info }.--");
        return true
    }
}
