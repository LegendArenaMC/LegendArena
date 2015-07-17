package legendarena.commands.staff

import legendarena.chat.ChatSystemOld
import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.ChatUtils
import legendarena.chat.Channel
import legendarena.chat.ChatSystem
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Chat : CommandExecutor {

    private fun run(sender: CommandSender, args: Array<String>) {
        if(args.size() == 0) {
            sender.sendMessage(ChatUtils.getFormattedHeader("Chat System"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat off", "Exits any special chats and enters global chat"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat admin", "Enters ADMIN chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat alert", "Enters ALERT chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat staff", "Enters STAFF chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat manage", "Manage chat settings."))
            return
        }

        if(args[0].equals("off")) {
            Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "GLOBAL" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
            ChatSystemOld.remove(sender as Player)
        } else if(args[0].equals("alert")) {
            if(args.size() == 1) {
                Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "ALERT" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
                ChatSystemOld.add(sender as Player, Channel.ALERT)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender as Player
                val oldChannel = ChatSystemOld.getChannel(p)
                ChatSystemOld.add(p, Channel.ALERT)
                ChatSystemOld.msg(p, cast)
                if(oldChannel == Channel.GLOBAL)
                    ChatSystemOld.remove(p)
                else
                    ChatSystemOld.add(p, oldChannel)
            }
        } else if(args[0].equals("admin")) {
            if(args.size() == 1) {
                Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "ADMIN" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
                ChatSystemOld.add(sender as Player, Channel.ADMIN)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender as Player
                val oldChannel = ChatSystemOld.getChannel(p)
                ChatSystemOld.add(p, Channel.ADMIN)
                ChatSystemOld.msg(p, cast)
                if(oldChannel == Channel.GLOBAL)
                    ChatSystemOld.remove(p)
                else
                    ChatSystemOld.add(p, oldChannel)
            }
        } else if(args[0].equals("staff")) {
            if(args.size() == 1) {
                Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "STAFF" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
                ChatSystemOld.add(sender as Player, Channel.STAFF)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender as Player
                val oldChannel = ChatSystemOld.getChannel(p)
                ChatSystemOld.add(p, Channel.STAFF)
                ChatSystemOld.msg(p, cast)
                if(oldChannel == Channel.GLOBAL)
                    ChatSystemOld.remove(p)
                else
                    ChatSystemOld.add(p, oldChannel)
            }
        }

        else if(args[0].equals("manage")) {
            if(args.size() == 1) {
                sender.sendMessage(ChatUtils.getFormattedHeader("Chat Settings"))
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat manage globalmute", "Toggle chat global mute"))
                //sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat mute allowsmute", "Toggle allowing of shadow mutes"))
                return
            }

            /*if(args[1].equals("allowsmute")) {
                ChatSystemOld.allowShadowMute(!ChatSystemOld.isShadowMuteAllowed())
            }

            else*/ if(args[1].equals("globalmute")) {
                if(ChatSystemOld.isChatMuted()) {
                    ChatSystem.setGlobalMute(false)
                } else {
                    ChatSystem.setGlobalMute(true)
                }
            }
        }

        else {
            sender.sendMessage(ChatUtils.getFormattedHeader("Chat System"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat off", "Exits any special chats and enters global chat"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat admin", "Enters ADMIN chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat alert", "Enters ALERT chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat staff", "Enters STAFF chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat manage", "Manage chat settings."))
        }
    }

    override fun onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array<String>): Boolean {
        run(commandSender, strings)
        return true
    }

}
