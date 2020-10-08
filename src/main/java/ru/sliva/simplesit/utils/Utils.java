package ru.sliva.simplesit.utils;

import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.permissions.*;
import org.bukkit.*;

public class Utils
{
    private static final BlockFace[] BLOCK_FACES;
    
    static {
        BLOCK_FACES = new BlockFace[] { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
    }
    
    public static BlockFace getBlockFace(final Player player) {
        return Utils.BLOCK_FACES[Math.round(player.getEyeLocation().getYaw() / 90.0f) & 0x3];
    }
    
    public static Class<?> getNMSClass(final String clazz) {
        try {
            return Class.forName("net.minecraft.server." + getServerVersion() + "." + clazz);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean isPermissionDefault(final String permissionDefault) {
        return PermissionDefault.valueOf(permissionDefault.toUpperCase()) != null;
    }
    
    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }
    
    public static String color(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
