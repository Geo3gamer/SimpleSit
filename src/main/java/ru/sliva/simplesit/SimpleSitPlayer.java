package ru.sliva.simplesit;

import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.*;

import ru.sliva.simplesit.events.*;
import ru.sliva.simplesit.utils.*;

import java.lang.reflect.*;

public class SimpleSitPlayer
{
    private Player player;
    private SimpleSit simpleSit;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public SimpleSitPlayer(final Player player) {
        this.player = player;
        this.simpleSit = (SimpleSit)JavaPlugin.getPlugin((Class)SimpleSit.class);
    }
    
    public Player getBukkitPlayer() {
        return this.player;
    }
    
    public void setSitting(final boolean arg) {
        if (arg && !this.isSitting()) {
            final Location location = this.player.getLocation();
            @SuppressWarnings({ "unchecked", "rawtypes" })
			final ArmorStand seat = (ArmorStand)location.getWorld().spawn(location.clone().subtract(0.0, 1.7, 0.0), (Class)ArmorStand.class);
            seat.setGravity(false);
            seat.setVisible(false);
            final PlayerSitEvent playerSitEvent = new PlayerSitEvent(this.player, seat, this.simpleSit.getSitDownMessage());
            Bukkit.getPluginManager().callEvent((Event)playerSitEvent);
            if (playerSitEvent.isCancelled()) {
                seat.remove();
                return;
            }
            this.player.sendMessage(playerSitEvent.getMessage());
            seat.addPassenger(this.player);
            this.simpleSit.getSeats().put(this.player.getUniqueId(), seat);
        }
        else if (!arg && this.isSitting()) {
            final ArmorStand seat2 = this.simpleSit.getSeats().get(this.player.getUniqueId());
            final PlayerStopSittingEvent playerStopSittingEvent = new PlayerStopSittingEvent(this.player, seat2, this.simpleSit.getSitUpMessage());
            Bukkit.getPluginManager().callEvent((Event)playerStopSittingEvent);
            this.player.sendMessage(playerStopSittingEvent.getMessage());
            this.simpleSit.getSeats().remove(this.player.getUniqueId());
            this.player.eject();
            this.player.teleport(seat2.getLocation().clone().add(0.0, 1.7, 0.0));
            seat2.remove();
        }
    }
    
    public boolean isSitting() {
        return this.simpleSit.getSeats().containsKey(this.player.getUniqueId());
    }
    
    public void sendPacket(final Object packet) {
        try {
            final Object entityPlayer = this.player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(this.player, new Object[0]);
            final Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            playerConnection.getClass().getMethod("sendPacket", Utils.getNMSClass("Packet")).invoke(playerConnection, Utils.getNMSClass("Packet").cast(packet));
        }
        catch (NoSuchMethodException | SecurityException | NoSuchFieldException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
