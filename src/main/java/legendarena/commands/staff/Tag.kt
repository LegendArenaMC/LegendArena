/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.commands.staff

import legendapi.exceptions.AreYouDrunkException
import legendapi.fanciful.FancyMessage
import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendapi.utils.StringUtils
import legendarena.hub.HubWarper
import legendarena.scoreboard.ScoreboardSystem
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Tag : CommandExecutor {

    private fun tagMsg(p: CommandSender) {

        var canFounder = false
        var canDev = false
        var canAdmin = false
        var canMod = false
        var canHelper = false
        var canVip = false
        var canMemberPlus = false

        if(Rank.FOUNDER.isRanked(p))
            canFounder = true

        if(Rank.DEV.isRanked(p))
            canDev = true

        if(Rank.ADMIN.isRanked(p))
            canAdmin = true

        if(Rank.MOD.isRanked(p))
            canMod = true

        if(Rank.HELPER.isRanked(p))
            canHelper = true

        if(Rank.VIP.isRanked(p))
            canVip = true

        if(Rank.MEMBERPLUS.isRanked(p))
            canMemberPlus = true

        var msg = FancyMessage("FOUNDER")
                    .command("/tag founder")
                    .color(if(canFounder) ChatColor.GREEN else ChatColor.RED)
                    .tooltip("Click to tag yourself as a " + Rank.FOUNDER.getNameColor() + "FOUNDER")
                .then(" " + ChatUtils.specialCharacters[3] + " ")
                    .color(ChatColor.GRAY)
                .then("DEV")
                    .command("/tag dev")
                    .color(if(canDev) ChatColor.GREEN else ChatColor.RED)
                    .tooltip("Click to tag yourself as a " + Rank.DEV.getNameColor() + "DEV")
                .then(" " + ChatUtils.specialCharacters[3] + " ")
                    .color(ChatColor.GRAY)
                .then("ADMIN")
                    .command("/tag admin")
                    .color(if(canAdmin) ChatColor.GREEN else ChatColor.RED)
                    .tooltip("Click to tag yourself as an " + Rank.ADMIN.getNameColor() + "ADMIN")
                .then(" " + ChatUtils.specialCharacters[3] + " ")
                    .color(ChatColor.GRAY)
                .then("MOD")
                    .command("/tag mod")
                    .color(if(canMod) ChatColor.GREEN else ChatColor.RED)
                    .tooltip("Click to tag yourself as a " + Rank.MOD.getNameColor() + "MOD")
                .then(" " + ChatUtils.specialCharacters[3] + " ")
                    .color(ChatColor.GRAY)
                .then("HELPER")
                    .command("/tag helper")
                    .color(if(canHelper) ChatColor.GREEN else ChatColor.RED)
                    .tooltip("Click to tag yourself as a " + Rank.HELPER.getNameColor() + "HELPER")
                .then(" " + ChatUtils.specialCharacters[3] + " ")
                    .color(ChatColor.GRAY)
                .then("VIP")
                    .command("/tag vip")
                    .color(if(canVip) ChatColor.GREEN else ChatColor.RED)
                    .tooltip("Click to tag yourself as a " + Rank.VIP.getNameColor() + "VIP")
                .then(" " + ChatUtils.specialCharacters[3] + " ")
                    .color(ChatColor.GRAY)
                .then("MEMBERPLUS")
                    .command("/tag memberplus")
                    .color(if(canMemberPlus) ChatColor.GREEN else ChatColor.RED)
                    .tooltip("Click to tag yourself as a " + Rank.MEMBERPLUS.getNameColor() + "MEMBER+")
                .then(" " + ChatUtils.specialCharacters[3] + " ")
                    .color(ChatColor.GRAY)
                .then("MEMBER")
                    .command("/tag member")
                    .color(ChatColor.GREEN)
                    .tooltip("Click to tag yourself as a " + Rank.MEMBER.getNameColor() + "MEMBER")

        msg.send(p)
    }

    private fun tagList(sender: CommandSender) {
        sender.sendMessage(ChatUtils.getFormattedHeader("Available Tags"))
        tagMsg(sender)
    }

    public fun setTag(p: Player, r: Rank) {
        RankUtils.setTag(p, r)
        ScoreboardSystem.setRank(p, r)
        p.sendMessage("" + ChatColor.GREEN + "Tagged yourself as a " + r.getNameColor() + r)
    }

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(sender !is Player) {
            sender.sendMessage("You can only use this command as a player! (plus, would it really make sense to tag the console as a different rank?)")
            return true
        }

        if(args.size() == 0) {
            tagList(sender)
            return true
        }

        if(args[0].equals("founder")) {
            try {
                setTag(sender, Rank.FOUNDER)
            } catch(ex: AreYouDrunkException) {
                sender.sendMessage("" + ChatColor.RED + "You can not tag yourself as that rank!")
            }
        } else if(args[0].equals("dev")) {
            try {
                setTag(sender, Rank.DEV)
            } catch(ex: AreYouDrunkException) {
                sender.sendMessage("" + ChatColor.RED + "You can not tag yourself as that rank!")
            }
        } else if(args[0].equals("admin")) {
            try {
                setTag(sender, Rank.ADMIN)
            } catch(ex: AreYouDrunkException) {
                sender.sendMessage("" + ChatColor.RED + "You can not tag yourself as that rank!")
            }
        } else if(args[0].equals("mod")) {
            try {
                setTag(sender, Rank.MOD)
            } catch(ex: AreYouDrunkException) {
                sender.sendMessage("" + ChatColor.RED + "You can not tag yourself as that rank!")
            }
        } else if(args[0].equals("helper")) {
            try {
                setTag(sender, Rank.HELPER)
            } catch(ex: AreYouDrunkException) {
                sender.sendMessage("" + ChatColor.RED + "You can not tag yourself as that rank!")
            }
        } else if(args[0].equals("vip")) {
            try {
                setTag(sender, Rank.VIP)
            } catch(ex: AreYouDrunkException) {
                sender.sendMessage("" + ChatColor.RED + "You can not tag yourself as that rank!")
            }
        } else if(args[0].equals("memberplus")) {
            try {
                setTag(sender, Rank.MEMBERPLUS)
            } catch(ex: AreYouDrunkException) {
                sender.sendMessage("" + ChatColor.RED + "You can not tag yourself as that rank!")
            }
        } else if(args[0].equals("member")) {
            setTag(sender, Rank.MEMBER) //almost no way that this will throw an error so just don't have a try/catch
        }

        else if(args[0].equals("off")) {
            RankUtils.clearTag(sender)
            sender.sendMessage("" + ChatColor.GREEN + "Cleared tag.")
        }

        else if(args[0].equals("id")) {
            if(args.size() == 1) {
                sender.sendMessage("" + ChatColor.RED + "Usage: /tag id <rankid>")
                return true
            }

            try {
                Integer.getInteger(args[1])
            } catch(ex: Exception) {
                sender.sendMessage("" + ChatColor.RED + "\"" + args[1] + "\" is not an integer, thus is not a rank ID!")
                return true
            }

            if(RankUtils.fromRankId(Integer.getInteger(args[1])) == null) {
                sender.sendMessage("" + ChatColor.RED + "\"" + args[1] + "\" is not a rank ID!")
                return true
            }

            try {
                RankUtils.setTag(sender, RankUtils.fromRankId(Integer.getInteger(args[1])))
                sender.sendMessage("" + ChatColor.GREEN + "Tagged yourself as a " + RankUtils.fromRankId(Integer.getInteger(args[1])).getNameColor() + RankUtils.fromRankId(Integer.getInteger(args[1])))
            } catch(ex: AreYouDrunkException) {
                sender.sendMessage("" + ChatColor.RED + "You can not tag yourself as that rank!")
            }
        }

        else
            tagList(sender)

        return true
    }

}