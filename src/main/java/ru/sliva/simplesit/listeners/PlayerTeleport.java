package ru.sliva.simplesit.listeners;

import org.bukkit.event.player.*;

import ru.sliva.simplesit.*;

import org.bukkit.event.*;

public class PlayerTeleport implements Listener
{
    @EventHandler
    public void onPlayerTeleport(final PlayerTeleportEvent e) {
        final SimpleSitPlayer player = new SimpleSitPlayer(e.getPlayer());
        if (player.isSitting()) {
            player.setSitting(false);
        }
    }
}
