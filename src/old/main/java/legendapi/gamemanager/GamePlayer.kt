package legendapi.gamemanager

import org.bukkit.entity.Player

class GamePlayer {

    private var p: Player? = null
    private var state: GamePlayerState? = null

    public constructor(p: Player, state: GamePlayerState) {
        this.p = p
        this.state = state
    }

    /**
     * Get the player instance
     * @return The player instance
     */
    public fun getPlayer(): Player {
        return p!!
    }

    /**
     * Get the state of the player
     * @return The player's state
     * @see GamePlayerState
     */
    public fun getState(): GamePlayerState {
        return state!!
    }

    /**
     * Set the state of the player
     * @param state The state to set to
     */
    public fun setState(state: GamePlayerState) {
        this.state = state
        if(state == GamePlayerState.SPECTATOR) {
            getPlayer().spigot().setCollidesWithEntities(false)
        }
    }

}