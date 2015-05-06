package net.thenamedev.legendapi.utils;

import org.bukkit.ChatColor;

/**
 * Code stolen from: http://hastebin.com/noqemohuda.java with various changes
 */
public class Cooldown {

    private double seconds;
    private long start;

    public Cooldown(double seconds) {
        this.seconds = seconds;
        this.start = System.currentTimeMillis();
    }

    public boolean done() {
        return ((System.currentTimeMillis() - start) > seconds * 1000);
    }

    public String remainingSeconds() {
        return String.format("%.1f", seconds - ((System.currentTimeMillis() - start) / 1000.0));
    }

    public String getTimeRemaining() {
        return PluginUtils.msgNormal + "Cooldown: " + ChatColor.YELLOW + remainingSeconds() + ChatColor.LIGHT_PURPLE + "/" + ChatColor.YELLOW + seconds + "s";
    }

}
