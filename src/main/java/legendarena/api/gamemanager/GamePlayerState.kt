package legendarena.api.gamemanager

enum class GamePlayerState private constructor(private val canInteract: Boolean) {

    /**
     * Spectator mode - cannot do anything except for just watching
     */
    SPECTATOR(false),
    /**
     * Playing mode - can actually play
     */
    PLAYING(true);

    /**
     * Can the player interact in the game (e.g open chests, etc)
     * @return `true` if the player can interact, `false` otherwise
     */
    public fun canInteractWithGame(): Boolean {
        return canInteract
    }

}