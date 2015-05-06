package net.thenamedev.legendapi.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when an action (kick, ban, warn, etc) is done on a player.
 *
 * Created by thepixeldev on 5/3/15.
 */
public final class PlayerActionEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final String reason;
    private final Player actingStaff;
    private final Action action;
    private final Player receivingAction;
    private boolean cancelled = false;

    public PlayerActionEvent(String reason, Player staff, Player target, Action action) {
        this.receivingAction = target;
        this.actingStaff = staff;
        this.reason = reason;
        this.action = action;
    }

    public String getReason() {
        return reason;
    }

    public Action getAction() {
        return action;
    }

    public Player getStaff() {
        return actingStaff;
    }

    public Player getTarget() {
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

    public static enum Action {
        KICK,
        WARN,
        BAN
    }

}