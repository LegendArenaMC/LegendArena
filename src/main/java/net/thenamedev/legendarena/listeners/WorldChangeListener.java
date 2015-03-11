package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.hub.warp.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

/**
 * @author ThePixelDev
 */
public class WorldChangeListener implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent ev) {
        if(!ev.getFrom().getName().equalsIgnoreCase("hub") && !ev.getPlayer().getWorld().getName().equalsIgnoreCase("hub")) return;
        if(Rank.getRank(ev.getPlayer()) == Rank.VIP) {
            ev.getPlayer().setAllowFlight(false);
            ev.getPlayer().setFlying(false);
        }
        if(ev.getPlayer().getWorld().getName().equalsIgnoreCase("hub")) {
            if(HubWarper.hidePlayersHolding.contains(ev.getPlayer().getUniqueId())) {
                HubWarper.hidePlayers.add(ev.getPlayer().getUniqueId());
                HubWarper.hidePlayersHolding.remove(ev.getPlayer().getUniqueId());
            }
        } else {
            if(HubWarper.hidePlayers.contains(ev.getPlayer().getUniqueId())) {
                HubWarper.hidePlayersHolding.add(ev.getPlayer().getUniqueId());
                HubWarper.hidePlayers.remove(ev.getPlayer().getUniqueId());
            }
        }
    }

}
