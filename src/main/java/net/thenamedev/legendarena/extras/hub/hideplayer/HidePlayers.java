package net.thenamedev.legendarena.extras.hub.hideplayer;

import net.thenamedev.legendapi.utils.OldRank;
import net.thenamedev.legendarena.commands.backends.Vanish;
import net.thenamedev.legendarena.extras.hub.warp.HubWarper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author ThePixelDev
 */
public class HidePlayers implements Runnable {

    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(HubWarper.hidePlayers.contains(p.getUniqueId())) {
                for(Player h : Bukkit.getOnlinePlayers()) {
                    if(OldRank.getRank(h, OldRank.Helper)) continue;
                    p.hidePlayer(h);
                }
            } else {
                for(Player h : Bukkit.getOnlinePlayers()) {
                    if(Vanish.vanishedPlayers.contains(h.getUniqueId())) {
                        continue;
                    }
                    p.showPlayer(h);
                }
            }
        }
    }

}
