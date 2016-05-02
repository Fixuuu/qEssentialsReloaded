package me.kavzaq.qEssentialsReloaded.impl;

import java.util.List;

import com.google.common.collect.Lists;

import me.kavzaq.qEssentialsReloaded.interfaces.Kit;

public class KitImpl implements Kit{
	
	public static List<Kit> list = Lists.newArrayList();
	private String name;
	private long cooldown;
	private List<String> items;

	public KitImpl(String name) {
		this.name = name;
	}
	
	@Override
	public List<Kit> getList() {
		return list;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public long getCooldown() {
		return cooldown;
	}

	@Override
	public List<String> getItems() {
		return items;
	}

	@Override
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	@Override
	public void setItems(List<String> items) {
		this.items = items;
	}

}
