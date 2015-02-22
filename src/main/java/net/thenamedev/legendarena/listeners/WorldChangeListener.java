package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.warp.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class WorldChangeListener implements Listener {

    @EventHandler
    public void onWorldChange(@NotNull PlayerChangedWorldEvent ev) {
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
