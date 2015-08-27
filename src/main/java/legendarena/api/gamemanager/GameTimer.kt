package legendarena.api.gamemanager

import legendarena.api.gamemanager.GameTool

interface GameTimer : Runnable, GameTool {

    /**
     * Main timer
     */
    override fun run()

    /**
     * The delay, in ticks (to get the ticks for seconds, seconds * 20)
     */
    public fun delay(): Long

}