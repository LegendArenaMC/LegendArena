package legendarena.commands.staff

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendapi.utils.StringUtils
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
        if (!Rank.MOD.isRanked(sender)) {
            sender.sendMessage(RankUtils.noPermissions(Rank.MOD))
            return
        }
        if(args.size() == 0) {
            sender.sendMessage(ChatUtils.getFormattedHeader("MOTD Randomizer"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("Amount of MOTDs", StringUtils.valueOf(MOTDUtils.getAmountOfMOTDs())))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("Current notice", "\"" + MOTDUtils.getNotice() + "\""))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/motd notice [notice]", "Sets the MOTD notice"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/motd lastgiven", "Get the last given randomized MOTD."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/motd setmotd <[--nodecor] New MOTD|--clear>", "Sets or clears the MOTD override."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/motd getmotd", "Get the currently showing MOTD."))
        } else if(args[0].equals("lastgiven")) {
            sender.sendMessage(ChatUtils.getCustomMsg("MOTD") + MOTDUtils.getLastMOTDGiven())
        } else if(args[0].equals("notice")) {
            if(!Rank.ADMIN.isRanked(sender)) {
                sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
                return
            }
            if(args.size() == 1) {
                sender.sendMessage("" + ChatColor.LIGHT_PURPLE + "Current notice: " + ChatColor.GREEN + MOTDUtils.getNotice())
            } else {
                ChatSystem.notice("Staff member " + sender.getName() + " has changed the MOTD notice from \"" + MOTDUtils.getNotice() + "\" to \"" + ChatUtils.formatCast(args, 0) + "\"")
                MOTDUtils.setNotice(ChatUtils.formatCast(args, 0))
            }
        } else if(args[0].equals("setmotd")) {
            if(!Rank.ADMIN.isRanked(sender)) {
                sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
                return
            }
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