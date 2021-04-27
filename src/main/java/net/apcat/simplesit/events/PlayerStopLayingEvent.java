package net.apcat.simplesit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerStopLayingEvent extends PlayerEvent {
	
    private static HandlerList handlers;
    private String message;
    
    static {
        handlers = new HandlerList();
    }
    
    public PlayerStopLayingEvent(Player player, String message) {
    	super(player);
        this.message = message;
    }
    
    public String getMessage() {
        return message;
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
