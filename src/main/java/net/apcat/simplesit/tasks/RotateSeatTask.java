package net.apcat.simplesit.tasks;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import net.apcat.simplesit.SimpleSit;

public class RotateSeatTask extends BukkitRunnable {
	
    private SimpleSit simpleSit;
    
    public RotateSeatTask(SimpleSit simpleSit) {
    	this.simpleSit = simpleSit;
        this.runTaskTimerAsynchronously(simpleSit, 0L, 1L);
    }
    
    @Override
    public void run() {
        for (final ArmorStand armorstand : simpleSit.seats.values()) {
            try {
                Object entityArmorstand = armorstand.getClass().getMethod("getHandle", new Class[0]).invoke(armorstand, new Object[0]);
                Field yaw = entityArmorstand.getClass().getField("yaw");
				yaw.set(entityArmorstand, armorstand.getPassengers().get(0).getLocation().getYaw());
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
				e.printStackTrace();
			}
        }
    }
}
