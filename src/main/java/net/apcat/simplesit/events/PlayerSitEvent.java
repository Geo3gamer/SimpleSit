package net.apcat.simplesit.events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerSitEvent extends PlayerEvent implements Cancellable {

	private static HandlerList handlers;
	private ArmorStand seat;
	private String message;
	private boolean cancel;

	static {
		handlers = new HandlerList();
	}

	public PlayerSitEvent(Player player, ArmorStand seat, String message) {
		super(player);
		this.cancel = false;
		this.seat = seat;
		this.message = message;
	}

	public ArmorStand getSeat() {
		return seat;
	}

	public String getMessage() {
		return message;
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

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}
}
