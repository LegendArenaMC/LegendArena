package net.thenamedev.legendapi;

import net.thenamedev.legendapi.utils.DebugInfo;

/**
 * Created on 3/4/2015
 *
 * @author ThePixelDev
 */
public class LegendAPI {

    public static final String apiVersion = "0.4";
    public static final String versionName = "Protective Horse";

    public static final DebugInfo debugInfo = new DebugInfo();

    public static boolean debug = debugInfo.isDebugOn();
    public static boolean extraDebug = debugInfo.isExtraDebugOn(); //this exact mode, can be VERY, VERY, VERY spammy. Only use this if you need it - please.

}
