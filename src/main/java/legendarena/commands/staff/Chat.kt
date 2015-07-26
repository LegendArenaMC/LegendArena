package legendarena.commands.staff

import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendarena.chat.Channel
import legendarena.chat.ChatSystem
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Chat : CommandExecutor {

    private fun run(sender: CommandSender, args: Array<String>) {
        if(!Rank.HELPER.isRanked(sender as Player)) {
            sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
            return
        }

        if(args.size() == 0) {
            sender.sendMessage(ChatUtils.getFormattedHeader("Chat System"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat off", "Exits any special chats and enters global chat"))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat admin", "Enters ADMIN chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat alert", "Enters ALERT chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat staff", "Enters STAFF chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat dev", "Enters DEV chat."))
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat manage", "Manage chat settings."))
            return
        }

        if(args[0].equals("off")) {
            Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "GLOBAL" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
            ChatSystem.remove(sender as Player)
        } else if(args[0].equals("alert")) {
            if(!Rank.MOD.isRanked(sender as Player)) {
                sender.sendMessage(RankUtils.noPermissions(Rank.MOD))
                return
            }
            if(args.size() == 1) {
                Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "ALERT" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
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
                sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
                return
            }
            if(args.size() == 1) {
                Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "ADMIN" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
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
            if(args.size() == 1) {
                Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "STAFF" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
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
                sender.sendMessage(RankUtils.noPermissions(Rank.DEV))
                return
            }
            if(args.size() == 1) {
                Message(MessageType.ACTIONBAR).append(ChatUtils.getCustomMsg("Chat") + "Entered " + ChatColor.RED + "DEV" + ChatColor.LIGHT_PURPLE + " chat.").send(sender as Player)
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
                sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
                return
            }

            if(args.size() == 1) {
                sender.sendMessage(ChatUtils.getFormattedHeader("Chat Settings"))
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat manage globalmute", "Toggle chat global mute"))
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/chat mute allowsmute", "Toggle allowing of shadow mutes"))
                return
            }

            if(args[1].equals("allowsmute")) {
                ChatSystem.allowShadowMute(!ChatSystem.isShadowMuteAllowed())
            }

            else if(args[1].equals("globalmute")) {
                if(ChatSystem.isChatMuted()) {
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
