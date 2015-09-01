/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.listeners

import legendarena.api.utils.Rank
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

public class PlayerCommandListener : Listener {

    EventHandler
    public fun listenForCommand(ev: PlayerCommandPreprocessEvent) {
        if(Rank.NOOB.isRanked(null)) {

        }
    }

}