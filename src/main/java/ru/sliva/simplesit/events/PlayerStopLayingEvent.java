package ru.sliva.simplesit.events;

import org.bukkit.event.*;
import org.bukkit.entity.*;

public class PlayerStopLayingEvent extends Event
{
    private static final HandlerList handlers;
    private Player player;
    private String message;
    
    static {
        handlers = new HandlerList();
    }
    
    public PlayerStopLayingEvent(final Player player, final String message) {
        this.player = player;
        this.message = message;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public HandlerList getHandlers() {
        return PlayerStopLayingEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerStopLayingEvent.handlers;
    }
}
