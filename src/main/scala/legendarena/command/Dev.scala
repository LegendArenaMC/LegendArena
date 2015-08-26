/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.command

import org.bukkit.command.{Command, CommandSender, CommandExecutor}

class Dev extends CommandExecutor {

  override def onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array[String]): Boolean = {
    //TODO: Rank checking
    true
  }

}
