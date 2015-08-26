/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.commands.staff.punish

import legendapi.fanciful.FancyMessage
import legendapi.message.Message
import legendapi.utils.ChatUtils

import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendapi.utils.StaffPlayer
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Ban : CommandExecutor {

    override fun onCommand(sender: CommandSender, p1: Command?, p2: String?, args: Array<out String>): Boolean {
        if(!Rank.ADMIN.isRanked(sender)) {
            RankUtils.fancyNoPermissions(Rank.ADMIN, sender)
            return true
        }
        if(args.size() == 0) {
            ChatUtils.fancyMsg("/ban <player>", "Ban a player from the network.")
            return true
        }

        var staff = StaffPlayer(sender as Player)
        var reason = ChatUtils.formatCast(args, 0)

        run(args[0], staff, reason)
        return true
    }

    public fun run(p: String, staff: StaffPlayer, reason: String) {
        var target = Bukkit.getPlayer(p)
        if(target != null)
            target.kickPlayer(ChatUtils.getCustomMsg("Punish") + "You were banned by " + staff.getFormattedName() + ChatColor.BLUE + " for " + reason)

        var broadcastMsg = FancyMessage(ChatUtils.getCustomMsg("Punish"))
                .then("Player ")
                    .color(ChatColor.BLUE)
                .then(p)
                    .color(ChatColor.YELLOW)
                .then(" has been banned by ")
                    .color(ChatColor.BLUE)
                .then(staff.getName())
                    .color(staff.getRank().getNameColor())
                .then(" for \"")
                    .color(ChatColor.BLUE)
                .then(reason)
                    .color(ChatColor.YELLOW)
                .then("\".")
                    .color(ChatColor.BLUE)

                //.then(p + ChatColor.BLUE + " has been banned by " + staff.getFormattedName() + " for \"" + ChatColor.YELLOW + reason + ChatColor.BLUE + "\"")

        Message().append(broadcastMsg).broadcast()
    }

}
