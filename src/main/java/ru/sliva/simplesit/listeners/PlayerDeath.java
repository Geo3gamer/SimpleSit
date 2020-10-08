package ru.sliva.simplesit.listeners;

import org.bukkit.event.entity.*;

import ru.sliva.simplesit.*;

import org.bukkit.event.*;

public class PlayerDeath implements Listener
{
    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent e) {
        final SimpleSitPlayer player = new SimpleSitPlayer(e.getEntity());
        if (player.isSitting()) {
            player.setSitting(false);
        }
    }
}
