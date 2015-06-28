package legendarena;

import legendapi.KotlinTest;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * fucking Bukkit
 *
 * @author ThePixelDev
 */
public class LegendArenaLoader extends JavaPlugin {

    public void onEnable() {
        //fucking bukkit dependencies
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                boolean failed = false;
                try {
                    new KotlinTest().test();
                } catch(Exception ex) {
                    failed = true;
                }

                if(failed) {
                    while(failed) {
                        try {
                            Thread.sleep(2000);
                        } catch(InterruptedException ex) {
                            //
                        }
                        failed = false;
                        try {
                            new KotlinTest().test();
                        } catch(Exception ex) {
                            failed = true;
                        }
                    }
                }
                new LegendArena().onEnable();
            }
        }, 40l);
    }

    public void onDisable() {
        new LegendArena().onDisable();
    }

}
