package legendarena.commands.staff

import legendapi.emeralds.EmeraldsCore
import legendapi.fanciful.FancyMessage
import legendapi.message.Message
import legendapi.utils.*
import legendarena.chat.ChatSystem
import legendarena.hub.HubWarper
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
import java.util.*

class Staff: CommandExecutor {

    var c : Cooldown? = null

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(args.size() >= 1 && args[0].equals("monstercat")) {
            //shh.... no one must know of this easter egg...
            sender.sendMessage(ChatUtils.getCustomMsg("Irrelevent Jokes") + "WELCOME, TO THE MONSTERCAT PODCAST")
            return true
        }

        if(!Rank.HELPER.isRanked(sender)) {
            RankUtils.fancyNoPermissions(Rank.HELPER, sender)
            return true
        }

        if(args.size() >= 1 && args[0].equals("gerald")) {
            //ah, A->N (for the nubs who have no idea what that is: Approaching Nirvana). your streams never fail to make me laugh.

            //read the following message in a Monty Python-type voice for maximum laughing
            sender.sendMessage(ChatUtils.getCustomMsg("Irrelevent Jokes") + "Gerald! Gerald please!")
            var gerald = (PluginUtils.spawn(EntityType.CREEPER, (sender as Player).getLocation()) as Creeper)
            gerald.setPowered(true)
            gerald.setCustomNameVisible(true)
            gerald.setCustomName("" + ChatColor.YELLOW + "Gerald")
            gerald.setHealth(0.5)

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
                    ChatUtils.fancyHelpSuggestMsg("/staff info <player>", "Gets various info about a specified player.", "staff info", true).send(sender)
                else {
                    if(Bukkit.getPlayer(args[1]) != null) {
                        @suppress("deprecation") val p = Bukkit.getPlayer(args[1])
                        sender.sendMessage(ChatUtils.getFormattedHeader("Info: " + p.getName()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("User rank", RankUtils.getRank(p).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("Tagged as", RankUtils.getDisplayRank(p).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("Emeralds", EmeraldsCore().getEmeralds(p.getName()).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("In channel", ChatSystem.getChannel(p).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("UUID", p.getUniqueId().toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("Speed; WALK", p.getWalkSpeed().toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("Speed; FLY", p.getFlySpeed().toString()))

                        //for people who are wondering "why was the fishbans link removed?" - fishbans seems to be going out of disrepair, so it's not really something I'll link to anymore. -Pixel
                    } else {
                        sender.sendMessage("" + ChatColor.RED + "Player \"" + args[0] + "\" was not found!") //the player was not found
                    }
                }
            } else if(args[0].equals("clearchat")) {
                if(!Rank.ADMIN.isRanked(sender)) {
                    RankUtils.fancyNoPermissions(Rank.ADMIN, sender)
                    return true
                }
                if(c != null && !c!!.done()) {
                    sender.sendMessage(MessageFormat.format("{0} (this is a SERVER-WIDE (not network-wide) cooldown!)", c!!.getTimeRemaining()))
                    return true
                }
                ChatUtils.clearChat(sender.getName())
                c = Cooldown(120.0)
            } else if(args[0].equals("vanish")) {
                sender.sendMessage("soon[tm]")
            } else if(args[0].equals("skull")) {
                if(args.size() == 1)
                    (sender as Player).getInventory().addItem(MenuUtils.createHead(sender.getName()))
                else
                    (sender as Player).getInventory().addItem(MenuUtils.createHead(args[1]))
                return true
            } else if(args[0].equals("jp")) {
                var inv = (sender as Player).getInventory()
                inv.addItem(MenuUtils.createItem(Material.IRON_PLATE, "" + ChatColor.GREEN + "JumpPad", "" + ChatColor.GREEN + "Place me anywhere to create a " + ChatColor.YELLOW + "JumpPad" + ChatColor.GREEN + "!"))
            } else if(args[0].equals("mainmenu")) {
                var inv = (sender as Player).getInventory()
                inv.addItem(HubWarper.getMainMenu(sender.getName()))
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
                ChatUtils.fancyHelpSuggestMsg("/staff info <player>", "Gets various info about a specified player.", "staff info", true).send(sender)
                ChatUtils.fancyHelpSuggestMsg("/staff lockdown <0/1/2>", "Set the current lockdown level. [See \"/staff help lockdown\" for more info]", "staff lockdown", true).send(sender)
                ChatUtils.fancyHelpSuggestMsg("/staff skull [playername]", "", "staff skull", true).send(sender)
                ChatUtils.fancyHelpMsg("/staff vanish", "Poof.", "staff vanish", true).send(sender)
                ChatUtils.fancyHelpMsg("/staff clearchat", "Clear the current server's chat for everyone.", "staff clearchat", true).send(sender)
                sender.sendMessage("" + ChatColor.YELLOW + "----.{ Staff [1/1] }.----")
            }
            "lockdown" -> {
                ChatUtils.fancyHelpSuggestMsg("/staff lockdown <0/1/2>", "Set the current lockdown level. Currently a work in progress.", "staff lockdown", true).send(sender)
                ChatUtils.fancyHelpMsg("/staff lockdown 0", "Level 0 lockdown, the basic mode.", "staff lockdown 0", true).send(sender)
                ChatUtils.fancyHelpMsg("/staff lockdown 1", "Level 1 lockdown, locks all normal players out from this server only.", "staff lockdown 1", true).send(sender)
                ChatUtils.fancyHelpMsg("/staff lockdown 2", "Level 2 lockdown, locks all normal players out from the entire network. [ONLY USABLE BY FOUNDER RANKED STAFF]", "staff lockdown 2", true).send(sender)
            }
            else -> sender.sendMessage(ChatUtils.getCustomMsg("" + ChatColor.RED + "Error") + "I don't know what you mean :(")
        }
    }

}
