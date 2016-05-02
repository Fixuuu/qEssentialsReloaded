package me.kavzaq.qEssentialsReloaded.interfaces;

import java.util.List;

public interface Kit {

	public List<Kit> getList();
	
	String getName();
	long getCooldown();
	List<String> getItems();
	
	void setCooldown(int cooldown);
	void setItems(List<String> items);
	
}
