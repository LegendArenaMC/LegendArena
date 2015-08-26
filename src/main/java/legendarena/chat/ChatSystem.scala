/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.chat

import java.util
import java.util.UUID

import org.bukkit.entity.Player

object ChatSystem {

  val channels: util.HashMap[UUID, Channel] = new util.HashMap[UUID, Channel]
  var globalMute = false

  def isChatMuted(): Boolean = {
    globalMute
  }

  def setGlobalMute(set: Boolean): Unit = {
    globalMute = set
  }

  /**
   * Gets the channel of a certain player.
   * @param p The player to get the channel of
   * @return The player's channel
   */
  def getChannel(p: Player): Channel = {
    if(!channels.containsKey(p.getUniqueId)) return Channel.GLOBAL
    channels.get(p.getUniqueId)
  }

}
