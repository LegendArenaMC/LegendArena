package legendarena.commands.staff

import legendapi.emeralds.EmeraldsCore
import legendapi.utils.ChatUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendarena.LegendArena
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EmeraldCmd : CommandExecutor {

    internal var emeralds: EmeraldsCore? = null
    internal var disabledDueToVaultBreakage = false

    public constructor() {
        try {
            this.emeralds = EmeraldsCore()
        } catch(ex: Exception) {
            this.disabledDueToVaultBreakage = true
        }
    }

    private fun help(sender: CommandSender) {
        sender.sendMessage(ChatUtils.getFormattedHeader("Staff Commands"))
        sender.sendMessage(ChatUtils.getFormattedHelpMsg("/emeralds add <player> <amount>", "Adds a specified amount of emeralds to a player's account."))
        sender.sendMessage(ChatUtils.getFormattedHelpMsg("/emeralds take <player> <amount>", "Takes a specified amount of emeralds from a player's account."))
        sender.sendMessage(ChatUtils.getFormattedHelpMsg("/emeralds reset <player>", "Takes all emeralds from a player's account."))
        sender.sendMessage(ChatUtils.getFormattedHeader("User Commands"))
        sender.sendMessage(ChatUtils.getFormattedHelpMsg("/emeralds info", "Shows your emeralds info."))
    }

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(disabledDueToVaultBreakage) {
            sender.sendMessage("" + ChatColor.RED + "Seems a certain someone forgot to install Vault and/or an economy plugin. As a result, the Emeralds system is currently offline. Sorry!")
            return true
        }

        if(args.size() == 0)
            help(sender)
        else {
            if(args[0].equals("info")) {
                sender.sendMessage(ChatUtils.getFormattedHeader("Your Emerald Info"))
                sender.sendMessage("" + ChatColor.YELLOW + "Amount " + ChatColor.YELLOW + ChatUtils.chars[1] + ChatColor.GREEN + " " + emeralds!!.getEmeralds(sender as Player))
            } else if(args[0].equals("add")) {
                if(!Rank.ADMIN.isRanked(sender)) {
                    sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
                    return true
                }
                if(args.size() <= 2)
                    sender.sendMessage(ChatUtils.getFormattedHelpMsg("/emeralds add <player> <amount>", "Adds a specified amount of emeralds to a player's account."))
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
                    emeralds!!.addEmeralds(p, add, true)
                }
            } else if(args[0].equals("remove") || args[0].equals("take")) {
                if(!Rank.ADMIN.isRanked(sender)) {
                    sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
                    return true
                }
                if(args.size() <= 2) {
                    sender.sendMessage(ChatUtils.getFormattedHelpMsg("/emeralds take <player> <amount>", "Takes a specified amount of emeralds from a player's account."))
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

                    if(remove > emeralds!!.getEmeralds(p)) {
                        sender.sendMessage("" + ChatColor.RED + "That player does not have that many emeralds!")
                        return true
                    }
                    sender.sendMessage("" + ChatColor.GREEN + "Removing " + remove + " emerald(s)...")
                    emeralds!!.removeEmeralds(p, remove, true)
                }
            } else if(args[0].equals("reset")) {
                if(!Rank.ADMIN.isRanked(sender)) {
                    sender.sendMessage(RankUtils.noPermissions(Rank.ADMIN))
                    return true
                }
                if(args.size() < 2) {
                    sender.sendMessage(ChatUtils.getFormattedHelpMsg("/emeralds reset <player>", "Takes all emeralds from a player's account."))
                } else {
                    val p = Bukkit.getPlayer(args[1])
                    if(Rank.MOD.isRanked(p)) {
                        sender.sendMessage(ChatUtils.getCustomMsg("Staff") + "You must be really fun at parties.")
                        return true
                    }
                    if(p == null && args[2] != "--FORCE") {
                        sender.sendMessage("" + ChatColor.RED + "That player was not found!")
                        return true
                    }
                    sender.sendMessage("" + ChatColor.GREEN + "Setting player's emerald count to zero...")
                    emeralds!!.resetEmeralds(p, true, sender.getName())
                }
            } else {
                help(sender)
            }
        }
        return true
    }

}