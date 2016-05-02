package me.kavzaq.qEssentialsReloaded.impl;

import me.kavzaq.qEssentialsReloaded.interfaces.KitData;

public class KitDataImpl implements KitData {

	private String name;
	private final long cooldown;
	
	public KitDataImpl(String name, long cooldown) {
		this.name = name;
		this.cooldown = cooldown;
	}

	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Long getCooldown() {
		return cooldown;
	}

}
