package net.thenamedev.legendarena.extras.hideplayer;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.extras.warp.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class HidePlayers implements Runnable {

    public void run() {
        for(@NotNull Player p : Bukkit.getOnlinePlayers()) {
            if(HubWarper.hidePlayers.contains(p.getUniqueId())) {
                for(@NotNull Player h : Bukkit.getOnlinePlayers()) {
                    if(Rank.getRank(h, Rank.GM)) continue;
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
