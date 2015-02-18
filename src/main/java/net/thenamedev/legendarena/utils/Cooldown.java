package net.thenamedev.legendarena.utils;

import org.bukkit.*;
import org.jetbrains.annotations.*;

/**
 * Code stolen from: http://hastebin.com/noqemohuda.java
 */
public class Cooldown {

    private int seconds;
    private int total;
    private long start;

    public Cooldown(int seconds) {
        this.seconds = seconds;
        this.total = seconds;

        this.start = System.currentTimeMillis();
    }

    public boolean done() {
        return ((System.currentTimeMillis() - start) > seconds * 1000);
    }

    private String remainingSeconds() {
        long number = System.currentTimeMillis() - start;

        return String.format("%.1f", seconds - (number / 1000.0));
    }

    @NotNull
    public String getTimeRemaining() {
        return PluginUtils.msgNormal + "Cooldown: " + ChatColor.YELLOW + remainingSeconds() + ChatColor.GRAY + "/" + ChatColor.YELLOW + total + "s";
    }

}
