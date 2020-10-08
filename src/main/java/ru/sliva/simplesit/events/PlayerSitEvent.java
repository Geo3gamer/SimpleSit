package ru.sliva.simplesit.events;

import org.bukkit.event.*;
import org.bukkit.entity.*;

public class PlayerSitEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private Player player;
    private ArmorStand seat;
    private String message;
    private boolean canceled;
    
    static {
        handlers = new HandlerList();
    }
    
    public PlayerSitEvent(final Player player, final ArmorStand seat, final String message) {
        this.canceled = false;
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
        return PlayerSitEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerSitEvent.handlers;
    }
    
    public boolean isCancelled() {
        return this.canceled;
    }
    
    public void setCancelled(final boolean canceled) {
        this.canceled = canceled;
    }
}
