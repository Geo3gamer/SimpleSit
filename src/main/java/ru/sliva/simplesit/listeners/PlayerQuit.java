package ru.sliva.simplesit.listeners;

import org.bukkit.event.player.*;

import ru.sliva.simplesit.*;

import org.bukkit.event.*;

public class PlayerQuit implements Listener
{
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e) {
        final SimpleSitPlayer player = new SimpleSitPlayer(e.getPlayer());
        if (player.isSitting()) {
            player.setSitting(false);
        }
    }
}
