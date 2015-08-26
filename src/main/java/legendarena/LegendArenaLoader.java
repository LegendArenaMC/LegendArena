/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.logging.Level;

/**
 * fuck you too bukkit, fuck you with a cactus
 */
public class LegendArenaLoader extends JavaPlugin {

    public void onEnable() {
        Plugin p = this;
        Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
            @Override
            public void run() {
                boolean failed = false;

                try {
                    Class.forName("legendapi.utils.ScalaTest").getMethod("test");
                } catch(Exception ex) {
                    failed = true;
                }

                if(failed)
                    while(failed) {
                        try {
                            Bukkit.getLogger().log(Level.INFO, "Scala wasn't found yet, waiting 0.5 seconds and trying again...");
                            Thread.sleep(500);
                        } catch(InterruptedException ex) {
                            //
                        }

                        failed = false;

                        try {
                            Class.forName("legendapi.utils.ScalaTest").getMethod("test");
                        } catch(Exception ex) {
                            failed = true;
                        }
                    }
                try {
                    Class.forName("legendarena.LegendArena").getMethod("onEnable");
                } catch(NoSuchMethodException | ClassNotFoundException ex) {
                    //
                }
            }
        }, 20l);
    }

    public void onDisable() {
        //
    }

}
