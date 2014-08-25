package net.wtako.WTAKOFly.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerWFlyTickEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean                  cancelled;
    private final Player             player;
    private double                   expRequired;

    public PlayerWFlyTickEvent(Player player, double expRequired) {
        this.player = player;
        this.expRequired = expRequired;
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

    @Override
    public HandlerList getHandlers() {
        return PlayerWFlyTickEvent.handlers;
    }

    public static HandlerList getHandlerList() {
        return PlayerWFlyTickEvent.handlers;
    }

}
