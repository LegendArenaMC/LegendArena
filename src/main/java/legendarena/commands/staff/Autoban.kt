/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.commands.staff

import legendapi.fanciful.FancyMessage
import legendapi.utils.ChatUtils
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Autoban : CommandExecutor {

    override fun onCommand(sender: CommandSender, p1: Command?, p2: String?, args: Array<out String>): Boolean {
        if(!Rank.MOD.isRanked(sender as Player)) {
            sender.sendMessage(RankUtils.noPermissions(Rank.MOD))
            return true
        }

        if(args.size() == 0) {
            sender.sendMessage(ChatUtils.getFormattedHelpMsg("/autoban <player>", "Bans a player with a selection of options."))
            return true
        }

        run(sender, args[0])
        return true
    }

    public fun run(sender: CommandSender, target: String) {
        //walls of code are fun (/s)
        //also no this isn't a gui. because fuck you. [tm]
        FancyMessage("Banning player ")
                    .color(ChatColor.GREEN)
                .then(target)
                    .color(ChatColor.RED)
                .then(".")
                    .color(ChatColor.GREEN)
                .then("\n\n")
                .then("Reasons:")
                    .color(ChatColor.GREEN)
                .then("\n")
                .then("1.")
                    .color(ChatColor.GREEN)
                .then(" Hacked client")
                    .suggest("/ban " + target + " Cheating - ")
                    .itemTooltip(MenuUtils.createItem(Material.PAPER,
                        "" + ChatColor.RED + "Ban " + target + " for", "" + ChatColor.BLUE + "Hacked client"))
                    .color(ChatColor.YELLOW)
                .then("\n")
                .then("2.")
                    .color(ChatColor.GREEN)
                .then(" Advertising servers")
                    .command("/ban " + target + " Advertising servers")
                    .itemTooltip(MenuUtils.createItem(Material.PAPER,
                            "" + ChatColor.RED + "Ban " + target + " for", "" + ChatColor.BLUE + "Advertising servers"))
                    .color(ChatColor.YELLOW)
                .then("\n")
                .then("3.")
                    .color(ChatColor.GREEN)
                .then(" Impersonating staff")
                    .command("/ban " + target + " Impersonating staff")
                    .itemTooltip(MenuUtils.createItem(Material.PAPER,
                            "" + ChatColor.RED + "Ban " + target + " for", "" + ChatColor.BLUE + "Advertising servers"))
                    .color(ChatColor.YELLOW)
                .then("\n")
                .then("4.")
                    .color(ChatColor.GREEN)
                .then(" Inappropriate name/skin")
                    .command("/ban " + target + " Inappropriate name/skin")
                    .itemTooltip(MenuUtils.createItem(Material.PAPER,
                            "" + ChatColor.RED + "Ban " + target + " for", "" + ChatColor.BLUE + "Inappropriate name/skin"))
                    .color(ChatColor.YELLOW)
                .then("\n")
                .then("5.")
                    .color(ChatColor.GREEN)
                .then(" Other")
                    .suggest("/ban " + target + " ")
                    .itemTooltip(MenuUtils.createItem(Material.PAPER,
                            "" + ChatColor.RED + "Ban " + target + " for", "" + ChatColor.BLUE + "Other"))
                    .color(ChatColor.YELLOW)
                .send(sender)
    }

}