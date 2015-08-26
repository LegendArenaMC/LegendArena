/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena

import legendapi.utils.SetupUtils
import legendarena.command.Dev
import org.bukkit.plugin.java.JavaPlugin

class LegendArena extends JavaPlugin {

  override def onEnable(): Unit = {
    val setup = new SetupUtils(this)
    setup.setupCommand("dev", new Dev())
  }

  override def onDisable(): Unit = {
    //TODO: Cleanup
  }

}