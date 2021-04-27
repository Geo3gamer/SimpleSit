package net.apcat.simplesit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.apcat.simplesit.SimpleSitPlayer;

public class PlayerDeath implements Listener {
	
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        SimpleSitPlayer player = new SimpleSitPlayer(e.getEntity());
        if (player.isSitting()) {
            player.setSitting(false);
        }
    }
}
