package legendarena.commands.staff

import legendarena.api.emeralds.EmeraldsCore
import legendarena.api.fanciful.FancyMessage
import legendarena.api.message.Message
import legendarena.api.utils.*
import legendarena.chat.ChatSystem
import legendarena.hub.HubWarper
import legendarena.staffutils.VanishUtils
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
        if(StringUtils.getSize(args) >= 1 && args[0].equals("monstercat")) {
            //shh.... no one must know of this easter egg...
            sender.sendMessage(ChatUtils.getCustomMsg("Irrelevent Jokes") + "WELCOME, TO THE MONSTERCAT PODCAST")
            return true
        }

        if(!Rank.HELPER.isRanked(sender)) {
            RankUtils.fancyNoPermissions(Rank.HELPER, sender)
            return true
        }

        if(StringUtils.getSize(args) >= 1 && args[0].equals("gerald")) {
            //ah, A->N (for the nubs who have no idea what that is: Approaching Nirvana). your streams never fail to make me laugh.

            //read the following message in a Monty Python-type voice for maximum laughing
            sender.sendMessage(ChatUtils.getCustomMsg("Irrelevent Jokes") + "Gerald! Gerald please!")
            var gerald = (PluginUtils.spawn(EntityType.CREEPER, (sender as Player).location) as Creeper)
            gerald.isPowered = true
            gerald.isCustomNameVisible = true
            gerald.customName = "" + ChatColor.YELLOW + "Gerald"
            gerald.health = 0.5

            return true
        }

        if(StringUtils.getSize(args) == 0) {
            help(sender, "1")
        } else {
            if(args[0].equals("help")) {
                if(StringUtils.getSize(args) == 1)
                    help(sender, "1")
                else
                    help(sender, args[1])
            } else if(args[0].equals("info")) {
                if(StringUtils.getSize(args) == 1)
                    ChatUtils.fancyHelpSuggestMsg("/staff info <player>", "Gets various info about a specified player.", "staff info", true).send(sender)
                else {
                    if(Bukkit.getPlayer(args[1]) != null) {
                        val p = Bukkit.getPlayer(args[1])
                        sender.sendMessage(ChatUtils.getFormattedHeader("Info: " + p.name))
                        sender.sendMessage(ChatUtils.getFormattedMsg("User rank", RankUtils.getRank(p).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("Tagged as", RankUtils.getDisplayRank(p).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("Emeralds", EmeraldsCore().getEmeralds(p.name).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("In channel", ChatSystem.getChannel(p).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("UUID", p.uniqueId.toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("Speed; WALK", p.walkSpeed.toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("Speed; FLY", p.flySpeed.toString()))

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
                    sender.sendMessage(c!!.getTimeRemaining() + " (this is a SERVER-WIDE (not network-wide) cooldown!)")
                    return true
                }

                ChatUtils.clearChat(sender.name)
                c = Cooldown(120.0)
            } else if(args[0].equals("vanish")) {
                if(!Rank.MOD.isRanked(sender)) {
                    RankUtils.fancyNoPermissions(Rank.MOD, sender)
                    return true
                }
                VanishUtils.toggleVanish(sender as Player, true)
            } else if(args[0].equals("skull")) {
                if(StringUtils.getSize(args) == 1)
                    (sender as Player).inventory.addItem(MenuUtils.createHead(sender.name))
                else
                    (sender as Player).inventory.addItem(MenuUtils.createHead(args[1]))
                return true
            } else if(args[0].equals("jp")) {
                var inv = (sender as Player).inventory
                inv.addItem(MenuUtils.createItem(Material.IRON_PLATE, "" + ChatColor.GREEN + "JumpPad", "" + ChatColor.GREEN + "Place me anywhere to create a " + ChatColor.YELLOW + "JumpPad" + ChatColor.GREEN + "!"))
            } else if(args[0].equals("mainmenu")) {
                var inv = (sender as Player).inventory
                inv.addItem(HubWarper.getMainMenu(sender.name))
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
                sender.sendMessage(ChatUtils.getFormattedHeader("Staff [1/1]"))
                ChatUtils.fancyHelpSuggestMsg("/staff info <player>", "Gets various info about a specified player.", "staff info", true).send(sender)
                ChatUtils.fancyHelpSuggestMsg("/staff skull [playername]", "", "staff skull", true).send(sender)
                ChatUtils.fancyHelpMsg("/staff vanish", "Poof.", "staff vanish", true).send(sender)
                ChatUtils.fancyHelpMsg("/staff clearchat", "Clear the current server's chat for everyone.", "staff clearchat", true).send(sender)
            }
            else -> sender.sendMessage(ChatUtils.getCustomMsg("Error", ChatColor.RED) + "I don't know what you mean!")
        }
    }

}
