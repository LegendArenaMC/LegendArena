package legendarena.commands

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendapi.utils.StringUtils
import legendarena.chat.ChatSystem
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Shadow : CommandExecutor {

    override fun onCommand(sender: CommandSender, p1: Command?, p2: String?, args: Array<out String>?): Boolean {
        if(!Rank.ADMIN.isRanked(sender)) {
            sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
            return true
        }

        if(args!!.size() == 0) {
            //they specified no arguments, return a help message
            sender.sendMessage(ChatUtils.getFormattedHeader("Shadow Utils"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/shadow mute <player>", "Shadow mute a player."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/shadow list", "List currently shadow muted players."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/shadow ignore", "Ignore shadow muted players messages. [WIP]"))
            return true
        }

        if(StringUtils.toLower(args[0]).equals("mute")) {

            if(args.size() == 1) {
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/shadow mute <player>", "Shadow mute a player."))
                return true
            }

            if(Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage("" + ChatColor.RED + "We didn't find a player with the name \"" + args[1] + "\". Sorry, we really tried.")
                return true
            }

            ChatSystem.toggleShadowMute(Bukkit.getPlayer(args[1]), sender.getName())

        }

        return true
    }

}