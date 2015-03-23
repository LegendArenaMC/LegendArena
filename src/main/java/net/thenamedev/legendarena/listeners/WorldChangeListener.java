package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.hub.warp.HubWarper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

/**
 * @author ThePixelDev
 */
public class WorldChangeListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onWorldChange(PlayerChangedWorldEvent ev) {
        if(!ev.getFrom().getName().equalsIgnoreCase("hub") && !ev.getPlayer().getWorld().getName().equalsIgnoreCase("hub")) return;
        if(Rank.getRank(ev.getPlayer()) == Rank.YOUTUBE || Rank.getRank(ev.getPlayer()) == Rank.TWITCH || Rank.getRank(ev.getPlayer()) == Rank.FAMOUS) {
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
