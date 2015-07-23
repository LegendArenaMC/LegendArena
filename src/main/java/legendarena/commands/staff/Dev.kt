package legendarena.commands.staff

import legendapi.message.Message
import legendapi.utils.CalendarUtils
import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.VersionUtils
import legendarena.hub.JumpPad
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Dev : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(!Rank.FOUNDER.isRanked(sender))
            return false
        if(args.size() == 0) {
            //thanks kotlin for not liking sender.sendMessage([color] + [string]) (also ignore the change from blue to green with no var name change)
            val blue = "" + ChatColor.GREEN
            sender.sendMessage(ChatUtils.getFormattedHeader("Server Info"))
            sender.sendMessage(blue + "Free memory: " + humanReadableByteCount((Runtime.getRuntime().freeMemory() / 8), true))
            //ignore the horrible code to get used memory
            sender.sendMessage(blue + "Used memory: " + humanReadableByteCount(((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 8), true))
            sender.sendMessage(blue + "Max memory: " + humanReadableByteCount((Runtime.getRuntime().maxMemory() / 8), true))
            sender.sendMessage(blue + "Date: " + CalendarUtils().getDateString())
            sender.sendMessage(ChatUtils.getFormattedHeader("API/Library Versions"))
            sender.sendMessage(blue + "Kotlin version: " + VersionUtils.getVersion("Kotlin"))
            sender.sendMessage(blue + "API version: " + VersionUtils.getAPIVersion() + ", codenamed \"" + VersionUtils.getAPIVersionCodename() + "\"")
            return true
        } else if(args[0].equals("gc")) {
            sender.sendMessage("" + ChatColor.YELLOW + "Running garbage collector....")
            Message().append("" + ChatColor.YELLOW + "Staff member " + ChatColor.BLUE + sender.getName() + ChatColor.YELLOW + " is running the garbage collector...").broadcast(Rank.FOUNDER)
            System.gc()
            sender.sendMessage("" + ChatColor.GREEN + "Successfully ran garbage collector.")
            return true
        } else if(args[0].equals("jp")) {
            JumpPad.jump(sender as Player)
            return true
        }
        return false
    }

    public fun humanReadableByteCount(bytes: Long, si: Boolean): String {
        val unit = if (si) 1000 else 1024
        if(bytes < unit) return "" + bytes + " B"
        val exp = (Math.log(bytes.toDouble()) / Math.log(unit.toDouble())).toInt()
        val pre = "" + (if(si) "kMGTPE" else "KMGTPE").charAt(exp - 1) + (if(si) "" else "i")
        return java.lang.String.format("%.1f %sB", bytes / Math.pow(unit.toDouble(), exp.toDouble()), pre)
    }

}
