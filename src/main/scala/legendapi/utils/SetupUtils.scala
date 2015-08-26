/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.utils

import java.util.logging.Level

import legendapi.LegendAPI
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

//noinspection JavaAccessorMethodCalledAsEmptyParen
class SetupUtils(p: Plugin) {

  val pm = Bukkit.getPluginManager()
  val logger = Bukkit.getLogger()

  def setupCommand(cmdName: String, cmd: CommandExecutor): Unit = {
    if(LegendAPI.debug)
      logger.log(Level.INFO, "DEBUG > Setting up command \"" + cmdName + "\"...")
    Bukkit.getPluginCommand(cmdName).setExecutor(cmd)
  }

  def setupListener(listener: Listener): Unit = {
    pm.registerEvents(listener, p)
  }

  def setupAliases(cmd: String, aliases: Array[String]): Unit = {
    //horrible hacky workarounds! yay! (this took way too long to do.)
    var build = ListBuffer.newBuilder[String]

    for(a: String <- aliases)
      build.+=(a)

    val alias = build.result().result()

    Bukkit.getPluginCommand(cmd).setAliases(alias.asJava)
  }

  def setupTimer(run: Runnable, time: Int): Unit = {
    Bukkit.getScheduler().runTaskTimer(p, run, time, time)
  }

}
