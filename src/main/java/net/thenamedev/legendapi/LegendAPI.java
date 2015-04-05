package net.thenamedev.legendapi;

import net.thenamedev.legendapi.utils.DebugInfo;
import org.bukkit.ChatColor;

/**
 * Created on 3/4/2015
 *
 * @author ThePixelDev
 */
public class LegendAPI {

    public static final String apiVersion = "0.3";
    public static final String versionName = "Hopeful Clouds";

    @Deprecated
    public static final String debugCat =
            ChatColor.BLUE +
            "<insert an ASCII cat here>\n" +
            ChatColor.RED;

    public static final DebugInfo debugInfo = new DebugInfo();

    public static boolean debug = debugInfo.isDebugOn();
    public static boolean extraDebug = debugInfo.isExtraDebugOn(); //this exact mode, can be VERY, VERY, VERY spammy. Only use this if you need it - please.

}
