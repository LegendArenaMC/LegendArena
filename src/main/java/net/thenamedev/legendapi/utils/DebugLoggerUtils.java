package net.thenamedev.legendapi.utils;

import org.bukkit.Bukkit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created on 5/31/2015
 *
 * @author ThePixelDev
 */
public class DebugLoggerUtils {

    public static void log(String text) {
        BufferedWriter output = null;
        try {
            File file = new File(Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().getAbsolutePath() + "");
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
        } catch(IOException e) {
            //ignore
        } finally {
            if(output != null) try {
                output.close();
            } catch(IOException e) {
                //ignore
            }
        }
    }

}
