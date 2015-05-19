package net.thenamedev.legendapi.gamemanager;

/**
 * Created on 5/16/2015
 *
 * @author ThePixelDev
 */
public interface GameTimer extends Runnable {

    void run();

    int delay();

}
