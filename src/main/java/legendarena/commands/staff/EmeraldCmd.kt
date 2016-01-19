package legendarena.commands.staff

import legendarena.api.emeralds.EmeraldsCore
import legendarena.api.fanciful.FancyMessage
import legendarena.api.log.BukLog
import legendarena.api.utils.ChatUtils
import legendarena.api.utils.Rank
import legendarena.api.utils.RankUtils
import legendarena.LegendArena
import legendarena.api.utils.StringUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EmeraldCmd : CommandExecutor {

    internal var emeralds: EmeraldsCore? = null
    internal var itBroke = false

    public constructor() {
        try {
            this.emeralds = EmeraldsCore()
        } catch(ex: Exception) {
            BukLog(Bukkit.getPluginManager().getPlugin("LegendArena")).dumpError(ex, "initializing the Emeralds command")
            this.itBroke = true
        }
    }

    private fun help(sender: CommandSender) {
        sender.sendMessage(ChatUtils.getFormattedHeader("Staff Commands"))
        sender.sendMessage(ChatUtils.getFormattedMsg("/emeralds add <player> <amount>", "Adds a specified amount of emeralds to a player's account."))
        sender.sendMessage(ChatUtils.getFormattedMsg("/emeralds take <player> <amount>", "Takes a specified amount of emeralds from a player's account."))
        sender.sendMessage(ChatUtils.getFormattedMsg("/emeralds reset <player>", "Takes all emeralds from a player's account."))
        sender.sendMessage(ChatUtils.getFormattedHeader("User Commands"))
        sender.sendMessage(ChatUtils.getFormattedMsg("/emeralds amount", "Shows how many emeralds you have."))
    }

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(itBroke) {
            FancyMessage("Seems a ")
                        .color(ChatColor.RED)
                    .then("certain someone")
                        .color(ChatColor.RED)
                        .tooltip("" + ChatColor.GRAY + "*cough Pixel cough*")
                    .then(" broke the Legend Economy class (or screwed up the ConfigUtils class, yet again). Sorry!")
                        .color(ChatColor.RED)
                    .send(sender)
            return true
        }

        if(StringUtils.getSize(args) == 0)
            help(sender)
        else {
            if(args[0].equals("amount")) {
                sender.sendMessage(ChatUtils.getFormattedMsg("You Have", "" + emeralds!!.getEmeralds(sender.name) + " emeralds"))
            } else if(args[0].equals("add")) {
                if(!Rank.ADMIN.isRanked(sender)) {
                    RankUtils.fancyNoPermissions(Rank.ADMIN, sender)
                    return true
                }
                if(StringUtils.getSize(args) <= 2)
                    sender.sendMessage(ChatUtils.getFormattedMsg("/emeralds add <player> <amount>", "Adds a specified amount of emeralds to a player's account."))
                else {
                    val p = Bukkit.getPlayer(args[1])
                    if(p == null) {
                        sender.sendMessage("" + ChatColor.RED + "That player wasn't found!")
                        return true
                    }
                    val add: Int
                    try {
                        add = Integer.parseInt(args[2])
                    } catch(ex: Exception) {
                        sender.sendMessage("" + ChatColor.RED + "\"" + args[2] + "\" is not a integer!")
                        return true
                    }
                    sender.sendMessage("" + ChatColor.GREEN + "Adding " + add + " emerald(s)...")
                    emeralds!!.addEmeralds(p.name, add)
                }
            } else if(args[0].equals("remove") || args[0].equals("take")) {
                if(!Rank.ADMIN.isRanked(sender)) {
                    RankUtils.fancyNoPermissions(Rank.ADMIN, sender)
                    return true
                }
                if(StringUtils.getSize(args) <= 2) {
                    sender.sendMessage(ChatUtils.getFormattedMsg("/emeralds take <player> <amount>", "Takes a specified amount of emeralds from a player's account."))
                } else {
                    val p = Bukkit.getPlayer(args[1])
                    if(p == null) {
                        sender.sendMessage("" + ChatColor.RED + "That player wasn't found!")
                        return true
                    }
                    val remove: Int
                    try {
                        remove = Integer.parseInt(args[2])
                    } catch(ex: Exception) {
                        sender.sendMessage("" + ChatColor.RED + "\"" + args[2] + "\" is not an integer!")
                        return true
                    }

                    if(remove > emeralds!!.getEmeralds(p.name)) {
                        sender.sendMessage("" + ChatColor.RED + "That player does not have that many emeralds!")
                        return true
                    }
                    sender.sendMessage("" + ChatColor.GREEN + "Removing " + remove + " emerald(s)...")
                    emeralds!!.removeEmeralds(p.name, remove)
                }
            } else if(args[0].equals("reset")) {
                if(!Rank.ADMIN.isRanked(sender)) {
                    RankUtils.fancyNoPermissions(Rank.ADMIN, sender)
                    return true
                }
                if(StringUtils.getSize(args) < 2) {
                    sender.sendMessage(ChatUtils.getFormattedMsg("/emeralds reset <player>", "Takes all emeralds from a player's account."))
                } else {
                    val p = Bukkit.getPlayer(args[1])
                    if(Rank.MOD.isRanked(p)) {
                        sender.sendMessage(ChatUtils.getCustomMsg("Staff") + "You must be really fun at parties.")
                        return true
                    }
                    sender.sendMessage("" + ChatColor.GREEN + "Setting player's emerald count to zero...")
                    emeralds!!.resetEmeralds(p.name)
                }
            } else {
                help(sender)
            }
        }
        return true
    }

}