package net.thenamedev.legendarena.extras.hub.hideplayer;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.hub.warp.*;
import org.bukkit.*;
import org.bukkit.entity.*;

/**
 * @author ThePixelDev
 */
public class HidePlayers implements Runnable {

    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(HubWarper.hidePlayers.contains(p.getUniqueId())) {
                for(Player h : Bukkit.getOnlinePlayers()) {
                    if(Rank.getRank(h, Rank.Helper)) continue;
                    p.hidePlayer(h);
                }
            } else {
                for(Player h : Bukkit.getOnlinePlayers()) {
                    p.showPlayer(h);
                }
            }
        }
    }

}
