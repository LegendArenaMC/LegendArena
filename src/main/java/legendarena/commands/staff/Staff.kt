package legendarena.commands.staff

import legendarena.api.emeralds.EmeraldsCore
import legendarena.api.utils.*
import legendarena.chat.ChatSystem
import legendarena.staffutils.VanishUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Staff: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(!Rank.HELPER.isRanked(sender)) {
            RankUtils.fancyNoPermissions(Rank.HELPER, sender)
            return true
        }

        if(StringUtils.getSize(args) == 0) help(sender)
        else {
            if(args[0].equals("info")) {
                if(StringUtils.getSize(args) == 1)
                    ChatUtils.fancyHelpSuggestMsg("/staff info <player>", "Gets various info about a specified player.", "staff info", true).send(sender)
                else {
                    if(Bukkit.getPlayer(args[1]) != null) {
                        val p = Bukkit.getPlayer(args[1])
                        sender.sendMessage(ChatUtils.getFormattedHeader("Info: " + p.name))
                        sender.sendMessage(ChatUtils.getFormattedMsg("True rank", RankUtils.getRank(p).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("Tagged as", RankUtils.getDisplayRank(p).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("Emeralds", EmeraldsCore().getEmeralds(p.name).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("In channel", ChatSystem.getChannel(p).toString()))
                        sender.sendMessage(ChatUtils.getFormattedMsg("UUID", p.uniqueId.toString()))
                    } else {
                        sender.sendMessage("" + ChatColor.RED + "Player \"" + args[0] + "\" was not found!") //the player was not found
                    }
                }
            } else if(args[0].equals("clearchat")) {
                if(!Rank.ADMIN.isRanked(sender)) {
                    RankUtils.fancyNoPermissions(Rank.ADMIN, sender)
                    return true
                }

                ChatUtils.clearChat(sender.name)
            } else if(args[0].equals("vanish")) {
                if(!Rank.MOD.isRanked(sender)) {
                    RankUtils.fancyNoPermissions(Rank.MOD, sender)
                    return true
                }
                VanishUtils.toggleVanish(sender as Player, true)
            } else if(args[0].equals("skull")) {
                if(StringUtils.getSize(args) == 1)
                    (sender as Player).inventory.addItem(MenuUtils.createHead(sender.getName()))
                else
                    (sender as Player).inventory.addItem(MenuUtils.createHead(args[1]))
                return true
            }

            else help(sender)
        }
        return true
    }

    private fun help(sender: CommandSender) {
        sender.sendMessage(ChatUtils.getFormattedHeader("Staff [1/1]"))
        ChatUtils.fancyHelpSuggestMsg("/staff info <player>", "Gets various info about a specified player.", "staff info", true).send(sender)
        ChatUtils.fancyHelpSuggestMsg("/staff skull [playername]", "", "staff skull", true).send(sender)
        ChatUtils.fancyHelpMsg("/staff vanish", "Poof. [WIP]", "staff vanish", true).send(sender)
        ChatUtils.fancyHelpMsg("/staff clearchat", "Clear the current server's chat for everyone.", "staff clearchat", true).send(sender)
    }

}
