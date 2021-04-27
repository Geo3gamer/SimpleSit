package net.apcat.simplesit.events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerStopSittingEvent extends PlayerEvent {
	
    private static HandlerList handlers;
    private ArmorStand seat;
    private String message;
    
    static {
        handlers = new HandlerList();
    }
    
    public PlayerStopSittingEvent(Player player, ArmorStand seat, String message) {
        super(player);
        this.seat = seat;
        this.message = message;
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
