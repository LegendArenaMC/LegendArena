package net.thenamedev.legendarena.utils;

import net.thenamedev.legendarena.LegendArena;

/**
 * Technical details for the interested (a summed up version can be found in the default config file):
 *
 * This feature connects to the TheNameDev download 'server' (is it a server? whatever, it is now), gets a JSON file managed by approved
 * Legend Arena staff, closes the connection to the server, does checks against the JSON file, and if it detects the following:
 *
 * - Local version >= Released version
 * - Development mode is on
 * - Or, can't reach the server
 *
 * It exits and returns false for the needsUpdating() function. Otherwise, if it detects the following:
 *
 * - Local version < Released version
 * - And development mode is off
 *
 * It returns a true, and thus the server prints a message in the console saying so, and it puts a (slightly annoying, but not as intrusive)
 * message in the /dev command, and tells people with the FOUNDER rank on login that there is a new version released.
 *
 * Also if the system breaks down yell at Pixel all you want (unless he's fixing it, then... don't?).
 *
 * Created on 4/15/2015
 *
 * @author ThePixelDev
 */
public class UpdateCheckUtils {

    private static boolean isInit = false;

    public static void init() {
        if(isInit)
            return;
        //
    }

    /**
     * Front-end version of checking updates.
     *
     * @return If the plugin needs updating
     */
    public static boolean needsUpdating() {
        return newUpdateAvailable().isUpdateAvailable();
    }

    /**
     * Internal version of the update checker.
     *
     * @return The latest update package from the server
     */
    private static UpdatePackage newUpdateAvailable() {
        return null;
    }

    private class UpdatePackage {

        private String severity;
        private boolean isUpdateAvailable;

        public UpdatePackage(double newVersion, double currentVersion, String severity) {
            this.severity = severity;
            //noinspection PointlessBooleanExpression
            this.isUpdateAvailable = (newVersion != currentVersion) && LegendArena.Version.isDevBuild;
        }

        public boolean isUpdateAvailable() {
            return isUpdateAvailable;
        }

    }

}
