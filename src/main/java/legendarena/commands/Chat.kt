package legendarena.commands

import legendarena.chat.ChatSystem
import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.ChatUtils
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
            return
        }

        if(args[0].equals("off")) {
            Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "GLOBAL" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
            ChatSystem.remove(sender as Player)
        } else if(args[0].equals("alert")) {
            if(args.size() == 1) {
                Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "ALERT" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
                ChatSystem.add(sender as Player, ChatSystem.Channel.ALERT)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender as Player
                val oldChannel = ChatSystem.getChannel(p)
                ChatSystem.add(p, ChatSystem.Channel.ALERT)
                ChatSystem.msg(p, cast)
                if(oldChannel === ChatSystem.Channel.GLOBAL)
                    ChatSystem.remove(p)
                else
                    ChatSystem.add(p, oldChannel)
            }
        } else if(args[0].equals("admin")) {
            if(args.size() == 1) {
                Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "ADMIN" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
                ChatSystem.add(sender as Player, ChatSystem.Channel.ADMIN)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender as Player
                val oldChannel = ChatSystem.getChannel(p)
                ChatSystem.add(p, ChatSystem.Channel.ADMIN)
                ChatSystem.msg(p, cast)
                if(oldChannel === ChatSystem.Channel.GLOBAL)
                    ChatSystem.remove(p)
                else
                    ChatSystem.add(p, oldChannel)
            }
        } else if(args[0].equals("staff")) {
            if(args.size() == 1) {
                Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "STAFF" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
                ChatSystem.add(sender as Player, ChatSystem.Channel.STAFF)
            } else {
                val cast = ChatUtils.formatCast(args, 0)
                val p = sender as Player
                val oldChannel = ChatSystem.getChannel(p)
                ChatSystem.add(p, ChatSystem.Channel.STAFF)
                ChatSystem.msg(p, cast)
                if(oldChannel === ChatSystem.Channel.GLOBAL)
                    ChatSystem.remove(p)
                else
                    ChatSystem.add(p, oldChannel)
            }
        }

        else {
            sender.sendMessage(ChatUtils.getFormattedHeader("Chat System"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat off", "Exits any special chats and enters global chat"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat admin", "Enters ADMIN chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat alert", "Enters ALERT chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat staff", "Enters STAFF chat."))
        }
    }

    override fun onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array<String>): Boolean {
        run(commandSender, strings)
        return true
    }

}
