package net.apcat.simplesit.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerLayEvent extends PlayerEvent implements Cancellable {

	private static HandlerList handlers;
	private Location layLocation;
	private String message;
	private boolean cancel;

	static {
		handlers = new HandlerList();
	}

	public PlayerLayEvent(Player player, Location layLocation, String message) {
		super(player);
		this.cancel = false;
		this.layLocation = layLocation;
		this.message = message;
	}

	public Location getLayLocation() {
		return layLocation;
	}

	public String getMessage() {
		return message;
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

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}
}
