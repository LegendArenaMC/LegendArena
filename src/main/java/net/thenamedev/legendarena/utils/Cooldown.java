package net.thenamedev.legendarena.utils;

import org.bukkit.*;

/**
 * Code stolen from: http://hastebin.com/noqemohuda.java
 * @author TheNameMan
 */
public class Cooldown {

    private int seconds;

    private long start;

    public Cooldown(int seconds) {
        this.seconds = seconds;

        this.start = System.currentTimeMillis();
    }

    public boolean done() {
        return (System.currentTimeMillis() - start) > seconds * 1000;
    }

    private String remainingSeconds() {
        long number = System.currentTimeMillis() - start;

        return String.format("%.1f", seconds - (number / 1000.0));
    }

    public String getTimeRemaining() {
        return ChatColor.RED + "You must wait another " + ChatColor.BLUE + remainingSeconds() + " seconds" + ChatColor.RED + " to use this again.";
    }

}
