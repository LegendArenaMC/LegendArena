package legendarena.commands

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendarena.chat.ChatSystem
import legendarena.motd.ListType
import legendarena.motd.MOTDUtils
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class MOTDTools : CommandExecutor {

    override fun onCommand(sender: CommandSender, p1: Command?, p2: String?, args: Array<out String>): Boolean {
        this.run(sender, args as Array<String>)
        return true
    }

    fun run(sender: CommandSender, args: Array<String>) {
        if (!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD))
            return
        }
        val length = MOTDUtils.getList().get(ListType.RANDOM).size() + MOTDUtils.getList().get(ListType.SONG).size() + MOTDUtils.getList().get(ListType.REFERENCE).size()
        if(args.size() == 0) {
            sender.sendMessage(ChatUtils.getFormattedHeader("MOTD Randomizer"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("Amount of MOTDs", java.lang.String.valueOf(length)))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("Current notice", "\"" + MOTDUtils.getNotice() + "\""))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/staff motd notice [notice]", "Sets the MOTD notice"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/motd setmotd <[--nodecor] New MOTD|--clear>", "Sets or clears the MOTD."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/motd getmotd", "Get the currently showing MOTD."))
        } else if(args[0].equals("list")) {
            val rawList = MOTDUtils.getList()
            val rawSongs = rawList.get(ListType.SONG)
            val rawReferences = rawList.get(ListType.REFERENCE)
            val rawRandom = rawList.get(ListType.RANDOM)
            var finishedList = ChatUtils.getCustomMsg("MOTD")
            for(add in rawSongs)
                finishedList += (if (finishedList == ChatUtils.getCustomMsg("MOTD")) "\"" + add + "\"" else ", \"" + add + "\"")
            for(add in rawReferences)
                finishedList += (if (finishedList == ChatUtils.getCustomMsg("MOTD")) "\"" + add + "\"" else ", \"" + add + "\"")
            for(add in rawRandom)
                finishedList += (if (finishedList == ChatUtils.getCustomMsg("MOTD")) "\"" + add + "\"" else ", \"" + add + "\"")
            sender.sendMessage(finishedList)
        } else if(args[0].equals("notice")) {
            if(args.size() == 1) {
                sender.sendMessage("" + ChatColor.LIGHT_PURPLE + "Current notice: " + ChatColor.GREEN + MOTDUtils.getNotice())
            } else {
                if(!Rank.isRanked(sender, Rank.ADMIN)) {
                    sender.sendMessage(Rank.noPermissions(Rank.ADMIN))
                    return
                }
                ChatSystem.notice("Staff member " + sender.getName() + " has changed the MOTD notice from \"" + MOTDUtils.getNotice() + "\" to \"" + ChatUtils.formatCast(args, 0, 1) + "\"")
                MOTDUtils.setNotice(ChatUtils.formatCast(args, 0))
            }
        } else if(args[0].equals("setmotd")) {
            if(args.size() == 1) {
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/motd setmotd <[--nodecor] New MOTD|--clear>", "Sets or clears the MOTD."))
                return
            }
            if(args.size() == 2) {
                if(args[1].equals("--nodecor")) {
                    sender.sendMessage("" + ChatColor.RED + "You can't make the MOTD blank...")
                } else if(args[1].equals("--clear")) {
                    MOTDUtils.override(false, "")
                    sender.sendMessage(ChatUtils.getCustomMsg("MOTD") + "Cleared custom MOTD override.")
                } else {
                    MOTDUtils.override(false, args[1])
                    sender.sendMessage(ChatUtils.getCustomMsg("MOTD") + "MOTD changed succssfully.")
                }
            } else {
                if(args[1].equals("--nodecor")) {
                    var cast = ChatUtils.formatCast(args, 0, 1)
                    MOTDUtils.override(true, cast)
                    sender.sendMessage(ChatUtils.getCustomMsg("MOTD") + "MOTD changed succssfully.")
                } else {
                    var cast = ChatUtils.formatCast(args, 0)
                    MOTDUtils.override(true, cast)
                    sender.sendMessage(ChatUtils.getCustomMsg("MOTD") + "MOTD changed succssfully.")
                }
            }
        } else if(args[0].equals("getmotd")) {
            sender.sendMessage(MOTDUtils.getBuiltMOTD())
        }
    }

}