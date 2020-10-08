package ru.sliva.simplesit.tasks;

import org.bukkit.scheduler.*;

import ru.sliva.simplesit.*;

import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import java.lang.reflect.*;

public class RotateSeatTask extends BukkitRunnable
{
    private SimpleSit simpleSit;
    
    public RotateSeatTask(final SimpleSit simpleSit) {
        this.runTaskTimerAsynchronously((Plugin)(this.simpleSit = simpleSit), 0L, 1L);
    }
    
    public void run() {
        for (final ArmorStand armorstand : this.simpleSit.getSeats().values()) {
            try {
                final Object entityArmorstand = armorstand.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(armorstand, new Object[0]);
                final Field yaw = entityArmorstand.getClass().getField("yaw");
                yaw.set(entityArmorstand, armorstand.getPassengers().get(0).getLocation().getYaw());
            }
            catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
