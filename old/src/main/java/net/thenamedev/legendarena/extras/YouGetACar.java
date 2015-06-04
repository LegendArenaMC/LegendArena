package net.thenamedev.legendarena.extras;

import org.bukkit.Bukkit;

/**
 * don't ask. just don't.
 *
 * @author ThePixelDev
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
