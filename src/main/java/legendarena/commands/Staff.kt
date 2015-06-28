package legendarena.commands

import legendapi.message.Message
import legendapi.utils.ChatUtils
import legendapi.utils.Cooldown
import legendapi.utils.Rank
import legendarena.chat.ChatSystem
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.text.MessageFormat

class Staff: CommandExecutor {

    var c : Cooldown? = null

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
                else {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        @SuppressWarnings("deprecation") val p = Bukkit.getPlayer(args[1])
                        sender.sendMessage(ChatUtils.getFormattedHeader("Info: " + p.getName()))
                        sender.sendMessage("" + ChatColor.YELLOW + "User rank " + ChatUtils.chars[1] + ChatColor.GREEN + " " + Rank.getRank(p))
                        sender.sendMessage("" + ChatColor.YELLOW + "Chat channel " + ChatUtils.chars[1] + ChatColor.GREEN + " " + ChatSystem.getChannel(p))
                        sender.sendMessage("" + ChatColor.YELLOW + "Gamemode " + ChatUtils.chars[1] + ChatColor.GREEN + " " + p.getGameMode())
                        sender.sendMessage("" + ChatColor.YELLOW + "UUID " + ChatUtils.chars[1] + ChatColor.GREEN + " " + p.getUniqueId())
                        sender.sendMessage("" + ChatColor.YELLOW + "Speed; WALK " + ChatUtils.chars[1] + ChatColor.GREEN + " " + p.getWalkSpeed())
                        sender.sendMessage("" + ChatColor.YELLOW + "Speed; FLY " + ChatUtils.chars[1] + ChatColor.GREEN + " " + p.getFlySpeed())
                        sender.sendMessage("" + ChatColor.YELLOW + "Global Bans " + ChatUtils.chars[1] + ChatColor.GREEN + " http://fishbans.com/u/" + p.getName())
                    } else {
                        sender.sendMessage("" + ChatColor.RED + "Player \"" + args[0] + "\" was not found!") //the player was not found
                    }
                }
            } else if (args[0].equals("motd")) {
                //MOTDList.run(sender, args)
            } else if (args[0].equals("chat")) {
                if(args.size() == 1) {
                    sender.sendMessage("" + ChatColor.LIGHT_PURPLE + "Chat Management suboptions:")
                    sender.sendMessage("" + ChatColor.YELLOW + "- CLEARCHAT [reason]")
                    sender.sendMessage("" + ChatColor.YELLOW + "- GLOBALMUTE")
                } else {
                    if (args[1].equals("globalmute")) {
                        if(!Rank.isRanked(sender, Rank.MOD)) {
                            sender.sendMessage(Rank.noPermissions(Rank.MOD))
                            return true
                        }
                        if(ChatSystem.isChatMuted()) {
                            Message().append(" ").broadcast()
                            Message().append(ChatUtils.getCustomMsg("Chat Management") + "Staff member " + ChatColor.YELLOW + sender.getName() + ChatColor.BLUE + " has lifted the global mute.").broadcast()
                            Message().append(" ").broadcast()
                            ChatSystem.setChatMuted(false)
                        } else {
                            Message().append(" ").broadcast()
                            Message().append(ChatUtils.getCustomMsg("Chat Management") + "Staff member " + ChatColor.YELLOW + sender.getName() + ChatColor.BLUE + " has globally muted the chat.").broadcast()
                            Message().append(" ").broadcast()
                            ChatSystem.setChatMuted(true)
                        }
                    } else if(args[1].equals("clearchat")) {
                        if(!Rank.isRanked(sender, Rank.ADMIN)) {
                            sender.sendMessage(Rank.noPermissions(Rank.ADMIN))
                            return true
                        }
                        if(c != null && !c!!.done()) {
                            sender.sendMessage(MessageFormat.format("{0} (this is a GLOBAL cooldown!)", c!!.getTimeRemaining()))
                            return true
                        }
                        ChatUtils.clearChat(sender.getName())
                        c = Cooldown(120.0)
                    } else {
                        sender.sendMessage("" + ChatColor.LIGHT_PURPLE + "Chat Management suboptions:")
                        sender.sendMessage("" + ChatColor.YELLOW + "- CLEARCHAT [reason]")
                        sender.sendMessage("" + ChatColor.YELLOW + "- GLOBALMUTE")
                    }
                }
            } else {
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
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/staff chat <various suboptions...>", "Chat managment tools."))
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/staff motd [various suboptions...]", "MOTD-related info."))
                sender.sendMessage("" + ChatColor.YELLOW + "----.{ Staff [1/1] }.----")
            }
            else -> sender.sendMessage(ChatUtils.Messages.errorMsg + "I don't know what help page you mean :(")
        }
    }

}
