/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.client

import legendapi.utils.Rank
import legendapi.utils.Rank._
import org.bukkit.entity.Player

//noinspection AccessorLikeMethodIsEmptyParen
class Client(p: Player) {

  def getRank(): Rank = {
    if(FOUNDER.isRanked(p))
      FOUNDER
    else if(DEV.isRanked(p))
      DEV
    else if(ADMIN.isRanked(p))
      ADMIN
    else if(MOD.isRanked(p))
      MOD
    else if(HELPER.isRanked(p))
      HELPER
    else if(VIP.isRanked(p))
      VIP
    else if(MEMBERPLUS.isRanked(p))
      MEMBERPLUS
    else
      MEMBER
  }

  def isBanned(): Boolean = {
    false
  }

}
