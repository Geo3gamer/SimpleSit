package net.apcat.simplesit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

import net.apcat.simplesit.SimpleSitArmorStand;

public class PlayerArmorStandManipulate implements Listener {

	@EventHandler
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent e) {
		SimpleSitArmorStand simpleSitArmorStand = new SimpleSitArmorStand(e.getRightClicked());
		if (simpleSitArmorStand.isSeat()) {
			e.setCancelled(true);
		}
	}
}
