package net.apcat.simplesit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.apcat.simplesit.events.PlayerSitEvent;
import net.apcat.simplesit.events.PlayerStopSittingEvent;

public class SimpleSitPlayer {

	private Player player;
	private SimpleSit simpleSit;

	public SimpleSitPlayer(final Player player) {
		this.player = player;
		this.simpleSit = JavaPlugin.getPlugin(SimpleSit.class);
	}

	public Player getBukkitPlayer() {
		return player;
	}

	public void setSitting(boolean value) {
		if (value && !isSitting()) {
			Location location = player.getLocation();
			ArmorStand seat = location.getWorld().spawn(location.clone().subtract(0.0, 1.7, 0.0), ArmorStand.class);
			seat.setGravity(false);
			seat.setVisible(false);
			PlayerSitEvent playerSitEvent = new PlayerSitEvent(player, seat, simpleSit.sitDownMessage);
			Bukkit.getPluginManager().callEvent(playerSitEvent);
			if (playerSitEvent.isCancelled()) {
				seat.remove();
			} else {
				player.sendMessage(playerSitEvent.getMessage());
				seat.addPassenger(player);
				simpleSit.seats.put(player.getUniqueId(), seat);
			}
		} else if (!value && isSitting()) {
			ArmorStand seat2 = simpleSit.seats.get(player.getUniqueId());
			PlayerStopSittingEvent playerStopSittingEvent = new PlayerStopSittingEvent(player, seat2, simpleSit.sitUpMessage);
			Bukkit.getPluginManager().callEvent(playerStopSittingEvent);
			player.sendMessage(playerStopSittingEvent.getMessage());
			simpleSit.seats.remove(player.getUniqueId());
			player.eject();
			player.teleport(seat2.getLocation().clone().add(0.0, 1.7, 0.0));
			seat2.remove();
		}
	}

	public boolean isSitting() {
		return simpleSit.seats.containsKey(player.getUniqueId());
	}
}
