/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.commands.staff

import legendarena.api.fanciful.FancyMessage
import legendarena.api.utils.*
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Autoban : CommandExecutor {

    override fun onCommand(sender: CommandSender, p1: Command?, p2: String?, args: Array<out String>): Boolean {
        if(!Rank.MOD.isRanked(sender as Player)) {
            RankUtils.fancyNoPermissions(Rank.MOD, sender)
            return true
        }

        if(StringUtils.getSize(args) == 0) {
            sender.sendMessage(ChatUtils.getFormattedMsg("/autoban <player>", "Bans a player with a selection of options."))
            return true
        }

        run(sender, args[0])
        return true
    }

    public fun run(sender: CommandSender, target: String) {
        //walls of code are fun (/s)
        //also no this isn't a gui. because fuck you. [tm]
        //..that, and I don't want to bother writing a AutoBanGUI class.
        FancyMessage("Banning player ")
                    .color(ChatColor.GREEN)
                .then(target)
                    .color(ChatColor.RED)
                .then(".")
                    .color(ChatColor.GREEN)
                .then("\n\n")
                .then("Common reasons:")
                    .color(ChatColor.GREEN)
                .then("\n")
                .then("1.")
                    .color(ChatColor.GREEN)
                .then(" Hacked client")
                    .suggest("/ban $target Cheating - ")
                    .tooltip("" + ChatColor.RED + "Ban " + target + " for\n" + ChatColor.BLUE + "Hacked client")
                    .color(ChatColor.YELLOW)
                .then("\n")
                .then("2.")
                    .color(ChatColor.GREEN)
                .then(" Advertising servers")
                    .command("/ban $target Advertising servers")
                    .tooltip("" + ChatColor.RED + "Ban " + target + " for\n" + ChatColor.BLUE + "Advertising servers")
                    .color(ChatColor.YELLOW)
                .then("\n")
                .then("3.")
                    .color(ChatColor.GREEN)
                .then(" Impersonating staff")
                    .command("/ban $target Impersonating staff")
                    .tooltip("" + ChatColor.RED + "Ban " + target + " for\n" + ChatColor.BLUE + "Impersonating staff")
                    .color(ChatColor.YELLOW)
                .then("\n")
                .then("4.")
                    .color(ChatColor.GREEN)
                .then(" Inappropriate name/skin")
                    .command("/ban $target Inappropriate name/skin")
                    .tooltip("" + ChatColor.RED + "Ban " + target + " for\n" + ChatColor.BLUE + "Inappropriate name/skin")
                    .color(ChatColor.YELLOW)
                .then("\n")
                .then("5.")
                    .color(ChatColor.GREEN)
                .then(" Other")
                    .suggest("/ban $target ")
                    .tooltip("" + ChatColor.RED + "Ban " + target + " for\n" + ChatColor.BLUE + "Other")
                    .color(ChatColor.YELLOW)
                .send(sender)
    }

}