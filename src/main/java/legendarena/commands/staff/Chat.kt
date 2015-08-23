package legendarena.commands.staff

import legendapi.fanciful.FancyMessage
import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
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
        if(args.size() == 0) {
            help(sender)
            return
        }

        if(args[0].equals("off")) {
            Message().append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "GLOBAL" + ChatColor.BLUE + " chat.").send(sender as Player)
            ChatSystem.remove(sender as Player)
        } else if(args[0].equals("alert")) {
            if(!Rank.MOD.isRanked(sender as Player)) {
                RankUtils.fancyNoPermissions(Rank.MOD, sender)
                return
            }
            if(args.size() == 1) {
                Message().append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "ALERT" + ChatColor.BLUE + " chat.").send(sender as Player)
                ChatSystem.add(sender as Player, Channel.ALERT)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender as Player
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
            if(args.size() == 1) {
                Message().append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "ADMIN" + ChatColor.BLUE + " chat.").send(sender as Player)
                ChatSystem.add(sender as Player, Channel.ADMIN)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender as Player
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
            if(args.size() == 1) {
                Message().append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "STAFF" + ChatColor.BLUE + " chat.").send(sender as Player)
                ChatSystem.add(sender as Player, Channel.STAFF)
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
            if(args.size() == 1) {
                Message().append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "DEV" + ChatColor.BLUE + " chat.").send(sender as Player)
                ChatSystem.add(sender as Player, Channel.DEV)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender as Player
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

            if(args.size() == 1) {
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

    internal fun help(sender: CommandSender) {
        sender.sendMessage(ChatUtils.getFormattedHeader("Available Channels"))
        FancyMessage("GLOBAL")
                    .color(ChatColor.GREEN)
                    .command("/c off")
                    .tooltip("" + ChatColor.YELLOW + "Click to join the " + ChatSystem.getChannelName(Channel.GLOBAL) + ChatColor.YELLOW + " channel")
                .send(sender)
        FancyMessage("STAFF")
                    .color(getChannelColor(Channel.STAFF, sender))
                    .command("/c staff")
                    .tooltip("" + ChatColor.YELLOW + "Click to join the " + ChatSystem.getChannelName(Channel.STAFF) + ChatColor.YELLOW + " channel")
                .send(sender)
        FancyMessage("ALERT")
                    .color(getChannelColor(Channel.ALERT, sender))
                    .command("/c alert")
                    .tooltip("" + ChatColor.YELLOW + "Click to join the " + ChatSystem.getChannelName(Channel.ALERT) + ChatColor.YELLOW + " channel")
                .send(sender)
        FancyMessage("ADMIN")
                    .color(getChannelColor(Channel.ADMIN, sender))
                    .command("/c admin")
                .tooltip("" + ChatColor.YELLOW + "Click to join the " + ChatSystem.getChannelName(Channel.ADMIN) + ChatColor.YELLOW + " channel")
                .send(sender)
        FancyMessage("DEV")
                    .color(getChannelColor(Channel.DEV, sender))
                    .command("/c dev")
                    .tooltip("" + ChatColor.YELLOW + "Click to join the " + ChatSystem.getChannelName(Channel.DEV) + ChatColor.YELLOW + " channel")
                .send(sender)

        sender.sendMessage("\n")

        FancyMessage("Manage Settings")
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
