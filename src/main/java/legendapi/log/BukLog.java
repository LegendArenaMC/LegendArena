/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.log;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class BukLog {

    public BukLog(Plugin p) {
        //
    }

    public void log(Level l, String m) {
        Bukkit.getLogger().info("[BukLog | " + l + "] " + m);
    }

    public void dumpError(Exception e, String r) {
        //do nothing at all for now
    }

    public void dumpRawError(Exception e, String r) {
        //do nothing at all for now
    }

}
