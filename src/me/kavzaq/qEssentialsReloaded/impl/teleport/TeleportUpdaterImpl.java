package me.kavzaq.qEssentialsReloaded.impl.teleport;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import me.kavzaq.qEssentialsReloaded.interfaces.teleport.TeleportUpdater;

public class TeleportUpdaterImpl implements TeleportUpdater{
	
	private static HashMap<Player, Location> locations = Maps.newHashMap();
	
	@Override
	public Location getLocation(Player player) {
		return locations.get(player) != null ? 
				locations.get(player) : player.getWorld().getSpawnLocation();
	}

	@Override
	public void setLocation(Player player) {
		locations.put(player, player.getLocation());
		return;
	}

}
