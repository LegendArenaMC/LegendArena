package legendarena.commands.staff

import legendarena.api.fanciful.FancyMessage
import legendarena.api.message.Message
import legendarena.api.message.MessageType
import legendarena.api.utils.ChatUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.RankUtils
import legendarena.api.utils.StringUtils
import legendarena.chat.Channel
import legendarena.chat.ChatSystem
import legendarena.chat.ChatSystemUtils
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Chat : CommandExecutor {

    internal fun getChannelColor(c: Channel, p: CommandSender): ChatColor {
        return if(c.getRank().isRanked(p)) ChatColor.GREEN else ChatColor.RED
    }

    private fun run(sender: CommandSender, args: Array<String>) {
        if(StringUtils.getSize(args) == 0) {
            help(sender)
            return
        }

        if(args[0].equals("off")) {
            Message().append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "GLOBAL" + ChatColor.GREEN + " chat.").send(sender as Player)
            ChatSystem.remove(sender)
        } else if(args[0].equals("alert")) {
            if(!Rank.MOD.isRanked(sender as Player)) {
                RankUtils.fancyNoPermissions(Rank.MOD, sender)
                return
            }
            if(StringUtils.getSize(args) == 1) {
                Message().append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "ALERT" + ChatColor.GREEN + " chat.").send(sender)
                ChatSystem.add(sender, Channel.ALERT)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender
                val oldChannel = ChatSystem.getChannel(p)
                ChatSystem.add(p, Channel.ALERT)
                ChatSystem.msg(p, cast)
                if(oldChannel == Channel.GLOBAL)
                    ChatSystem.remove(p)
                else
                    ChatSystem.add(p, oldChannel)
            }
        } else if(args[0].equals("admin")) {
            if(!Rank.ADMIN.isRanked(sender as Player)) {
                RankUtils.fancyNoPermissions(Rank.ADMIN, sender)
                return
            }
            if(StringUtils.getSize(args) == 1) {
                Message().append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "ADMIN" + ChatColor.GREEN + " chat.").send(sender)
                ChatSystem.add(sender, Channel.ADMIN)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender
                val oldChannel = ChatSystem.getChannel(p)
                ChatSystem.add(p, Channel.ADMIN)
                ChatSystem.msg(p, cast)
                if(oldChannel == Channel.GLOBAL)
                    ChatSystem.remove(p)
                else
                    ChatSystem.add(p, oldChannel)
            }
        } else if(args[0].equals("staff")) {
            if(!Rank.HELPER.isRanked(sender)) {
                RankUtils.fancyNoPermissions(Rank.HELPER, sender)
                return
            }
            if(StringUtils.getSize(args) == 1) {
                Message().append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "STAFF" + ChatColor.GREEN + " chat.").send(sender as Player)
                ChatSystem.add(sender, Channel.STAFF)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender as Player
                val oldChannel = ChatSystem.getChannel(p)
                ChatSystem.add(p, Channel.STAFF)
                ChatSystem.msg(p, cast)
                if(oldChannel == Channel.GLOBAL)
                    ChatSystem.remove(p)
                else
                    ChatSystem.add(p, oldChannel)
            }
        } else if(args[0].equals("dev")) {
            if(!Rank.DEV.isRanked(sender as Player)) {
                RankUtils.fancyNoPermissions(Rank.DEV, sender)
                return
            }
            if(StringUtils.getSize(args) == 1) {
                Message().append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "DEV" + ChatColor.GREEN + " chat.").send(sender)
                ChatSystem.add(sender, Channel.DEV)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender
                val oldChannel = ChatSystem.getChannel(p)
                ChatSystem.add(p, Channel.DEV)
                ChatSystem.msg(p, cast)
                if(oldChannel == Channel.GLOBAL)
                    ChatSystem.remove(p)
                else
                    ChatSystem.add(p, oldChannel)
            }
        }

        else if(args[0].equals("manage")) {
            if(!Rank.ADMIN.isRanked(sender as Player)) {
                RankUtils.fancyNoPermissions(Rank.ADMIN, sender)
                return
            }

            if(StringUtils.getSize(args) == 1) {
                sender.sendMessage(ChatUtils.getFormattedHeader("Chat Settings"))
                ChatUtils.fancyHelpMsg("/chat manage globalmute", "Toggle chat global mute", "chat manage globalmute", true).send(sender)
                ChatUtils.fancyHelpMsg("/chat manage allowsmute", "Toggle allowing of shadow mutes", "chat manage allowsmute", true).send(sender)
                return
            }

            if(args[1].equals("allowsmute"))
                ChatSystem.allowShadowMute(!ChatSystem.isShadowMuteAllowed())

            else if(args[1].equals("globalmute")) {
                if(ChatSystem.isChatMuted())
                    ChatSystem.setGlobalMute(false)
                else
                    ChatSystem.setGlobalMute(true)
            }
        }

        else
            help(sender)
    }

    private fun send(sender: CommandSender, c: Channel, cmd: String) {
        if(ChatSystem.getChannel(sender as Player) == c)
            FancyMessage("* ")
                    .color(ChatColor.YELLOW)
                    .then(c.name.toString())
                    .color(ChatColor.GREEN)
                    .command(cmd)
                    .tooltip("" + ChatColor.YELLOW + "Click to join the " + ChatSystem.getChannelName(c) + ChatColor.YELLOW + " channel")
                    .send(sender)
        else
            FancyMessage("  " + c.name.toString())
                    .color(ChatColor.GREEN)
                    .command(cmd)
                    .tooltip("" + ChatColor.YELLOW + "Click to join the " + ChatSystem.getChannelName(c) + ChatColor.YELLOW + " channel")
                    .send(sender)
    }

    internal fun help(sender: CommandSender) {
        sender.sendMessage(ChatUtils.getFormattedHeader("Available Channels"))
        send(sender, Channel.GLOBAL, "/c off")
        send(sender, Channel.STAFF, "/c staff")
        send(sender, Channel.ALERT, "/c alert")
        send(sender, Channel.ADMIN, "/c admin")
        send(sender, Channel.DEV, "/c dev")

        sender.sendMessage("\n")

        FancyMessage("${ChatUtils.specialCharacters[1]} ")
                    .color(ChatColor.DARK_GRAY)
                    .then("Manage Settings")
                    .color(getChannelColor(Channel.ADMIN, sender))
                    .suggest("/c manage")
                    .tooltip("" + ChatColor.YELLOW + "Click to change settings")
                .send(sender)

        FancyMessage("Current Channel")
                    .color(ChatColor.GREEN)
                .then(" " + ChatUtils.specialCharacters[1] + " ")
                    .color(ChatColor.DARK_GRAY)
                .then(ChatSystem.getChannel(sender as Player).toString())
                    .color(ChatSystem.getChannelColour(ChatSystem.getChannel(sender)))
                .send(sender)
    }

    override fun onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array<String>): Boolean {
        run(commandSender, strings)
        return true
    }

}
