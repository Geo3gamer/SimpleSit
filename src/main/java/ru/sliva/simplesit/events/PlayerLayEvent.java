package ru.sliva.simplesit.events;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class PlayerLayEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private Player player;
    private Location layLocation;
    private String message;
    private boolean canceled;
    
    static {
        handlers = new HandlerList();
    }
    
    public PlayerLayEvent(final Player player, final Location layLocation, final String message) {
        this.canceled = false;
        this.player = player;
        this.layLocation = layLocation;
        this.message = message;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Location getLayLocation() {
        return this.layLocation;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public HandlerList getHandlers() {
        return PlayerLayEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerLayEvent.handlers;
    }
    
    public boolean isCancelled() {
        return this.canceled;
    }
    
    public void setCancelled(final boolean canceled) {
        this.canceled = canceled;
    }
}
