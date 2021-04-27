package net.apcat.simplesit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import net.apcat.simplesit.SimpleSitPlayer;

public class PlayerTeleport implements Listener {
	
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        SimpleSitPlayer player = new SimpleSitPlayer(e.getPlayer());
        if (player.isSitting()) {
            player.setSitting(false);
        }
    }
}
