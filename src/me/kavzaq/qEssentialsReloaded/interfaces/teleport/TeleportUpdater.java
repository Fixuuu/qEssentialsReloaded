package me.kavzaq.qEssentialsReloaded.interfaces.teleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface TeleportUpdater {
	
	Location getLocation(Player player);
	void setLocation(Player player);
	
}
