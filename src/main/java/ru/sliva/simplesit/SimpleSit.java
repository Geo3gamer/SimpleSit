package ru.sliva.simplesit;

import org.bukkit.plugin.java.*;
import org.bukkit.entity.*;
import java.util.*;

import ru.sliva.simplesit.commands.*;
import ru.sliva.simplesit.listeners.*;
import ru.sliva.simplesit.tasks.*;
import ru.sliva.simplesit.utils.*;

import org.bukkit.*;
import java.io.*;
import java.util.logging.*;
import org.bukkit.permissions.*;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;

public class SimpleSit extends JavaPlugin
{
    private Map<UUID, ArmorStand> seats;
    private Permission sitPermission;
    private String sitDownMessage;
    private String sitUpMessage;
    private String sitFailMessage;
    
    public SimpleSit() {
        this.seats = new HashMap<UUID, ArmorStand>();
    }
    
    public void onEnable() {
        this.registerConfig();
        this.registerPermissions();
        this.registerCommands();
        this.registerListeners();
        new RotateSeatTask(this);
    }
    
    public void onDisable() {
        Object[] array;
        for (int length = (array = this.seats.keySet().toArray()).length, i = 0; i < length; ++i) {
            final Object uuid = array[i];
            final SimpleSitPlayer player = new SimpleSitPlayer(Bukkit.getPlayer((UUID)uuid));
            player.setSitting(false);
        }
    }
    
    public void convertConfig() {
        boolean convert = false;
        String oldPermissionDefault = null;
        String oldSitDownMessage = null;
        String oldSitUpMessage = null;
        String oldSitFailMessage = null;
        if (this.getConfig().contains("checkForUpdates")) {
            convert = true;
            if (this.getConfig().contains("sitPermissionDefault")) {
                oldPermissionDefault = this.getConfig().getString("sitPermissionDefault");
            }
            if (this.getConfig().contains("sitDown")) {
                oldSitDownMessage = this.getConfig().getString("sitDown");
            }
            if (this.getConfig().contains("sitFail")) {
                oldSitFailMessage = this.getConfig().getString("sitFail");
            }
            if (this.getConfig().contains("sitUp")) {
                oldSitUpMessage = this.getConfig().getString("sitUp");
            }
            final File configFile = new File(this.getDataFolder(), "config.yml");
            configFile.delete();
        }
        this.saveDefaultConfig();
        this.reloadConfig();
        if (!convert) {
            return;
        }
        if (oldPermissionDefault != null) {
            this.getConfig().set("sit-permission-default", (Object)oldPermissionDefault);
        }
        if (oldSitDownMessage != null) {
            this.getConfig().set("sitdown-message", (Object)oldSitDownMessage);
        }
        if (oldSitFailMessage != null) {
            this.getConfig().set("sitfail-message", (Object)oldSitFailMessage);
        }
        if (oldSitUpMessage != null) {
            this.getConfig().set("situp-message", (Object)oldSitUpMessage);
        }
        this.saveConfig();
        this.reloadConfig();
    }
    
    private void registerConfig() {
        this.convertConfig();
        this.sitDownMessage = Utils.color(this.getConfig().getString("sitdown-message"));
        this.sitUpMessage = Utils.color(this.getConfig().getString("situp-message"));
        this.sitFailMessage = Utils.color(this.getConfig().getString("sitfail-message"));
    }
    
    private void registerPermissions() {
        this.sitPermission = this.getPermission("simplesit.sit", "sit-permission-default");
    }
    
    private Permission getPermission(final String permissionString, final String location) {
        final Permission permission = new Permission(permissionString);
        final String permissionDefault = this.getConfig().getString(location);
        if (!Utils.isPermissionDefault(permissionDefault)) {
            this.sendConfigError(Text.INVALID_PERMISSION_DEFAULT.format(location, permissionDefault), Level.WARNING);
            permission.setDefault(PermissionDefault.TRUE);
        }
        else {
            permission.setDefault(PermissionDefault.valueOf(permissionDefault.toUpperCase()));
        }
        Bukkit.getPluginManager().addPermission(permission);
        return permission;
    }
    
    private void registerCommands() {
        this.getCommand("sit").setExecutor((CommandExecutor)new SitCommand(this));
    }
    
    private void registerListeners() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents((Listener)new PlayerTeleport(), (Plugin)this);
        pm.registerEvents((Listener)new PlayerQuit(), (Plugin)this);
        pm.registerEvents((Listener)new PlayerArmorStandManipulate(), (Plugin)this);
        pm.registerEvents((Listener)new PlayerDeath(), (Plugin)this);
    }
    
    public Map<UUID, ArmorStand> getSeats() {
        return this.seats;
    }
    
    public String getSitFailMessage() {
        return this.sitFailMessage;
    }
    
    public String getSitDownMessage() {
        return this.sitDownMessage;
    }
    
    public String getSitUpMessage() {
        return this.sitUpMessage;
    }
    
    public Permission getSitPermission() {
        return this.sitPermission;
    }
    
    private void sendConfigError(final String message, final Level level) {
        this.getLogger().log(level, message);
    }
}
