package legendarena.commands.staff

import legendapi.emeralds.EmeraldsCore
import legendapi.message.Message
import legendapi.utils.CalendarUtils
import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.VersionUtils
import legendarena.hub.JumpPad
import org.apache.commons.io.FileUtils
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Dev : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(!Rank.DEV.isRanked(sender))
            return false //pretend the command is broken/not registered
        if(args.size() == 0) {
            //thanks kotlin for not liking sender.sendMessage([color] + [string]) (also ignore the change from blue to green with no var name change)
            var msg = Message()

            msg.append(ChatUtils.getFormattedHeader("Server Info") + "\n")
            msg.append(getReadableMemory().toString())
            msg.append(ChatUtils.getFormattedMsg("Date", CalendarUtils().getDateString()) + "\n")

            msg.send(sender)
            return true
        } else if(args[0].equals("gc")) {
            sender.sendMessage("" + ChatColor.YELLOW + "Running garbage collector....")
            Message().append("" + ChatColor.YELLOW + "Staff member " + ChatColor.BLUE + sender.getName() + ChatColor.YELLOW + " is running the garbage collector...").broadcast(Rank.ADMIN)
            System.gc()
            sender.sendMessage("" + ChatColor.GREEN + "Successfully ran garbage collector.")
            return true
        } else if(args[0].equals("jp")) {
            JumpPad.jump(sender as Player)
            return true
        } else if(args[0].equals("resetemeralds")) {
            EmeraldsCore().resetEmeralds(sender as Player, false, "Dev Command Manual Reset")
            sender.sendMessage("" + ChatColor.GREEN + "Emeralds amount reset.")
        }

        else if(args[0].equals("moreinfo")) {
            var msg = Message()

            msg.append(ChatUtils.getFormattedHeader("Operating System") + "\n")
            msg.append(ChatUtils.getFormattedMsg("Architecture", System.getProperty("os.arch")) + "\n")
            msg.append(ChatUtils.getFormattedMsg("OS Name", System.getProperty("os.name")) + "\n")
            msg.append(ChatUtils.getFormattedMsg("Version", System.getProperty("os.version")) + "\n")
            msg.append(ChatUtils.getFormattedHeader("System") + "\n")
            msg.append(getReadableMemory().toString())
            msg.append(ChatUtils.getFormattedMsg("Available proccessors", Runtime.getRuntime().availableProcessors().toString()) + "\n")
            msg.append(ChatUtils.getFormattedHeader("API/Library Versions") + "\n")
            msg.append(ChatUtils.getFormattedMsg("Kotlin version", VersionUtils.getVersion("KotlinLoader")) + "\n")
            msg.append(ChatUtils.getFormattedMsg("API version", VersionUtils.getAPIVersion() + ", codenamed \"" + VersionUtils.getAPIVersionCodename() + "\"") + "\n")

            msg.send(sender)
        }
        return true
    }

    public fun getReadableMemory(): Message {
        var msg = Message()

        msg.append(ChatUtils.getFormattedMsg("Free memory", FileUtils.byteCountToDisplaySize(Math.round(getFreeMemory()).toLong())) + "\n")
        msg.append(ChatUtils.getFormattedMsg("Max memory", FileUtils.byteCountToDisplaySize(Math.round(getMaxMemory()).toLong())) + "\n")
        msg.append(ChatUtils.getFormattedMsg("Used memory", FileUtils.byteCountToDisplaySize(Math.round(getUsedMemory()).toLong()) + " (" + getUsedMemoryPercentage() + "%)") + "\n")

        //msg.append(blue + "Free memory: " + FileUtils.byteCountToDisplaySize(Math.round(getFreeMemory()).toLong()))
        //msg.append(blue + "Max memory: " + FileUtils.byteCountToDisplaySize(Math.round(getMaxMemory()).toLong()))
        //msg.append(blue + "Used memory: " + FileUtils.byteCountToDisplaySize(Math.round(getUsedMemory()).toLong()) + " (" + getUsedMemoryPercentage() + "%)")

        return msg
    }

    public fun getMaxMemory(): Double {
        return Runtime.getRuntime().maxMemory().toDouble()
    }

    public fun getTotalMemory(): Double {
        return Runtime.getRuntime().totalMemory().toDouble()
    }

    public fun getFreeMemory(): Double {
        return Runtime.getRuntime().freeMemory().toDouble()
    }

    public fun getUsedMemory(): Double {
        return (getTotalMemory() - getFreeMemory()).toDouble()
    }

    public fun getUsedMemoryPercentage(): Double {
        val untruncated = ((getUsedMemory() / getMaxMemory()) * 100L)
        return Math.round(untruncated * 100).toDouble() / 100 //2.D.P Precision
    }

}
