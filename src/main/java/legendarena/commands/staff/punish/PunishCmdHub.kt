/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.commands.staff.punish

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
//import org.bukkit.entity.Player

class PunishCmdHub : CommandExecutor {

    override fun onCommand(sender: CommandSender?, ignored: Command?, cmd: String?, args: Array<out String>?): Boolean {
        if(cmd == "ban") {
            //Ban().run(null, (sender as Player))
        }
        return true
    }

}