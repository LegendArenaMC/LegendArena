package net.thenamedev.legendarena.extras;

import org.bukkit.Bukkit;

/**
 * don't ask.
 *
 * Created by thepixeldev on 5/19/15.
 */
public class YouGetACar {

    public YouGetACar() {}

    public YouGetACar youGetACar() {
        Bukkit.getLogger().info("You get a car!");
        return this;
    }

    public void everybodyGetsACar() {
        Bukkit.getLogger().info("Everybody gets a car!");
    }

}
