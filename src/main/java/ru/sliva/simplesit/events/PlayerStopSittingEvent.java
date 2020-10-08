package ru.sliva.simplesit.events;

import org.bukkit.event.*;
import org.bukkit.entity.*;

public class PlayerStopSittingEvent extends Event
{
    private static final HandlerList handlers;
    private Player player;
    private ArmorStand seat;
    private String message;
    
    static {
        handlers = new HandlerList();
    }
    
    public PlayerStopSittingEvent(final Player player, final ArmorStand seat, final String message) {
        this.player = player;
        this.seat = seat;
        this.message = message;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public ArmorStand getSeat() {
        return this.seat;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public HandlerList getHandlers() {
        return PlayerStopSittingEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerStopSittingEvent.handlers;
    }
}
