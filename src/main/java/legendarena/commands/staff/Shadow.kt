package legendarena.commands.staff

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendapi.utils.StringUtils
import legendarena.chat.ChatSystem
import legendarena.chat.ShadowMuteNotAllowedException
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Shadow : CommandExecutor {

    override fun onCommand(sender: CommandSender, p1: Command?, p2: String?, args: Array<out String>): Boolean {
        if(!Rank.ADMIN.isRanked(sender)) {
            sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
            return true
        }

        if(args.size() == 0) {
            help(sender)
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

            try {
                ChatSystem.toggleShadowMute(Bukkit.getPlayer(args[1]), sender.getName())
            } catch(ex: ShadowMuteNotAllowedException) {
                sender.sendMessage("" + ChatColor.RED + "Shadow mutes are currently disallowed!")
            }

        } else if(StringUtils.toLower(args[0]).equals("ignore")) {
            var e = ChatSystem.toggleShadowMuteNotice(sender as Player)
            if(e)
                sender.sendMessage(ChatUtils.getCustomMsg("Chat System") + "Now ignoring shadow mutes.")
            else
                sender.sendMessage(ChatUtils.getCustomMsg("Chat System") + "No longer ignoring shadow mutes.")
        }

        else help(sender)

        return true
    }

    internal fun help(sender: CommandSender) {
        sender.sendMessage(ChatUtils.getFormattedHeader("Shadow Utils"))
        sender.sendMessage(ChatUtils.getFormattedHelpMsg("/shadow mute <player>", "Shadow mute a player."))
        //sender.sendMessage(ChatUtils.getFormattedHelpMsg("/shadow list", "List currently shadow muted players."))
        sender.sendMessage(ChatUtils.getFormattedHelpMsg("/shadow ignore", "Ignore shadow muted players messages. [WIP]"))
    }

}