package net.thenamedev.legendapi;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created on 3/4/2015
 *
 * @author ThePixelDev
 */
public class LegendAPI extends JavaPlugin {

    public static final String apiVersion = "0.1";
    public static final String versionName = "Hello World";

    public static boolean debug = true;

    public void onEnable() {
        //
    }

    public void onDisable() {
        //
    }

    /**
     * Just throws a NullPointerException with the message "I FOUND THE UNDEFINED FUNCTION". That's it.
     *
     * No really, that's all this does.<br><br>
     *
     * and yes was stupidly drunk when making this
     */
    public static void undefined() {
        throw new NullPointerException("I FOUND THE UNDEFINED FUNCTION");
    }

}
