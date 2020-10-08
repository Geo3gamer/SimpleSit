package ru.sliva.simplesit.commands;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import ru.sliva.simplesit.*;
import ru.sliva.simplesit.utils.*;

public class SitCommand implements CommandExecutor
{
    private SimpleSit simpleSit;
    
    public SitCommand(final SimpleSit simpleSit) {
        this.simpleSit = simpleSit;
    }
    
    @SuppressWarnings("deprecation")
	public boolean onCommand(final CommandSender sender, final Command command, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Text.PLAYER_COMMAND.format(new Object[0]));
            return false;
        }
        if (!sender.hasPermission(this.simpleSit.getSitPermission())) {
            sender.sendMessage(Text.INVALID_PERMISSION.format(new Object[0]));
            return false;
        }
        final SimpleSitPlayer player = new SimpleSitPlayer((Player)sender);
        if (player.isSitting()) {
            player.setSitting(false);
        }
        else if (player.getBukkitPlayer().isOnGround()) {
            player.setSitting(true);
        }
        else {
            player.getBukkitPlayer().sendMessage(this.simpleSit.getSitFailMessage());
        }
        return true;
    }
}
