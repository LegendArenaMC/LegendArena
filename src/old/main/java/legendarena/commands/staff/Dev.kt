package legendarena.commands.staff

import legendapi.emeralds.EmeraldsCore
import legendapi.message.Message
import legendapi.utils.*
import legendarena.hub.JumpPad
import legendarena.scoreboard.ScoreboardSystem
import legendarena.staffutils.VanishUtils
import org.apache.commons.io.FileUtils
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Dev : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(!Rank.DEV.isRanked(sender)) {
            RankUtils.fancyNoPermissions(Rank.DEV, sender as Player)
            return true
        }
        if(args.size() == 0) {
            var msg = Message()

            msg.append(ChatUtils.getFormattedHeader("Operating System") + "\n")
            msg.append(ChatUtils.getFormattedMsg("Architecture", System.getProperty("os.arch")) + "\n")
            msg.append(ChatUtils.getFormattedMsg("OS Name", System.getProperty("os.name")) + "\n")
            msg.append(ChatUtils.getFormattedMsg("Version", System.getProperty("os.version")) + "\n")
            msg.append(ChatUtils.getFormattedMsg("Date [UK Format]", CalendarUtils().getDayString(false) + " " + CalendarUtils().getDateString()) + "\n")
            msg.append(ChatUtils.getFormattedMsg("Date [US Format]", CalendarUtils().getDayString(false, CalendarUtils.DateLocale.US) + " " + CalendarUtils().getDateString(CalendarUtils.DateLocale.US)) + "\n")
            msg.append(ChatUtils.getFormattedHeader("System") + "\n")
            msg.append(ChatUtils.getFormattedMsg("Using", "" + getUsedMemoryPercentage() + "% memory") + "\n")
            msg.append(ChatUtils.getFormattedMsg("Available proccessors", Runtime.getRuntime().availableProcessors().toString()) + "\n")
            msg.append(ChatUtils.getFormattedHeader("API/Library Versions") + "\n")
            msg.append(ChatUtils.getFormattedMsg("Kotlin version", VersionUtils.getVersion("Kotlin")) + "\n")
            msg.append(ChatUtils.getFormattedMsg("API version", VersionUtils.getAPIVersion() + ", codenamed \"" + VersionUtils.getAPIVersionCodename() + "\"") + " (backend ID: " + VersionUtils.getAPIVersionInt() + ")\n")

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
        } else if(args[0].equals("cooldown")) {
            var c = Cooldown(999.0)
            sender.sendMessage(c.getTimeRemaining())
            return true
        }

        else if(args[0].equals("testnoperms")) {
            RankUtils.fancyNoPermissions(Rank.MEMBERPLUS, sender as Player)
            RankUtils.fancyNoPermissions(Rank.VIP, sender)
            RankUtils.fancyNoPermissions(Rank.HELPER, sender)
            RankUtils.fancyNoPermissions(Rank.MOD, sender)
            RankUtils.fancyNoPermissions(Rank.ADMIN, sender)
            RankUtils.fancyNoPermissions(Rank.DEV, sender)
            RankUtils.fancyNoPermissions(Rank.FOUNDER, sender)
        }
        return true
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
