package ru.sliva.simplesit;

import org.bukkit.entity.*;
import org.bukkit.plugin.java.*;

public class SimpleSitArmorStand
{
    private SimpleSit simpleSit;
    private ArmorStand armorstand;
    
    @SuppressWarnings({"unchecked","rawtypes"})
	public SimpleSitArmorStand(final ArmorStand armorStand) {
        this.armorstand = armorStand;
        this.simpleSit = (SimpleSit)JavaPlugin.getPlugin((Class)SimpleSit.class);
    }
    
    public boolean isSeat() {
        return this.simpleSit.getSeats().containsValue(this.armorstand);
    }
    
    public ArmorStand getBukkitArmorStand() {
        return this.armorstand;
    }
}
