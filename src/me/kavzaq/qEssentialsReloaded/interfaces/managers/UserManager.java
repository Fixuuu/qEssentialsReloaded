package me.kavzaq.qEssentialsReloaded.interfaces.managers;

import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.interfaces.User;

public interface UserManager {
	
	User getUser(Player player);
	User getUser(CommandSender sender);
	User getUser(UUID uuid);
	
	User implementUser(Player player);
}
