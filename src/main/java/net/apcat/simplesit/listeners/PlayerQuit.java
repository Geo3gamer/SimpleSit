package net.apcat.simplesit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.apcat.simplesit.SimpleSitPlayer;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		SimpleSitPlayer player = new SimpleSitPlayer(e.getPlayer());
		if (player.isSitting()) {
			player.setSitting(false);
		}
	}
}
