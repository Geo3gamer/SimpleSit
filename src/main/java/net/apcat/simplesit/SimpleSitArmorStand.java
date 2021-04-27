package net.apcat.simplesit;

import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleSitArmorStand {
	
    private SimpleSit simpleSit;
    private ArmorStand armorstand;
    
	public SimpleSitArmorStand(final ArmorStand armorStand) {
        this.armorstand = armorStand;
        this.simpleSit = JavaPlugin.getPlugin(SimpleSit.class);
    }
    
    public boolean isSeat() {
        return simpleSit.seats.containsValue(this.armorstand);
    }
    
    public ArmorStand getBukkitArmorStand() {
        return armorstand;
    }
}
