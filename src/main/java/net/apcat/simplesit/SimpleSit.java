package net.apcat.simplesit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.apcat.simplesit.listeners.PlayerArmorStandManipulate;
import net.apcat.simplesit.listeners.PlayerDeath;
import net.apcat.simplesit.listeners.PlayerQuit;
import net.apcat.simplesit.listeners.PlayerTeleport;
import net.apcat.simplesit.tasks.RotateSeatTask;

public class SimpleSit extends JavaPlugin {
	
    private Logger logger;
    private FileConfiguration config;
	public Map<UUID, ArmorStand> seats = new HashMap<>();
    public Permission sitPermission;
    public String sitDownMessage;
    public String sitUpMessage;
    public String sitFailMessage;
    
    @Override
    public void onEnable() {
    	logger = getLogger();
        registerConfig();
        registerPermissions();
        registerListeners();
        new RotateSeatTask(this);
    }

    @Override
    public void onDisable() {
        UUID[] array = null;
        for (int length = (array = seats.keySet().toArray(array)).length, i = 0; i < length; ++i) {
            UUID uuid = array[i];
            SimpleSitPlayer player = new SimpleSitPlayer(Bukkit.getPlayer(uuid));
            player.setSitting(false);
        }
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if(command.getName().equalsIgnoreCase("sit")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Text.PLAYER_COMMAND.format(new Object[0]));
                return true;
            }
            if (!sender.hasPermission(sitPermission)) {
                sender.sendMessage(Text.INVALID_PERMISSION.format(new Object[0]));
                return true;
            }
            SimpleSitPlayer player = new SimpleSitPlayer((Player)sender);
            if (player.isSitting()) {
                player.setSitting(false);
            } else if (player.getBukkitPlayer().isOnGround()) {
                player.setSitting(true);
            } else {
                player.getBukkitPlayer().sendMessage(sitFailMessage);
            }
    	}
        return true;
    }
    
    private void registerConfig() {
    	config = getConfig();
        saveDefaultConfig();
        sitDownMessage = color(config.getString("sitdown-message"));
        sitUpMessage = color(config.getString("situp-message"));
        sitFailMessage = color(config.getString("sitfail-message"));
    }
    
    private void registerPermissions() {
        sitPermission = getPermission("simplesit.sit", "sit-permission-default");
    }
    
    private Permission getPermission(final String permissionString, final String location) {
        Permission permission = new Permission(permissionString);
         String permissionDefault = this.getConfig().getString(location);
        if (!isPermissionDefault(permissionDefault)) {
            logger.warning(Text.INVALID_PERMISSION_DEFAULT.format(location, permissionDefault));
            permission.setDefault(PermissionDefault.TRUE);
        } else {
            permission.setDefault(PermissionDefault.valueOf(permissionDefault.toUpperCase()));
        }
        Bukkit.getPluginManager().addPermission(permission);
        return permission;
    }
    
    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerTeleport(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new PlayerArmorStandManipulate(), this);
        pm.registerEvents(new PlayerDeath(), this);
    }
    
    private boolean isPermissionDefault(final String permissionDefault) {
        return PermissionDefault.valueOf(permissionDefault.toUpperCase()) != null;
    }
    
    private String color(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    
    private enum Text {
    	
        PLAYER_COMMAND("PLAYER_COMMAND", 0, ChatColor.RED + "Only players can use this command!"), 
        PREFIX("PREFIX", 4, "[" + ChatColor.AQUA + ChatColor.BOLD + "SimpleSit" + ChatColor.RESET + "] "), 
        INVALID_PERMISSION_DEFAULT("INVALID_PERMISSION_DEFAULT", 5, "%s has an invalid permission default: '%s'. Please use: TRUE, FALSE, OP, NOT_OP. Meanwhile using default settings."), 
        INVALID_PERMISSION("INVALID_PERMISSION", 6, ChatColor.DARK_RED + "You don't have permission to use this command.");
        
        private String text;
        
        private Text(final String s, final int n, final String text) {
            this.text = text;
        }
        
        public String format(final Object... args) {
            return String.format(this.text, args);
        }
        
        @Override
        public String toString() {
            return this.text;
        }
    }
}
