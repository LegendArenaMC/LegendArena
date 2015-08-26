/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena

import legendapi.log.{Level, BukLog}
import legendapi.utils.SetupUtils
import legendarena.command.Dev
import org.bukkit.Bukkit

class LegendArena {

  def onEnable(): Unit = {
    //noinspection JavaAccessorMethodCalledAsEmptyParen
    val setup = new SetupUtils(Bukkit.getPluginManager().getPlugin("LegendArena"))
    setup.setupCommand("dev", new Dev())
  }

  def onDisable(): Unit = {
    //TODO: Cleanup
  }

}