package net.wtako.WTAKOFly.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerWFlyEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player             player;

    public PlayerWFlyEndEvent(Player player) {
        this.player = player;

    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return PlayerWFlyEndEvent.handlers;
    }

    public static HandlerList getHandlerList() {
        return PlayerWFlyEndEvent.handlers;
    }

}
