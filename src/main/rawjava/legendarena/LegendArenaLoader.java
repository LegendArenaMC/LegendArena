package legendarena;

import legendapi.utils.KotlinLoaderTools;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * fucking Bukkit
 *
 * @author ThePixelDev
 */
public class LegendArenaLoader extends JavaPlugin {

    public void onEnable() {
        //fucking bukkit dependencies not working how they should
        new KotlinLoaderTools(new LegendArena(), this).run();
    }

    public void onDisable() {
        new LegendArena().onDisable();
    }

}
