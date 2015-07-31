/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.commands.staff.punish

import legendapi.utils.StaffPlayer
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Ban : CommandExecutor {

    override fun onCommand(sender: CommandSender?, p1: Command?, p2: String?, args: Array<out String>?): Boolean {
        //
        return true
    }

    public fun run(p: Player, staff: StaffPlayer) {
        //
    }

}
