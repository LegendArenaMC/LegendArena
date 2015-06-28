package legendarena.commands

import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Staff: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(args.size() >= 1 && args[0].equals("monstercat")) {
            //shh....
            sender.sendMessage(ChatUtils.getCustomMsg("Irrelevent Jokes") + "WELCOME, TO THE MONSTERCAT PODCAST")
            return true
        }
        if(!Rank.isRanked(sender, Rank.HELPER)) {
            sender.sendMessage(Rank.noPermissions(Rank.HELPER))
            return true
        }
        if(args.size() == 0) {
            help(sender, "1")
        } else {
            if(args[0].equals("help")) {
                if (args.size() == 1)
                    help(sender, "1")
                else
                    help(sender, args[1])
            } else if(args[0].equals("info")) {
                if(args.size() == 1)
                    sender.sendMessage(ChatUtils.getFormattedHelpMsg("/staff info <player>", "Gets info about a specified player."))
                //else
                    //Info.run(sender, args)
            } else if (args[0].equals("motd")) {
                //MOTDList.run(sender, args)
            } /*else if (args[0].equalsIgnoreCase("chat")) {
                if (args.size() == 1) {
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Chat Management suboptions:")
                    sender.sendMessage(ChatColor.YELLOW + "- CLEARCHAT [reason]")
                    sender.sendMessage(ChatColor.YELLOW + "- GLOBALMUTE")
                } else {
                    if (args[1].equalsIgnoreCase("globalmute")) {
                        GlobalMute.run(sender)
                    } else if (args[1].equalsIgnoreCase("clearchat")) {
                        ClearChat.run(sender, args)
                    } else {
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Chat Management suboptions:")
                        sender.sendMessage(ChatColor.YELLOW + "- CLEARCHAT [reason]")
                        sender.sendMessage(ChatColor.YELLOW + "- GLOBALMUTE")
                    }
                }
            }*/ else {
                help(sender, "unknown")
            }
        }
        return true
    }

    private fun help(sender: CommandSender, page: String) {
        when(page) {
            "1" -> {
                sender.sendMessage("" + ChatColor.YELLOW + "----.{ Staff [1/1] }.----")
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/staff help [page]", "Displays this menu, or optionally, a help page."))
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/staff info <player>", "Gets info about a specified player."))
                //sender.sendMessage(ChatUtils.getFormattedHelpMsg("/staff chat <various suboptions...>", "Chat managment tools."))
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/staff motd [various suboptions...]", "MOTD-related info."))
                sender.sendMessage("" + ChatColor.YELLOW + "----.{ Staff [1/1] }.----")
            }
            else -> sender.sendMessage(ChatUtils.Messages.errorMsg + "I don't know what help page you mean :(")
        }
    }

}
