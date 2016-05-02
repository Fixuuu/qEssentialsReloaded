package me.kavzaq.qEssentialsReloaded.impl;

import org.bukkit.Location;

import me.kavzaq.qEssentialsReloaded.interfaces.HomeData;

public class HomeDataImpl implements HomeData {
	
	private String name;
	private final Location location;
	
	public HomeDataImpl(String name, Location location) {
		this.name = name;
		this.location = location;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Location getLocation() {
		return location;
	}

}
