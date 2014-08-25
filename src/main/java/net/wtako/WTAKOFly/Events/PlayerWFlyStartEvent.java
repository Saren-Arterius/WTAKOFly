package net.wtako.WTAKOFly.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerWFlyStartEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean                  cancelled;
    private final Player             player;
    private double                   expRequired;
    private double                   expCost;

    public PlayerWFlyStartEvent(Player player, double expRequired, double expCost) {
        this.player = player;
        this.expRequired = expRequired;
        this.expCost = expCost;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean arg0) {
        cancelled = arg0;
    }

    public Player getPlayer() {
        return player;
    }

    public double getExpRequired() {
        return expRequired;
    }

    public void setExpRequired(double expRequired) {
        this.expRequired = expRequired;
    }

    public double getExpCost() {
        return expCost;
    }

    public void setExpCost(double expCost) {
        this.expCost = expCost;
    }

    @Override
    public HandlerList getHandlers() {
        return PlayerWFlyStartEvent.handlers;
    }

    public static HandlerList getHandlerList() {
        return PlayerWFlyStartEvent.handlers;
    }

}
