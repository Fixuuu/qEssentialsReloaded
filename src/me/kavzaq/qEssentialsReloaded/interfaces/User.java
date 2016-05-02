package me.kavzaq.qEssentialsReloaded.interfaces;

import java.util.List;
import java.util.UUID;

import me.kavzaq.qEssentialsReloaded.impl.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.KitDataImpl;

public interface User {
	
	String getName();
	UUID getUUID();

	List<String> getHomes();
	List<String> getKits();
	boolean isGod();

	void addHome(HomeDataImpl homeData);
	void delHome(HomeDataImpl homeData);
	void setHomes(List<String> homes);
	
	void addKit(KitDataImpl kitData);
	void delKit(KitDataImpl kitData);
	void setKits(List<String> kits);
	
	void setGod(boolean god);
	
	void save();
}
