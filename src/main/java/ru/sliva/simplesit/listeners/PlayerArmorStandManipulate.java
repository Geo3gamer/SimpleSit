package ru.sliva.simplesit.listeners;

import org.bukkit.event.player.*;

import ru.sliva.simplesit.*;

import org.bukkit.event.*;

public class PlayerArmorStandManipulate implements Listener
{
    @EventHandler
    public void onPlayerArmorStandManipulate(final PlayerArmorStandManipulateEvent e) {
        final SimpleSitArmorStand simpleSitArmorStand = new SimpleSitArmorStand(e.getRightClicked());
        if (simpleSitArmorStand.isSeat()) {
            e.setCancelled(true);
        }
    }
}
