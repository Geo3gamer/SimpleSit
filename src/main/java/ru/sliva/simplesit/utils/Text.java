package ru.sliva.simplesit.utils;

import org.bukkit.*;

public enum Text
{
    PLAYER_COMMAND("PLAYER_COMMAND", 0, ChatColor.RED + "Only players can use this command!"), 
    CHECK_FOR_UPDATES("CHECK_FOR_UPDATES", 1, "Version %s is available for %s. Your current version is %s."), 
    CHECK_FOR_UPDATES_FAIL("CHECK_FOR_UPDATES_FAIL", 2, "Unable to check for updates, please contact the developer."), 
    CHECK_FOR_UPDATES_FAIL_CONNECT("CHECK_FOR_UPDATES_FAIL_CONNECT", 3, "Unable to check for updates: %s. Please contact the developer."), 
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
