/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.api.gamemanager

import legendarena.api.utils.SetupUtils
import org.bukkit.plugin.Plugin

class Game {

    var p: Plugin? = null
    var setup: SetupUtils? = null

    constructor(p: Plugin) {
        //TODO: Plugin field init stuffs
        this.p = p
        this.setup = SetupUtils(p)
    }

    fun register(reg: GameTool) {
        if(reg is GameListener) {
            //register as a listener
            setup!!.registerListener(reg)
        } else if(reg is GameTimer) {
            //register as a non async timer
            setup!!.registerNonAsyncTimer(reg, reg.delay())
        }
    }

    fun setState(state: GameState) {
        //TODO: State stuff
        //TODO: Announce network status
    }

    internal fun announceNetworkStatus(state: GameState) {
        //TODO: Mass field of if checks to see if the config has this is a network or a singular server
    }

}