/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.module

import legendapi.utils.ChatUtils

//noinspection AccessorLikeMethodIsEmptyParen
class Module(modName: String) {

  def getModuleName(): String = ChatUtils.getCustomMsg(modName)

}
