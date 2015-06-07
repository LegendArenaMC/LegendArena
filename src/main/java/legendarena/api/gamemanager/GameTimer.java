package legendarena.api.gamemanager;

/**
 * Game timer.
 *
 * @author ThePixelDev
 */
public interface GameTimer extends Runnable {

    /**
     * Main timer
     */
    void run();

    /**
     * The delay, in ticks (to get the ticks for seconds, seconds * 20)
     */
    int delay();

}
