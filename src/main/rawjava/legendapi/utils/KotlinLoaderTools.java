package legendapi.utils;

import legendapi.KotlinTest;
import legendapi.exceptions.FailedKotlinException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

public class KotlinLoaderTools {

    private KotlinUtils c;
    private int[] attempt = {1};
    private Plugin p;

    public KotlinLoaderTools(KotlinUtils c, Plugin p) {
        this.c = c;
        this.p = p;
    }

    public void run() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
            @Override
            public void run() {
                boolean failed = false;

                try {
                    new KotlinTest().test();
                } catch(Exception ex) {
                    failed = true;
                }

                if(failed)
                    while(failed) {
                        if(attempt[0] >= 15)
                            throw new FailedKotlinException();

                        try {
                            Bukkit.getLogger().log(Level.INFO, "Kotlin wasn't found yet, waiting 0.5 seconds and trying again... [Attempt #" + attempt[0] + "]");
                            attempt[0]++;
                            Thread.sleep(500);
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
                c.onEnable();
            }
        }, 20l);
    }

}
