package legendarena.commands.staff

import legendapi.message.Message
import legendapi.utils.ChatUtils
import legendapi.utils.Cooldown
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendarena.chat.ChatSystem
import legendarena.utils.MobSpawnUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Creeper
import org.bukkit.entity.EntityType
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

        if(!Rank.HELPER.isRanked(sender)) {
            sender.sendMessage(RankUtils.noPermissions(Rank.HELPER))
            return true
        }

        if(args.size() >= 1 && args[0].equals("gerald")) {
            //ah, A->N (for the nubs who have no idea what that is: Approaching Nirvana). your streams never fail to make me laugh.

            //read the following message in a Monty Python-type voice for maximum laughing
            sender.sendMessage(ChatUtils.getCustomMsg("Irrelevent Jokes") + "Gerald! Gerald please!")
            var gerald = (MobSpawnUtils().spawn(EntityType.CREEPER, (sender as Player).getLocation()) as Creeper)
            gerald.setPowered(true)
            gerald.setCustomNameVisible(true)
            gerald.setCustomName("" + ChatColor.YELLOW + "Gerald")
            gerald.setTarget(sender)
            gerald.setHealth(1.0)

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
                        @suppress("deprecation") val p = Bukkit.getPlayer(args[1])
                        sender.sendMessage(ChatUtils.getFormattedHeader("Info: " + p.getName()))
                        sender.sendMessage("" + ChatColor.YELLOW + "User rank " + ChatUtils.chars[1] + ChatColor.GREEN + " " + RankUtils.getRank(p))
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
            } else if(args[0].equals("clearchat")) {
                if(!Rank.ADMIN.isRanked(sender)) {
                    sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
                    return true
                }
                if(c != null && !c!!.done()) {
                    sender.sendMessage(MessageFormat.format("{0} (this is a GLOBAL cooldown!)", c!!.getTimeRemaining()))
                    return true
                }
                ChatUtils.clearChat(sender.getName())
                c = Cooldown(120.0)
            } else if(args[0].equals("vanish")) {
                //
            }

            else {
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
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/staff vanish", "Poof."))
                sender.sendMessage(ChatUtils.getFormattedHelpMsg("/staff clearchat", "Clear the current server's chat."))
                sender.sendMessage("" + ChatColor.YELLOW + "----.{ Staff [1/1] }.----")
            }
            else -> sender.sendMessage(ChatUtils.Messages.errorMsg + "I don't know what you mean :(")
        }
    }

}
