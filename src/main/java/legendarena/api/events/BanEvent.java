package legendarena.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when a player is banned. That's it. It's that simple.<br>
 *
 * Created by thepixeldev on 5/3/15.
 */
public class BanEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final String reason;
    private final Player actingStaff;
    private final String receivingAction;
    private boolean cancelled = false;

    public BanEvent(String reason, Player staff, String target) {
        this.receivingAction = target;
        this.actingStaff = staff;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public Player getStaff() {
        return actingStaff;
    }

    public String getTarget() {
        return receivingAction;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

}