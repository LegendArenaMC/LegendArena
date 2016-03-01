/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.commands

import legendarena.api.user.User
import legendarena.api.utils.ChatUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.StringUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import java.util.*

public class Report : CommandExecutor {

    override fun onCommand(sender: CommandSender, p1: Command?, p2: String?, args: Array<out String>): Boolean {
        if(StringUtils.getSize(args) == 0) {
            help(sender)
            return true
        }

        if(Rank.MOD.isRanked(sender)) {
            if(args[0].equals("check")) {
                if(StringUtils.getSize(args) == 1) {
                    ChatUtils.fancyHelpSuggestMsg("/report check <player>", "Get the report list for a player", "report check ", true).send(sender)
                    return true
                }
                if(Bukkit.getPlayer(args[1]) == null) {
                    sender.sendMessage("" + ChatColor.RED + "That player was not found!")
                    return true
                }
                var user = User(Bukkit.getPlayer(args[1]))
                var reportList = user.getReports()
                var reportId = 1
                if(!user.hasBeenReported()) {
                    sender.sendMessage(ChatUtils.getCustomMsg("Reports") + "There are no reports for player " + ChatColor.YELLOW + Bukkit.getPlayer(args[1]).getName() + ChatColor.GREEN + "!")
                    return true
                }
                sender.sendMessage(ChatUtils.getFormattedHeader("Reports for player " + ChatColor.YELLOW + Bukkit.getPlayer(args[1]).getName() + ChatColor.GRAY + " (" + user.getReportAmount() + ")"))
                var l = HashMap<String, Int>()
                for(report in reportList) {
                    if(l.containsKey(report))
                        l.put(report, l[report]!! + 1)
                    else
                        l.put(report, 1)
                }
                for(rep in l.keys) {
                    var i = "";
                    if(l[rep]!! > 1) i = " " + ChatColor.GRAY + "(x${l[rep]})"
                    sender.sendMessage("" + ChatColor.GRAY + "(#" + reportId + ") " + ChatColor.RED + rep + i)
                    reportId++
                }
                return true
            } else if(args[0].equals("clear")) {
                if(StringUtils.getSize(args) == 1) {
                    ChatUtils.fancyHelpSuggestMsg("/report clear <player>", "Clear the reports for a player", "report clear ", true).send(sender)
                    return true
                }
                if(Bukkit.getPlayer(args[1]) == null) {
                    sender.sendMessage("" + ChatColor.RED + "That player was not found!")
                    return true
                }
                User(Bukkit.getPlayer(args[1])).clearReports()
                sender.sendMessage(ChatUtils.getCustomMsg("Reports") + "Cleared reports for player " + ChatColor.YELLOW + Bukkit.getPlayer(args[1]).getName() + ChatColor.GREEN + ".")
                return true
            } else if(args[0].equals("amount")) {
                if(StringUtils.getSize(args) == 1) {
                    ChatUtils.fancyHelpSuggestMsg("/report amount <player>", "Show how many reports the specified player has", "report amount ", true).send(sender)
                    return true
                }
                if(Bukkit.getPlayer(args[1]) == null) {
                    sender.sendMessage("" + ChatColor.RED + "That player was not found!")
                    return true
                }
                var user = User(Bukkit.getPlayer(args[1]))
                sender.sendMessage(ChatUtils.getFormattedMsg("Report amount for " + ChatColor.YELLOW + user.getPlayer().getName(), "" + user.getReportAmount()))
                return true
            }
        }

        if(StringUtils.getSize(args) == 1) {
            ChatUtils.fancyHelpSuggestMsg("/report <player> <reason>", "Report a player", "report ", true).send(sender)
            return true
        }

        var reportMsg = ChatUtils.formatCast(args, 0)
        var report: User?

        try {
            report = User(Bukkit.getPlayer(args[0]))
        } catch(ex: Exception) {
            sender.sendMessage(ChatUtils.getCustomMsg("Reports", ChatColor.YELLOW, ChatColor.RED) + "Player \"" + ChatColor.YELLOW + args[0] + ChatColor.RED + "\" was not found!")
            return true
        }

        if(report!!.getDisplayRank().isStaff() && !Rank.MOD.isRanked(sender)) {
            sender.sendMessage(ChatUtils.getCustomMsg("Reports") + "You must think you're funny, eh?")
            return true
        }

        report.report(reportMsg)
        sender.sendMessage(ChatUtils.getCustomMsg("Reports") + "You reported player " + ChatColor.YELLOW + report.getPlayer().getName() + ChatColor.GREEN + " for reason " + ChatColor.YELLOW + reportMsg + ChatColor.GREEN + ".")

        return true
    }

    private fun help(sender: CommandSender) {
        if(Rank.MOD.isRanked(sender)) {
            ChatUtils.fancyHelpSuggestMsg("/report check <player>", "Get the report list for a player", "report check ", true).send(sender)
            ChatUtils.fancyHelpSuggestMsg("/report clear <player>", "Clear the reports for a player", "report clear ", true).send(sender)
            ChatUtils.fancyHelpSuggestMsg("/report amount <player>", "Show how many reports the specified player has", "report amount ", true).send(sender)
        }
        ChatUtils.fancyHelpSuggestMsg("/report <player> <reason>", "Report a player", "report ", true).send(sender)
    }

}